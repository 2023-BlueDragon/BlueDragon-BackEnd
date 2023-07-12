package com.bluedragon.arth.auth.domain.exception;

import com.bluedragon.arth.z_common.error.CustomException;
import com.bluedragon.arth.z_common.error.ErrorCode;

public class WrongPassWordException extends CustomException {

    public static final CustomException EXCEPTION = new WrongPassWordException();

    private WrongPassWordException() {
        super(ErrorCode.WRONG_PASSWORD);
    }

}