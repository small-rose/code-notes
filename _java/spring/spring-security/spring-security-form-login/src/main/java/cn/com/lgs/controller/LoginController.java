package cn.com.lgs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luguosong
 * @date 2021/11/9 16:02
 */
@RestController
public class LoginController {
    @GetMapping("index")
    public String index() {
        return "登录成功";
    }
}
