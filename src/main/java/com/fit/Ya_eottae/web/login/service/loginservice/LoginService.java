package com.fit.Ya_eottae.web.login.service.loginservice;

import com.fit.Ya_eottae.domain.member.Member;

public interface LoginService {

    public Member loginCheck(String memberId, String password);
}
