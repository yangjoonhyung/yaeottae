package com.fit.Ya_eottae.web.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckPwChangeDto {

    @NotBlank
    private String memberId;
    @NotBlank
    private String email;
    @NotBlank
    private String checkNumber;
}
