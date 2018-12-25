package com.domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class Article {
    private Integer id;

    private String title;

    private String keywords;

    private String desci;

    private String pic;

    private Integer click;

    private Date time;

    private Integer catalogId;

    private String content;

  }