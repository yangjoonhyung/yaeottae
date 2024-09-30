package com.fit.Ya_eottae.web.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class LoginDto {

    @NotBlank
    private String memberId;

    @NotBlank
    private String password;
}
