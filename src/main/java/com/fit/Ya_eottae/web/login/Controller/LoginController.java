package com.fit.Ya_eottae.web.login.Controller;

import com.fit.Ya_eottae.SessionConst;
import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import com.fit.Ya_eottae.web.login.dto.IdFIndDto;
import com.fit.Ya_eottae.web.login.dto.LoginDto;
import com.fit.Ya_eottae.web.login.dto.CheckPwChangeDto;
import com.fit.Ya_eottae.web.login.dto.PwChangeDto;
import com.fit.Ya_eottae.web.login.service.loginservice.LoginService;
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

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;
    private final LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginMember(@Validated @ModelAttribute("member") LoginDto loginDto, BindingResult bindingResult,
                              @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {

        Member loginMember = loginService.loginCheck(loginDto.getMemberId(), loginDto.getPassword());

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        if (loginMember == null) {
            bindingResult.reject("loginCheck");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.SESSION_ID, loginMember.getMemberId());

        return "redirect:" + redirectURL;
    }

    @GetMapping("/login/idFind")
    public String idFindForm(Model model) {
        model.addAttribute("findId", new Member());
        return "login/idFindForm";
    }

    @PostMapping("/login/idFind")
    public String idFind(@Validated @ModelAttribute IdFIndDto idFIndDto, BindingResult bindingResult, Model model) {
        Member findMember = memberRepository.findId(idFIndDto.getMemberName(), idFIndDto.getEmail());

        if (bindingResult.hasErrors()) {
            return "login/idFindForm";
        }

        if (findMember == null) {
            bindingResult.reject("idFindCheck");
            return "login/idFindForm";
        }

        model.addAttribute("findId", findMember);
        return "login/idFindForm";
    }

    @GetMapping("/login/is-ok-to-changePw")
    public String isOkToChangePwForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("checkPwChangeDto", new CheckPwChangeDto());

        return "login/checkPwChangeForm";
    }

    @PostMapping("/login/is-ok-to-changePw")
    public String isOkToChangePw(@Validated @ModelAttribute CheckPwChangeDto checkPwChangeDto, BindingResult bindingResult, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member findMember = memberRepository.isOkToChangePassword(checkPwChangeDto.getMemberId(), checkPwChangeDto.getEmail());
        Boolean isMatch = (Boolean) session.getAttribute("isMatch");

        // 인증이 되지 않았으면 에러 메시지 추가
        if (isMatch == null || !isMatch) {
            bindingResult.reject("noMatch", "인증을 완료해주세요."); // 커스텀 에러 메시지
            return "login/checkPwChangeForm"; // 인증 실패 시 다시 폼으로 돌아감
        }


        if (findMember == null) {
            bindingResult.reject("okToChangePassword");
            return "login/checkPwChangeForm";
        }

        session.setAttribute("member", findMember);
        return "login/changePwForm";
    }

    @GetMapping("/login/is-ok-to-changePw/changePw")
    public String changePwFrom(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        return "login/changePwForm";
    }

    @PostMapping("/login/is-ok-to-changePw/changePw")
    public String changePw(@Validated @ModelAttribute PwChangeDto pwChangeDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        if (!pwChangeDto.getPassword().equals(pwChangeDto.getCheckPassword())) {
            bindingResult.reject("inconsistentPw");
            return "login/changePwForm";
        }

        memberRepository.changePw(member, pwChangeDto.getPassword());
        return "login/complete-change-password";
    }

    @GetMapping("/login/complete-changePw")
    public String completeChangePw() {
        return "login/complete-change-pw";
    }
}
