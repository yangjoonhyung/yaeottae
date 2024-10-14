package com.fit.Ya_eottae.web.review.controller;

import com.fit.Ya_eottae.SessionConst;
import com.fit.Ya_eottae.domain.AiModel.AiModel;
import com.fit.Ya_eottae.domain.comment.Comment;
import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.domain.review.Review;
import com.fit.Ya_eottae.domain.review.ReviewUpdateDto;
import com.fit.Ya_eottae.domain.trustpoint.TrustPoint;
import com.fit.Ya_eottae.repository.commentrepository.CommentRepository;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import com.fit.Ya_eottae.repository.reviewrepository.ReviewRepository;
import com.fit.Ya_eottae.repository.trustpointrepository.TrustPointRepository;
import com.fit.Ya_eottae.web.review.service.PredictionService;
import com.fit.Ya_eottae.web.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final ReviewService reviewService;
    private final TrustPointRepository trustPointRepository;
    private final PredictionService predictionService;

    @GetMapping("/{reviewId}")
    public String review(@PathVariable long reviewId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Review review = reviewRepository.findById(reviewId).get();
        model.addAttribute("review", review);
        List<Comment> findCommentList = commentRepository.findByReviewId(reviewId);
        model.addAttribute("comment", findCommentList);

        if (session == null) {
            return "review/review";
        }

        String memberId = (String) session.getAttribute(SessionConst.SESSION_ID);


        List<Review> allReview = reviewRepository.findAllReview();
        String reviewTrustScore = reviewService.calculateBayesianAverage(review, allReview);
        review.setTrustScore(reviewTrustScore);

        model.addAttribute("memberId", memberId);

        if (review.getMember().getMemberId().equals(memberId)) {
            return "review/my-review";
        }

        return "review/review";
    }

    @GetMapping("/{restaurantId}/review-save")
    public String reviewSaveForm(@PathVariable long restaurantId) {
        return "review/reviewSave";
    }

    @PostMapping("/{restaurantId}/review-save")
    public String reviewSave(@PathVariable long restaurantId, @ModelAttribute Review review, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String memberId = (String) session.getAttribute(SessionConst.SESSION_ID);
        Member findMember = memberRepository.findByMemberId(memberId);
        // PredictionService를 이용하여 FastAPI로부터 예측 결과 받기
        Mono<AiModel> aiPredictionMono = predictionService.getPrediction(review.getReviewDetail());

        // Mono를 블로킹 방식으로 동기 처리하여 결과 받기 (필요시 비동기 처리도 가능)
        AiModel aiPrediction = aiPredictionMono.block();

        // 예측 값을 String에서 float로 변환 (퍼센트 기호 제거 후 변환)
        String predictionStr = aiPrediction.getPrediction().replace("%", ""); // '%' 기호 제거
        float predictionFloat = Float.parseFloat(predictionStr);

        // 소수점 첫째 자리까지만 표현 (예: 3.1)
        float roundedPrediction = Math.round(predictionFloat * 10) / 10.0f;

        // 다시 String으로 변환
        String formattedPrediction = String.format("%.2f", roundedPrediction);
        formattedPrediction = formattedPrediction + "%";

        Review saveReview = new Review(review.getReviewName(), review.getReviewDetail(), review.getReviewPoint(),
                formattedPrediction, restaurantId);

        saveReview.setMember(findMember);

        reviewRepository.save(saveReview);
        return "redirect:/restaurant/{restaurantId}";
    }

    @GetMapping("/{reviewId}/review-update")
    public String reviewUpdateForm(@PathVariable long reviewId, Model model) {
        Review findReview = reviewRepository.findById(reviewId).get();
        model.addAttribute("review", findReview);
        return "review/update-review";
    }

    @PostMapping("/{reviewId}/review-update")
    public String reviewUpdate(@PathVariable long reviewId, @ModelAttribute("updateReview") ReviewUpdateDto updateParam) {
        // PredictionService를 이용하여 FastAPI로부터 예측 결과 받기
        Mono<AiModel> aiPredictionMono = predictionService.getPrediction(updateParam.getUpdateReview());

        // Mono를 블로킹 방식으로 동기 처리하여 결과 받기 (필요시 비동기 처리도 가능)
        AiModel aiPrediction = aiPredictionMono.block();

        // 예측 값을 String에서 float로 변환 (퍼센트 기호 제거 후 변환)
        String predictionStr = aiPrediction.getPrediction().replace("%", ""); // '%' 기호 제거
        float predictionFloat = Float.parseFloat(predictionStr);

        // 소수점 첫째 자리까지만 표현 (예: 3.1)
        float roundedPrediction = Math.round(predictionFloat * 10) / 10.0f;

        // 다시 String으로 변환
        String formattedPrediction = String.format("%.2f", roundedPrediction);
        formattedPrediction = formattedPrediction + "%";

        ReviewUpdateDto updateDto = new ReviewUpdateDto(updateParam.getUpdateReviewName(), updateParam.getUpdateReview(),
                updateParam.getUpdateReviewPoint(), formattedPrediction);

        reviewRepository.updateReview(reviewId, updateDto);
        return "redirect:/review/{reviewId}";
    }

    @PostMapping("/{reviewId}/plus")
    public String plusTrustPoint(@PathVariable long reviewId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        TrustPoint findMemberTrustPoint = trustPointRepository.findByMemberId(memberId, reviewId);

        if (findMemberTrustPoint == null) {
            TrustPoint trustPoint = new TrustPoint();
            trustPoint.setReviewId(reviewId);
            trustPoint.setMemberId(memberId);
            trustPointRepository.save(trustPoint);
        }

        if (!reviewService.isOkToCheckTrustPoint(reviewId, memberId)) {
            redirectAttributes.addFlashAttribute("errorMessage", "리뷰당 한번만 누르실 수 있습니다.");
            return "redirect:/review/{reviewId}";
        }

        TrustPoint memberTrustPoint = trustPointRepository.findByMemberId(memberId, reviewId);
        memberTrustPoint.setPlusTrustPoint(1);
        reviewRepository.plusTrustPoint(reviewId);
        return "redirect:/review/{reviewId}";
    }

    @PostMapping("/{reviewId}/minus")
    public String plusNoTrustPoint(@PathVariable long reviewId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        String  memberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        TrustPoint findMemberTrustPoint = trustPointRepository.findByMemberId(memberId, reviewId);

        if (findMemberTrustPoint == null) {
            TrustPoint trustPoint = new TrustPoint();
            trustPoint.setReviewId(reviewId);
            trustPoint.setMemberId(memberId);
            trustPointRepository.save(trustPoint);
        }

        if (!reviewService.isOkToCheckNoTrustPoint(reviewId, memberId)) {
            redirectAttributes.addFlashAttribute("errorMessage", "리뷰당 한번만 누르실 수 있습니다.");
            return "redirect:/review/{reviewId}";
        }

        TrustPoint memberTrustPoint = trustPointRepository.findByMemberId(memberId, reviewId);
        memberTrustPoint.setMinusTrustPoint(1);
        reviewRepository.plusNoTrustPoint(reviewId);
        return "redirect:/review/{reviewId}";
    }

    @PostMapping("/{reviewId}/delete")
    public String deleteReview(@PathVariable long reviewId) {
        reviewRepository.deleteReview(reviewId);
        return "redirect:/myPage";
    }

    @PostMapping("/{reviewId}/comment-save")
    public String commentSave(@PathVariable long reviewId, @ModelAttribute Comment comment, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String memberId = (String) session.getAttribute(SessionConst.SESSION_ID);
        Review findReview = reviewRepository.findById(reviewId).get();

        Comment saveComment = new Comment(comment.getComment());
        saveComment.setReview(findReview);
        saveComment.setMemberId(memberId);
        commentRepository.save(saveComment);
        return "redirect:/review/{reviewId}";
    }

    @PostMapping("{reviewId}/{commentId}/delete-comment")
    public String deleteComment(@PathVariable long reviewId, @PathVariable long commentId , RedirectAttributes redirectAttributes) {
        List<Comment> findCommentList = commentRepository.findByReviewId(reviewId);
        Comment deleteComment = findCommentList.stream().filter(comment -> comment.getCommentId() == commentId).findAny().orElse(null);
        commentRepository.deleteComment(deleteComment.getCommentId());
        return "redirect:/review/{reviewId}";
    }

}
