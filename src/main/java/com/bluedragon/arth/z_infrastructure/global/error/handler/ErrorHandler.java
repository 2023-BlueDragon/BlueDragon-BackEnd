package com.bluedragon.arth.z_infrastructure.global.error.handler;

import com.bluedragon.arth.z_common.error.CustomException;
import com.bluedragon.arth.z_common.error.ErrorCode;
import com.bluedragon.arth.z_infrastructure.global.error.dto.response.ErrorResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return new ResponseEntity<>(
                ErrorResponse.create(e.getErrorCode()), valueOf(e.getErrorCode().getStatusValue()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.ILLEGAL_ARGUMENT_ERROR), BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<ErrorResponse> handleDBException(DataAccessException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.DBACCESS_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(ErrorResponse.create(ErrorCode.INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

}