package com.fit.Ya_eottae.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter
@ToString
@Entity
public class Member {

    @Id
    private String memberId; // 사용자 ID
    private String memberName; // 사용자 이름
    private String password; // 사용자 비밀번호
    private String email; // 사용자 이메일
    private LocalDate memberDate; // 사용자 생년월일
    private MemberTendency memberTendency; // 사용자 성향
    private int memberAge;// 사용자 나이
    private String memberIntroduce; // 사용자 소개
    private Boolean termOfUse; // 이용약관
    private Boolean marketingReception; // 마케팅 수신
    private Boolean personalInformation; // 개인정보 수집 이용

    public Member() {
    }

    public Member(String memberId, String memberName, String password, String email, int year, int month, int date,
                  boolean termOfUse, boolean marketingReception, boolean personalInformation) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.email = email;
        this.memberDate = LocalDate.of(year, month, date);
        this.memberAge = LocalDate.now().getYear() - year + 1;
        this.memberTendency = MemberTendency.NOTEST;
        this.memberIntroduce = "-";
        this.termOfUse = termOfUse;
        this.marketingReception = marketingReception;
        this.personalInformation = personalInformation;
    }


}
