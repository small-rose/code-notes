package cn.com.lgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自定义认证逻辑添加验证码功能
 *
 * @author luguosong
 * @date 2021/11/23 10:23
 */
@SpringBootApplication
public class SpringSecurityKaptchaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityKaptchaApplication.class, args);
    }
}
