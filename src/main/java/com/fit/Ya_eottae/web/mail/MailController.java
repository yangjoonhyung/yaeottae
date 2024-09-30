package com.fit.Ya_eottae.web.mail;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private int number; // 이메일 인증 숫자를 저장하는 변수

    // 인증 이메일 전송
    @PostMapping("/mailSend")
    public String mailSend(@RequestParam(required = false) String memberId,
                           @RequestParam(required = false) String memberName,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String checkPassword,
                           @RequestParam String email,
                           @RequestParam(required = false) Integer year,
                           @RequestParam(required = false) Integer month,
                           @RequestParam(required = false) Integer date,
                           HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        // 세션에 입력된 데이터 저장
        session.setAttribute("memberId", memberId);
        session.setAttribute("memberName", memberName);
        session.setAttribute("password", password);
        session.setAttribute("checkPassword", checkPassword);
        session.setAttribute("email", email);
        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("date", date);

        try {
            number = mailService.sendMail(email);
            String num = String.valueOf(number);

            map.put("success", Boolean.TRUE);
            map.put("number", num);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        String referer = request.getHeader("Referer");

        if (referer != null && referer.contains("/login/is-ok-to-changePw")) {
            return "redirect:/login/is-ok-to-changePw";
        }

        return "redirect:/join";
    }

    // 인증번호 일치여부 확인
    @PostMapping("/mailCheck")
    public String mailCheck(/*@RequestParam(required = false) String memberId,
                            @RequestParam(required = false) String memberName,
                            @RequestParam(required = false) String password,
                            @RequestParam(required = false) String checkPassword,
                            @RequestParam String email,
                            @RequestParam(required = false) Integer year,
                            @RequestParam(required = false) Integer month,
                            @RequestParam(required = false) Integer date, */
                            @RequestParam(required = false) String checkNumber,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {

        boolean isMatch = false;

        if (checkNumber != null) {
            isMatch = checkNumber.equals(String.valueOf(number)); // 인증번호 비교
        }

        HttpSession session = request.getSession();
        // 세션에 입력된 데이터 저장
//        session.setAttribute("memberId", memberId);
//        session.setAttribute("memberName", memberName);
//        session.setAttribute("password", password);
//        session.setAttribute("checkPassword", checkPassword);
//        session.setAttribute("email", email);
//        session.setAttribute("year", year);
//        session.setAttribute("month", month);
//        session.setAttribute("date", date);
        session.setAttribute("checkNumber", checkNumber);
        session.setAttribute("isMatch", isMatch); // 인증 성공 여부 저장

        String referer = request.getHeader("Referer");

        if (isMatch) {
            redirectAttributes.addFlashAttribute("goodMessage", "인증번호가 맞습니다.");
            if (referer != null && referer.contains("/login/is-ok-to-changePw")) {
                return "redirect:/login/is-ok-to-changePw";
            }

            return "redirect:/join";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "인증번호가 틀립니다.");
            if (referer != null && referer.contains("/login/is-ok-to-changePw")) {
                return "redirect:/login/is-ok-to-changePw";
            }

            return "redirect:/join";
        }
    }
}
