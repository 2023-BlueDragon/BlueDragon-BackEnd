package com.bluedragon.arth.file.ui;

import com.bluedragon.arth.file.application.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "File API")
@RestController
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService fileUploadService;

    @Operation(description = "File(List) 업로드")
    @PostMapping
    @ResponseStatus(OK)
    public List<String> uploadFile(@RequestPart("fileList") List<MultipartFile> multipartFileList) {
        return fileUploadService.uploadFileList(multipartFileList);
    }

}