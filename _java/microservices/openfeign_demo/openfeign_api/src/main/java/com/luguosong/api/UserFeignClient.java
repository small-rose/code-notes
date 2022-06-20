package com.luguosong.api;

import com.luguosong.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Connection;

/**
 * @author 10545
 * @date 2022/6/9 23:41
 */
@FeignClient("user-server/user")
public interface UserFeignClient {
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id);
}
