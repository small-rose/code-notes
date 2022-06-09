package com.luguosong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 10545
 * @date 2022/6/9 22:16
 */
@RestController
public class TestController {

    @Value("${pattern.dateformat}")
    private String dateformat;

    @GetMapping("test")
    public String test(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
    }
}
