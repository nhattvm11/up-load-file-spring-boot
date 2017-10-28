package com.example.uploadfilespring.controller;

import com.example.uploadfilespring.service.ImageService;
import org.apache.tomcat.jni.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/v1/api")
public class UploadController {

    public final static Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private ImageService imageService;

    private static String UPLOADED_FOLDER = "/home/minhat/IdeaProjects/up-load-file-spring/src/main/resources/image";

    @GetMapping("/uploadPage")
    private String uploadPage() {
        LOGGER.info("");
        return "upload";
    }

    @PostMapping("/handleUpload")
    public String handleUpload(@RequestParam("file")MultipartFile file) throws IOException {
        LOGGER.info("handle upload");
        if(file.isEmpty()) {
            throw new RuntimeException();
        }
        LOGGER.info(file.getOriginalFilename());


        byte[] imageData = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, imageData);
        FileInputStream fis =  new FileInputStream(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file.getOriginalFilename());
        while(fis.read(imageData) != -1) {
            fos.write(imageData);
            fos.flush();
        }
        fis.close();
        fos.close();
        return "upload";
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().build();
    }


}
