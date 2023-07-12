package com.bluedragon.arth.user.ui.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotNull @Email
        String email,
        @NotNull @Size(min = 8, max = 255)
        String passWord,
        String name,
        String nickName) {}