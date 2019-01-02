package com.domain;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class Comment implements Serializable {
    private Long id;

    private Long articleId;

    private Date date;

    private String name;

    private String email;

    private String content;

    }