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

# 注册中心概述

在`Spring Cloud Commons`定义了服务注册和发现的接口，不管是eureka还是nacos遵循该接口开发。

# 注册中心-Eureka

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220602211231.png)

## 服务端

- 记录服务信息
- 心跳监控

服务端搭建：

- 导入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
</dependencies>
```

- 配置

```yaml
server:
  port: 10086
spring:
  application:
    name: eurekaserver
eureka:
  client:
    service-url:
      defaultZone: http://127.0.0.1:10086/eureka
```

- SpringBoot启动类配置自动装配

```java
/**
 * @author 10545
 */
@EnableEurekaServer //表示让SpringBoot自动装配
@SpringBootApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

## 客户端

- 服务提供者
    - 注册自己的信息到服务端
    - 每隔30秒像服务端发送心跳
- 服务消费者
    - 根据服务名从服务端拉取服务列表
    - 基于服务列表做负载均衡，选中一个服务后发起远程调用

多个客户端搭建步骤是一样的，具体如下：

- 引入客户端依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
</dependencies>
```

- 配置服务名称和配置eureka服务端地址，这里以`eureka_client_user`工程为例

```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/cloud_user?useSSL=false
    username: root
    password: 12345678
    driver-class-name: com.mysql.jdbc.Driver
  application:
    name: user_server #指定服务名称
mybatis:
  type-aliases-package: com.luguosong.pojo
  configuration:
    map-underscore-to-camel-case: true
logging:
  level:
    com.luguosong: debug
  pattern:
    dateformat: MM-dd HH:mm:ss:SSS
eureka:
  client:
    service-url:
      defaultZone: http://127.0.0.1:10086/eureka  #配置eureka地址
```

- 服务消费方调用服务提供方

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
        //通过服务名称访问服务，而不是写死IP和端口
        String url = "http://user_server/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        //3.将User对象注入到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
```

配置负载均衡：

```java

@MapperScan("com.luguosong.mapper")
@SpringBootApplication
public class EurekaClientOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientOrderApplication.class, args);
    }

    /**
     * 通过@LoadBalanced配置负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

# Nacos安装

## windows下安装

- 下载安装包（绿色版）
- 解压
- 切换到bin目录下，输入`startup.cmd -m standalone`启动

# 注册中心-Nacos

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220605222100.png)

## 服务端

启动nacos服务

## 客户端

- 导入nacos客户端依赖,需要注意的是新版本已不再使用ribbon，需要手动引入`spring-cloud-starter-loadbalancer`

```xml

<dependencies>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    </dependency>
</dependencies>
```

- 编写配置文件

```yaml
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/cloud_order?useSSL=false
    username: root
    password: 12345678
    driver-class-name: com.mysql.jdbc.Driver
  application:
    name: order-server #指定服务名
  cloud:
    nacos:
      server-addr: 127.0.0.1:8848 #指定nacos服务端地址
mybatis:
  type-aliases-package: com.luguosong.pojo
  configuration:
    map-underscore-to-camel-case: true
logging:
  level:
    com.luguosong: debug
  pattern:
    dateformat: MM-dd HH:mm:ss:SSS
```

- 剩余部分与eureka客户端一致

## Nacos服务分级模型

- 一级：服务
- 二级：集群
- 三级：实例

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220604232558.png)

- 为实例配置集群名称

```yaml
spring:
  cloud:
    nacos:
      discovery:
        cluster-name: 集群名称，也可以理解为机房的位置
```

## 实例权重设置

在nacos控制台可以配置实例权重

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220605211541.png)

## 环境隔离

### 命名空间

- 创建namespace，用来隔离不同环境

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220605213737.png)

- 设置namespace名称

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220605213811.png)

- 自动生成命名空间id

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220605221215.png)

- 在实例的配置文件中配置namespace的id

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220605221254.png)

## 临时实例和非临时实例

设置非临时实例：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220605222230.png)



# 服务间远程调用-RestTemplate

- 将RestTemplate注入到Spring容器中

```java

@MapperScan("com.luguosong.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
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
        String url = "http://localhost:8081/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        //3.将User对象注入到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
```

# 负载均衡

## RestTemplate负载均衡

- 通过`@LoadBalanced`注解标记`RestTemplate`
- 被注解标记的`RestTemplate`发送的HTTP请求会被`LoadBalancerInterceptor`拦截器拦截
- `LoadBalancerInterceptor`拦截请求，获取请求中在`eureka`注册的服务名称
- 通过负载均衡器进行负载均衡返回最终访问地址，之前默认为`Ribbon`，现在已经改为`Spring-cloud-loadbalancer`






