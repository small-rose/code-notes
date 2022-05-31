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

# Spring相关API

## ApplicationContext接口

代表应用上下文，可以`通过其实例获得Spring容器中的Bean对象`

- 实现类
    - ClassPathXmlApplicationContext:从类的根路径加载配置文件
    - FileSystemXmlApplicationContext：从磁盘加载配置文件（文件全路径）
    - AnnotationConfigApplicationContext：使用注解配置容器对象时，需要使用此类来创建Spring容器。它用来读取注解

### getBean方法

简单工厂设计模式，通过传入参数获取指定对象。使得对象的创建和使用分离。

# IOC入门案例

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
public class UserImpl implements User {
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
        User user = (User) app.getBean("user");
        user.hello();
    }
}
```

# IOC XML开发

## Bean标签

用于配置对象由`Spring`来创建

默认情况下调用类中的无参构造函数，如果没有无参构造则不能创建成功

### 基本属性

- `id属性`:Bean实例在Spring容器中的唯一标识
- `class属性`:Bean的全限定名称
- `scope属性`:对象的作用范围
    - `singleton`:默认值，单例模式,对象会在配置文件被加载时创建
    - `prototype`:原型模式，对象会在被获取时创建
    - `request`:WEB项目中，Spring创建一个Bean的对象，将对象存入到request域中
    - `session`:WEB项目中，Spring创建一个Bean的对象，将对象存入到session域中
    - `global session`:WEB项目中，应用在Portlet环境，如果没有Portlet环境那么globalSession相当于session
- `init-method属性`：指定类中的初始化方法名称
- `destroy-method属性`：指定类中销毁方法名称

### 实例化方式

- 方式一：通过待实例化对象的无参构造

```xml

<bean id="userDao" class="com.luguosong.UserDaoImpl"/>
```

- 方式二：工厂的静态方法返回Bean实例

```java
public class StaticFactoryBean {
    public static UserDao createUserDao() {
        return new UserDaoImpl();
    }
}
```

```xml

<bean id="userDao" class="com.luguoosng.StaticFactoryBean"
      factory-method="createUserDao"/>
```

- 方式三：

```java
public class DynamicFactoryBean {
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }
}
```

```xml

<bean id="factoryBean" class="com.luguoosng.DynamicFactoryBean"/>
<bean id="userDao" factory-bean="factoryBean" factory-method="createUserDao"/>
```

### 依赖注入(Dependency Injection)

通过Spring来维护类之间的依赖关系。

通过Spring进行依赖注入，用户在使用对象时，无需再手动注入对象（没必要手动实例化字段对象）

- 构造方法方式依赖注入
- set方法方式依赖注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userDao" class="com.luguosong.dao.impl.UserDaoImpl"></bean>

    <!--通过setter方法依赖注入-->
    <bean id="userService1" class="com.luguosong.service.impl.UserServiceImpl">
        <!--name表示set方法的名字-->
        <!--ref指向要注入的bean对象-->
        <property name="userDao" ref="userDao"/>
        <property name="number" value="18"/>
        <property name="list">
            <list>
                <value>aaa</value>
                <value>bbb</value>
                <value>ccc</value>
            </list>
        </property>
        <property name="map">
            <map>
                <entry key="key1" value="value1"></entry>
                <entry key="key2" value="value2"></entry>
                <entry key="key3" value="value3"></entry>
            </map>
        </property>
        <property name="properties">
            <props>
                <prop key="key1">111</prop>
                <prop key="key2">222</prop>
                <prop key="key3">333</prop>
            </props>
        </property>
    </bean>

    <!--使用p命名空间进行setter方法依赖注入-->
    <!--使用p命名空间没办法注入集合-->
    <bean id="userService2"
          class="com.luguosong.service.impl.UserServiceImpl"
          p:userDao-ref="userDao"
          p:number="19"></bean>

    <!--通过有参构造进行依赖注入-->
    <bean id="userService3" class="com.luguosong.service.impl.UserServiceImpl">
        <constructor-arg name="userDao" ref="userDao"></constructor-arg>
        <constructor-arg name="number" value="18"></constructor-arg>
        <constructor-arg name="list">
            <array>
                <value>111</value>
                <value>222</value>
                <value>333</value>
            </array>
        </constructor-arg>
        <constructor-arg name="map">
            <map>
                <entry key="key1" value="111"></entry>
                <entry key="key2" value="222"></entry>
                <entry key="key3" value="333"></entry>
            </map>
        </constructor-arg>
        <constructor-arg name="properties">
            <props>
                <prop key="key1">111</prop>
                <prop key="key2">222</prop>
                <prop key="key3">333</prop>
            </props>
        </constructor-arg>
    </bean>
</beans>
```

## import标签

导入其它Spring配置文件

# Spring集成Junit

- 导入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.20</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>5.3.20</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.8.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

- 测试类编写

```java
/**
 * @author luguosong
 * @date 2022/5/29 20:59
 */
@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class AnnotationTest {
    @Autowired
    private User user;

    @Test
    public void test() {
        user.hello();
    }
}
```

# IOC注解开发

简化配置，提高开发效率

## 组件扫描

指定哪个包及其子包下的Bean需要进行扫描以便识别使用注解配置的类、字段和方法。

```xml

<context:component-scan base-package="com.luguosong"/>
```

## 将类标记为Bean

- `@Component`
- `@Controller`:使用在web层类上
- `@Service`:使用在service层类上
- `@Repository`:使用在dao层类上
- `@Bean`:将方法的返回值标记为Bean，一般使用于第三方非自定义的类

## 依赖注入

- `@Autowired`:根据类型进行依赖注入
- `@Autowired+@Qualifier`:根据名称进行依赖注入
- `@Resource`:相当于@Autowired+@Qualifier，根据名称进行依赖注入
- `@Value`:注入普通属性

## @Scope

相当于bean标签的scope属性,表面Bean是单例还是原型

## 初始化方法和销毁方法

- `@PostConstruct`：Bean的初始化方法
- `@PreDestroy`：Bean的销毁方法

## @Configuration

指定当前类是一个 Spring 配置类，当创建容器时会从该类上加载注解

## @ComponentScan

指定 Spring 在初始化容器时要扫描的包。

## @PropertySource

用于加载.properties 文件中的配置

## @Import

用于导入其他配置类

# 面向切面编程(Aspect Oriented Programming)

`面向切面编程（AOP）`：程序运行期间，在不修改源码的情况下对方法进行功能增强

Spring框架监控切入点方法的执行。一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的对应位置，将通知对应的功能织入，完成完整的代码逻辑运行

Spring会根据目标类是否实现了接口来决定采用JDK代理还是cglib代理

# 动态代理

## JDK动态代理

JDK动态代理生成的`代理对象`和`真实对象`实现相同的接口，`代理对象`和`真实对象`之间时兄弟关系

- 为什么JDK动态代理是基于接口，而不是继承被代理类进行方法增强？
    - `代理设计模式`就是这种基于接口的结构
    - 因为Proxy生成的代理类是继承了Proxy类的，因为Java是单继承，没办法再继承被代理的类，只能实现被代理类实现的接口
- 为什么JDK生成的动态代理类要继承Proxy类？
    - 首先，动态代理类并没有使用Proxy中的什么属性或者方法（虽然使用了InvocationHandler对象，但是也可以在生成class之初就将InvocationHandler放入到代理类中）
    - Proxy类统一维护InvocationHandler接口对象

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202205311711348.png)

简单代码演示：

```java
/**
 * @author 10545
 * @date 2022/5/30 22:32
 */
public interface User {
    public void hello();

    public void hello2();
}

/**
 * @author 10545
 * @date 2022/5/30 23:09
 */
public class UserImpl implements User {
    @Override
    public void hello() {
        System.out.println("hello");
    }

    @Override
    public void hello2() {
        System.out.println("hello2");
    }
}

/**
 * @author 10545
 * @date 2022/5/30 23:10
 */
public class ProxyDemo {

    public static void main(String[] args) {

        UserImpl user = new UserImpl();

        User userProxy = (User) Proxy.newProxyInstance(
                user.getClass().getClassLoader(),
                user.getClass().getInterfaces(),
                (Object proxy, Method method, Object[] args1) -> {
                    //只对hello方法进行增强，hello2方法不进行增强
                    if ("hello".equals(method.getName())) {
                        System.out.println("前置增强");
                    }
                    Object invoke = method.invoke(user, args1);
                    if ("hello".equals(method.getName())) {
                        System.out.println("后置增强");
                    }
                    return invoke;
                }
        );
        userProxy.hello();
        userProxy.hello2();
    }
}
```

## cglib动态代理

如果需要被动态代理的类`没有实现接口`，则无法使用JDK的`Proxy类`生成代理对象。这个时候需要使用cglib生成代理对象

cglib生成的`代理对象`是`真实对象`的子类，`真实对象`和`代理对象`是父子关系

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202205311712390.png)

```java
/**
 * 被代理的类，采用cglib，没有接口
 *
 * @author luguosong
 * @date 2022/5/31 17:30
 */
public class User {
    public void hello() {
        System.out.println("hello");
    }
}

/**
 * @author luguosong
 * @date 2022/5/31 17:45
 */
public class Demo {
    public static void main(String[] args) {
        User user = new User();

        //创建增强器
        Enhancer enhancer = new Enhancer();
        //设置父类（也就是要被代理的类）
        enhancer.setSuperclass(user.getClass());
        //设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("前置增强");
                Object invoke = method.invoke(user, objects);
                System.out.println("后置增强");
                return invoke;
            }
        });
        //生成代理对象，动态生成的代理类是被代理类的子类
        User proxy = (User) enhancer.create();
        //执行被增强后的方法
        proxy.hello();
    }
}
```

# AOP相关概念

- `Target(目标对象)`：代理的目标对象，对应代理设计模式中的`RealSubject（真实主题角色）`
- `Proxy(代理)`：`Target(目标对象)`被AOP织入增强后，就产生一个结果代理类。对应代理设计模式中的`Proxy（代理主题角色）`
- `JoinPoint(连接点)`：`Target(目标对象)`中可以被增强的方法
- `PointCut(切入点)`：`Target(目标对象)`中真正被增强的方法
- `Advice(通知/增强)`：对方法增强的内容
- `Aspect(切面)`:`切入点`和`通知`的结合，被增强后的方法
- `Weaving(织入)`：`切入点`和`通知`结合的过程

# AOP XML开发



