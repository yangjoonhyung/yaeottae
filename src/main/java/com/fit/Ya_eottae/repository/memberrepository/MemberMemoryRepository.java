package com.fit.Ya_eottae.repository.memberrepository;

import com.fit.Ya_eottae.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//@Repository
public class MemberMemoryRepository implements MemberRepository {

    //AtomicLong
    private Map<String, Member> memberStore = new ConcurrentHashMap<>();

    @Override
    public Member join(Member member) { //
        memberStore.put(member.getMemberId(), member);
        return member;
    }

    @Override
    public Member findByMemberId(String memberId) { //
        return memberStore.get(memberId);
    }

    @Override
    public List<Member> findAll() { //
        return new ArrayList<>(memberStore.values());
    }

    @Override
    public Member findId(String name, String email) { //
        Member findMember = findBymemberName(name).stream().filter(member -> member.getEmail().equals(email)).findAny().get();

        if (findMember == null) {
            return null;
        }

        return findMember;
    }

    @Override
    public List<Member> findBymemberName(String memberName) {
        return findAll().stream().filter(member -> member.getMemberName().equals(memberName)).collect(Collectors.toList());
    }

    @Override
    public Member updateName(Member member, String updateName) {
        member.setMemberName(updateName);
        return member;
    }

    @Override
    public Member updateIntroduction(Member member, String updateIntro) {
        member.setMemberIntroduce(updateIntro);
        return member;
    }

    @Override
    public Member isOkToChangePassword(String memberId, String memberEmail) {
        Member findMember = findAll().stream().filter(member -> member.getMemberId().equals(memberId) && member.getEmail().equals(memberEmail)).findAny().orElse(null);

        if (findMember == null) {
            return null;
        }

        return findMember;
    }

    @Override
    public void changePw(Member member, String newPassword) {
        member.setPassword(newPassword);
    }

    public void clear() {
        memberStore.clear();
    }
}
