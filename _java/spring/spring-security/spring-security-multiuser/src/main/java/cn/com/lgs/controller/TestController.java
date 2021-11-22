package cn.com.lgs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luguosong
 * @date 2021/11/22 10:37
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "success";
    }
}
