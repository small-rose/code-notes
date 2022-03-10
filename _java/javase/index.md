---
layout: default
title: JavaSE
nav_order: 1
latex: true
---
# 参考资料

参考网站：

- [The Java™ Tutorials](https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html)

参考书籍：

- [Java语言导学（原书第6版）](https://book.douban.com/subject/27102988/)
- [On Java 8](https://book.douban.com/subject/30217317/)
- [Java核心技术（第十版）](https://book.douban.com/subject/26880667/)

# 思维导图

![](https://edrawcloudpubliccn.oss-cn-shenzhen.aliyuncs.com/viewer/self/1059758/share/2021-11-15/1636970334/main.svg)

# 概述

## 特性

- 简单些
  - Java剔除了C++中许多很少使用、难以理解、易混淆的特性。
  - 没有头文件
  - 没有指针运算和指针语法
  - 没有结构、联合、操作符重载、虚基类等
  - 开发能够在小型机器上独立运行的软件
- 面向对象
- 分布式
  - Java有一个丰富的例程库，用于处理像HTTP和FIT之类的TCP/IP协议。Java应用程序能够通过URL打开和访问网络上的对象， 其便捷程度就好像访问本地文件一样。
- 健壮性
  - Java采用的指针模型可以消除重写内存和损坏数据的可能性。
  - Java编译器能够检测许多在其他语言中仅在运行时才能够检测出来的问题
- 安全性
  - 防止运行时堆栈溢出。如蠕虫和病毒常用的攻击手段。
  - 防止破坏自己的进程空间之外的内存。
  - 防止未经授权读写文件。
- 体系结构中立
  - Java编译器通过生成与特定的计算机体系结构无关的字节码指令来实现这一特性。
- 可移植性
  - 对基本数据类型的大小做了明确说明，二进制数据以固定的格式进行存储和传输，消除了字节顺序的困扰。字符串是用标准的Unicode格式存储的。
- 解释型
  - Java解释器可以在任何移植了解释器的机器上执行Java字节码
- 高性能
  - 字节码可以（在运行时刻）动态地翻译成对应运行这个应用的特定CPU的机器码
  - 即时编译器可以监控经常执行哪些代码并优化这些代码以提高速度
  - 消除函数调用（即“内联”）
- 多线程
  - 第一个支持并发程序设计的主流语言
- 动态性
  - 库中可以自由地添加新方法和实例变量，而对客户端却没有任何影响
  - 在Java中找出运行时类型信息十分简单

## 发展史

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111251010773.png)

## 术语

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111251012249.png)

# 语言基础

## 注释

方式一：

```java
// 单行注释
```

方式二：

```java
/* 注释内容 */
```

方式三：

这种注释可以用来 `自动地生成文档`

```java
/**
 * @author luguosong
 * @date 2021/11/9 9:30
 */
```

javadoc生成文档

```shell
javadoc -d docDirectory nameOfPackage
#或者
javadoc -d docDirectory *.java
```

## 基本数据类型

Java跟C++不同，基本数据类型的长度是固定的，跟机器无关

Java有 `8种`基本数据类型

| 名称    | 类型                           | 长度 | 最小值      | 最大值       | 默认值(`只有字段有默认值`) |
| ------- | ------------------------------ | ---- | ----------- | ------------ | ---------------------------- |
| byte    | 整数                           | 8    | -128        | 127          | 0                            |
| short   | 整数                           | 16   | -32768      | 32767        | 0                            |
| int     | 整数                           | 32   | $-2^{31}$ | $2^{31}-1$ | 0                            |
| long    | 整数                           | 64   | $-2^{63}$ | $2^{63}-1$ | 0L                           |
| float   | 单精度**IEEE 754**浮点数 | 32   | -           | -            | 0.0f                         |
| double  | 双精度**IEEE 754**浮点数 | 64   | -           | -            | 0.0d                         |
| boolean | 布尔值                         | 1    | -           | -            | false                        |
| char    | 字符                           | 16   | \u0000      | \uffff       | null                         |

## 变量

每个变量都有一个类型（type)。

使用变量前必须进行初始化：

- 可以在声明的时候就显示初始化
- 也可以在构造方法中初始化

在声明变量时，变量的类型位于变量名之前。格式如下：

```java
class Demo {
  //变量类型 变量名;
  int number;
  long length = 10L;
}
```

- 变量分类
  - `字段`：类中声明的成员变量
  - `局部变量`：方法或代码块中的变量
  - `参数`：方法声明中的变量

## 常量

使用final修饰表示常量

常量只能被赋值一次，一旦被赋值后，就不能再更改了。

常量名一般全部使用大写

```java
class Demo {
  final double CM_PER_INCH = 2.54;
}
```

## 操作符

`操作符`接受一个或多个参数，然后生成一个新的值

### 赋值

操作符 `=`用来赋值。它的意思是 `取等号右边的值（右值），把它复制给等号左边（左值）`

- 左值和右值

  - `右值`可以是任何 `常量`、`变量`或者 `可以产生值的表达式`
  - `左值`必须是一个独特的命名变量
- 基本数据类型和引用数据类型赋值

  - 基本类型存储了实际的值，赋值直接将一个地方的内容复制到了另一个地方。
  - 将一个对象赋值给另一个对象，其实是将这个引用从一个地方复制到另一个地方

### 算数操作符

- 加法：`+`
- 减法：`-`
- 除法：`/`
  - 整数除法的结果会舍弃小数位，而非四舍五入
- 乘法：`*`
- 取模：`%`
- 一元减运算符
  - 反转数据的符号
- 一元加运算符
  - 将较小类型的操作数提升为int类型

Java还使用一种来自C和C++的 `快捷运算符`,可以 `同时进行运算和赋值操作`。这种快捷运算符通过在操作符后紧跟一个等号来表示：

```java
class Demo {
  public static void main(String[] args) {
    int x = 1;
    //表示把x加4，然后将结果赋值给x
    x += 4;
  }
}
```

### 自动递增和自动递减

- 递减操作符：`--`
- 递增操作符：`++`
- 前缀式

  - 操作符位于变量之前
  - 先执行运算，然后返回结果
- 后缀式

  - 操作符位于变量之后
  - 先返回变量的值，再执行运算
  - 副作用：会改变操作数

### 关系运算符

`操作数`的值之间的关系生成一个 `布尔`结果

- 小于：`<`
- 大于：`>`
- 小于等于:`<=`
- 大于等于:`>=`
- 等于：`==`
- 不等于：`!=`

### 逻辑运算符

- 与：`&&`
- 或：`||`
- 非：`!`

逻辑操作符支持一种称为“短路”的现象。一旦表达式当前部分的计算结果能够明确无误地确定整个表达式的值， 表达式余下部分就不会被执行了。因此，逻辑表达式后面的部分有可能不被执行。

### 字面量

- 整型字面量
  - `26L` 表示long类型
  - `26` 十进制int类型
  - `0x1a` 十六进制int类型(小写)
  - `0X2F` 十六进制int类型(大写)
  - `0177` 八进制（前置0）
  - `0b11010` 二进制int类型
  - `1234_5678_9012` 可以在数字之间增加下划线增加可读性
- 浮点字面量
  - `123.4` 不加任何后缀 `默认`表示double类型
  - `123.4d` double类型
  - `1.234e2` 科学计数标记
  - `123.4f` float类型
- 类字面量:`类.class`,表示类型本身的对象
  - String.class
- 字符字面量和字符串字面量
- null

### 按位操作符

操作整数基本数据类型中的单个二进制位

- 按位“与”操作符：`&`
- 按位“或”操作符：`|`
- 按位“异或”操作符：`^`
- 按位“非 ”操作符: `~`

### 移位操作符

了解就行，八辈子用不到

- 左移位操作符:`<<`
- 有符号右移位操作符:`>>`
  - 有符号的右移位操作符使用了 `符号扩展`：如果符号为正，则在高位插入0，否则在高位插入1。
- 无符号右移位操作符:`>>>`
  - 无论符号为正还是为负，都在高位插入0。

### 三元操作符

也叫 `条件操作符`

格式如下：

```java
class Demo {
  public static void main(String[] args) {
    boolean boolean_exp = ture;
    int value0 = 1;
    int value1 = 2;
    boolean_exp ? value0 : value1
  }
}
```

- `boolean_exp`为true，返回value0
- `boolean_exp`为false,返回value1

### 字符串操作符

`+`和 `+=`操作符都可以连接字符串

### 类型转换操作符

要对某个值执行类型转换，可以将希望得到的数据类型放在括号内，置于该值的左边。

```java
class Demo {
  public static void main(String[] args) {
    long lng = 200;
    //一个窄化转型
    int i = (int) lng; // 需要强制类型转换
  }
}
```

## 表达式、语句和块

`表达式`由 `变量`、`操作符`和 `方法调用`组成。`表达式不一定是语句，只有部分表达式是语句。`,比如三元运算符只是表达式，并不是语句。

![](src/main/resources/表达式和语句.svg)

`语句`构成了具体的执行单元。

- 表达式语句
  - 赋值表达式
  - 递增或递减表达式
  - 方法调用
  - 对象创建表达式
- 其它语句
  - 声明语句
  - 控制流语句

```java
import com.sun.org.apache.xpath.internal.operations.String;

class Demo {
  public static void main(String[] args) {
    //声明语句
    int i = 0;
    //赋值语句
    i = 10;
    //自增自减语句
    i++;
    i--;
    //方法调用语句
    System.out.println(i);
    //控制流语句
    if (i > 0) {
      System.out.println("i大于0");
    }
    //对象创建语句
    new String();
  }
}
```

`块`（代码块）是一对花括号之间的一组语句。

## 控制流语句

Java继承了C语言的所有执行控制语句

### if-else

if-else语句是最基本的控制程序流程的方式。其中else是可选的，所以if-else语句有如下两种使用方式：

```java
class Demo {
  public static void main(String[] args) {
    int i = 1;
    if (i > 0) {
      System.out.println("i>0");
    } else {
      System.out.println("i<0");
    }
  }
}
```

### 迭代语句

#### while语句

```java
class Demo {
  public static void main(String[] args) {
    int i = 0;
    while (i < 10) {
      i++;
    }
  }
}
```

#### do-while语句

while和do-while的唯一的区别是do-while中的语句至少会执行一次，即便表达式的第一次计算结果就是false。

```java
class Demo {
  public static void main(String[] args) {
    int i = 0;
    do {
      i++;
    } while (i < 10);
  }
}
```

#### for语句

```java
class Demo {
  public static void main(String[] args) {
    int nums[] = {1, 2, 3};

    for (int i = 0; nums.length; i++) {
      System.out.println(nums[i]);
    }
  
    //或者增强for
    for (int num : nums) {
      System.out.println(num);
    }
  }
}
```

### return关键字

它可以指定一个方法的返回值（如果不存在就返回void），还会导致 `当前的方法退出`，并且返回这个值。

### break和continue

`break`会直接退出循环，不再执行循环里的剩余语句。

`continue`则会停止执行当前的迭代，然后退回循环开始位置执行下一次迭代。

### 臭名昭著的goto

尽管goto是Java中的一个保留字，但Java中并没有使用它——Java没有goto。但是 `continue`和 `break`都有相似的功能

- 这里的 `continue label1`会同时中断内部迭代以及外部迭代，直接跳到label1处，然后它实际上会重新进入外部迭代开始继续执行。
- 这里的 `break label1`也会中断所有迭代，跳回到label1处，不过它并不会重新进入外部迭代。它实际是完全跳出了两个迭代。

```java
package cn.com.lgs.control_flow;

/**
 * @author 10545
 * @date 2021/11/26 20:49
 */
public class GotoDemo {
    public static void main(String[] args) {
        int i = 0;
        outer:
        // 此处不能有语句
        for (; true; ) { // 无限循环
            inner:
            // 此处不能有语句
            for (; i < 10; i++) {
                System.out.println("i = " + i);
                if (i == 2) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("break");
                    i++; // 否则i不会递增
                    break;
                }
                if (i == 7) {
                    System.out.println("continue outer");
                    i++; // 否则i不会递增
                    continue outer;
                }
                if (i == 8) {
                    System.out.println("break outer");
                    break outer;
                }
                for (int k = 0; k < 5; k++) {
                    if (k == 3) {
                        System.out.println("continue inner");
                      continue inner;
                    }
                }
            }
        }
      // 此处不能有标签
    }
}
```

在Java里使用标签的 `唯一理由`就是你用到了嵌套循环，而且你需要使用break或continue来跳出多层的嵌套。

### switch语句

根据 `表达式的值`，switch语句选择多个代码片段中的一个去执行。

```java
package cn.com.lgs.control_flow;

/**
 * @author luguosong
 * @date 2021/12/18 14:54
 */
public class SwitchDemo {
  public static void main(String[] args) {
    //选择器
    switch ("1" + "2") {
      //整数值
      case "3":
        System.out.println(3);
        break;
      case "12":
        System.out.println(12);
        break;
      //默认语句
      default:
        System.out.println("默认语句");
    }
  }
}
```

# 类

## 类的声明

```java
package cn.com.lgs.class_and_object;

/**
 * 类的声明
 *
 * @author luguosong
 * @date 2021/12/18 16:54
 */
public class MyClass {
  //包含构造器、字段和方法
}
```

- 类的声明包含以下组件
  - `修饰符`：public、private等
  - `类名`：首字母大写
  - `extends关键字`：如果有超类，要在超类前添加 `extends`关键字，一个类 `只能有一个`超类。
  - `implements关键字`：如果实现一系列接口，用逗号分隔。在接口前添加 `implements`关键字。一个类可以实现多个接口。
  - `类体`：花括号之间的部分。

## 字段

- 字段包含以下组件
  - `零个或多个修饰符`:如public或private
  - `字段类型`
  - `字段名`
  
***

- 初始化字段
  - 声明时初始化
    - 当初始化需要逻辑处理这种方式不适合
  - 构造器初始化
  - 代码块初始化
    - 编译器会将`代码块`复制到每个`构造器`中。可以在多个构造器中`共享代码块`
  - 使用final方法初始化
  - 静态代码块初始化
    - 多个静态代码块时，按顺序执行
      - 可以使用`私有静态方法`替代静态代码块
        - 相比静态代码块，`私有静态方法`可以重用


## 方法

```java
class Demo {
  public double calculateAnswer(double wingSpan, int numberOfEngines,
                                double length, double grossTons) {
    //do the calculation here
  }
}
```

- 方法包含以下组件
  - `修饰符`：public、private等
  - `返回类型`：方法返回的数据类型，如果不返回其类型为void
  - `方法名`:与字段的命名规则相同（小驼峰式命名法，lower camel case）
    - 首字母小写，后面的单词首字母大写
    - 首字母是一个动词
  - `圆括号内的参数列表`：参数间用分号隔开
    - 参数类型后加 `...`表示可变参数
  - `异常列表`
  - `花括号内的方法体`

`方法签名`：方法名+参数

## 构造器

调用构造器可以从类创建对象

构造器与方很相似，但有一下区别：

- 构造器没有返回值
- 构造器名称与类名一样

```java
public class Bicycle {
  private int cadence;

  public Bicycle(int startCadence) {
    cadence = startCadence;
  }
}
```

使用 `new`关键字调用构造器：

```java
Bicycle mybBike = new Bicycle(30);
```

如果一个类不写构造器，编译器会自动创建无参构造器去调用父类无参构造器

如果手动编写构造器，则不会创建默认无参构造器

⭐`错误情况：如果子类不写构造器使用默认无参构造器，父类手动编写有参构造器。则会报错`

## 参数传递

不管是基本数据类型还是引用数据类型，都是通过 `传值方式`传入方法。

## this关键字

表示 `当前对象`的引用。

## 访问权限修饰符

Java访问权限修饰符包括 `public`、`protected`和 `private`，它们放在类中成员定义的前面，包括字段和方法。
每个访问权限修饰符仅控制对该特定定义的访问。如果不提供访问权限修饰符，则表示 `包访问权限`。

| 修饰符    | 类 | 包 | 子类 | 所有环境 |
| --------- | -- | -- | ---- | -------- |
| public    | Y  | Y  | Y    | Y        |
| protected | Y  | Y  | Y    | N        |
| 无修饰符  | Y  | Y  | N    | N        |
| private   | Y  | N  | N    | N        |

## static关键字

### 静态字段

声明中有`static`修饰的字段称为`静态字段`或`类变量`。

它们与类关联，而不与对象关联。类的所有实例共享同一个`静态字段`。

即使不创建实例，也能处理这些`静态字段`。

静态字段推荐使用类直接引用，不推荐使用对象来引用。

### 静态方法

使用`static`修饰符声明的方法

静态方法推荐使用类直接引用，不推荐使用对象来引用。

不能直接访问`实例变量`或`实例方法`。

不能使用`this`关键字

### 常量

`常量`：同时使用`static`和`final`修饰的字段

常量名都用大写

# 对象

## 创建对象

```java
Point point = new Point(23,94);
```

创建对象包含以下组件：

- `声明`：将变量名关联到对象类型上
- `实例化`：new关键字创建对象
  - 返回创建对象的引用
- `初始化`：new关键字后跟一个构造器调用，用于初始化新对象。

## 使用对象

- 引用对象字段
- 调用对象方法

Java在确定不再使用某些对象时就会删除它们，这个过程叫做 `垃圾收集`

当对象没有引用时，就可以进行垃圾收集

# 内部类

在类内定义另一个类

## 为什么使用内部类

- 对单一用途的类进行逻辑分类
- 提高封装性，一种代码隐藏机制
- 提高代码可读性和可维护性
- 内部类完善了多重继承问题的解决方案，每个内部类都可以独立地继承自一个实现。因此，外部类是否已经继承了某个实现，
对内部类并没有限制。
- `闭包`：内部类包含所在作用域信息。可以访问到外围类的变量、方法或者其它内部类等所有成员。
- 在`控制框架`中的应用。


## 分类

- `静态嵌套类`：用static修饰的内部类
  - 不能直接引用外部类的实例变量和实例方法
  - 为了打包方便才嵌套在另一个顶层类中
- `内部类`:非静态嵌套类
  - 可以直接访问外部类的方法和字段
  - 内部类不能定义任何静态成员

## 嵌套类

也就是`static`修饰的静态内部类

不需要一个外部类对象来创建类对象。无法从嵌套类对象内部访问非static的外部对象

放到`接口`中的任何类都会自动成为`public`和`static`的。

## 内部类
  
### 内部类创建

创建非静态内部类对象，前提是先要有一个`外部类对象`。这是因为内部类的对象会暗中连接到用于创建它的外部类对象,
不可以绕过创建外部类对象直接创建内部类对象。

```java
package cn.com.lgs.inner_classes;

/**
 * 外部类的创建
 *
 * @author luguosong
 * @date 2021/12/29 9:59
 */
public class Parcel {

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public static void main(String[] args) {
        //创建内部类对象方式一：先创建外部类对象，再创建内部类对象
        Destination hello = new Parcel().new Destination("hello");
        //创建内部类对象方式二：将创建内部类的行为包装到外部类的方法中
        Destination hello1 = new Parcel().to("hello");
    }
}
```

### 内部类访问外部类成员

`内部类`中可以直接访问`外部类`的成员

`内部类`可以访问外围对象的所有方法和字段，就好像拥有它们一样。这非常方便，我们在前面的示例中也看到了。

```java
package cn.com.lgs.inner_classes;

/**
 * 内部类访问外部类字段
 *
 * @author luguosong
 * @date 2021/12/29 10:17
 */

interface Selector {
    boolean end();

    Object current();

    void next();
}

public class Sequence {
    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next++] = x;
    }

    //内部类
    private class SequenceSelector implements Selector {
        private int i = 0;

        @Override
        public boolean end() {
            //内部类访问外部items数组
            return i == items.length;
        }

        @Override
        public Object current() {
            //内部类访问外部items数组
            return items[i];
        }

        @Override
        public void next() {
            //内部类访问外部items数组
            if (i < items.length) i++;
        }
    }

    //外部类通过方法创建内部类对象
    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        //创建外部类添加数组
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        //创建内部类
        Selector selector = sequence.selector();
        //内部内访问外部类数组进行遍历
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
```

### 内部类this访问外部类引用

内部类中通过`外部类类名.this`访问外部类对象引用

```java
package cn.com.lgs.inner_classes;

/**
 * 内部类访问外部类对象引用
 *
 * @author luguosong
 * @date 2021/12/29 11:28
 */
public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis outer() {
            //返回外部类对象引用
            return DotThis.this;
            // 如果直接写“this”，引用的会是Inner的“this”
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
      DotThis.Inner dti = dt.inner();
      dti.outer().f();
    }
}
```

### 内部类向上转型

使用private修饰内部类，并实现公共的接口。达到隐藏实现的目的。


### 继承内部类

构造器要传递一个指向其包围类对象的引用，并且执行`super()`方法

```java
package cn.com.lgs.inner_classes;

/**
 * 继承一个内部类
 *
 * @author luguosong
 * @date 2022/1/25 10:25
 */
class WithInner {
    class Inner {
    }
}

public class InheritInner extends WithInner.Inner {
    //- InheritInner() {} // 不能编译

    InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}
```

## 局部类

特殊的内部类。局部类可以在任意`块`中定义,一般定义在方法体中。

局部类都是非静态的，因为它们要访问所属块的实例成员。块中不能声明接口，因为接口是内在静态的。

局部类中`不可以有静态成员`。但可以有`常量`。

局部类可以访问所属类的成员。静态方法中的局部类只能引用所属类的静态成员。

局部类也可以访问`所属块`的`局部变量`。JDK8之前，访问的这些局部变量必须使用final修饰。JDK8后，访问所属块的局部变量可以是`final`或者`effectively final`。

- 为什么局部类访问所属块的变量得是`final`或者`effectively final`
  - 是因为块中的局部变量的`生命周期`导致的
  - 局部类中引用所在块的局部变量。所在块走完，局部变量销毁。这个时候如果在局部类中对该变量进行操作，该变量已经不在了，将产生矛盾。
  - 局部类`复制一份局部变量`，作为局部类的成员变量。即使局部变量死亡后，局部类仍然可以访问它。

```java
package cn.com.lgs.inner_classes;

/**
 * 局部类示例：验证两个号码的合法性
 *
 * @author 10545
 * @date 2022/1/3 20:37
 */
public class LocalClassExample {
    static String regularExpression = "[^0-9]";

    public static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {
        //JDK8之前，局部类引用的外部变量需要使用final修饰
        //final int numberLength = 10;
        //JDK8以及之后版本不需要final修饰
        int numberLength = 10;

        /////////////////////////////////////////////////////////////////////////////////

        /**
         * 局部类
         */
        class PhoneNumber {

            //局部类中不可以有静态成员
            //static String phone;
            //但允许常量
            final static String PHONE = "123-456-7890";

            String formattedPhoneNumber = null;

            PhoneNumber(String phoneNumber) {
                String currentNumber = phoneNumber.replaceAll(regularExpression, "");
                //局部类可以访问所属块的局部变量numberLength，该变量必须是effectively final的
                if (currentNumber.length() == numberLength) {
                    formattedPhoneNumber = currentNumber;
                } else {
                    formattedPhoneNumber = null;
                }
            }

            public String getNumber() {
                return formattedPhoneNumber;
            }

            //JDK8之后可以访问所在方法的参数，但同样是effectively final的
            public void printOriginalNumbers() {
                System.out.println("原始数字是：" + phoneNumber1 + "和" + phoneNumber2);
            }
        }

        /////////////////////////////////////////////////////////////////////////////////

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

        //JDK8之后可以访问局部类外部非final修饰的
        myNumber1.printOriginalNumbers();

        if (myNumber1.getNumber() == null) System.out.println("第一个数字无效");
        else System.out.println("第一个数字是：" + myNumber1.getNumber());

        if (myNumber2.getNumber() == null) System.out.println("第二个数无效");
        else System.out.println("第二个数字是：" + myNumber2.getNumber());
    }

    public static void main(String[] args) {
        validatePhoneNumber("123-456-7890", "456-7890");
    }
}
```

## 匿名类

局部类是`类声明`，而匿名类是`表达式`。

如果局部类只使用一次，则推荐使用匿名类。

匿名类可以访问所属类的成员

与局部类相同，可以访问所在块`final`或者`effectively final`的局部变量

- 匿名类表达式包含如下内容
  - `new`运算符
  - 实现的接口或继承的类名
  - 将参数包含入构造器的括号。和通常的类实例表达式类似
  - 类声明体

```java
package cn.com.lgs.inner_classes;

/**
 * 匿名类和局部类比较
 *
 * @author luguosong
 * @date 2022/1/10 9:57
 */
public class HelloWorldAnonymousClasses {
    interface HelloWorld {
        public void greet();
    }

    public void sayHello() {
        //局部类，是一个类的声明
        class LocalClassesGreeting implements HelloWorld {
            @Override
            public void greet() {
                System.out.println("Hello,局部类");
            }
        }
        //使用局部类
        LocalClassesGreeting localClassesGreeting = new LocalClassesGreeting();

        //匿名类不需要声明，而是作为表达式直接使用
        HelloWorld helloWorld = new HelloWorld() {
            @Override
            public void greet() {
                System.out.println("Hello,匿名类");
            }
        };

        //使用对象
        localClassesGreeting.greet();
        helloWorld.greet();
    }

    public static void main(String[] args) {
        HelloWorldAnonymousClasses worldAnonymousClasses = new HelloWorldAnonymousClasses();
        worldAnonymousClasses.sayHello();
    }
}
```

# Lambda表达式

## 概述

用于创建只有`一个抽象方法`的`接口`。相当于是简化的匿名内部类。

Lambda由以下三部分组成：
- 形参列表
- 箭头
- 代码块

## 示例

```java
/**
 * Lambda HelloWorld
 *
 * @author 10545
 * @date 2022/2/24 21:40
 */

interface Command {
  void process(int element);
}

class ProcessArray {
  public void process(int[] target, Command cmd) {
    for (int t : target) {
      cmd.process(t);
    }
  }
}

/**
 * 使用匿名内部类创建Command对象
 */
class CommandTest1 {
  public static void main(String[] args) {
    ProcessArray processArray = new ProcessArray();
    int[] target = {3, -4, 6, 4};
    processArray.process(target, new Command() {
      @Override
      public void process(int element) {
        System.out.println("数组元素的平方是：" + element * element);
      }
    });
  }
}

/**
 * 使用Lambda创建Command对象
 */
class CommandTest2 {
  public static void main(String[] args) {
    ProcessArray processArray = new ProcessArray();
    int[] target = {3, -4, 6, 4};
    processArray.process(target, (int element) -> {
      System.out.println("数组元素的平方是：" + element * element);
    });
  }
}
```

## 简化写法

Lambda表达式的简化写法
- 当只有一个形参时，可以省略圆括号
- 当Lambda表达式只有一行代码，可以省略代码块的花括号
- 如果接口有返回值且Lambda表达式只有一行代码，则该语句将作为该代码块的返回值，可以省略`return`关键字

## 递归

`注意`：这个lambda表达式必须被赋值给一个静态变量或一个实例变量，否则会出现编译错误

```java
package cn.com.lgs.lambda;

/**
 * 使用Lambda的递归计算斐波拉契数
 *
 * @author luguosong
 * @date 2022/3/3 11:13
 */

interface IntCall {
    int call(int arg);
}

public class RecursiveFibonacci {
    IntCall fib;

    //在构造中使用Lambda实例化fib变量
    RecursiveFibonacci() {
        fib = n ->
                n == 0 ? 0 : n == 1 ? 1 : fib.call(n - 1) + fib.call(n - 2);
    }

    int fibonacci(int n){
        return fib.call(n);
    }

    public static void main(String[] args) {
        RecursiveFibonacci rf = new RecursiveFibonacci();
        System.out.println(rf.fibonacci(20));
    }
}
```

上面示例算法复杂度是2的n次方，仅为说明Lambda的递归。一般情况尽量是线性递归

# 枚举类型

允许变量为`一组预定义常量`的特殊数据类型.

# 注解

# 接口

# 继承

# Number类

# String类

# 泛型

# 包

# 异常

# 流

# 平台环境

# 正则表达式

# 反射

# 集合

# JAR包

# 国际化

# 安全

# JavaBeans

# 拓展机制

# JavaFX

## Scene Builder

# Swing

# 部署

# 2D Graphics

# 全屏独占模式API

# JDBC

# JMX

# JNDI

# JAXP

# RMI

# 并发
