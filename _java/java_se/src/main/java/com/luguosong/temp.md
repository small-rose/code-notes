
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

# 类（Classes）

## 什么是类

## 声明类

## 声明成员变量

## 定义方法

## 为类提供构造函数

## 将消息传给方法或构造函数

## 方法返回值

## this关键字

## 类成员访问权限控制符

## 类成员（静态成员）

## 字段初始化

# 对象（Objects）

## 什么是对象

## 创建对象

## 使用对象

# 嵌套类

## 为什么使用嵌套类

## 静态嵌套类

## 内部类

## 覆盖（Shadowing）

## 序列化（Serialization）

## 内部类实例

## 局部类（Local Classes）

## 匿名类（Anonymous Classes）

## Lambda表达式

## 何时使用嵌套类、局部类、匿名类和Lambda表达式

# 注解（Annotations）

## 注解格式

## 注解使用场景

## 声明注解类型

## 预定义注解类型

## 类型注解和可插拔类型系统

## 重复注解

# 接口（Interfaces）

## 什么是接口

## 将接口用作API

## 定义接口

## 实现接口

## 将接口用作类型

## 进化接口

## 默认方法

# 继承（Inheritance）

## 什么是继承

## Java平台中类的层次结构

## 继承实例

## 子类能做什么

## 超类的私有成员

## 转换对象

## 状态、实现和类型的多重继承

## 覆盖和屏蔽方法

## 多态性

## 屏蔽字段

## 使用super关键字

## 将对象用作超类

## 编写final类和方法

## 抽象方法和类

# Numbers类

## 概述

## 格式化数字打印输出

## 其他数学运算方法

## 自动装箱和拆箱

# 字符和转义字符

# String类

## 创建字符串

## 字符串长度

## 字符串连接

## 创建格式字符串

## 数字和字符串之间转换

## 操作字符串中的字符

## 比较字符串和字符串的子串

## StringBuilder类

# 泛型（Generics）

## 为什么使用泛型

## 泛型类型

## 泛型方法

## 受限类型形式参数

## 泛型、继承和子类型

## 类型推导

## 通配符

## 类型擦除

## 泛型的局限性

# 包（Packages）

## 什么是包

## 包的创建

## 包的命名

## 包成员的使用

## 源文件和类文件管理
