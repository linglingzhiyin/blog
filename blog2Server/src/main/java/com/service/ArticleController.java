package com.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    }
