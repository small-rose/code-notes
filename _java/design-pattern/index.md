---
layout: default
title: 设计模式（Java语言描述）
nav_order: 4
---
# 概述

`模式`是在特定环境下人们解决某类重复出现问题的一套成功或有效的解决方案

`设计模式`是在特定环境下为了解决某一通用软件设计问题提供的一套定制的解决方案，该方案
描述了对象和类之间的相互作用。

## 参考资料

[设计模式学习网站](https://refactoringguru.cn/design-patterns)

[史上最全设计模式导学](https://blog.csdn.net/lovelion/article/details/17517213)


## 设计模式基本要素

- 模式名称
- 别名
- 模式的分类
  - 模式所属类别
- 模式概述
  - 模式的动机与意图，模式的定义
- 模式结构与实现
  - 描述设计模式的组成成分，以及这些组成成分之间的相互关系、各自的职责和协作方式
- 实例代码
- 模式拓展
  - 该模式的一些改进，与其它模式联用
- 效果
  - 模式优缺点分析
  - 结合设计原则进行分析
- 模式的适用性
  - 什么情况下可以使用该设计模式
- 模式应用
  - 在已有系统中该模式的使用


## 分类

| 范围/目的          | 创建型模式                                               | 结构型模式                                                                                             | 行为型模式                                                                                                                         |
| ------------------ | -------------------------------------------------------- | ------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------- |
| **类模式**   | 工厂方法模式                                             | （类）适配器模式                                                                                       | 解释器模式<br />模板方法模式                                                                                                       |
| **对象模式** | 抽象工厂模式<br />建造者模式<br />原型模式<br />单例模式 | （对象）适配器模式<br />桥接模式<br />组合模式<br />装饰模式<br />外观模式<br />享元模式<br />代理模式 | 职责链模式<br />命令模式<br />迭代器模式<br />中介模式<br />备忘录模式<br />观察者模式<br />状态模式<br />策略模式<br />访问者模式 |

# 面向对象设计原则

- 衡量软件质量
  - 可维护性（Maintainability）
  - 可复用性（Reusability）

## 单一职责原则

一个对象应该只包含单一的职责，并且该职责被完整地封装在一个类中。

## 开闭原则

软件实体应该对拓展开发，对修改关闭。

软件实体应尽量在`不修改原有代码`的情况下进行`拓展`。

## 里氏代换原则

所有引用基类的地方必须能透明地使用其子类的对象。

父类对象可以用子类对象替换，子类对象不能用父类对象替换。

`里氏代换原则`是实现`开闭原则`的重要方式之一。程序中尽量使用父类类型来对对象进行定义，而在运行时再确定其子类类型，
用子类对象来替代父类对象。

## 依赖倒转原则

高层模块不应该依赖低层模块，它们都应该依赖抽象。抽象不应该依赖于细节，细节应该依赖于抽象。

针对接口编程，不要针对实现编程。换句话说，就是尽量不要使用具体的实现类，而是使用它对应的接口或者所继承的抽象类。

面向接口编程时，具体类需要通过依赖注入（Dependence Injection）的方式注入到对象中，
一般有以下三种注入方式：

- 构造注入：通过构造函数传入具体类的对象
- 设值注入：通过Setter方法传入具体类的对象
- 接口注入：方法在定义时使用的是抽象类型，运行时再传入具体类的对象

## 接口隔离原则

客户端不应该依赖那些它不需要的接口

将大接口中的方法根据职责不同放在不同小接口中，以确保每个接口使用起来都较为方便。

同时接口的粒度也不能太小，否则会导致系统接口泛滥，不利于维护。

## 合成复用原则

优先使用对象组合，而不是通过继承来达到复用的目的。

继承破坏了基类的封装性，将基类的实现细节暴露给了子类。

相比于继承，组合可以使系统更加灵活，降低类与类之间的耦合度。

## 迪米特法则

每一个软件单位对其它单位都只是最少的知识，而且局限于那些与本单位密切相关的软件单位。

不要和`陌生人`说话，只与你的直接`朋友`通讯。在迪米特法则中，对于一个对象，其朋友包括以下几类：

- 当前对象本身（this）
- 以参数形式传入到当前对象方法中的对象。
- 当前对象的成员对象
- 如果当前对象的成员对象是一个集合，那么集合中的元素也都是朋友
- 当前对象所创建的对象

## 小结

一般情况下`开闭原则`，`里氏代换原则`和`依赖倒置原则`会同时出现，其中：

- 开闭原则是目的
- 里氏代换原则是基础
- 依赖导致原则是手段

# 简单工厂模式（Simple Factory）

## 模式分类

创建型模式

对象模式

## 模式概述

定义一个工厂类，它可以根据参数的不同返回不同类的实例，被创建的实例通常具有共同的父类。

## 模式结构与实现

![](img/simple-factory-pattern/structure.svg)

- Factory(工厂角色)
- Product(抽象产品角色)
- ConcreteProduct(具体产品角色)

## 实例代码

> 某公司需要开发一套图表库，该表标可以为应用系统提供多种不同外观的图表。
> 如柱状图（HistogramChart）、饼状图（PieChart）、折线图（LineChart）等。
> 通过设置不同的参数即可得到不同类型的图表，而且可以较为方便地对图表库进行拓展，以便增加新的图表库。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202202101737486.png)

- 抽象图表接口，充当抽象产品类：

```java
package cn.com.lgs.simple_factory_pattern;

/**
 * 抽象图表接口，充当抽象产品类
 *
 * @author luguosong
 * @date 2022/2/2 9:56
 */
public interface Chart {
  public void display();
}
```

- 柱状图类，充当具体产品类：

```java
package cn.com.lgs.simple_factory_pattern;

/**
 * 柱状图类
 *
 * @author luguosong
 * @date 2022/2/2 9:58
 */
public class HistogramChart implements Chart {

  public HistogramChart() {
    System.out.println("创建柱状图！");
  }

  @Override
  public void display() {
    System.out.println("显示柱状图！");
  }
}
```

- 饼状图类，充当具体产品类：

```java
package cn.com.lgs.simple_factory_pattern;

/**
 * 饼状图类
 *
 * @author luguosong
 * @date 2022/2/2 10:00
 */
public class PieChart implements Chart {

  public PieChart() {
    System.out.println("创建饼状图！");
  }

  @Override
  public void display() {
    System.out.println("显示饼状图！");
  }
}
```

- 折线图类，充当具体产品类：

```java
package cn.com.lgs.simple_factory_pattern;

/**
 * 折线图类
 *
 * @author luguosong
 * @date 2022/2/2 10:02
 */
public class LineChart implements Chart {

  public LineChart() {
    System.out.println("创建折线图！");
  }

  @Override
  public void display() {
    System.out.println("显示折线图！");
  }
}
```

- 图表工厂类，充当工厂类。

```java
package cn.com.lgs.simple_factory_pattern;

/**
 * @author luguosong
 * @date 2022/2/2 10:04
 */
public class ChartFactory {
  public static Chart getChart(String type) {
    Chart chart = null;
    if (type.equalsIgnoreCase("histogram")) {
      chart = new HistogramChart();
      System.out.println("初始化设置柱状图！");
    } else if (type.equalsIgnoreCase("pie")) {
      chart = new PieChart();
      System.out.println("初始化设置饼状图！");
    } else if (type.equalsIgnoreCase("line")) {
      chart = new LineChart();
      System.out.println("初始化设置折线图！");
    }
    return chart;
  }
}
```

- 测试类：

```java
package cn.com.lgs.simple_factory_pattern;

/**
 * @author luguosong
 * @date 2022/2/2 10:10
 */
public class Demo {
  public static void main(String[] args) {
    Chart chart;
    chart = ChartFactory.getChart("histogram"); //通过静态工厂方法创建产品
    chart.display();
  }
}
```

- 运行结果

```shell
创建柱状图！
初始化设置柱状图！
显示柱状图！
```

## 模式拓展

- 客户端调用简单工厂类，可以将参数放到配置文件中，满足开闭原则。
- 为了简化`简单工厂`,可以将`静态工厂方法`移至`抽象产品类`中，不提供具体的工厂类。 客户端可以通过调用产品父类的静态工厂方法，根据参数不同创建不同类型的产品子类对象。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202202100925118.png)

## 效果

- 注意点
  - 如果一个类很简单，不存在太多变化，其构造过程也很简单， 此时就无需为其提供工厂类。直接在使用之前实例化就行，避免工厂泛滥。


- 优点
  - 将`创建对象`和`使用对象`职责分离，降低了耦合性。
  - 将创建对象的代码集中到一处，而不是散播的到处都是。防止出现代码重复、创建蔓延的问题。
  - 客户端无须知道所创建的具体产品类的`复杂`类名，只需要知道产品所对应的参数即可，减少记忆量。

- 缺点
  - 工厂类集中了所有产品的创建逻辑，职责过重，一旦不能正常工作，整个系统都要受到影响。
  - 新增了`工厂类`,增加了系统的复杂度和理解难度
  - 拓展困难，一旦增加新产品就不得不修改工厂逻辑。产品类型较多时工厂类逻辑会过于复杂。
  - 使用静态工厂方法，无法形成基于继承的等级结构

## 模式的适用性

- 工厂类负责创建对象较少，不会造成工厂类业务逻辑过于复杂
- 客户端只知道传入工厂类的参数，对如何创建对象并不关心

# 工厂方法模式（Factory Method）

## 别名

虚构造器（Virtual Constructor）

工厂模式(Factory Pattern)

多态工厂模式(Polymorphic Factory Pattern)

## 模式分类

创建型模式

类模式

## 模式概述

定义一个用于创建对象的接口，让子类决定将哪一个类实例化。工厂方法模式让一个类的实例化延迟到其子类

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202202101035560.png)

- `Product（抽象产品）`：它是定义产品的接口，是工厂方法模式所创建对象的超类型，也就是产品对象的公共父类。
- `ConcreteProduct（具体产品）`：它实现了抽象产品接口，某种类型的具体产品由专门的具体工厂创建，具体工厂和具体产品之间一一对应。
- `Factory（抽象工厂）`：在抽象工厂类中，声明了工厂方法(Factory Method)，用于返回一个产品。抽象工厂是工厂方法模式的核心，所有创建对象的工厂类都必须实现该接口。
- `ConcreteFactory（具体工厂）`：它是抽象工厂类的子类，实现了抽象工厂中定义的工厂方法，并可由客户端调用，返回一个具体产品类的实例。
 
## 实例代码

> 某软件公司欲开发一个系统运行日志记录器(Logger)，该记录器可以通过多种途径保存系统的运行日志，
> 如通过文件记录或数据库记录，用户可以通过修改配置文件灵活地更换日志记录方式。
> 在设计各类日志记录器时，Sunny公司的开发人员发现需要对日志记录器进行一些初始化工作，
> 初始化参数的设置过程较为复杂，而且某些参数的设置有严格的先后次序，否则可能会发生记录失败。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202202101701637.png)

```java
/**
 * 日志记录接口，充当抽象产品
 *
 * @author luguosong
 * @date 2022/2/10 17:04
 */
public interface Logger {
  public void writeLog();
}

/**
 * 数据库记录日志器，充当具体产品
 *
 * @author luguosong
 * @date 2022/2/10 17:08
 */
public class DatabaseLogger implements Logger{
  @Override
  public void writeLog() {
    System.out.println("数据库日志记录。");
  }
}

/**
 * 文件日志记录器，充当具体产品角色
 *
 * @author luguosong
 * @date 2022/2/10 17:11
 */
public class FileLogger implements Logger{
  @Override
  public void writeLog() {
    System.out.println("文件记录日志");
  }
}

/**
 * 日志记录器工厂接口，充当抽象工厂角色
 *
 * @author luguosong
 * @date 2022/2/10 17:17
 */
public interface LoggerFactory {
  public Logger createLogger();  //抽象工厂方法
}

/**
 * 数据库日志记录器工厂类，充当具体工厂
 *
 * @author luguosong
 * @date 2022/2/10 17:20
 */
public class DatabaseLoggerFactory implements LoggerFactory {
  @Override
  public Logger createLogger() {
    //连接数据库
    //...

    //创建数据库日志记录器对象
    Logger logger = new DatabaseLogger();

    //初始化数据库日志记录器
    //...

    return logger;
  }
}

/**
 * 文件日志记录器工厂类，充当具体工厂
 *
 * @author luguosong
 * @date 2022/2/10 17:27
 */
public class FileLoggerFactory implements LoggerFactory {
  @Override
  public Logger createLogger() {
    //创建文件日志记录器对象
    Logger logger = new FileLogger();

    //创建文件
    //...

    return logger;
  }
}

/**
 * @author luguosong
 * @date 2022/2/10 17:35
 */
public class Demo {
  public static void main(String[] args) {
    LoggerFactory factory;
    Logger logger;
    factory = new FileLoggerFactory();
    logger = factory.createLogger();
    logger.writeLog();
  }
}
```

- 测试结果

```shell
文件记录日志
```

## 模式拓展

- 可以通过`反射`和`配置文件`改进客户端不满足开闭原则的问题
- 工厂方法可以进行重载

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202202221552381.png)

- 可以在抽象工厂中对业务方法进行调用，客户端直接通过抽象工厂调用业务方法，而不需要调用工厂方法获取产品对象。

## 效果

- 优点
  - 改进了`简单工厂`新增具体产品需要修改工厂违背`开闭原则`的弊端。

- 缺点
  - 增加新产品时，既要新增产品类，又要新增具体工厂类，增加了系统的复杂度。
  - 客户端都使用抽象层进行定义，增加了系统的抽象性和理解难度。

## 模式的适用性

- 客户端不需要知道具体产品的类名，只需要知道对应的工厂类。
- 抽象工厂类通过其子类来指定创建哪个对象

# 抽象工厂模式（Abstract Factory）

## 别名

Kit

## 模式分类

创建型模式

对象模式

## 模式概述

提供一个创建一系列相关或者相互依赖对象的接口，而无须指定它们具体的类。

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220222212140.png)

- `AbstractFactory(抽象工厂)`：声明了一组用于创建一族产品的方法，每一个方法对应一种产品
- `ConcreteFactory(具体工厂)`：实现在抽象工厂中声明的创建产品的方法
- `AbstractProduct(抽象产品)`：每种产品声明接口
- `ConcreteProduct(具体产品)`：实现抽象产品接口中声明的业务方法

## 实现代码

> 某软件公司要开发一套界面皮肤库。在使用时可以通过菜单来选择皮肤。不同的皮肤库提供不同
> 视觉效果的按钮、文本框、组合框等界面元素

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220222215500.png)

```java
/**
 * 按钮接口，充当抽象产品
 *
 * @author 10545
 * @date 2022/2/22 22:10
 */
public interface Button {
    public void display();
}

/**
 * Spring按钮类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:29
 */
public class SpringButton implements Button{
  @Override
  public void display() {
    System.out.println("显示浅绿色按钮");
  }
}

/**
 * Summer按钮类，充当具体产品
 * 
 * @author 10545
 * @date 2022/2/22 22:32
 */
public class SummerButton implements Button{
  @Override
  public void display() {
    System.out.println("显示浅蓝色按钮");
  }
}

/**
 * 文本框接口，充当抽象产品
 *
 * @author 10545
 * @date 2022/2/22 22:41
 */
public interface TextField {
  public void display();
}

/**
 * Spring文本框类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:46
 */
public class SpringTextField implements TextField{
  @Override
  public void display() {
    System.out.println("显示绿色边框文本框！");
  }
}

/**
 * Summer文本框类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:48
 */
public class SummerTextField implements TextField{
  @Override
  public void display() {
    System.out.println("显示蓝色边框文本框");
  }
}

/**
 * 组合框接口，充当抽象产品
 *
 * @author 10545
 * @date 2022/2/22 22:54
 */
public interface ComboBox {
  public void display();
}

/**
 * Spring组合框类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:55
 */
public class SpringComboBox implements ComboBox{
  @Override
  public void display() {
    System.out.println("显示绿色边框组合框");
  }
}

/**
 * Summer组合框类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 23:14
 */
public class SummerComboBox implements ComboBox{
  @Override
  public void display() {
    System.out.println("显示蓝色边框组合框");
  }
}

/**
 * 界面皮肤工厂接口，充当抽象工厂接口
 *
 * @author 10545
 * @date 2022/2/23 21:27
 */
public interface SkinFactory {
  public Button createButton();
  public TextField createTextField();
  public ComboBox createComboBox();
}

/**
 * Spring皮肤工厂，充当具体工厂
 *
 * @author 10545
 * @date 2022/2/23 21:35
 */
public class SpringSkinFactory implements SkinFactory{
  @Override
  public Button createButton() {
    return new SpringButton();
  }

  @Override
  public TextField createTextField() {
    return new SpringTextField();
  }

  @Override
  public ComboBox createComboBox() {
    return new SpringComboBox();
  }
}

/**
 * Summer皮肤工厂，充当具体工厂
 *
 * @author 10545
 * @date 2022/2/23 21:53
 */
public class SummerSkinFactory implements SkinFactory{
  @Override
  public Button createButton() {
    return new SummerButton();
  }

  @Override
  public TextField createTextField() {
    return new SummerTextField();
  }

  @Override
  public ComboBox createComboBox() {
    return new SummerComboBox();
  }
}





```

## 模式拓展

## 效果

- 优点
  - 抽象工厂相比工厂方法同时生产多个产品（创建`产品族`）

## 模式适用性









