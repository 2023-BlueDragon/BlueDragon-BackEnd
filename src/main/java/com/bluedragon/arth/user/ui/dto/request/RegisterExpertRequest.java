package com.bluedragon.arth.user.ui.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterExpertRequest(
        @NotNull @Email
        String email,
        @NotBlank
        String passWord,
        String name,
        @NotBlank
        String nickName,
        @NotBlank
        String major) {}