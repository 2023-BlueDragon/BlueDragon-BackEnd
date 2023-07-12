package com.bluedragon.arth.z_common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    IO_ERROR(500, "IOException occurred"),
    DBACCESS_ERROR(500, "DBAccessException occurred"),
    SERVLET_BINDING_ERROR(500, "ServletBindingException occurred"),
    INTERNAL_SERVER_ERROR(500, "InternalServerException occurred"),
    FILE_CONVERT_ERROR(500, "FileConvertException occurred"),
    FILE_UPLOAD_ERROR(500, "FileUploadException occurred"),

    USER_NOT_FOUND(404, "User not found"),

    WRONG_LOGIN_TYPE(403, "Check your login type"),

    WRONG_TOKEN_TYPE(400, "Check your token type"),
    WRONG_PASSWORD(400, "Check your password"),
    EXPIRED_JWT(400, "Jwt is expired"),
    MALFORMED_JWT(400, "Jwt is malformed"),
    UNSUPPORTED_JWT(400, "Jwt is unsupported"),
    ILLEGAL_ARGUMENT_ERROR(400, "IllegalArgumentException occurred");

    private final int statusValue;
    private final String message;

}