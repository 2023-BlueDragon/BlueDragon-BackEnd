package com.bluedragon.arth.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    USER("ROLE_USER"), EXPERT("ROLE_EXPERT");

    private final String role;

}