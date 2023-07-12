package com.bluedragon.arth.z_infrastructure.thirdparty.s3.exception;

import com.bluedragon.arth.z_common.error.CustomException;
import com.bluedragon.arth.z_common.error.ErrorCode;

public class FileUploadException extends CustomException {

    public static final CustomException EXCEPTION = new FileUploadException();

    private FileUploadException() {
        super(ErrorCode.FILE_UPLOAD_ERROR);
    }

}