---
layout: default
title: Servlet
nav_order: 20
parent: JavaWeb
---

# Web容器

{: .new-title}
> Web容器
> 
> 一个用Java写的程序，运行于JVM之上。用于存储管理`Servlet/JSP`对象

# Tomcat

## 安装

下载压缩包后直接解压

## 启动和关闭

{: .warning-title}
> 启动前提
> 
> 需要先配置`JAVA_HOME`环境变量

启动：双击`bin/startup.bat`

关闭：双击`bin/stop.bat`

## idea配置Tomcat

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221226211352.png)


# Servlet概述

{: .new-title}
> Servlet
> 
> Servlet是JavaEE规范之一，其实就是一个`接口`。我们需要定义Servlet类实现Servlet接口，
> 并由Web服务器运行Servlet

Servlet项目依赖坐标：

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
```

`<scope>provided</scope>`表示在编译环境和测试环境有效。运行环境则依赖于`Tomcat内部`的实现。

# Servlet接口方法

- `init`:初始化方法，在Servlet被创建时只执行一次
- `service`：提供服务方法
- `destroy`：销毁方法
- `getServletConfig`：获取ServletConfig对象
- `getServletInfo`：获取Servlet信息

# Servlet体系

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/HttpServlet.png)

# Servlet路径配置

- 注解方式

```java
@WebServlet(name = "helloServlet", value = "/hello-servlet")
```

- xml方式

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20230103095317.png)

# 请求

## 请求对象体系

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20230103105229.png)

## 转发

- 浏览器地址栏不发生变化
- 只能转发到当前服务器的内部资源
- 一次请求，可以在转发的资源间使用request共享数据

## 示例

```java
/**
 * 请求
 *
 * @author luguosong
 * @date 2023/1/3
 */
@WebServlet(name = "request", value = "/request")
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求信息
        getRequestInfo(req, resp);

        //GET方法从请求行获取参数
        System.out.println("GET方式从请求行中获取请求参数：" + req.getQueryString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //POST方式通过流传输，设置编码解决中文乱码
        req.setCharacterEncoding("UTF-8");

        //获取请求信息
        getRequestInfo(req, resp);

        //POST方法从请求体获取参数
        System.out.println("POST方式从请求体中获取请求参数：" + req.getReader().readLine());
    }

    private void getRequestInfo(HttpServletRequest request, HttpServletResponse response) {
        //请求行信息获取
        System.out.println("=====请求行=====");
        System.out.println("请求方式：" + request.getMethod());
        System.out.println("项目路径：" + request.getContextPath());
        System.out.println("统一资源定位符:" + request.getRequestURL());
        System.out.println("统一资源标识符：" + request.getRequestURI());

        //获取请求头
        System.out.println("=====请求头=====");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            System.out.println(element + ":" + request.getHeader(element));
        }

        //通用方式获取请求参数
        System.out.println("=====获取请求参数=====");
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map);
        for (String key : map.keySet()) {
            System.out.println(key + ":" + request.getParameter(key));
        }

        //存储数据到request域中
        request.setAttribute("id", 12);

        //请求转发
        try {
            request.getRequestDispatcher("/response").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

# 响应

## 响应对象体系

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20230104101912.png)

## 重定向

- 浏览器地址发生变化
- 可以重定向到任意位置的资源
- 两次请求，不能使用request共享数据

## 示例

```java
/**
 * 请求
 *
 * @author luguosong
 * @date 2023/1/3
 */
@WebServlet(name = "request", value = "/request")
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求信息
        getRequestInfo(req, resp);

        //GET方法从请求行获取参数
        System.out.println("GET方式从请求行中获取请求参数：" + req.getQueryString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //POST方式通过流传输，设置编码解决中文乱码
        req.setCharacterEncoding("UTF-8");

        //获取请求信息
        getRequestInfo(req, resp);

        //POST方法从请求体获取参数
        System.out.println("POST方式从请求体中获取请求参数：" + req.getReader().readLine());
    }

    private void getRequestInfo(HttpServletRequest request, HttpServletResponse response) {
        //请求行信息获取
        System.out.println("=====请求行=====");
        System.out.println("请求方式：" + request.getMethod());
        System.out.println("项目路径：" + request.getContextPath());
        System.out.println("统一资源定位符:" + request.getRequestURL());
        System.out.println("统一资源标识符：" + request.getRequestURI());

        //获取请求头
        System.out.println("=====请求头=====");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            System.out.println(element + ":" + request.getHeader(element));
        }

        //通用方式获取请求参数
        System.out.println("=====获取请求参数=====");
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map);
        for (String key : map.keySet()) {
            System.out.println(key + ":" + request.getParameter(key));
        }

        //存储数据到request域中
        request.setAttribute("id", 12);

        //请求转发
        try {
            request.getRequestDispatcher("/response").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

