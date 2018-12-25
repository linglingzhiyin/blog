package com.domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class AdminLoginLog {
    private Long id;

    private Integer adminId;

    private Date date;

    private String ip;
}