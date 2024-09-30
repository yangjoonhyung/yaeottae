package com.fit.Ya_eottae.domain.inquiry;

import com.fit.Ya_eottae.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Inquiry {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inquiryId; // 문의 아이디

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member; // 멤버 아이디
    private String inquiry; // 문의
    private String answer; // 답변

    public Inquiry() {
    }

    public Inquiry(Member member, String inquiry, String answer) {
        this.member = member;
        this.inquiry = inquiry;
        this.answer = "";
    }
}
