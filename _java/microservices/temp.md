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

# 服务间远程调用-Feign

## 概述

Feign是一个声明式的http客户端

## RestTemplate存在的问题

- 代码可读性差，编程体验不统一
- 参数复杂的URL难以维护

## 开发步骤

- 引入依赖

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608101104.png)

- 在启动类中添加`@EnableFeignClients`注解开启Feign功能

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

- `方式二（引用）`：将FeignClienti抽取为独立模块，并且把接口有关的POO、默认的Feign配置都放到这个模块中，提供给所有消费者使用

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220608111506.png)

需要在服务消费者启动的@EnableFeignClients注解中配置扫描包或者配置clients
