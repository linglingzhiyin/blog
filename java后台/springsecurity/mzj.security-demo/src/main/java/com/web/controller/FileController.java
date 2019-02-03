package com.web.controller;

import com.web.dto.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {
    private String folder = "E:\\2018";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getSize());

        File locadFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(locadFile);
        return new FileInfo(locadFile.getAbsolutePath());
    }

    @GetMapping("/{id:\\d+}")
    public void downLoad(
            @PathVariable String id, HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }

    }
}
