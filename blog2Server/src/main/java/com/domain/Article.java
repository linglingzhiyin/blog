package com.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class Article implements Serializable {
    @TableId(type = IdType.AUTO)
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