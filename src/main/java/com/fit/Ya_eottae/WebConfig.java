package com.fit.Ya_eottae;

import com.fit.Ya_eottae.web.interceptor.IsLoginInterceptor;
import com.fit.Ya_eottae.web.interceptor.PwChangeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IsLoginInterceptor())
                .order(1)
                .addPathPatterns("/review/**", "/myPage/**", "/tendency-test/**") // 이곳에 적히는 URL은 로그인 사용자만 접근할 수 있음.
                .excludePathPatterns("/review/{reviewId}", "/review/{reviewId}/see-review");

        registry.addInterceptor(new PwChangeInterceptor())
                .order(2)
                .addPathPatterns("/login/is-ok-to-changePw/changePw", "/login/complete-changePw");
    }
}
