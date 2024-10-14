package com.fit.Ya_eottae.repository.memberrepository;

import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.domain.member.MemberTendency;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    public Member join(Member member); // 회원가입
    public Member findByMemberId(String memberId); // 아이디로 회원 찾기
    public List<Member> findAll(); // 모든 회원 찾기
    public Member findId(String name, String email); // 아이디 찾기 ( 이름과 이메일이 한 DB에 일치하면 아이디 공개 인증번호 X )
    public Member isOkToChangePassword(String memberId, String memberEmail);
    public void changePw(Member member, String newPassword);
    public List<Member> findBymemberName(String memberName);
    public Member updateName(Member member, String updateName);
    public Member updateIntroduction(Member member, String updateIntro);
    void setTendency(Member member, MemberTendency memberTendency);

}
