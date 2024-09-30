package com.fit.Ya_eottae.web.member.service;

import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;

    public boolean checkPasswordForm(String password) {

        if (!password.matches("[a-zA-Z0-9!@#$%^&*()+=\\-_<>,.?/]*")) {
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()+=\\-_<>,.?/].*")) {
            return false;
        }

        if (password.length() < 10) {
            return false;
        }

        return true;
    }

    public boolean isSamePassword(String password, String checkPassword) {

        if (password.equals(checkPassword)) {
            return true;
        }
        return false;
    }
}
