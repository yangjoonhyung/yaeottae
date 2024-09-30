package com.fit.Ya_eottae.web.mypage.controller;

import com.fit.Ya_eottae.SessionConst;
import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.domain.review.Review;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import com.fit.Ya_eottae.repository.reviewrepository.ReviewRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String findMemberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        Member findMember = memberRepository.findByMemberId(findMemberId);
        model.addAttribute("member", findMember);

        return "mypage/myPage";
    }

    @GetMapping("/myPage/change-name")
    public String myPageChangeNameForm() {
        return "mypage/change-name";
    }

    @PostMapping("/myPage/change-name")
    public String myPageChangeName(HttpServletRequest request, @RequestParam String updateName) {
        HttpSession session = request.getSession(false);
        String findMemberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        Member findMember = memberRepository.findByMemberId(findMemberId);
        memberRepository.updateName(findMember, updateName);

        return "redirect:/myPage";
    }

    @GetMapping("/myPage/change-introduce")
    public String myPageChangeIntroduceForm() {
        return "mypage/change-introduce";
    }

    @PostMapping("/myPage/change-introduce")
    public String myPageChangeIntroduce(HttpServletRequest request, @RequestParam String updateIntroduce) {
        HttpSession session = request.getSession(false);
        String findMemberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        Member findMember = memberRepository.findByMemberId(findMemberId);
        memberRepository.updateIntroduction(findMember, updateIntroduce);

        return "redirect:/myPage";
    }

    @GetMapping("/myPage/my-reviews")
    private String myReview(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        List<Review> myReviewList = reviewRepository.findBymemberId(memberId);
        Member findMember = memberRepository.findByMemberId(memberId);
        model.addAttribute("member", findMember);

        model.addAttribute("reviewList", myReviewList);
        return "mypage/my-review";
    }

    @GetMapping("/review/{memberId}/see-review")
    private String memberReview(@PathVariable String memberId, HttpServletRequest request, Model model) {

        List<Review> myReviewList = reviewRepository.findBymemberId(memberId);
        Member findMember = memberRepository.findByMemberId(memberId);
        model.addAttribute("member", findMember);

        model.addAttribute("reviewList", myReviewList);

        return "review/member-review-list";
    }
}
