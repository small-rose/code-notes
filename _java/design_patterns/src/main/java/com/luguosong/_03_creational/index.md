---
layout: default
title: 创建型模式（Creational Patterns）
nav_order: 3
parent: 设计模式（Design Pattern）
---

# 创建型模式概述

将软件模块中对象的创建和使用分离，对用户隐藏了对象的创建细节

# 简单工厂模式（Simple Factory）

## 模式分类

创建型模式

对象模式

## 模式概述

定义一个工厂类，它可以根据参数的不同返回不同类的实例，被创建的实例通常具有共同的父类。

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/structure.svg)

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

```text
创建柱状图！
初始化设置柱状图！
显示柱状图！
```

## 模式拓展

- 客户端调用简单工厂类，可以将参数放到配置文件中，满足开闭原则。
- 为了简化 `简单工厂`,可以将 `静态工厂方法`移至 `抽象产品类`中，不提供具体的工厂类。 客户端可以通过调用产品父类的静态工厂方法，根据参数不同创建不同类型的产品子类对象。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202202100925118.png)

## 效果

- 注意点

    - 如果一个类很简单，不存在太多变化，其构造过程也很简单， 此时就无需为其提供工厂类。直接在使用之前实例化就行，避免工厂泛滥。
- 优点

    - 将 `创建对象`和 `使用对象`职责分离，降低了耦合性。
    - 将创建对象的代码集中到一处，而不是散播的到处都是。防止出现代码重复、创建蔓延的问题。
    - 客户端无须知道所创建的具体产品类的 `复杂`类名，只需要知道产品所对应的参数即可，减少记忆量。
- 缺点

    - 工厂类集中了所有产品的创建逻辑，职责过重，一旦不能正常工作，整个系统都要受到影响。
    - 新增了 `工厂类`,增加了系统的复杂度和理解难度
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
public class DatabaseLogger implements Logger {
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
public class FileLogger implements Logger {
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

```text
文件记录日志
```

## 模式拓展

- 可以通过 `反射`和 `配置文件`改进客户端不满足开闭原则的问题
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

## 名词解释

`产品等级结构`：产品的继承结构

`产品族`：同一个工厂生产的不同产品

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

## 实例代码

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
public class SpringButton implements Button {
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
public class SummerButton implements Button {
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
public class SpringTextField implements TextField {
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
public class SummerTextField implements TextField {
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
public class SpringComboBox implements ComboBox {
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
public class SummerComboBox implements ComboBox {
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
public class SpringSkinFactory implements SkinFactory {
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
public class SummerSkinFactory implements SkinFactory {
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

/**
 * 从xml配置文件中提取具体工厂类的类名，并返回一个实例对象
 *
 * @author 10545
 * @date 2022/2/28 21:47
 */
public class XMLUtil {
    public static Object getBean() {
        try {
            //创建DOM对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc = builder.parse("_java/design-pattern/src/main/java/cn/com/lgs/abstract_factory_pattern/config.xml");

            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();

            //通过类名生成实例对象并将其返回
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * 客户端调用
 *
 * @author 10545
 * @date 2022/2/28 22:22
 */
public class Demo {
    public static void main(String[] args) {
        //使用抽象层定义
        SkinFactory factory;
        Button bt;
        TextField tf;
        ComboBox cb;
        //使用工具类创建工厂
        factory = (SkinFactory) XMLUtil.getBean();
        //工厂创建对象
        bt = factory.createButton();
        tf = factory.createTextField();
        cb = factory.createComboBox();

        //运行具体产品
        bt.display();
        tf.display();
        cb.display();
    }
}
```

客户端配置文件：

```xml
<?xml version="1.0" ?>
<config>
    <className>cn.com.lgs.abstract_factory_pattern.SpringSkinFactory</className>
</config>
```

运行结果：

```text
显示浅绿色按钮
显示绿色边框文本框！
显示绿色边框组合框
```

## 效果

- 优点
    - 抽象工厂相比工厂方法同时生产多个产品（创建 `产品族`）
    - 隔离了具体产品的生成，只需要改变具体工厂实例就可以改变整个软件系统的行为
    - 当一个产品族中多个对象被设计在一起工作，能保证客户端使用同一个产品族的对象。
    - 增加新的产品族很方便
- 缺点
    - 如果工厂中需要新增产品，需要先修改抽象工厂接口，再逐一修改具体工厂类，不满足开闭原则。
      换句话说，`增加产品族`很方便，但是 `增加产品等级`结构很麻烦。

## 模式适用性

- 用户无需关心对象的创建过程，将对象的创建和使用解耦
- 系统中有多于一个的产品族，而每次只使用其中某一个产品族。通过配置文件来改变产品族
- 属于同一个产品族的产品在一起使用
- 产品等级结构稳定，在设计完后不会向系统中增加新的产品等级结构说删除已有的产品等级结构

## 模式应用

Java语言的AWT(抽象窗口工具包)中使用了抽象工厂模式

# 生成器模式（Builder）

## 别名

建造者模式

## 模式分类

创建型模式

对象模式

## 模式概述

将一个复杂对象的构建与它的表示分离,使得同样的构建过程可以创建不同的表示。

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220302214849.png)

- `Builder(抽象建造者)`：为创建一个产品对象的各个部件指定抽象接口和返回复杂对象的方法。既可以是抽象类，也可以是接口。
- `ConcreteBuilder(具体建造者)`：实现 `Builder`接口
- `Product(产品)`：被构建的复杂对象
- `Director(指挥者)`：负责安排复杂对象的建造次序

## 实例代码

> 开发一款网络游戏，需要对游戏角色进行设计，而且随着该游戏的升级将不断增加新的角色。
> 不同类型的游戏角色，其性别、脸型、服装、发型等外部特性都有所差异，例如“天使”拥有美丽的面容和披肩的长发，
> 并身穿一袭白裙；而“恶魔”极其丑陋，留着光头并穿一件刺眼的黑衣。
> Sunny公司决定开发一个小工具来创建游戏角色，可以创建不同类型的角色并可以灵活增加新的角色。

```java
/**
 * 游戏角色类，充当复杂产品对象
 *
 * @author 10545
 * @date 2022/3/9 22:17
 */
public class Actor {
    private String type;  //角色类型
    private String sex;  //性别
    private String face;  //脸型
    private String costume;  //服饰
    private String hairstyle;  //发型

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getCostume() {
        return costume;
    }

    public void setCostume(String costume) {
        this.costume = costume;
    }

    public String getHairstyle() {
        return hairstyle;
    }

    public void setHairstyle(String hairstyle) {
        this.hairstyle = hairstyle;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "type='" + type + '\'' +
                ", sex='" + sex + '\'' +
                ", face='" + face + '\'' +
                ", costume='" + costume + '\'' +
                ", hairstyle='" + hairstyle + '\'' +
                '}';
    }
}

/**
 * 游戏角色生成器，充当抽象生成器
 *
 * @author 10545
 * @date 2022/3/9 22:22
 */
public abstract class ActorBuilder {
    protected Actor actor = new Actor();

    public abstract void buildType();

    public abstract void buildSex();

    public abstract void buildFace();

    public abstract void buildCostume();

    public abstract void buildHairstyle();

    public Actor createActor() {
        return actor;
    }
}

/**
 * 英雄角色生成器，充当具体生成器
 *
 * @author 10545
 * @date 2022/3/9 22:50
 */
public class HeroBuilder extends ActorBuilder {

    @Override
    public void buildType() {
        actor.setType("英雄");
    }

    @Override
    public void buildSex() {
        actor.setSex("男");
    }

    @Override
    public void buildFace() {
        actor.setFace("英俊");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("盔甲");
    }

    @Override
    public void buildHairstyle() {
        actor.setHairstyle("飘逸");
    }
}

/**
 * 英雄角色生成器，充当具体生成器
 *
 * @author 10545
 * @date 2022/3/9 22:50
 */
public class HeroBuilder extends ActorBuilder {

    @Override
    public void buildType() {
        actor.setType("英雄");
    }

    @Override
    public void buildSex() {
        actor.setSex("男");
    }

    @Override
    public void buildFace() {
        actor.setFace("英俊");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("盔甲");
    }

    @Override
    public void buildHairstyle() {
        actor.setHairstyle("飘逸");
    }
}

/**
 * 天使角色生成器，充当具体生成器
 *
 * @author 10545
 * @date 2022/3/9 23:01
 */
public class AngelBuilder extends ActorBuilder {
    @Override
    public void buildType() {
        actor.setType("天使");
    }

    @Override
    public void buildSex() {
        actor.setSex("女");
    }

    @Override
    public void buildFace() {
        actor.setFace("漂亮");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("白裙");
    }

    @Override
    public void buildHairstyle() {
        actor.setHairstyle("披肩长发");
    }
}

/**
 * 恶魔角色生成器，充当具体生成器
 *
 * @author luguosong
 * @date 2022/3/15 10:43
 */
public class DevilBuilder extends ActorBuilder {
    @Override
    public void buildType() {
        actor.setType("恶魔");
    }

    @Override
    public void buildSex() {
        actor.setSex("妖");
    }

    @Override
    public void buildFace() {
        actor.setFace("丑陋");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("黑衣");
    }

    @Override
    public void buildHairstyle() {
        actor.setHairstyle("光头");
    }
}

/**
 * 角色控制器，充当指挥官
 *
 * @author luguosong
 * @date 2022/3/15 11:02
 */
public class ActorController {
    public Actor construct(ActorBuilder ab) {
        Actor actor;
        ab.buildType();
        ab.buildSex();
        ab.buildFace();
        ab.buildCostume();
        ab.buildHairstyle();
        actor = ab.createActor();
        return actor;
    }
}

/**
 * 工具类，通过配置文件创建具体构造器
 *
 * @author luguosong
 * @date 2022/3/15 13:54
 */
public class XMLUtil {
    public static Object getBean() {
        try {
            //创建DOM文件对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("_java/design-pattern/src/main/java/cn/com/lgs/builder_pattern/config.xml"));

            //获取包含类名的文本节点
            NodeList n1 = doc.getElementsByTagName("className");
            Node classNode = n1.item(0).getFirstChild();
            String cName = classNode.getNodeValue();


            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * 客户端
 *
 * @author luguosong
 * @date 2022/3/15 16:39
 */
public class Demo {
    public static void main(String[] args) {
        //通过xml创建具体生成器对象
        ActorBuilder ab;
        ab = (ActorBuilder) XMLUtil.getBean();

        //创建指挥官，并通过指挥官创建对象的各个部件，最后返回对象
        ActorController ac = new ActorController();
        Actor actor;
        actor = ac.construct(ab);

        //打印对象
        System.out.println(actor);
    }
}
```

配置文件：

```xml
<?xml version="1.0" ?>
<config>
    <className>cn.com.lgs.builder_pattern.AngelBuilder</className>
</config>
```

运行结果：

```text
Actor{type='天使', sex='女', face='漂亮', costume='白裙', hairstyle='披肩长发'}
```

## 模式拓展

- 可以将 `指挥官`类与 `抽象生成器`类合并，直接由 `指挥官方法`返回最终的对象。
- 可以在抽象生成器中设置钩子函数，在指挥官类中通过调用钩子函数，对复杂产品的构建进行精细化控制，具体如下：

```java
/**
 * 游戏角色生成器，充当抽象生成器
 *
 * @author 10545
 * @date 2022/3/9 22:22
 */
public abstract class ActorBuilder {
    protected Actor actor = new Actor();

    public abstract void buildType();

    public abstract void buildSex();

    public abstract void buildFace();

    public abstract void buildCostume();

    public abstract void buildHairstyle();

    //钩子方法，判断是否为光头，默认返回false，不是光头
    public boolean isBareheaded() {
        return false;
    }

    public Actor createActor() {
        return actor;
    }
}

/**
 * 恶魔角色生成器，充当具体生成器
 *
 * @author luguosong
 * @date 2022/3/15 10:43
 */
public class DevilBuilder extends ActorBuilder {
    @Override
    public void buildType() {
        actor.setType("恶魔");
    }

    @Override
    public void buildSex() {
        actor.setSex("妖");
    }

    @Override
    public void buildFace() {
        actor.setFace("丑陋");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("黑衣");
    }

    @Override
    public void buildHairstyle() {
        actor.setHairstyle("光头");
    }

    //在发型为光头的具体生成器中覆写isBareheaded，使之返回true
    @Override
    public boolean isBareheaded() {
        return true;
    }
}

/**
 * 角色控制器，充当指挥官
 *
 * @author luguosong
 * @date 2022/3/15 11:02
 */
public class ActorController {
    public Actor construct(ActorBuilder ab) {
        Actor actor;
        ab.buildType();
        ab.buildSex();
        ab.buildFace();
        ab.buildCostume();
        //在指挥官类中通过调用钩子函数，判断是否需要构建头发。达到精细化控制的目的
        if (!ab.isBareheaded()) {
            ab.buildHairstyle();
        }
        actor = ab.createActor();
        return actor;
    }
}
```

## 效果

- 优点
    - 客户端不需要知道产品的内部组成细节，将产品本身与产品的创建过程解耦。使得相同的创建过程可以创建不同的产品对象
    - 增加或修改 `具体生成器`,不需要修改指挥官代码，满足开闭原则
    - 可以更加精细地控制产品地创建过程
- 缺点
    - 组成部分不同地产品，不适合使用生成器模式
    - 如果产品内部变化复杂，可能需要定义很多 `具体生成器`类来实现这种变化

## 模式适用性

- 需要生成的产品对象有复杂的内部结构，这些产品对象通常包含多个成员变量。
- 需要生成的产品对象的属性相互依赖，需要指定其生成顺序。
- 对象的创建过程独立于创建该对象的类。在建造者模式中通过引入了指挥者类，将创建过程封装在指挥者类中，而不在建造者类和客户类中。
- 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。

# 原型模式（Prototype）

## 模式分类

创建型模式

对象模式

## 模式概述

使用原型实例指定待创建对象的类型，并且通过复制这个原型来创建新的对象

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202203241610502.png)

- `Prototype（抽象原型类）`：它是声明克隆方法的接口，是所有具体原型类的公共父类，可以是抽象类也可以是接口，甚至还可以是具体实现类。
- `ConcretePrototype（具体原型类）`：它实现在抽象原型类中声明的克隆方法，在克隆方法中返回自己的一个克隆对象。
- `Client（客户类）`
  ：让一个原型对象克隆自身从而创建一个新的对象，在客户类中只需要直接实例化或通过工厂方法等方式创建一个原型对象，再通过调用该对象的克隆方法即可得到多个相同的对象。由于客户类针对抽象原型类Prototype编程，因此用户可以根据需要选择具体原型类，系统具有较好的可扩展性，增加或更换具体原型类都很方便。

克隆分类：

- `浅克隆`：`原型对象`和 `克隆对象`中的 `引用类型`成员变量不进行复制
- `深克隆`：`原型对象`和 `克隆对象`中的 `引用类型`成员变量进行复制

## 实例代码

> 某OA系统，可以快速创建相同或相似的周报，包括周报的附件

### 浅克隆实现

原型类通过覆写Object类的clone方法，同时实现Cloneable接口，实现浅克隆

```java
/**
 * 附件类
 *
 * @author 10545
 * @date 2022/3/24 21:50
 */
public class Attachment {
    private String name; //附件名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void download() {
        System.out.println("下载附件，文件名为：" + name);
    }
}

/**
 * 工作周报类，充当原型角色
 * <p>
 * 实现Cloneable接口表示这个类可以克隆
 *
 * @author 10545
 * @date 2022/3/24 21:59
 */
public class WeeklyLog implements Cloneable {
    private Attachment attachment;
    private String name;
    private String date;
    private String content;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected WeeklyLog clone() {
        try {
            return (WeeklyLog) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制");
            return null;
        }
    }
}

/**
 * 客户端
 *
 * @author 10545
 * @date 2022/3/24 22:08
 */
public class Demo {
    public static void main(String[] args) {
        WeeklyLog log_previous, log_new;
        //这里主要讨论原型模式，不考虑开闭原则，因此直接使用new
        log_previous = new WeeklyLog();
        Attachment attachment = new Attachment();
        log_previous.setAttachment(attachment);
        log_new = log_previous.clone();
        //==比较的是地址，因此不相同
        System.out.println("周报是否相同：" + (log_previous == log_new));
        //因为是软克隆，因此附件相同
        System.out.println("附件是否相同：" + (log_previous.getAttachment() == log_new.getAttachment()));
    }
}
```

### 深克隆实现

通过序列化，将对象写到一个流中，再从流中将其读出来，实现深度克隆

```java
/**
 * 附件类
 *
 * @author 10545
 * @date 2022/3/24 23:08
 */
public class Attachment implements Serializable {
    private String name; //附件名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void download() {
        System.out.println("下载附件，文件名为：" + name);
    }
}

/**
 * 工作周报类，充当原型角色
 * <p>
 * 通过流进行深度克隆
 *
 * @author 10545
 * @date 2022/3/24 21:59
 */
public class WeeklyLog implements Serializable {
    private Attachment attachment;
    private String name;
    private String date;
    private String content;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public WeeklyLog deepClone() throws IOException, ClassNotFoundException {
        //将对象写入流中
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);
        //将对象从流中取出
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (WeeklyLog) ois.readObject();
    }
}

/**
 * 客户端
 *
 * @author 10545
 * @date 2022/3/24 23:33
 */
public class Demo {
    public static void main(String[] args) {
        WeeklyLog log_previous, log_new = null;
        log_previous = new WeeklyLog();
        Attachment attachment = new Attachment();
        log_previous.setAttachment(attachment);
        try {
            log_new = log_previous.deepClone();
        } catch (Exception e) {
            System.out.println("克隆失败");
        }
        //比较周报
        System.out.println("周报是否相同：" + (log_previous == log_new));
        //比较附件
        System.out.println("附件是否相同：" + (log_previous.getAttachment() == log_new.getAttachment()));
    }
}
```

## 模式拓展

可以提供一个专门克隆对象的工厂。对多个 `抽象原型类`的 `多个子类`进行克隆。

这很像 `简单工厂模式`，只不过简单工厂模式是new对象，而这是克隆对象

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220325224717.png)

`原型管理器`实现代码如下：

```java
import java.util.*;

public class PrototypeManager {
    //使用Hashtable存储原型对象
    private Hashtable prototypeTable = new Hashtable();

    //构造添加两个默认具体原型类
    public PrototypeManager() {
        prototypeTable.put("A", new ConcretePrototypeA());
        prototypeTable.put("B", new ConcretePrototypeB());
    }

    //提供注入原型类的方法
    public void add(String key, Prototype prototype) {
        prototypeTable.put(key, prototype);
    }

    //通过克隆方法创建新对象
    public Prototype get(String key) {
        return (Prototype) prototypeTable.get(key).clone();
    }
}
```

我们可以将PrototypeManager设计为单例类，使用饿汉式单例实现， 确保系统中有且仅有一个PrototypeManager对象，有利于节省系统资源， 并可以更好地对原型管理器对象进行控制。

## 效果

- 优点
    - 当创建新对象比较复杂，通过原型模式可以简化创建过程，提高创建效率
    - 客户端可以针对抽象原型类进行编程，而将具体原型类写在配置文件中。拓展性好
    - 相比于 `工厂方法`,结构更加简单。不需要专门创建相关工厂方法或类
    - 可以用深克隆的方式保存对象的状态，以便在需要的时候使用，可以辅助实现撤销操作
- 缺点
    - 需要对每个类配置一个克隆方法，当类发生变化时，需要修改克隆方法，不满足开闭原则
    - 当对象之间存在多重嵌套时，为了实现 `深克隆`,每一层对象对应的类都必须支持深克隆（实现 `Serializable接口`）。实现起来比较麻烦。

## 模式适用性

- 创建对象成本较大时
- 系统需要保存对象的状态，而对象的状态变化很小
- 需要避免使用分层次的工厂类来创建分层次的对象

# 单例模式（Singleton）

## 模式分类

创建型模式

对象模式

## 模式概述

确保一个类只有一个实例，并提供一个全局访问点来访问这个唯一的实例。

## 模式结构与实例

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220328224553.png)

- `单例类`：在 `单例类`的内部创建它的唯一实例,并通过静态方法 `getInstance()`让客户端可以使用它的唯一实例。为了防止外部对单例类实例化，其构造函数要设置成 `private私有`的。

## 实例代码

> Sunny软件公司承接了一个服务器负载均衡(Load Balance)软件的开发工作，该软件运行在一台负载均衡服务器上，
> 可以将并发访问和数据流量分发到服务器集群中的多台设备上进行并发处理，提高系统的整体处理能力，缩短响应时间。
> 由于集群中的服务器需要动态删减，且客户端请求需要统一分发，因此需要确保负载均衡器的唯一性，
> 只能有一个负载均衡器来负责服务器的管理和请求的分发，否则将会带来服务器状态的不一致以及请求分配冲突等问题。
> 如何确保负载均衡器的唯一性是该软件成功的关键。

```java
/**
 * 负载均衡器类，充当单例角色
 *
 * @author 10545
 * @date 2022/3/28 23:28
 */
public class LoadBalancer {
    //私有静态成员变量，存储唯一实例
    private static LoadBalancer instance = null;

    //服务器集合
    private List serverList = null;

    //私有构造
    private LoadBalancer() {
        serverList = new ArrayList();
    }

    //公有静态成员方法，返回唯一实例
    public static LoadBalancer getLoadBalancer() {
        if (instance == null) {
            instance = new LoadBalancer();
        }
        return instance;
    }

    /**
     * 添加服务
     *
     * @param server
     */
    public void addServer(String server) {
        serverList.add(server);
    }

    /**
     * 删除服务
     *
     * @param server
     */
    public void removeServer(String server) {
        serverList.remove(server);
    }

    /**
     * 随机获取服务器
     *
     * @return
     */
    public String getServer() {
        Random random = new Random();
        int i = random.nextInt(serverList.size());
        return (String) serverList.get(i);
    }
}

/**
 * 单例测试类
 *
 * @author 10545
 * @date 2022/3/28 23:45
 */
public class Demo {
    public static void main(String[] args) {
        //创建4个LoadBalancer对象
        LoadBalancer loadBalancer1 = LoadBalancer.getLoadBalancer();
        LoadBalancer loadBalancer2 = LoadBalancer.getLoadBalancer();
        LoadBalancer loadBalancer3 = LoadBalancer.getLoadBalancer();
        LoadBalancer loadBalancer4 = LoadBalancer.getLoadBalancer();

        //判断4个对象是否相同
        if (loadBalancer1 == loadBalancer2 && loadBalancer2 == loadBalancer3 && loadBalancer3 == loadBalancer4) {
            System.out.println("服务器负载均衡器具有唯一性");
        }

        //增加服务器
        loadBalancer1.addServer("Server 1");
        loadBalancer2.addServer("Server 2");
        loadBalancer3.addServer("Server 3");
        loadBalancer4.addServer("Server 4");

        //模拟客户端请求的分发
        for (int i = 0; i < 10; i++) {
            String server = loadBalancer1.getServer();
            System.out.println("分发请求至服务器：" + server);
        }

    }
}
```

## 模式拓展

### 饿汉式单例

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220402235617.png)

在定义静态变量的时候实例化单例类

### 懒汉式单例

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220403001149.png)

在单例类被第一次引用时将自己实例化（延迟加载）

为了避免多个线程同时调用getInstance()方法， 可以使用synchronized进行双重检查锁定

```java
class LazySingleton {
    private volatile static LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        //第一重判断
        if (instance == null) {
            //锁定代码块
            synchronized (LazySingleton.class) {
                //第二重判断
                if (instance == null) {
                    instance = new LazySingleton(); //创建单例实例
                }
            }
        }
        return instance;
    }
}
```

### 使用静态内部类实现单例

Java语言中可以通过 `Initialization on Demand Holder`(IoDH)技术来实现单例模式

```java
//Initialization on Demand Holder
class Singleton {
    private Singleton() {
    }

    private static class HolderClass {
        private final static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return HolderClass.instance;
    }

    public static void main(String args[]) {
        Singleton s1, s2;
        s1 = Singleton.getInstance();
        s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}
```

- 由于静态单例对象没有作为Singleton的成员直接实例化，因此类加载时不会实例化Singleton
- 第一次调用getInstance()时将加载内部类HolderClass， 该内部类中定义了一个static类型的变量instance，由Java虚拟机保证其线程安全，确保其只能初始化一次
- 由于getInstance()方法没有任何线程锁定，不会影响性能

### 多例

可以对单例模式进行拓展，获取多个数目的实例对象

既节省系统资源，又解决了对象共享对象过多影响性能问题

## 效果

- 优点
    - 可以严格控制客户端这样怎样以及何时访问单例对象
    - 在系统内存中只存在一个对象，可以节约系统资源，提高系统性能
- 缺点
    - 单例模式没有抽象层，拓展性差
    - 单例类既负责业务方法，又负责创建对象的方法。不符合单一职责原则
    - 许多语言提高了垃圾回收，实例化单例对象长期不被利用会被回收，会导致单例对象
      状态丢失。

## 模式适用性

- 系统只需要一个实例对象
- 因为资源消耗太大，只允许创建一个对象
- 客户只允许使用一个公共访问点，除此之外不允许使用其它途径访问该实例
