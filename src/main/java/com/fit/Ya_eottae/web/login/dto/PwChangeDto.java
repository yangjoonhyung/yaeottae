package com.fit.Ya_eottae.web.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PwChangeDto {

    @NotBlank
    private String password;
    @NotBlank
    private String checkPassword;
}
