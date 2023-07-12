package com.bluedragon.arth.z_global.error;

import com.bluedragon.arth.z_common.error.ErrorCode;

public record ErrorResponse(
        int status,
        String message) {
    public static ErrorResponse create(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatusValue(), errorCode.getMessage());
    }
}