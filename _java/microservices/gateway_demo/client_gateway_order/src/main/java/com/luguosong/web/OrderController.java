package com.luguosong.web;


import com.luguosong.pojo.Order;
import com.luguosong.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("queryOrderByUserId/{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }

    /**
     * 测试gateway过滤器
     * @param key1
     * @return
     */
    @GetMapping("gatewayFilterTest")
    public String gatewayFilterTest(@RequestHeader String key1) {
        return key1;
    }
}
