package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice("com.service")
@Slf4j
public class ErrorConfig {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> errorResult(RuntimeException runtimeException) {
        Map<String, Object> result = new HashMap<String, Object>();
        log.error(runtimeException.getMessage());
        result.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR);
        result.put("errorMsg",runtimeException.getMessage());
        return result;
    }

}
