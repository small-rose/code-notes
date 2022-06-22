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

- `临时实例`：默认是临时实例，与eureka一样采用`心跳检测`，如果检测不到心跳nacos会将临时实例直接从服务列表中剔除。
- `非临时实例`：nacos主动发送请求给实例询问实例是否存在，即使非临时实例不存在了，nacos也不会将非临时实例从服务列表中剔除

设置非临时实例：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220605222230.png)

## 服务列表更新

`nacos服务消费者`会定期向`nacos服务端`发送请求拉取`服务列表缓存`（这与eureka一样）

如果nacos服务端服务发生变化，`nacos服务端`会主动向`服务消费者`推送变更消息（eureka不会主动推送）。

# 统一配置管理-Nacos

## nacos服务端新建配置

对有`热更新`需求的配置在nacos服务端进行统一配置

其中：`Data ID`=服务名称+环境+后缀

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220609225335.png)

## 服务实例读取nacos配置

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220607222359.png)

SpringBoot实例需要先去nacos服务端读取配置文件，并将读取到的配置文件与本地的`application.yml`配置文件结合。

之前nacos的服务端地址是在`application.yml`，提前去nacos读取配置需要将nacos服务地址等相关配置在优先级更高的`bootstrap.yml`配置文件中

具体步骤如下：

- 引入Nacos的配置管理客户端依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--服务发现依赖-->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    <!--统一配置依赖-->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    </dependency>
    <!--新版本需要导入spring-cloud-starter-bootstrap才会先读取bootstrap配置文件-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-bootstrap</artifactId>
    </dependency>
</dependencies>
```

- 新建`bootstrap.yml`配置文件

其中：`name`+`active`+`file-extension`= 服务端`Data ID`

```yaml
spring:
  application:
    name: nacos-config-demo
  profiles:
    active: dev
  cloud:
    nacos:
      server-addr: 127.0.0.1:8848
      config:
        file-extension: yaml
```

- 实例可以拿到服务端的配置

```java
/**
 * @author 10545
 * @date 2022/6/9 22:16
 */
@RestController
public class TestController {

    @Value("${pattern.dateformat}")
    private String dateformat;

    @GetMapping("test")
    public String test(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
    }
}
```

## 配置热更新

热更新表示nacos服务端配置更新后，实例会自动生效

- 方式一

在`@Value`注解所在的类添加`@RefreshScope`注解

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608091938.png)

- 方式二

使用`@ConfigurationProperties`注解

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608092439.png)

## 多环境配置共享

微服务启动时会从nacos读取多个配置文件：
- `spring.application.name`-`spring.profiles.active`.yaml,例如：userservice-dev.yaml
- `spring.application.name`.yaml,例如：userservice,yaml

无论profile如何变化，`spring.application.name`.yaml这个文件一定会加载，因此多环境共享配置可以写入这个文件

## 配置文件的优先级

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608093610.png)

# Nacos集群搭建

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608094221.png)

集群搭建步骤：

- 搭建数据库，初始化数据库表结构
  ![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608100040.png)
- 下载nacos安装包
- 配置nacos
  - 进入nacos的conf目录，修改配置文件cluster.conf.example,重命名为cluster.conf
  - 在配置文件中配置Nacos集群中每一个节点的信息
  - 然后修改`application.properties`文件，添加数据库配置
    ![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608100221.png)
  - 如果在同一个电脑启动多个节点，还需要在`application.properties`中修改nacos端口
- 启动nacos集群
- nginx反向代理
  - 修改conf/nginx.conf文件
    ![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608095328.png)
- 实例直接配置nginx地址作为nacos地址

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

# 服务间远程调用-OpenFeign

## 概述

Feign是一个声明式的http客户端

## RestTemplate存在的问题

- 代码可读性差，编程体验不统一
- 参数复杂的URL难以维护

## 开发步骤

- 引入依赖

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608101104.png)

- 在服务消费方启动类中添加`@EnableFeignClients`注解开启Feign功能

- 编写Feign客户端

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608101425.png)

## 自定义配置

- feign.Logger.Level
  - 修改日志级别
  - 包含四种不同的级别：NONE、BASIC、HEADERS、FULL
- feign.codec.Decoder
  - 响应结果的解析器
  - http远程调用的结果做解析，例如解析json字符串为java对象
- feign.codec.Encoder
  - 请求参数编码
  - 将请求参数编码，便于通过http请求发送
- feign.Contract
  - 支持的注解格式
  - 默认是SpringMVC的注解
- feign.Retryer
  - 失败重试机制
  - 请求失败的重试机制，默认是没有，不过会使用Ribbon的重试

## 性能优化

Feign底层的客户端实现：
- `URLConnection`:默认实现，不支持连接池
- `Apache HttpClient`:支持连接池
- `OKHttp`:支持连接池

因此优化Feign的性能主要包括：
- 使用连接池代替默认的URLConnection
- 日志级别，最好用basic或none

优化步骤：

- 引入依赖

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608110121.png)

- 配置连接池

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608110138.png)

## 最佳实践

- `方式一（继承）`：给消费者的FeignClient和提供者的controller定义统一的父接口作为标准。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608110753.png)

这种方式不推荐，一旦接口变了，服务消费者和提供者都得修改代码。且这种方法对Spring MVC不起作用

- `方式二（引用）`：将FeignClient抽取为独立模块，并且把接口有关的POJO、默认的Feign配置都放到这个模块中，提供给所有消费者使用

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608111506.png)

需要在服务消费者启动的@EnableFeignClients注解中配置扫描包或者配置clients

# 负载均衡

## RestTemplate负载均衡

- 通过`@LoadBalanced`注解标记`RestTemplate`
- 被注解标记的`RestTemplate`发送的HTTP请求会被`LoadBalancerInterceptor`拦截器拦截
- `LoadBalancerInterceptor`拦截请求，获取请求中在`eureka`注册的服务名称
- 通过负载均衡器进行负载均衡返回最终访问地址，之前默认为`Ribbon`，现在已经改为`Spring-cloud-loadbalancer`

# 网关

## 网关的功能

- 身份认证和权限校验
- 服务路由、负载均衡
- 请求限流

# 网关-zuul

Zuul是基于servlet的实现，属于阻塞式编程。

# 网关-Gateway

Gateway是基于`Spring5`中提供的`WebFlux`,属于响应式编程，具备更好的性能

## 导入依赖

- 创建网关模块，引入Gateway依赖和nacos的服务发现依赖

```xml
<dependencies>
  <!--网关依赖-->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
  </dependency>
  <!--nacos服务发现依赖-->
  <dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
  </dependency>
  <!--spring Cloud新版本负载均衡从ribbon变为loadbalancer，需要手动导入依赖-->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
  </dependency>
</dependencies>
```

## 路由配置

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220611161903.png)

方式一，手动配置：

```yaml
server:
  port: 10010
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: localhost:8848
    gateway:
      routes:
        - id: router1
          uri: lb://client-gateway-user #路由的目标地址
          predicates:
            - Path=/user/** # 路径断言，判断路径是否是以/user开头
        - id: router2
          uri: lb://client-gateway-order
          predicates:
            - Path=/order/**
```

方式二`spring.cloud.gateway.discovery.locator.enabled`设置为true，生成默认的`id=xservice,uri: lb://xserver , path=/serviceId/**`不需要再手动配置路由：

```yaml
server:
  port: 10010
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: localhost:8848
    gateway:
      discovery:
        locator:
          enabled: true
          lower-case-service-id: true
```

## 路由断言（predicates）

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220611162158.png)

## 路由过滤器（GatewayFilter）

`GatewayFilter`是网关中提供的一种过滤器，可以对进入网关的请求和微服务返回的响应做处理

Spring:提供了31种不同的路由过滤器工厂。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220611164952.png)

下面是添加请求头的过滤器示例：

```yaml
server:
  port: 10010
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: localhost:8848
    gateway:
      routes:
        - id: router1
          uri: lb://client-gateway-user #路由的目标地址
          predicates:
            - Path=/user/** # 路径断言，判断路径是否是以/user开头
        - id: router2
          uri: lb://client-gateway-order
          predicates:
            - Path=/order/**
          filters:
            - AddRequestHeader=key1,value1 #向order实例添加自定义请求头
      default-filters:
        - AddRequestHeader=key2,value2 # 默认过滤器，作用于所有实例
```

在order中进行测试

```java
@RestController
@RequestMapping("order")
public class OrderController {
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
```

## 自定义全局过滤器

上面的过滤器都是框架事先定义好了的，只能在配置文件中进行参数配置。如果要完成自定义逻辑的过滤，需要在gateway中自定义全局过滤器

步骤：

- 编写实现`GlobalFilter`接口的过滤器类
- 添加`@Component注解`将类注入到Spring容器中
- 添加`@Order注解`或者实现`Order接口`指定过滤器的顺序
- 编写业务逻辑

```java
/**
 * Gateway自定义全局过滤器
 *
 * @author luguosong
 * @date 2022/6/11 18:18
 */
//@Order(0)
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求参数列表
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //获取参数中的authorization参数
        String auth = queryParams.getFirst("authorization");
        //判断参数值是否等于admin
        if ("admin".equals(auth)){
            //是：放行
            return chain.filter(exchange);
        }

        //否：拦截
        //设置响应状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //拦截请求
        return exchange.getResponse().setComplete();
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
```

## 过滤器的执行顺序

请求路由后，会将当前`路由过滤器`和`DefaultFilter`、`GlobalFilter`,合并到一个过滤器链（集合）中，排序后依次执行每个过滤器

其中`路由过滤器`和`DefaultFilter`都是`GatewayFilter`。而`GlobalFilter`也通过`GatewayFilterAdapter`适配器转化为`GatewayFilter`，做到网关中所有过滤器都是`GatewayFilter`类型

- 每一个过滤器都必须指定一个int类型的order值，`order值越小，优先级越高，执行顺序越靠前`。
- `GlobalFilter`通过实现Ordered接口，或者添加@Order注解来指定order值，由我们自己指定
- `路由过滤器`和`defaultFilter`的order由Spring指定，默认是`按照声明顺序`从1递增。
- 当过滤器的`order值一样时`，会按照`defaultFilter`>`路由过滤器`>`GlobalFilter`的顺序执行。

## 跨域问题处理

跨域问题：`浏览器禁止`请求的发起者与服务端发生跨域ajx请求，请求被浏览器拦截的问题

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220612105440.png)


# 消息队列

## 同步通讯

优点：时效性强，可以立刻获得结果

基于`Feign`的调用就属于同步方式，存在一些问题:
- 耦合度高：每次加入新需求都需要修改原来的代码
- 性能下降：调用时长是调用链时长之和
- 浪费资源：调用链中每个服务等待过程中，不能释放请求占用的资源
- 级联失败：如果服务提供者出现问题，会导致服务调用者跟着出问题

## 异步通讯

事件驱动模式

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220615225006.png)

- 优点
  - 服务解耦
  - 性能提高，吞吐量提高
  - 服务之间没有强依赖关系，不用担心级联失败问题
  - 流量削峰，当大量并发时，服务提供者不至于产生过大压力

- 缺点
  - 依赖于Broker的可靠性，安全性、吞吐能力
  - 架构复杂了，业务没有明显的流程线，不好追踪管理，出了问题不好排查

## 消息队列

也就是事件驱动中的Broker。

`消息`也就是`事件驱动`中的事件

## 各个消息队列实现的区别

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220616213931.png)

# 消息队列-RabbitMQ

`RabbitMQ`是基于`Erlang语言`开发的开源消息通信中间件

## 结构和概念

- channel:操作mq的工具
- exchange:消息路由
- queue:缓存消息
- virtual host:虚拟主机，对资源的逻辑分组

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220616223058.png)

## 基于docker安装

```shell
# 拉取镜像
docker pull rabbitmq:3-management
```

```shell
# 运行MQ容器
docker run \
 -e RABBITMQ_DEFAULT_USER=user \
 -e RABBITMQ_DEFAULT_PASS=12345678 \
 --name mq \
 --hostname mq1 \
 -p 15672:15672 \
 -p 5672:5672 \
 -d \
 rabbitmq:3-management
```

通过`localhost:15672`访问

## RabbitMQ控制台简单说明

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220618205659.png)

- `Overview`：节点详细详细
- `Connections`：消息的`发布者`或`消费者`与`RabbitMQ`建立的连接
- `Channels`:连接后的服务基于`channels通道`进行消息的发送和接收。是做消息发送的具体对象。
- `Exchanges`:消息路由
- `Queues`：消息存储
- `Admin`:对用户等信息进行管理


## 入门案例

基本消息队列的消息发送流程：
1. 建立connection
2. 创建channel
3. 利用channel声明队列
4. 利用channel向队列发送消息

```java
//不使用AMQP的服务发布者代码
public class PublisherTest {
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
      // 1.建立连接
      ConnectionFactory factory = new ConnectionFactory();
      // 1.1.设置连接参数，分别是：主机名、端口号、vhost、用户名、密码
      factory.setHost("127.0.0.1");
      factory.setPort(5672);
      factory.setVirtualHost("/");
      factory.setUsername("user");
      factory.setPassword("12345678");
      // 1.2.建立连接
      Connection connection = factory.newConnection();

      // 2.创建通道Channel
      Channel channel = connection.createChannel();

      // 3.创建队列
      String queueName = "simple.queue";
      channel.queueDeclare(queueName, false, false, false, null);

      // 4.发送消息
      String message = "hello, rabbitmq!";
      channel.basicPublish("", queueName, null, message.getBytes());
      System.out.println("发送消息成功：【" + message + "】");

      // 5.关闭通道和连接
      channel.close();
      connection.close();
    }
}
```

基本消息队列的消息接收流程：
5. 建立connection
6. 创建channel
7. 利用channel声明队列
8. 定义consumer的消费行为handleDelivery()
9. 利用channel将消费者与队列绑定

```java
//不使用AMQP的服务接收者代码
public class ConsumerTest {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.建立连接
        ConnectionFactory factory = new ConnectionFactory();
        // 1.1.设置连接参数，分别是：主机名、端口号、vhost、用户名、密码
        factory.setHost("192.168.150.101");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("itcast");
        factory.setPassword("123321");
        // 1.2.建立连接
        Connection connection = factory.newConnection();

        // 2.创建通道Channel
        Channel channel = connection.createChannel();

        // 3.创建队列
        String queueName = "simple.queue";
        channel.queueDeclare(queueName, false, false, false, null);

        // 4.订阅消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 5.处理消息
                String message = new String(body);
                System.out.println("接收到消息：【" + message + "】");
            }
        });
        System.out.println("等待接收消息。。。。");
    }
}
```

## SpringAMQP

`AMQP(Advanced Message Queuing Protocol)`:是用于在应用程序或之间传递业务消息的开放标准。该协议与语言和平台无关，更符合微服务中独立性的要求。

`Spring AMQP`:对AMQP协议的具体实现。提供了模板来发送和接收消息。包含两部分，其中`spring-amqp`是基础抽象，`spring-rabbit`是底层的默认实现。

特征：
- 侦听器容器，用于异步处理入站消息
- 用于发送和接收消息的RabbitTemplate
- RabbitAdmin用于自动声明队列，交换和绑定

## 常见消息模型

### 基本消息队列

- publisher：消息发布者，将消息发送到队列
- queue：消息队列，负责接收并缓存消息
- consumer：订阅队列，处理队列中的消息

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220616223952.png)

- 父工程引入AMQP依赖

```xml
<dependencies>
    <!--父工程中引入AMQP工程-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
</dependencies>
```

- 服务发布者

```yaml
spring:
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    virtual-host: /
    username: user
    password: 12345678
```

```java
/**
 * @author luguosong
 * @date 2022/6/18
 */
@SpringBootApplication
public class RabbitmqHelloPublisher {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqHelloPublisher.class,args);
    }
}
```

```java
/**
 * 发布消息
 * @author luguosong
 * @date 2022/6/18
 */

@SpringBootTest
public class RabbitMQTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void test(){
        String queueName="simple.queue";
        String message="hello,Spring amqp!";
        amqpTemplate.convertAndSend(queueName,message);
    }
}
```

- 服务接收者

```yaml
spring:
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    virtual-host: /
    username: user
    password: 12345678
```

```java
/**
 * @author luguosong
 * @date 2022/6/18
 */
@SpringBootApplication
public class RabbitmqHelloConsumer {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqHelloConsumer.class,args);
    }
}
```

```java
/**
 * @author luguosong
 * @date 2022/6/18
 */
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listen(String msg){
        System.out.println(msg);
    }
}
```

### 工作消息队列(Work queues)

一个队列绑定多个消费者，提高消息处理效率

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220620230123.png)

### 广播发布订阅

### 路由发布订阅

### 主题发布订阅


# 分布式事务

## 事务ACID原则

- 原子性：事务中的所有操作，要么全部成功，要么全部失败
- 一致性：要保证数据库内部完整性约束、声明性约束
- 隔离性：对同一资源操作的事务不能同时发生
- 持久性：对数据库做的一切修改将永久保存，不管是否出现故障

## CAP定理

分布式系统中有三个指标：
- `Consistency（一致性）`：用户访问分布式系统中的任意节点，得到的数据必须一致

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220621172933.png)

- `Availability（可用性）`：用户访问集群中的任意健康节点，必须能得到响应，而不是超时或拒绝

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220621174054.png)

- `Partition tolerance（分区容错性）`：因为网络故障或其它原因导致分布式系统中的部分节点与其它节点失去连接，形成独立分区。在集群出现分区时，整个系统也要持续对外提供服务。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220621174428.png)

分布式系统`无法同时满足`这三个指标

在分布式系统中分区不可避免，为了能让整个系统被正常使用，`分区容错性（P）`一定要实现。因此需要抉择是满足`一致性`还是`可用性`

- `一致性+分区容错性（CP）`：当节点出现分区，访问断开的节点会拒绝访问，等节点恢复数据同步后再允许其访问（不满足可用性）
- `可用性+分区容错性（AP）`：当节点出现分区，允许访问断开连接的节点，此时导致该节点的数据与其它节点数据不一致（不满足一致性）

## BASE理论

- `Basically Available(基本可用)`：分布式系统在出现故障时，允许损失部分可用性，即保证核心可用。
- `Soft State(软状态)`：在一定时间内，允许出现中间状态，比如临时的不一致状态。
- `Eventual心y Consistent(最终一致性)`：虽然无法保证强一致性，但是在软状态结束后，最终达到数据一致。

## 分布式事务解决思路

- `AP模式`：各子事务分别执行和提交，允许出现结果不一致，然后采用弥补措施恢复数据即可，实现`最终一致`。
- `CP模式`：各个子事务执行后互相等待，`同时提交，同时回滚`，`达成强一致`。但事务等待过程中，处于弱可用状态。

# 分布式事务-seata

## 角色

- `TC(Transaction Coordinator)-事务协调者`：维护全局和分支事务的状态，协调全局事务提交或回滚。
- `TM(Transaction Manager)-事务管理器`：定义全局事务的范围、开始全局事务、提交或回滚全局事务。
- `RM(Resource Manager)-资源管理器`：管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220621182221.png)

## 解决方案

- `XA模式`：强一致性分阶段事务模式，牺牲了一定的可用性，无业务侵入
- `TCC模式`：最终一致的分阶段事务模式，有业务侵入
- `AT模式`：最终一致的分阶段事务模式，无业务侵入，也是Seata的默认模式
- `SAGA模式`：长事务模式，有业务侵入


## TC服务器搭建

- 本地安装

直接下载压缩包解压

- docker安装

```shell
docker pull seataio/seata-server
```

```shell
$ docker run --name seata-server \
        -p 8091:8091 \
        -e SEATA_CONFIG_NAME=file:/root/seata-config/registry \
        -v 本机路径:/root/seata-config  \
        seataio/seata-server
```

- 配置注册中心和配置中心

```yaml
seata:
  config:
    # support: nacos, consul, apollo, zk, etcd3
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      namespace:
      group: SEATA_GROUP
      username:nacos
      password:nacos
      ##if use MSE Nacos with auth, mutex with username/password attribute
      #access-key: ""
      #secret-key: ""
      data-id: seataServer.properties
  registry:
    # support: nacos, eureka, redis, zk, consul, etcd3, sofa
    type: nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      group: DEFAULT_GROUP
      namespace:
      cluster: default
      username:nacos
      password:nacos
      ##if use MSE Nacos with auth, mutex with username/password attribute
      #access-key: ""
      #secret-key: ""
```

## XA模式

### 概述

`XA规范`是`X/Open组织`定义的分布式事务处理（DTP，Distributed Transaction Processing）标准，XA规范描述了全局的 TM与局部的RM之间的接口，几乎所有主流的数据库都对XA规范提供了支持。

标准的XA模式步骤：

- 阶段一:准备阶段
  - `事务协调者(TC)`向`事务参与者（RM）`发起准备请求
  - `事务参与者（RM）`执行完业务先不提交，将执行的结果告诉`事务协调者(TC)`
- 阶段二
  - `事务协调者(TC)`如果收到的结果全部成功，则通知所有`事务参与者（RM）`提交事务
  - `事务协调者(TC)`如果收到失败的结果，通知所有`事务参与者（RM）`回滚事务

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220622170228.png)

seata实现XA的具体步骤:

- 阶段一
  - `事务管理器(TM)`向`事务协调者(TC)`开启全局事务
  - `事务管理器(TM)`调用`事务参与者（RM）`
  - `事务参与者（RM）`向`事务协调者(TC)`进行分支事务的注册
  - `事务参与者（RM）`开始执行业务，但执行完不提交，将执行的结果告诉`事务协调者(TC)`
- 阶段二
  - `事务管理器(TM)`通知`事务协调者(TC)`
  - `事务协调者(TC)`检查分支事务状态，如果全部成功全部提交，如果失败进行回滚

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220622093726.png)

- 优点
  - 具备强一致性
  - 数据库本身就支持XA规范，用起来比较简单
- 缺点
  - 事务执行过程中占用数据库锁，会产生资源的浪费
  - 依赖与数据库底层事务实现，如果数据库不支持事务则无法使用

## AT模式

- 阶段一
  - `事务管理器(TM)`向`事务协调者(TC)`开启全局事务
  - `事务管理器(TM)`调用`事务参与者（RM）`
  - `事务参与者（RM）`向`事务协调者(TC)`进行分支事务的注册
  - `事务参与者（RM）`开始执行业务，但执行完不提交，将执行的结果告诉`事务协调者(TC)`
- 阶段二
  - `事务管理器(TM)`通知`事务协调者(TC)`
  - `事务协调者(TC)`检查分支事务状态，如果全部成功全部提交，如果失败进行回滚



