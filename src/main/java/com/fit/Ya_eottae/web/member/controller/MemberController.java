package com.fit.Ya_eottae.web.member.controller;

import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.domain.member.form.MemberJoinDto;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import com.fit.Ya_eottae.web.member.service.JoinService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final JoinService joinService;

    @ModelAttribute("memberYears")
    public List<Integer> memberYears() {
        List<Integer> memberYears = new ArrayList<>();
        for (int i = 1970; i < LocalDateTime.now().getYear(); i++) {
            memberYears.add(i);
        }
        return memberYears;
    }

    @ModelAttribute("memberMonth")
    public List<Integer> memberMonths() {
        List<Integer> memberMonths = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            memberMonths.add(i);
        }
        return memberMonths;
    }

    @ModelAttribute("memberDaysByMonth")
    public Map<Integer, List<Integer>> memberDaysByMonth() {
        Map<Integer, List<Integer>> daysByMonth = new HashMap<>();

        // 31일이 있는 달
        List<Integer> days31 = createDaysList(31);
        daysByMonth.put(1, days31);  // 1월
        daysByMonth.put(3, days31);  // 3월
        daysByMonth.put(5, days31);  // 5월
        daysByMonth.put(7, days31);  // 7월
        daysByMonth.put(8, days31);  // 8월
        daysByMonth.put(10, days31); // 10월
        daysByMonth.put(12, days31); // 12월

        // 30일이 있는 달
        List<Integer> days30 = createDaysList(30);
        daysByMonth.put(4, days30);  // 4월
        daysByMonth.put(6, days30);  // 6월
        daysByMonth.put(9, days30);  // 9월
        daysByMonth.put(11, days30); // 11월

        // 2월 (28일)
        List<Integer> days28 = createDaysList(28);
        daysByMonth.put(2, days28);  // 2월

        return daysByMonth;
    }

    private List<Integer> createDaysList(int numberOfDays) {
        List<Integer> days = new ArrayList<>();
        for (int i = 1; i <= numberOfDays; i++) {
            days.add(i);
        }
        return days;
    }



    @GetMapping("/join")
    public String join(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("password", session.getAttribute("password"));
        model.addAttribute("checkPassword",session.getAttribute("checkPassword"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("year", session.getAttribute("year"));
        model.addAttribute("month", session.getAttribute("month"));
        model.addAttribute("date", session.getAttribute("date"));
        model.addAttribute("member", new MemberJoinDto());


        return "/members/joinForm";
    }

    @PostMapping("/join")
    public String joinMember(@Validated @ModelAttribute("memberJoinForm") MemberJoinDto memberJoinForm, BindingResult bindingResult, Model model) {

        List<Member> members = memberRepository.findAll();

        for (Member oneMember : members) {
            if (oneMember.getEmail().equals(memberJoinForm.getEmail())) {
                bindingResult.rejectValue("email", "uniqueEmail");
            }
        }

        if (joinService.checkPasswordForm(memberJoinForm.getPassword()) == false) {
            bindingResult.rejectValue("password", "checkPasswordForm");
        }

        if (joinService.isSamePassword(memberJoinForm.getPassword(), memberJoinForm.getCheckPassword()) == false) {
            bindingResult.rejectValue("checkPassword", "isSamePassword");
        }

        Member joinMember = new Member(memberJoinForm.getMemberId(), memberJoinForm.getMemberName(), memberJoinForm.getPassword(),
                memberJoinForm.getEmail(), memberJoinForm.getYear(), memberJoinForm.getMonth(), memberJoinForm.getDate(),
                memberJoinForm.getTermOfUse(), memberJoinForm.getMarketingReception(), memberJoinForm.getPersonalInformation());
        memberRepository.join(joinMember);

        if (bindingResult.hasErrors()) {
            return "/members/joinForm";
        }

        return "redirect:/";
    }

    @PostMapping("/isSameId")
    public String isSameId(@RequestParam String memberId, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        List<Member> members = memberRepository.findAll();

        for (Member oneMember : members) {
            if (oneMember.getMemberId().equals(memberId)) {
                redirectAttributes.addFlashAttribute("errorMessage", "아이디가 중복입니다.");
                return "redirect:/join";
            }
        }

        return "redirect:/join";
    }
}
