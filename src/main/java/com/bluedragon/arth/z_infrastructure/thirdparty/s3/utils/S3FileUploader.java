package com.bluedragon.arth.z_infrastructure.thirdparty.s3.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bluedragon.arth.z_infrastructure.thirdparty.s3.exception.FileConvertException;
import com.bluedragon.arth.z_infrastructure.thirdparty.s3.exception.FileUploadException;
import com.bluedragon.arth.z_infrastructure.thirdparty.s3.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3FileUploader {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public String uploadFile(final MultipartFile multipartFile) {
        try {
            File convertFile = convert(multipartFile)
                    .orElseThrow(() -> FileConvertException.EXCEPTION);

            String fileName = makefileName(convertFile.getName());

            amazonS3.putObject(putRequest(fileName, convertFile));

            convertFile.delete();

            return getUrl(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e.getMessage());
            throw FileUploadException.EXCEPTION;
        }
    }

    public List<String> uploadFileList(final List<MultipartFile> multipartFileList) {
        return multipartFileList.stream().map(this::uploadFile).toList();
    }

    public void delete(final String path) {
        amazonS3.deleteObject(s3Properties.getBucket(), path);
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = File.createTempFile("amazons3", file.getOriginalFilename()); // new File(System.getProperty("user.home") + file.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
        }

        return Optional.of(convertFile);
    }

    private String makefileName(final String convertFileName) {
        return s3Properties.getBucket() + "/" + UUID.randomUUID() + convertFileName;
    }

    private PutObjectRequest putRequest(final String fileName, final File convertFile) {
        return new PutObjectRequest(s3Properties.getBucket(), fileName, convertFile)
                .withCannedAcl(CannedAccessControlList.PublicRead);
    }

    private String getUrl(final String fileName) {
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }

}