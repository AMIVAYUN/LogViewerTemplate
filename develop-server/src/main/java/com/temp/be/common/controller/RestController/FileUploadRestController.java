package com.temp.be.common.controller.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/common")
@RestController

public class FileUploadRestController {
//    private final FileUploadService fileUploadService;

//    @PostMapping("/images")
//    public ResponseEntity<ImageUploadResponse> uploadImageFile(
//            @RequestPart("image") MultipartFile multipartFile) {
//        return ResponseEntity.ok(ImageUploadResponse.from(fileUploadService.uploadFile(multipartFile)));
//    }

}
