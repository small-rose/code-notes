---
layout: docs
title: Spring Security
nav_order: 400
parent: Spring相关
---

# 版本说明

Spring Boot：`2.3.2.RELEASE`

Spring Security 版本：`5.3.3.RELEASE`

# 参考资料

[官方文档](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/)

[中文文档](http://docs.jcohy.com/docs/spring-security/5.3.0.RELEASE/html5/zh-cn)

参考书：[深入浅出Spring Security](https://book.douban.com/subject/35383505/)

[博客教程](http://www.javaboy.org/springsecurity/)

# 概述

核心功能：

- 认证
  - 表单认证
  - OAuth2.0认证
  - SAML2.0认证
  - CAS认证
  - RememberMe自动认证
  - JAAS认证
  - OpenID去中心化认证
  - Pre-Authentication Scenarios认证
  - X509认证
  - HTTP Basic认证
  - HTTP Digest认证
- 授权
  - 基于URL的请求授权
  - 支持方法访问授权
  - 支持SpEL访问控制
  - 支持域对象安全（ACL）
  - 支持动态权限配置
  - 支持RBAC权限模型

# 入门案例

## 步骤

spring boot下使用spring security。只需要引入依赖，即可使用。

- 创建`spring-security-hello`工程

- pom文件引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <version>2.3.2.RELEASE</version>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>spring-security-hello</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
  </properties>

  <dependencies>
    <!--spring security-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
  </dependencies>

</project>
```

- 编写启动类

```java
package cn.com.lgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luguosong
 * @date 2021/11/9 9:30
 */
@SpringBootApplication
public class SpringSecurityHello {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityHello.class, args);
  }
}
```

- 编写测试Controller

```java
package cn.com.lgs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luguosong
 * @date 2021/11/9 9:31
 */
@RestController
public class HelloController {
  @GetMapping("/hello")
  public String hello() {
    return "hello spring security";
  }
}
```

## 运行测试

- 访问`http://localhost:8080/hello`,页面重定向到`http://localhost:8080/login`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211113143737.png)

- 默认用户名为`user`,默认密码在控制台打印。输入用户名密码可以访问Controller

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211113143843.png)

## 自动配置

我们只是引入了依赖，其实spring boot帮我们做了很多默认配置，如下：

- 启用 Spring Security 的默认配置,该配置会创建 springSecurityFilterChain ,然后加入到 Servlet 过滤器链中. 此bean负责应用程序内的所有安全性(
  保护应用程序URL,验证提交的用户名和密码,重定向到登录表单等) .
- 创建一个 `UserDetailsService` bean,其中包含用户名 user 和随机生成的密码,该密码将记录到控制台.
- 针对每个请求,使用Servlet容器向 `springSecurityFilterChain` 注册过滤器.

使用Spring Security，其实就是将这些默认配置改成我们实际需要的配置。 
比如用户名密码默认是存储在内存中的，我们得改成从数据库中读取。 
比如默认提供了登录表单，我们需要修改成我们自己的表单页面。等等

# 整体架构

搞清整体架构很重要，否则后面容易乱掉

## 认证

用户的认证信息主要由`Authentication`的实现类来保存：

```java
public interface Authentication extends Principal, Serializable {
  //用来获取用户的权限
  Collection<? extends GrantedAuthority> getAuthorities();

  //用来获取用户凭证，一般来说就是密码。
  Object getCredentials();

  //用来获取用户携带的详细信息，可能是当前请求之类等。
  Object getDetails();

  //用来获取当前用户，例如是一个用户名或者一个用户对象。
  Object getPrincipal();

  //用来获取当前用户，例如是一个用户名或者一个用户对象。
  boolean isAuthenticated();

  void setAuthenticated(boolean isAuthenticated);
}
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151436083.png)

Spring Security中的认证工作主要由`AuthenticationManager`接口来负责:

```java
public interface AuthenticationManager {
  //返回Authentication，表示认证成功。
  //抛出AuthenticationException异常，表示用户输入了无效的凭证。
  //返回null，表示不能断定。
  Authentication authenticate(Authentication authentication)
          throws AuthenticationException;
}
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151439798.png)

`AuthenticationManager`最主要的实现类是`ProviderManager`。
`ProviderManager`管理众多的`AuthenticationProvider`实例:

```java
private List<AuthenticationProvider> providers = Collections.emptyList();
```

`AuthenticationProvider`的实现类处理不同的身份验证：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151433201.png)

总结一下：
- Authentication：保存认证信息
- AuthenticationManager：负责认证
- ProviderManager：认证具体实现
- AuthenticationProvider：它的实现类处理各种类型的认证


## 授权

`AccessDecisionManager`接口决定访问是否允许被通过：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/AccessDecisionManager.png)

`AccessDecisionManager`的实现类管理着一批`AccessDecisionVoter`。
`AccessDecisionVoter`是一个投票器，检查用户是否具备应有的角色。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151430644.png)

在Spring Security中，用户请求一个资源（通常是一个网络接口或者一个Java方法）所需要的角色会被封装成一个`ConfigAttribute`对象，
在`ConfigAttribute`中只有一个`getAttribute`方法，该方法返回一个String字符串，就是角色的名称。
一般来说，角色名称都带有一个ROLE_前缀，投票器`AccessDecisionVoter`所做的事情，
其实就是比较用户所具备的角色和请求某个资源所需的`ConfigAttribute`之间的关系。

## 过滤器

Spring Security中的认证和授权基于Servlet过滤器完成的，
下面是Spring Security中常见的过滤器。第三列默认加载表示不进行任何配置，默认加载的过滤器：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211114164051.png)

可以看到，`UsernamePasswordAuthenticationFilter`过滤器默认是开着的，这对应这前面入门案例的表单登录

这些过滤器按照既定优先级排列，最终形成一个过滤器链。过滤器并不是直接放在Web原生过滤器链中的，
而是通过`FilterChainProxy`进行管理。`FilterChainProxy`通过Spring框架提供的`DelegatingFilterProxy`整合到Web原生过滤器链中。

当系统中存在多个不同的认证体系时，会使用多个多滤器链。
`FilterChainProxy`先匹配上哪个过滤器链，就使用哪个过滤器链处理。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211114195501.png)


## 登录数据保存

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211114210007.png)

登录成功后，登录成功的用户信息保存在`SecurityContextHolder`中。
登录完毕后，`SecurityContextHolder`中的数据会保存到Session中。
`SecurityContextHolder`会被清空

以后每次再发送请求，会先从Session中获取登录数据，保存到`SecurityContextHolder`中。
在请求结束时，`SecurityContextHolder`中的数据会被保存到Session,然后清空`SecurityContextHolder`。

## 获取用户

Spring Security中定义了`UserDetails`规范用户定义。

`UserDetailsService`接口负责提供用户数据：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151427376.png)

Spring Boot下默认使用`InMemoryUserDetailsManager`提供用户。

一般开发者自定义`UserDetailsService`的实现提供用户。

# 组件

## ObjectPostProcessor

`ObjectPostProcessor`是一个对象后置处理器。当一个对象创建成功后，如果还有一些额外的事情需要补充，
可以通过`ObjectPostProcessor`来处理。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151027456.png)

- `AutowireBeanFactoryObjectPostProcessor`：由于Spring Security中大量采用了Java配置，
许多过滤器都是直接new出来的，这些直接new出来的对象并不会自动注入到Spring容器中。
Spring Security这样做的本意是为了简化配置，但是却带来了另外一个问题就是，
大量new出来的对象需要我们手动注册到Spring容器中去。`AutowireBeanFactoryObjectPostProcessor`对象所承担的就是这件事，
一个对象new出来之后，只要调用`AutowireBeanFactoryObjectPostProcessor#postProcess`方法，
就可以成功注入到Spring容器中，它的实现原理就是通过调用Spring容器中的`AutowireCapableBeanFactory`对象将一个new出来的对象注入到Spring容器中去。
- `CompositeObjectPostProcessor`：一个对象可以有一个后置处理器，
开发者也可以自定义多个对象后置处理器。`CompositeObjectPostProcessor`是一个组合的对象后置处理器，
它里边维护了一个`List`集合，集合中存放了某一个对象的所有后置处理器，当需要执行对象的后置处理器时，
会遍历集合中的所有`ObjectPostProcessor`实例，分别调用实例的`postProcess`方法进行对象后置处理。
在Spring Security框架中，最终使用的对象后置处理器其实就是`CompositeObjectPostProcessor`，
它里边的集合默认只有一个对象，就是`AutowireBeanFactoryObjectPostProcessor`。

在Spring Security中，开发者可以灵活地配置项目中需要哪些Spring Security过滤器，
一旦选定过滤器之后，每一个过滤器都会有一个对应的配置器，
叫作`xxxConfigurer`（例如`CorsConfigurer`、`CsrfConfigurer`等），过滤器都是在`xxxConfigurer`中new出来的，
然后在`postProcess`方法中处理一遍，就将这些过滤器注入到Spring容器中了。

这是对象后置处理器`ObjectPostProcessor`的主要作用。

## SecurityFilterChain

Spring Security中的过滤器链。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151053703.png)

- SecurityFilterChain
  - matches方法：判断request请求是否应该被当前过滤器链处理
  - getFilters方法：返回过滤器集合

`SecurityFilterChain`只有一个默认的实现类就是`DefaultSecurityFilterChain`。

- DefaultSecurityFilterChain
  - requestMatcher字段：请求匹配器
  - filters字段：过滤器集合
  - matches方法：判断request请求是否应该被当前过滤器链处理
  - getFilters方法：返回过滤器集合

一个`Spring Security`项目中，`SecurityFilterChain`的实例可能会有多个


## SecurityBuilder

Spring Security中所有需要构建的对象都可以通过`SecurityBuilder`来实现，
`默认的过滤器链`、`代理过滤器`、`AuthenticationManager`等，都可以通过`SecurityBuilder`来构建。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151310149.png)

### SecurityBuilder

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151406184.png)

`SecurityBuilder`中只有一个build方法，就是对象构建方法。build方法的返回值，就是具体构建的对象泛型O，
也就是说不同的`SecurityBuilder`将来会构建出不同的对象。

### HttpSecurityBuilder

用来构建`HttpSecurity`对象。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151445400.png)

- `HttpSecurityBuilder`对象本身在定义时就有一个泛型，这个泛型是`HttpSecurityBuilder`的子类，
由于默认情况下`HttpSecurityBuilder`的实现类只有一个`HttpSecurity`，所以可以暂且把接口中的H都当成`HttpSecurity`来理解。
- `HttpSecurityBuilder`继承自`SecurityBuilder`接口，同时也指定了`SecurityBuilder`中的泛型为`DefaultSecurityFilterChain`，
也就是说，`HttpSecurityBuilder`最终想要构建的对象是`DefaultSecurityFilterChain`。
- `getConfigurer方法`用来获取一个配置器，所谓的配置器就是`xxxConfigurer`。
- `removeConfigurer方法`用来移除一个配置器（相当于从Spring Security过滤器链中移除一个过滤器）。
- `setSharedObject`/`getSharedObject`这两个方法用来设置或者获取一个可以在多个配置器之间共享的对象。
- `authenticationProvider方法`可以用来配置一个认证器`AuthenticationProvider`。
- `userDetailsService方法`可以用来配置一个数据源`UserDetailsService`。
- `addFilterAfter`/`addFilterBefore`方法表示在某一个过滤器之后或者之前添加一个自定义的过滤器。
- `addFilter`方法可以添加一个过滤器，这个过滤器必须是Spring Security框架提供的过滤器的一个实例或者其扩展，
添加完成后，会自动进行过滤器的排序。

### AbstractSecurityBuilder

实现了SecurityBuilder接口，并对build做了完善，确保只build一次。作用是确保目标对象只被构建一次。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151423700.png)

- 首先声明了`building`变量，可以确保即使在多线程环境下，配置类也只构建一次。
- 对build方法进行重写，并且设置为final，这样在AbstractSecurityBuilder的子类中将不能再次重写build方法。
在build方法内部，通过building变量来控制配置类只构建一次，具体的构建工作则交给doBuild方法去完成。
- `getObject方法`用来返回构建的对象。
- `doBuild方法`则是具体的构建方法，该方法在`AbstractSecurityBuilder`中是一个抽象方法，具体的实现在其子类中。

### AbstractConfiguredSecurityBuilder

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151447044.png)

- `buildState枚举类`
  - `UNBUILT`：配置类构建前。
  - `INITIALIZING`：初始化中（初始化完成之前是这个状态）。
  - `CONFIGURING`：配置中（开始构建之前是这个状态）。
  - `BUILDING`：构建中。
  - `BUILT`：构建完成。
  - `isInitializing方法`表示是否正在初始化中
  - `isConfigured方法`表示是否已完成配置
- `configurers变量`：保存所有的配置类
  - `apply方法`:向`configurers`变量中添加配置类，具体的添加过程则是调用`add方法`
  - `add方法`:用来将所有的配置类保存到`configurers`中，在添加的过程中，
  如果`allowConfigurersOfSameType`变量为true，则表示允许相同类型的配置类存在，
  也就是List集合中可以存在多个相同类型的配置类。默认情况下，如果是`普通配置类`，
  `allowConfigurersOfSameType`是false，所以List集合中的配置类始终只有一个配置类；
  如果在`AuthenticationManagerBuilder`中设置`allowConfigurersOfSameType`为true，
  此时相同类型的配置类可以有多个
  - `getConfigurers(Class<C>)方法`:可以从configurers中返回某一个配置类对应的所有实例。
  - `getConfigurer方法`:也是获取配置类实例，但是只获取集合中第一项。
  - `getConfigurers方法`:是一个私有方法，主要是把所有的配置类实例放到一个集合中返回。
    在配置类初始化和配置的时候，会调用到该方法。
  - `removeConfigurers方法`:可以从configurers中移除某一个配置类对应的所有实例，
  并返回被移除掉的配置类实例集合。
  - `removeConfigurer方法`:可以从configurers中移除某一个配置类对应的所有配置类实例，
  并返回被移除掉的配置类实例中的第一项。

```java
@Override
protected final O doBuild() throws Exception {
	synchronized (configurers) {
		buildState = BuildState.INITIALIZING;
		beforeInit();
		init();
		buildState = BuildState.CONFIGURING;
		beforeConfigure();
		configure();
		buildState = BuildState.BUILDING;
		O result = performBuild();
		buildState = BuildState.BUILT;
		return result;
	}
}
```

- `doBuild方法`:核心的构建方法
  - 在`doBuild`方法中，一边更新构建状态，一边执行构建方法。构建方法中，
  beforeInit是一个空的初始化方法，如果需要在初始化之前做一些准备工作，
  可以通过重写该方法实现。
  - `init方法`是所有配置类的初始化方法，在该方法中，遍历所有的配置类，
  并调用其init方法完成初始化操作。
  - `beforeConfigure方法`可以在configure方法执行之前做一些准备操作。该方法默认也是一个空方法。
  - `configure方法`用来完成所有配置类的配置，在configure方法中，遍历所有的配置类，
  分别调用其configure方法完成配置
  - `performBuild方法`用来做最终的构建操作，前面的准备工作完成后，
  最后在performBuild方法中完成构建，这是一个抽象方法，具体的实现则在不同的配置类中。

### ProviderManagerBuilder

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151552217.png)

继承自`SecurityBuilder`接口，并制定了构建的对象是`AuthenticationManager`

### AuthenticationManagerBuilder

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151550142.png)

`AuthenticationManagerBuilder`用来构建`AuthenticationManager`对象，它继承自`AbstractConfiguredSecurityBuilder`，
并且实现了`ProviderManagerBuilder`接口。

- 首先在`AuthenticationManagerBuilder`的构造方法中，调用了父类的构造方法，注意第二个参数传递了true，
表示允许相同类型的配置类同时存在（结合`AbstractConfiguredSecurityBuilder`的源码来理解）。
- `parentAuthenticationManager方法`用来给一个`AuthenticationManager`设置parent。
- `inMemoryAuthentication方法`用来配置基于内存的数据源，该方法会自动创建`InMemoryUserDetailsManagerConfigurer`配置类，
并最终将该配置类添加到父类的configurers变量中。由于设置了允许相同类型的配置类同时存在，
因此`inMemoryAuthentication`方法可以反复调用多次。
- `jdbcAuthentication`以及`userDetailsService`方法与`inMemoryAuthentication`方法类似，也是用来配置数据源的，
这里不再赘述。
- `authenticationProvider方法`用来向`authenticationProviders`集合中添加`AuthenticationProvider`对象，
一个`AuthenticationManager`实例中包含多个`AuthenticationProvider`实例，
那么多个`AuthenticationProvider`实例可以通过`authenticationProvider`方法进行添加。
- `performBuild方法`则执行具体的构建工作，常用的`AuthenticationManager`实例就是`ProviderManager`，
所以这里创建`ProviderManager`对象，并且配置`authenticationProviders`和`parentAuthenticationManager`对象，
`ProviderManager`对象创建成功之后，再去对象后置处理器中处理一遍再返回。

### HttpSecurity

用来构建一条过滤器链，并反映到代码上，也就是构建一个`DefaultSecurityFilterChain`对象。

`HttpSecurity`中通过收集各种各样的`xxxConfigurer`，将Spring Security过滤器对应的配置类收集起来，
并保存到父类`AbstractConfiguredSecurityBuilder`的`configurers`变量中，在后续的构建过程中，
再将这些`xxxConfigurer`构建为具体的Spring Security过滤器，同时添加到`HttpSecurity`的`filters`对象中。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151607942.png)


### WebSecurity

WebSecurity负责将HttpSecurity所构建的DefaultSecurityFilterChain对象（可能有多个），
以及其他一些需要忽略的请求，再次重新构建为一个FilterChainProxy对象，同时添加上HTTP防火墙。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151632642.png)



# 默认配置类

Spring Boot之所以能坐到零配置使用Spring Security,就是因为它提供了众多的自动化配置类

自动化配置类是`Spring Boot提供的`。而不是Spring Security自带的。
当使用单纯的Spring框架时，是不存在这个自动化配置类的

## UserDetailsServiceAutoConfiguration

配置类生效前提：
- 当前classpath下存在`AuthenticationManager`类
- 系统没有提供`AuthenticationManager`、`AuthenticationProvider`、`UserDetailsService`以及`ClientRegistrationRepository`实例。

由上可知，当自定义`UserDetailsService`接口的实现注入到Spring中。`UserDetailsServiceAutoConfiguration`自动配置将不再生效

配置功能：
- Spring Boot提供`SecurityProperties`属性配置类，其中提供默认的用户名和密码
- 提供一个`InMemoryUserDetailsManager`实例,让Spring Security从内存获取用户

<hb></hb>
```java
package org.springframework.boot.autoconfigure.security.servlet;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(AuthenticationManager.class)
@ConditionalOnBean(ObjectPostProcessor.class)
@ConditionalOnMissingBean(
		value = { AuthenticationManager.class, AuthenticationProvider.class, UserDetailsService.class },
		type = { "org.springframework.security.oauth2.jwt.JwtDecoder",
				"org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector" })
public class UserDetailsServiceAutoConfiguration {

	private static final String NOOP_PASSWORD_PREFIX = "{noop}";

	private static final Pattern PASSWORD_ALGORITHM_PATTERN = Pattern.compile("^\\{.+}.*$");

	private static final Log logger = LogFactory.getLog(UserDetailsServiceAutoConfiguration.class);

	@Bean
	@ConditionalOnMissingBean(
			type = "org.springframework.security.oauth2.client.registration.ClientRegistrationRepository")
	@Lazy
	public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties,
			ObjectProvider<PasswordEncoder> passwordEncoder) {
		SecurityProperties.User user = properties.getUser();
		List<String> roles = user.getRoles();
		return new InMemoryUserDetailsManager(
				User.withUsername(user.getName()).password(getOrDeducePassword(user, passwordEncoder.getIfAvailable()))
						.roles(StringUtils.toStringArray(roles)).build());
	}

	private String getOrDeducePassword(SecurityProperties.User user, PasswordEncoder encoder) {
		String password = user.getPassword();
		if (user.isPasswordGenerated()) {
			logger.info(String.format("%n%nUsing generated security password: %s%n", user.getPassword()));
		}
		if (encoder != null || PASSWORD_ALGORITHM_PATTERN.matcher(password).matches()) {
			return password;
		}
		return NOOP_PASSWORD_PREFIX + password;
	}

}
```
<he></he>

# 初始化流程分析


