package com.fit.Ya_eottae.web.membertendencytest;

import com.fit.Ya_eottae.SessionConst;
import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.domain.member.MemberTendency;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import com.fit.Ya_eottae.web.membertendencytest.question.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class TendencyTestController {

    private final MemberRepository memberRepository;
    private final MemberTendencyTestService memberTendencyTestService;

    @ModelAttribute("tendencyQuestion1")
    public TendencyQuestion1[] tendencyTest1() {
        return TendencyQuestion1.values();
    }

    @ModelAttribute("tendencyQuestion2")
    public TendencyQuestion2[] tendencyTest2() {
        return TendencyQuestion2.values();
    }

    @ModelAttribute("tendencyQuestion3")
    public TendencyQuestion3[] tendencyTest3() {
        return TendencyQuestion3.values();
    }

    @ModelAttribute("tendencyQuestion4")
    public TendencyQuestion4[] tendencyTest4() {
        return TendencyQuestion4.values();
    }

    @ModelAttribute("tendencyQuestion5")
    public TendencyQuestion5[] tendencyTest5() {
        return TendencyQuestion5.values();
    }


    // 1번
    @GetMapping("/tendency-test/1")
    public String tendencyTestQuestion1() {
        return "/tendency-test/test1";
    }

    @PostMapping("tendency-test/1")
    public String tendencyTestAnswer1(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion1 answer1) {
        memberTendencyTest.setAnswer1(answer1);

        if (answer1 == null) {
            bindingResult.reject("mustCheck");
            return "test/test1";
        }

        return "test/test2";
    }

    // 2번
    @GetMapping("/tendency-test/2")
    public String tendencyTestQuestion2() {
        return "/tendency-test/test2";
    }

    @PostMapping("tendency-test/2")
    public String tendencyTestAnswer2(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion2 answer2) {
        memberTendencyTest.setAnswer2(answer2);

        if (answer2 == null) {
            bindingResult.reject("mustCheck");
            return "test/test2";
        }

        return "test/test3";
    }

    // 3번
    @GetMapping("/tendency-test/3")
    public String tendencyTestQuestion3() {
        return "/tendency-test/test3";
    }

    @PostMapping("tendency-test/3")
    public String tendencyTestAnswer3(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion3 answer3) {
        memberTendencyTest.setAnswer3(answer3);

        if (answer3 == null) {
            bindingResult.reject("mustCheck");
            return "test/test3";
        }

        return "test/test4";
    }

    // 4번
    @GetMapping("/tendency-test/4")
    public String tendencyTestQuestion4() {
        return "/tendency-test/test4";
    }

    @PostMapping("tendency-test/4")
    public String tendencyTestAnswer4(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion4 answer4) {
        memberTendencyTest.setAnswer4(answer4);

        if (answer4 == null) {
            bindingResult.reject("mustCheck");
            return "test/test4";
        }

        return "test/test5";
    }

    // 5번
    @GetMapping("/tendency-test/5")
    public String tendencyTestQuestion5() {
        return "/tendency-test/test5";
    }

    @PostMapping("tendency-test/5")
    public String tendencyTestAnswer5(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion5 answer5, HttpServletRequest request, Model model) {
        memberTendencyTest.setAnswer5(answer5);

        if (answer5 == null) {
            bindingResult.reject("mustCheck");
            return "test/test5";
        }

        MemberTendency memberTendency = memberTendencyTestService.checkTendency(memberTendencyTest.getAnswer1(), memberTendencyTest.getAnswer2(),
                memberTendencyTest.getAnswer3(), memberTendencyTest.getAnswer4(), memberTendencyTest.getAnswer5());

        HttpSession session = request.getSession(false);
        String findMemberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        Member findMember = memberRepository.findByMemberId(findMemberId);
        findMember.setMemberTendency(memberTendency);

        model.addAttribute("memberTendency", memberTendency);
        return "test/result";
    }

    // 결과 보기
    @GetMapping("/tendency-test/result")
    public String tendencyResult(HttpServletRequest request) {

        return "redirect:/";

    }
}
