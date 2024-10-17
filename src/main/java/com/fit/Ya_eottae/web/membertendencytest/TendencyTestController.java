package com.fit.Ya_eottae.web.membertendencytest;

import com.fit.Ya_eottae.SessionConst;
import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.domain.member.MemberTendency;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import com.fit.Ya_eottae.web.membertendencytest.question.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
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
        return "tendency-test/test1";
    }

    @PostMapping("/tendency-test/1")
    public String tendencyTestAnswer1(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion1 answer1, HttpServletRequest request) {
        memberTendencyTest.setAnswer1(answer1);

        if (answer1 == null) {
            bindingResult.reject("mustCheck");
            return "tendency-test/test1";
        }

        HttpSession session = request.getSession();
        session.setAttribute("memberTendencyTest", memberTendencyTest);

        log.info("answer1={}", memberTendencyTest.getAnswer1());

        return "tendency-test/test2";
    }

    // 2번
    @GetMapping("/tendency-test/2")
    public String tendencyTestQuestion2() {
        return "tendency-test/test2";
    }

    @PostMapping("/tendency-test/2")
    public String tendencyTestAnswer2(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion2 answer2, HttpServletRequest request) {

        HttpSession session = request.getSession();
        MemberTendencyTest sessionTest = (MemberTendencyTest) session.getAttribute("memberTendencyTest");
        sessionTest.setAnswer2(answer2);

        if (answer2 == null) {
            bindingResult.reject("mustCheck");
            return "tendency-test/test2";
        }

        log.info("answer2={}", sessionTest.getAnswer2());

        return "tendency-test/test3";
    }

    // 3번
    @GetMapping("/tendency-test/3")
    public String tendencyTestQuestion3() {
        return "/tendency-test/test3";
    }

    @PostMapping("/tendency-test/3")
    public String tendencyTestAnswer3(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion3 answer3, HttpServletRequest request) {

        HttpSession session = request.getSession();
        MemberTendencyTest sessionTest = (MemberTendencyTest) session.getAttribute("memberTendencyTest");
        sessionTest.setAnswer3(answer3);

        if (answer3 == null) {
            bindingResult.reject("mustCheck");
            return "tendency-test/test3";
        }

        log.info("answer3={}", memberTendencyTest.getAnswer3());

        return "tendency-test/test4";
    }

    // 4번
    @GetMapping("/tendency-test/4")
    public String tendencyTestQuestion4() {
        return "/tendency-test/test4";
    }

    @PostMapping("/tendency-test/4")
    public String tendencyTestAnswer4(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion4 answer4, HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberTendencyTest sessionTest = (MemberTendencyTest) session.getAttribute("memberTendencyTest");
        sessionTest.setAnswer4(answer4);

        if (answer4 == null) {
            bindingResult.reject("mustCheck");
            return "tendency-test/test4";
        }

        log.info("answer4={}", memberTendencyTest.getAnswer4());

        return "tendency-test/test5";
    }

    // 5번
    @GetMapping("/tendency-test/5")
    public String tendencyTestQuestion5() {
        return "/tendency-test/test5";
    }

    @PostMapping("/tendency-test/5")
    public String tendencyTestAnswer5(@ModelAttribute MemberTendencyTest memberTendencyTest, BindingResult bindingResult,
                                      @RequestParam TendencyQuestion5 answer5, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        MemberTendencyTest sessionTest = (MemberTendencyTest) session.getAttribute("memberTendencyTest");
        sessionTest.setAnswer5(answer5);

        if (answer5 == null) {
            bindingResult.reject("mustCheck");
            return "tendency-test/test5";
        }

        log.info("answer5={}", memberTendencyTest.getAnswer5());

        MemberTendency memberTendency = memberTendencyTestService.checkTendency(sessionTest.getAnswer1(), sessionTest.getAnswer2(),
                sessionTest.getAnswer3(), sessionTest.getAnswer4(), sessionTest.getAnswer5());

        String findMemberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        Member findMember = memberRepository.findByMemberId(findMemberId);

        memberRepository.setTendency(findMember, memberTendency);

        model.addAttribute("memberTendency", memberTendency);
        log.info("멤버 타임={}", memberTendency);
        return "redirect:/tendency-test/result";
    }

    // 결과 보기
    @GetMapping("/tendency-test/result")
    public String tendencyResult(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute(SessionConst.SESSION_ID);

        Member findMember = memberRepository.findByMemberId(memberId);
        MemberTendency memberTendency = findMember.getMemberTendency();

        model.addAttribute("memberTendency", memberTendency);
        return "tendency-test/result";
    }
}

