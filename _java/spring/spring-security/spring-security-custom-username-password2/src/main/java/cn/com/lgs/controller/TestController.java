package cn.com.lgs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luguosong
 */
@RestController
@RequestMapping("/test")
public class TestController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello security";
  }
}
