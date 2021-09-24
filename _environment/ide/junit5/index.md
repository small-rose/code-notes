---
layout: docs
title: JUnit5 
nav_order: 100 
parent: ide和环境配置
---
# 概述

**JUnit 5 = *JUnit Platform* + *JUnit Jupiter* + *JUnit Vintage***

支持Java版本：Java8+

## JUnit Platform

* **Group ID**: `org.junit.platform`
* **Version**: `1.8.0`
* **Artifact IDs**:

|         **Artifact ID**         | **

|                描述**                |                                                                                                                                                  |
| :-----------------------------------: | ------------------------------------------------------------------------------------------------------------------------------------------------ |
|      `junit-platform-commons`      | JUnit Platform的通用 API 和支持实用程序。 任何用 @API(status = INTERNAL) 注释的 API 都仅供在 JUnit 框架本身内使用。 不支持外部各方使用内部 API！ |
|      `junit-platform-console`      | 支持从控制台发现和执行JUnit Platform上的测试                                                                                                    |
| `junit-platform-console-standalone` | Maven Central 中的 junit-platform-console-standalone 目录下提供了一个包含所有依赖项的可执行 JAR。 有关详细信息，请参阅控制台启动器。             |
|       `junit-platform-engine`       | 用于test engine的公共API.有关详细信息，请参阅插入自己的测试引擎。                                                                                |
|        `junit-platform-jfr`        | 为JUnit Platform上的 Java Flight Recorder 事件提供一个 LauncherDiscoveryListener 和 TestExecutionListener。                                      |
|      `junit-platform-launcher`      | 用于配置和启动测试计划的公共 API (通常由 ide 和构建工具使用)。详细信息请参阅 JUnit Platform启动器 API。                                          |
|     `junit-platform-reporting`     | 生成测试报告的 TestExecutionListener 实现 — 通常由 IDE 和构建工具使用。 有关详细信息，请参阅 JUnit Platform报告。                               |
|       `junit-platform-runner`       | 用于在JUnit 4环境中，在JUnit Platform上执行test和test suite的运行程序。有关详细信息，请参阅使用JUnit4运行JUnit平台。                             |
|       `junit-platform-suite`       | 它在构建工具(如 Gradle 和 Maven)中为简化的依赖管理过渡性地引入对 JUnit-Platform-Suite-api 和 JUnit-Platform-Suite-engine 的依赖。                |
|     `junit-platform-suite-api`     | 在JUnit Platform上配置test suite的注解。 由JUnitPlatform runner转换器支持，也可能由第三方 TestEngine实现。                                       |
|   `junit-platform-suite-commons`   | 在JUnit Platform上执行test suites支持实用程序                                                                                                    |
|    `junit-platform-suite-engine`    | 在JUnit Platform上执行test suites，只在运行时需要。                                                                                              |
|      `junit-platform-testkit`      | 支持为给定的 TestEngine 执行测试计划，然后通过 fluent API 访问结果以验证预期的结果。                                                             |

## JUnit Jupiter

* **Group ID**: `org.junit.jupiter`
* **Version**: `5.8.0`
* **Artifact IDs**:

| **Artifact

| ID**                               | 描述                                                                                                                                                                |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `junit-jupiter`                  | JUnit Jupiter 聚合器工件，可传递地引入对 junit-jupiter-api、junit-jupiter-params 和 junit-jupiter-engine 的依赖，以简化构建工具（如 Gradle 和 Maven）中的依赖管理。 |
| `junit-jupiter-api`              | JUnit Jupiter API                                                                                                                                                   |
| `junit-jupiter-engine`           | JUnit Jupiter 测试引擎实现; 只在运行时需要。                                                                                                                        |
| `junit-jupiter-params`           | 在JUnit Jupiter中支持参数化测试                                                                                                                                     |
| `junit-jupiter-migrationsupport` | 从JUnit4到JUnit Jupiter的迁移支持，仅在运行选定的JUnit规则时需要                                                                                                    |

## JUnit Vintage

* **Group ID**: `org.junit.vintage`
* **Version**: `5.8.0`
* **Artifact IDs**:

| **Artifact

| ID**                     | 描述                                                                                                           |
| ------------------------ | -------------------------------------------------------------------------------------------------------------- |
| `junit-vintage-engine` | JUnit Vintage测试引擎实现，容许在新的JUnit Platform上运行vintage JUnit测试，例如用JUnit3或者JUnit4风格写的测试 |

## 依赖关系图

![](https://junit.org/junit5/docs/current/user-guide/images/component-diagram.svg)

# 开始JUnit 5

## Maven依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.example</groupId>
	<artifactId>junit5-jupiter-starter-maven</artifactId>
	<version>1.0-SNAPSHOT</version>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
	</properties>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.junit</groupId>
				<artifactId>junit-bom</artifactId>
				<version>5.8.0</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<dependencies>
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.1</version>
			</plugin>
			<plugin>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.22.2</version>
			</plugin>
		</plugins>
	</build>

</project>
```

## 编写应用程序代码

1. 创建`Calculator`类

```java
package cn.com.lgs;

import java.util.stream.DoubleStream;

/**
 * @author luguosong
 * @date 2021/9/22 11:02
 */
public class Calculator {
    static double add(double... operands) {
        return DoubleStream.of(operands)
                .sum();
    }

    static double multiply(double... operands) {
        return DoubleStream.of(operands)
                .reduce(1, (a, b) -> a * b);
    }
}
```

## 创建测试

现在让我们创建一个测试。 测试是一段代码，其功能是检查另一段代码是否正确运行。 为了进行检查，它调用测试方法并将结果与预定义的预期结果进行比较。 例如，预期结果可以是特定的返回值或异常。

1. 将光标放在 Calculator 类声明上，然后按 Alt + Enter。或者，右键单击它并选择“显示上下文操作”。从菜单中选择`创建测试`。
   
   ![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109221339447.png)
3. 选择我们要测试的两个方法

   ![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109221341753.png)
4. 成功创建测试类

```java
package cn.com.lgs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luguosong
 * @date 2021/9/22 13:40
 */
class CalculatorTest {

    @Test
    void add() {
        assertEquals(4, Calculator.add(2, 2));
    }

    @Test
    void multiply() {
        assertAll(() -> assertEquals(4, Calculator.multiply(2, 2)),
                () -> assertEquals(-4, Calculator.multiply(2, -2)),
                () -> assertEquals(4, Calculator.multiply(-2, -2)),
                () -> assertEquals(0, Calculator.multiply(1, 0)));
    }
}
```

## 运行测试并查看结果

1. 要运行单个测试，点击测试方法前的`三角按钮`，选择`Run`。若要在测试类中运行所有测试，则点击测试类前的`三角按钮`，选择`Run`
   
   ![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109221359841.png)

2. 可以在`运行`窗口中查看测试结果

   ![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202109221400433.png)


