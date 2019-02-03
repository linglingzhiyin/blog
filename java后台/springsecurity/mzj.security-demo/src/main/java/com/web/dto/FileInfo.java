package com.web.dto;

import lombok.Data;

@Data
public class FileInfo {
    private String path;
    public FileInfo(){}
    public FileInfo(String path) {
        this.path = path;
    }
}
