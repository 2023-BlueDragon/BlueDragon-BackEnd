package com.bluedragon.arth.z_infrastructure.thirdparty.s3.exception;

import com.bluedragon.arth.z_common.error.CustomException;
import com.bluedragon.arth.z_common.error.ErrorCode;

public class FileConvertException extends CustomException {

    public static final CustomException EXCEPTION = new FileConvertException();

    public FileConvertException() {
        super(ErrorCode.FILE_CONVERT_ERROR);
    }

}