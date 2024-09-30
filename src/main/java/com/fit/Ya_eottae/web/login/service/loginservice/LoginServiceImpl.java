package com.fit.Ya_eottae.web.login.service.loginservice;

import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public Member loginCheck(String memberId, String password) {

        Member tryJoinMember = memberRepository.findByMemberId(memberId);

        if (tryJoinMember == null) {
            return null;
        }

        if (tryJoinMember.getPassword().equals(password)) {
            return tryJoinMember;
        }

        return null;
    }

}
