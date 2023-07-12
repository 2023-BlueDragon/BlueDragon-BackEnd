package com.bluedragon.arth.auth.ui.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken) {}