package com.fit.Ya_eottae.memberrepository;

import com.fit.Ya_eottae.domain.member.Member;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberMemoryRepositoryTest {

    private final EntityManager em;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberMemoryRepositoryTest(EntityManager em, MemberRepository memberRepository) {
        this.em = em;
        this.memberRepository = memberRepository;
    }


    @Test
    void joinTest() {
        Member member = new Member("han12", "한현성", "h1234!", "hhh@naver.com", 2001, 10, 04, true, true, true);

        Member joinMember = memberRepository.join(member);

        Member findMember = memberRepository.findId("한현성", "hhh@naver.com");
        assertThat(joinMember.getMemberId()).isEqualTo(findMember.getMemberId());
    }

    @Test
    void findAllTest() {
        Member member1 = new Member("한현성1", "han", "han!" ,"han1@email" , 2001, 10, 26, true, true, true);
        Member member2 = new Member("한현성2", "han", "han!" ,"han2@email" , 2001, 10, 26, true, true, true);

        memberRepository.join(member1);
        memberRepository.join(member2);

        assertThat(memberRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    void changePwTest() {
        Member member = new Member("한현성1", "han", "han!" ,"han1@email" , 2001, 10, 26, true, true, true);
        Member joinMember = memberRepository.join(member);

        System.out.println("joinMember.getPassword() = " + joinMember.getPassword());
        assertThat(joinMember.getPassword()).isEqualTo("han@");
    }

}
