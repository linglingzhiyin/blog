package com.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    }
