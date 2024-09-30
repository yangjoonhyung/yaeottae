package com.fit.Ya_eottae.domain.member.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberJoinDto {

    @NotBlank
    private String memberId;
    @NotBlank
    private String password;
    @NotBlank
    private String checkPassword;
    @NotBlank
    private String memberName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private int year;
    @NotBlank
    private int month;
    @NotBlank
    private int date;
    @AssertTrue(message = "동의하셔야 합니다.")
    private Boolean termOfUse;
    private Boolean marketingReception;
    @AssertTrue(message = "동의하셔야 합니다.")
    private Boolean personalInformation;

}
