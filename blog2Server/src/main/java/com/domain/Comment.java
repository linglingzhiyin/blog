package com.domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class Comment {
    private Long id;

    private Long articleId;

    private Date date;

    private String name;

    private String email;

    private String content;

    }