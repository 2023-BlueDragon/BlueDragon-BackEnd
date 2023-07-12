package com.bluedragon.arth.auth.domain.exception;

import com.bluedragon.arth.z_common.error.CustomException;
import com.bluedragon.arth.z_common.error.ErrorCode;

public class WrongTokenTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(ErrorCode.WRONG_TOKEN_TYPE);
    }

}