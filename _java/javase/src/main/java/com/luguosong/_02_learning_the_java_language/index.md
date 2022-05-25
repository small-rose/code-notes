---
layout: default
title: 基本概念
nav_order: 2
parent: JavaSE
---

# 语言基础（Language Basics）

## 变量（Variables）

### 变量分类

- 实例变量（非静态字段）
- 类变量（静态字段）
- 局部变量
- 参数

### 命名规范

- 变量名区分大小写
- 确定首个字符之后，后续字符可以是字母，数字，美元符号或下划线
- 小驼峰命名

### 基本数据类型

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

### 数组（Arrays）

数组是`固定数目`的`单一数据类型`值的容器对象。创建时要指定其长度，创建后其长度就固定了。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220523210700.png)

数组中每个项都称作`元素`，每个元素都通过数字`索引`访问，索引从0开始。因此第9个元素要通过索引8来访问。

使用`System.arraycopy`进行数组的复制：
```java
class ArrayCopyDemo {
    public static void main(String[] args) {
        String[] copyFrom = {
            "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",   
            "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",      
            "Marocchino", "Ristretto" };
        
        String[] copyTo = new String[7];
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");           
        }
    }
}
```

运行结果：
```text
Cappuccino Corretto Cortado Doppio Espresso Frappucino Freddo
```

为了方便起见，Java在`java.util.Arrays`类中提供了几个数组操作方法。

使用`java.util.Arrays`中的方法进行复制：
```java
class ArrayCopyOfDemo {
    public static void main(String[] args) {
        String[] copyFrom = {
            "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",   
            "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",      
            "Marocchino", "Ristretto" };
        
        String[] copyTo = java.util.Arrays.copyOfRange(copyFrom, 2, 9);        
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");           
        }            
    }
}
```

运行结果：
```text
Cappuccino Corretto Cortado Doppio Espresso Frappucino Freddo 
```

`java.util.Arrays`中还提供了其它有用的造作方法：
<dl>
  <dt>binarySearch()</dt>
  <dd>在数组中搜索特定值，并返回其位置索引</dd>
  <dt>equals()</dt>
  <dd>对两个数组进行比较，并确定两者是否相等</dd>
  <dt>fill()</dt>
  <dd>在数组的每个索引位置上填上指定值</dd>
  <dt>升序排序方法</dt>
  <dd>JavaSE8引入两种方法</dd>
  <dd>一种是顺序方法sort()</dd>
  <dd>另一种是并发方法parallelSort()</dd>
  <dd>多处理器系统中大数组的并发排序比顺序排序要快</dd>
</dl>

## 运算符（Operators）

下图根据运算符优先级顺序列出了所有运算符：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220523215555.png)

- 简单赋值运算符
  - =  简单赋值运算符
- 算术运算符
  - \+ 加法运算符（也用于字符串连接）
  - \- 减法运算符
  - \* 乘法运算符
  - / 除法运算符
  - % 取模运算符
- 一元运算符
  - \+ 一元加运算符，表示正值，没有该运算符时也表示正值
  - \- 一元减运算符，否定表达式
  - ++ 递增运算符，将值加1
  - \-- 递减运算符，将值减一
  - ！ 逻辑非运算符，反转布尔值
- 等式和关系运算符
  - == 等于
  - != 不等于
  - \> 大于
  - \>= 大于等于
  - < 小于
  - <= 小于等于
- 条件运算符
  - && 条件与（逻辑与）
  - \|\| 条件或（逻辑或）
  - ?: 三元运算符（if-then-else语句简化版本）
- 类型比较运算符
  - instanceof 将对象和指定类型进行比较
- 位与移位运算符
  - ~ 一元按位求补
  - << 带符号左移
  - \>> 带符号右移
  - \>>> 无符号右移,会在最左边移入0
  - & 按位与
  - ^ 按位异或
  - \| 按位或

## 表达式、语句和块（Expressions, Statements, and Blocks）

`表达式`是由变量、运算符和方法调用等组成的构造，且表达式的计算结果是单个值。

`语句`构成了完整的执行单元。

`表达式语句`-部分`表达式`加上分号(;)可以直接成为语句：
- 赋值表达式
- 递增或递减表达式
- 方法调用
- 对象创建表达式

除了`表达式语句`，还有`声明语句`和`控制流语句`

`块`（代码块）是一对花括号之间的一组语句

## 控制流语句(Control Flow Statements)

- if-then语句
- if-then-else语句
- switch语句
- while语句
- do-while语句
- for语句
- break语句
- continue语句
- return语句

# 面向对象的编程概念（Object-Oriented Programming Concepts）

## 对象（Object）

对象包含`状态`和`行为`两大属性

Java中对象用`字段`（field,有些语言成为变量，variable）存储`状态`

Java中对象用`方法`（method,有些语言成为函数，function）定义`行为`

通过`方法`对象内部`状态`进行操作，是对象之间通信的基本机制。这种`隐藏对象内部状态`，要求所有通信都通过方法实现的行为，是面向对象编程的基本原则，称为`封装`

将代码构建成独立对象的优点：
- 模块化
- 信息隐藏
- 代码重用
- 可插拔，易于调试

### 创建对象

创建对象由三部分组成：
- `声明`：将变量名关联到对象类型上
- `实例化`：new关键字
- `初始化`：构造器调用

### 使用对象

- 引用对象的字段
- 调用对象的方法
- 当对象没有引用后，会被垃圾收集器清理


## 类（Class）

`类`是创建对象的蓝图

### 声明类

```java
class MyClass {
    // field, constructor, and 
    // method declarations
}
```

类声明包含以下组件：
- 修饰符：public、private等
- 类名：按约定首字母大写
- 如果有超类，要在超类名称前添加`extends关键字`。一个类只能继承一个超类
- 如果实现一系列接口，要在接口前添加`implements关键字`。一个类可以实现多个接口
- 类体：花括号之间的部分

### 声明成员变量

成员变量通常称为`字段`。

字段声明由下面三个组件组成：
- 零个或多个修饰符
- 字段类型
- 字段名

初始化静态字段：
- `声明时直接初始化`：适用于没有复杂逻辑的初始化
- `静态初始化块`：可以用于初始化静态字段，功能等价于私有静态方法，但私有静态方法可以重复调用

初始化实例成员：
- `声明时直接初始化`：适用于没有复杂逻辑的初始化
- `构造器初始化`
- `初始化块`：Java编译器会将初始化块复制到每个构造器，因此，多个构造器可以共享初始化块
- `使用final方法初始化`：子类可以重用final方法

```java
//使用final方法初始化
class Whatever {
    private varType myVar = initializeInstanceVariable();
        
    protected final varType initializeInstanceVariable() {

        // initialization code goes here
    }
}
```

### 定义方法

方法声明包括六个组件：
- 修饰符
- 返回类型
- 方法名
- 圆括号内的参数列表
- 异常列表
- 花括号内的方法体

方法名+参数类型=`方法签名`

`重载方法`：如果类内方法的参数列表不同，则可以使用同一方法名

### 构建构造器

每个类都有`构造器`，构造器可以从`类`创建`对象`。

构造器与方法声明很像，只不过构造器名称与类名一样，且没有返回类型

需要用`new`运算符调用构造器

一个类如果没有构造器，会`默认创建`无参构造器，并去调用父类无参构造器。而一个类如果自定义了构造器，则不会创建默认无参构造器。

在子类没有声明构造器的情况下，父类必须存在无参构造。

如果父类存在`多个`构造器，子类`只需要有一个`构造器并调用父类中任意一个构造器即可

### 将消息传给方法或构造器

形参：方法声明中的变量列表

实参：方法调用时实际传递的值

可变参数:

```java
class Demo{
  public static void test(String... strings) {
    for (int i = 0; i < strings.length; i++) {
      System.out.println(strings[i]);
    }
  }

  public static void main(String[] args) {
    //参数可以是多个元素
    test("111","222");
    //参数也可以是数组
    String[] strArray={"1","2","3"};
    test(strArray);
  }
}
```

不管是基本数据类型还是引用数据类型，都是通过 `传值方式`传入方法。

### this关键字

this关键字是`当前对象的引用`

### 访问权限修饰符

Java访问权限修饰符包括 `public`、`protected`和 `private`，它们放在类中成员定义的前面，包括字段和方法。
每个访问权限修饰符仅控制对该特定定义的访问。如果不提供访问权限修饰符，则表示 `包访问权限`。

| 修饰符    | 类 | 包 | 子类 | 所有环境 |
| --------- | -- | -- | ---- | -------- |
| public    | Y  | Y  | Y    | Y        |
| protected | Y  | Y  | Y    | N        |
| 无修饰符  | Y  | Y  | N    | N        |
| private   | Y  | N  | N    | N        |

### 类成员

`类成员`：类中使用static修饰的成员

类成员与类关联，而不与对象关联

普通方法内部可以访问类成员，类方法中不能直接访问`普通字段和方法`且不能使用`this关键字`

### 常量

同时使用static和final定义常量

final修饰符表示字段的值`不能更改`

static保证在创建多个对象时，常量在内存中只有一份

常量名都使用大写字母

## 嵌套类

### 为什么使用嵌套类

### 静态嵌套类

## 继承（Inheritance）

`类`可以`继承`其它类的`状态`和`行为`

每个类只能有一个`直接超类`

## 接口（Interface）

接口构成了类与外界的契约。如果类声明实现一个接口，那么接口中定义的所有方法都必须出现在该类中

## 包（Package）

包是相关类和接口的`命名空间`
