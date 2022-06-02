---
layout: default
title: 微服务
nav_order: 140
---

# 微服务技术栈

- 注册中心
- 配置中心
- 网关
- 分布式缓存和搜索
- 消息队列
- 分布式日志
- 系统监控链路追踪
- 自动化部署

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202206021354433.png)

# 架构演变

## 单体架构

把业务的所有功能集中在一个项目中开发，打成一个包部署

- 优点
    - 架构简单
    - 部署成本低
- 缺点
    - 耦合度高

## 分布式架构

根据业务功能对系统进行拆分，每个业务模块作为独立项目开发

- 优点
    - 降低服务耦合
    - 有利于服务升级拓展

## 微服务

一种经过良好架构设计的分布式架构方案

- 单一职责
- 面向服务
- 自治
- 隔离性强

# 微服务技术对比

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202206021515243.png)

# SpringCloud

使用最广泛的微服务框架

SpringCloud集成了各种微服务功能组件，并基于`SpringBoot`实现了这些组件的`自动装配`，从而提供了良好的开箱即用体验。

`SpringCloud`和`SpringBoot`之间存在版本对应关系

# 注册中心

- 作用
  - 服务提供者将信息提供给注册中心

## Eureka



# 服务间远程调用

## RestTemplate

- 将RestTemplate注入到Spring容器中

```java
@MapperScan("com.luguosong.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

- 通过RestTemplate调用其它服务

```java
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
```
