---
layout: default
title: Spring
nav_order: 5
---

# 为什么用Spring

- 方便解耦，简化开发
- AOP编程支持
- 声明式事务的支持
- 方便程序的测试
- 方便集成各种优秀的框架
- 降低JavaEE API的使用难度

# IOC

## 入门案例

- 导入Spring开发的基本包坐标

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
    </dependencies>
```

- 编写接口和实现类

```java
/**
 * @author luguosong
 * @date 2022/5/27 10:11
 */
public interface User {
    public void hello();
}

/**
 * @author luguosong
 * @date 2022/5/27 10:11
 */
public class UserImpl implements User{
    @Override
    public void hello() {
        System.out.println("hello");
    }
}
```

- 创建Spring核心配置文件,在Spring配置文件中配置实现类

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="com.luguoosng.UserImpl"></bean>

</beans>
```

- 使用Spring的API获取Bean实例

```java
/**
 * @author luguosong
 * @date 2022/5/27 10:12
 */
public class Demo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User)app.getBean("user");
        user.hello();
    }
}
```

## Bean标签

