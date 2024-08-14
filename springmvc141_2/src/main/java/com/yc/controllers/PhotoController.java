package com.yc.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String outputPath = "F:\\" + uuid +"." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        try (InputStream is = file.getInputStream();
             OutputStream oos = new BufferedOutputStream(new FileOutputStream(outputPath))) {

            byte[] buffer = new byte[8192]; // 定义一个较大的缓冲区大小
            int len;
            long totalLengthInKB = 0;

            while ((len = is.read(buffer)) != -1) {
                oos.write(buffer, 0, len);
                totalLengthInKB += len / 1024; // 计算总长度，单位为KB
            }

            System.out.println("Total length in KB: " + totalLengthInKB);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "上传成功";

    }
}
