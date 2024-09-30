package com.fit.Ya_eottae.repository.memberrepository;

import com.fit.Ya_eottae.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Primary
@Repository
@Transactional
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    @Override
    public Member join(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Member findByMemberId(String memberId) {
        Member findMember = em.find(Member.class, memberId);
        return findMember;
    }

    @Override
    public List<Member> findAll() {
        String jpql = "select m from Member m";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        List<Member> memberList = query.getResultList();
        return memberList;
    }

    @Override
    public Member findId(String name, String email) {
        String jpql = "select m from Member m Where m.memberName = :name and m.email = :email";

        try {
            Member member = em.createQuery(jpql, Member.class)
                    .setParameter("name", name)
                    .setParameter("email", email)
                    .getSingleResult();

            return member;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Member isOkToChangePassword(String memberId, String memberEmail) {

        try {
            Member member = em.find(Member.class, memberId);

            if (member != null && member.getEmail().equals(memberEmail)) {
                return member;
            }
            return null;
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public void changePw(Member member, String newPassword) {
        Member findMember = em.find(Member.class, member.getMemberId());
        findMember.setPassword(newPassword);
    }

    @Override
    public List<Member> findBymemberName(String memberName) {
        String jpql = "select m from Member m Where m.memberName = :memberName";
        TypedQuery<Member> findMember = em.createQuery(jpql, Member.class)
                .setParameter("memberName", memberName);
        List<Member> findMemberList = findMember.getResultList();
        return findMemberList;
    }

    @Override
    public Member updateName(Member member, String updateName) {
        Member findMember = em.find(Member.class, member.getMemberId());
        findMember.setMemberName(updateName);
        return findMember;
    }

    @Override
    public Member updateIntroduction(Member member, String updateIntro) {
        Member findMember = em.find(Member.class, member.getMemberId());
        findMember.setMemberIntroduce(updateIntro);
        return findMember;
    }
}
