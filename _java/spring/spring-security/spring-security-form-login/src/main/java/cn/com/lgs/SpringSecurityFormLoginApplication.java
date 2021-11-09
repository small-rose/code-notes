package cn.com.lgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author luguosong
 * @date 2021/11/9 15:49
 */
@SpringBootApplication
public class SpringSecurityFormLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityFormLoginApplication.class, args);
    }
}
