package com.fit.Ya_eottae.web.main;

import com.fit.Ya_eottae.SessionConst;
import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.domain.review.Review;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import com.fit.Ya_eottae.repository.reviewrepository.ReviewRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    //@GetMapping("/")
    /*public String mainLogin(HttpServletRequest request, Model model) {

        // 세션을 받아서 세션이 존재하는지 확인
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "main";
        }

        // 세션이 존재해도 세션에 회원아이디가 없는지 확인
        String memberId = String.valueOf(session.getAttribute(SessionConst.SESSION_ID));
        if (memberId == null) {
            return "main";
        }

        Member findMember = memberRepository.findByMemberId(memberId);
        model.addAttribute("member", findMember);
        return "loginMain";
    }*/

    @GetMapping("/")
    public String mainLogin2(@SessionAttribute(name = SessionConst.SESSION_ID, required = false) String loginSession, Model model) {

        Review bestReview = reviewRepository.findBestReview();
        Review secondBestReview = reviewRepository.findSecondBestReview();


        model.addAttribute("bestReview", bestReview);
        model.addAttribute("secondBestReview", secondBestReview);

        if (bestReview == null) {
            model.addAttribute("bestReviewMemberId", "리뷰 집계중입니다.");
            model.addAttribute("bestReviewReviewName", "리뷰 집계중입니다.");
        } else {
            model.addAttribute("bestReviewMemberId", bestReview.getMember().getMemberId());
            model.addAttribute("bestReviewReviewName", bestReview.getReviewName());
        }

        if (secondBestReview == null) {
            model.addAttribute("secondBestReviewMemberId", "리뷰 집계중입니다.");
            model.addAttribute("secondBestReviewReviewName", "리뷰 집계중입니다.");
        } else {
            model.addAttribute("secondBestReviewMemberId", secondBestReview.getMember().getMemberId());
            model.addAttribute("secondBestReviewReviewName", secondBestReview.getReviewName());
        }

        if (loginSession == null) {
            return "main";
        }
        Member findMember = memberRepository.findByMemberId(loginSession);
        model.addAttribute("member", findMember);


        return "loginMain";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/tryRestaurant")
    public String tryRestaurant() {
        return "main/try-page-r";
    }

    @GetMapping("/tryCafe")
    public String tryCafe() {
        return "main/try-page-c";
    }

    @GetMapping("/search")
    public String searchRestaurantFrom() {
        return "main/search";
    }

    @PostMapping("/restaurant/search")
    public String searchRestaurant(@RequestParam String searchWord, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("searchWord", searchWord);
        model.addAttribute("searchWord", searchWord);
        log.info("SearchWord={}", searchWord);

        return "redirect:/restaurant/search";
    }

    @GetMapping("/notice/request-challenge-for-ceo")
    public String requestChallengeForCeo() {
        return "main/requestChallengeForCeo";
    }

    @GetMapping("/notice/request-challenge-for-user")
    public String requestChallengeForcUser() {
        return "main/requestChallengeForUser";
    }

    @GetMapping("/noice/review-save-read")
    public String reviewSaveRead() {
        return "main/reviewSaveRead";
    }

    @GetMapping("/notice/intro-site")
    public String introSite() {
        return "main/introSite";
    }
}
