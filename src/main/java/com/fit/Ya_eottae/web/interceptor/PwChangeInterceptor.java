package com.fit.Ya_eottae.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class PwChangeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        Boolean isMatch = (Boolean) session.getAttribute("isMatch");

        if (isMatch == null) {
            response.sendRedirect(request.getContextPath() + "/login/is-ok-to-changePw"); // 인증 실패 시 폼으로 리다이렉트
            return false; // 요청 처리를 중단
        }

        if (!isMatch) {
            response.sendRedirect(request.getContextPath() + "/login/is-ok-to-changePw"); // 인증 실패 시 폼으로 리다이렉트
            return false; // 요청 처리를 중단
        }

        return true;
    }
}
