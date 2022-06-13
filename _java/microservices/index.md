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

# 服务间远程调用-Feign

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



