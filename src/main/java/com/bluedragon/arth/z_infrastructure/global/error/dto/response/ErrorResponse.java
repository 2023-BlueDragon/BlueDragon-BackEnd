package com.bluedragon.arth.z_infrastructure.global.error.dto.response;

import com.bluedragon.arth.z_common.error.ErrorCode;

public record ErrorResponse(
        int status,
        String message) {
    public static ErrorResponse create(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatusValue(), errorCode.getMessage());
    }
}