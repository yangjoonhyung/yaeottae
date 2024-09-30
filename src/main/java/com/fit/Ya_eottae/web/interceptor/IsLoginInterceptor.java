package com.fit.Ya_eottae.web.interceptor;

import com.fit.Ya_eottae.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class IsLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 현재 인터셉터에 걸린 URL을 받음
        String requestURI = request.getRequestURI();

        // 세션도 받음 // false 로 설정하여 없을 때 새로 생성하지는 않음
        HttpSession session = request.getSession(false);

        // 세션이 null 이거나 세션에 회원 아이디가 존재하지 않는다면 로그인 화면으로 돌려보냄
        if (session == null || session.getAttribute(SessionConst.SESSION_ID) == null) {
            // 로그인 화면으로 돌려보낼 때 어디서 로그인으로 갔는지 표기한다. -> 추후 로그인 성공시 다시 이 화면으로 리다이렉트 한다.
            response.sendRedirect("/login?requestURL=" + requestURI);
            return false;
        }

        return true;
    }
}
