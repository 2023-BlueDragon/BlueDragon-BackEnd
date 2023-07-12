package com.bluedragon.arth.file.application;

import com.bluedragon.arth.z_infrastructure.thirdparty.s3.utils.S3FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3FileUploader s3FileUploader;

    public List<String> uploadFileList(List<MultipartFile> multipartFileList) {
        return s3FileUploader.uploadFileList(multipartFileList);
    }

}