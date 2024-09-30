package com.fit.Ya_eottae.web.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IdFIndDto {

    @NotBlank
    private String memberName;
    @NotBlank
    private String email;

    public IdFIndDto() {
    }
}
