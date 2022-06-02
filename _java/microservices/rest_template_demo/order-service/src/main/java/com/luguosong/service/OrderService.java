package com.luguosong.service;

import com.luguosong.mapper.OrderMapper;
import com.luguosong.pojo.Order;
import com.luguosong.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;
    
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.通过RestTemplate发送请求
        String url="http://localhost:8081/user/"+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        //3.将User对象注入到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
