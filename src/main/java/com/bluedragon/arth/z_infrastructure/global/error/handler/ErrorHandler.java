package com.bluedragon.arth.z_infrastructure.global.error.handler;

import com.bluedragon.arth.z_common.error.CustomException;
import com.bluedragon.arth.z_common.error.ErrorCode;
import com.bluedragon.arth.z_infrastructure.global.error.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return new ResponseEntity<>(
                ErrorResponse.create(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatusValue()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException() {
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

}