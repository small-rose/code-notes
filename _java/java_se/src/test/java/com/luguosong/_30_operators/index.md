---
layout: default
title: 操作符
nav_order: 30
parent: JavaSE
---

# 概述

`操作符`接受一个或多个参数，然后`生成一个新的值`。

几乎所有的操作符都只能操作`基本类型`。例外的是`=`、`==`和`!=`，它们也能操作对象。另外，String类也支持`+`和`+=`。

# 优先级

当多个操作符同时存在时，操作符的优先级决定了表达式的计算顺序。

当忘记优先级时，可以用括号来明确指定计算顺序。

# 赋值

操作符`=`用来赋值。

它的意思是：取等号右边的值，把它复制给等号左边

`右值`可以是任何`常量`、`变量`或者`可以产生值的表达式`。

`左值`必须是一个独特的命名变量

当`b`为基本数据类型时，`a = b`就是将`b`的内容复制给`a`。如果你接着修改了`a`，`b`并不会受这个修改的影响。

不过在给`对象`
赋值的时候，情况发生了变化。将一个对象赋值给另一个对象,其实是将这个引用从一个地方复制到另一个地方。这意味着对对象而言，`c = d`
就是将`c`和`d`都指向原本只有`d`指向的那个对象。

# 算术操作符

```java
/**
 * 算数运算符
 *
 * @author luguosong
 * @date 2022/9/15
 */
public class MathOps {
    public static void main(String[] args) {
        // 创建一个种子随机数生成器
        Random rand = new Random(47);
        int i, j, k;
        // 从1~100的范围中选择：
        j = rand.nextInt(100) + 1;
        System.out.println("j : " + j);
        k = rand.nextInt(100) + 1;
        System.out.println("k : " + k);
        i = j + k;
        System.out.println("j + k : " + i);
        i = j - k;
        System.out.println("j - k : " + i);
        i = k / j;
        System.out.println("k / j : " + i);
        i = k * j;
        System.out.println("k * j : " + i);
        i = k % j;
        System.out.println("k % j : " + i);
        j %= k;
        System.out.println("j %= k : " + j);
        // 单精度浮点数（float）测试：
        float u, v, w; // 同样适用于双精度浮点数（double）
        v = rand.nextFloat();
        System.out.println("v : " + v);
        w = rand.nextFloat();
        System.out.println("w : " + w);
        u = v + w;
        System.out.println("v + w : " + u);
        u = v - w;
        System.out.println("v - w : " + u);
        u = v * w;
        System.out.println("v * w : " + u);
        u = v / w;
        System.out.println("v / w : " + u);
        // 以下对char、byte、short、int、long和double都适用
        u += v;
        System.out.println("u += v : " + u);
        u -= v;
        System.out.println("u -= v : " + u);
        u *= v;
        System.out.println("u *= v : " + u);
        u /= v;
        System.out.println("u /= v : " + u);
    }
}
```

# 自动递增和自动递减

递增和递减操作符`不仅改变了变量`，还会把变量的值`作为生成的结果返回`。

`前缀式`:程序会先执行运算，然后返回生成的结果

`后缀式`:程序则会先返回变量的值，然后再执行运算

除了那些涉及赋值的操作符，它们是唯一具有副作用的操作符,它们会`改变操作数`，而不仅仅是使用自己的返回值。

```java
/**
 *  ++和--操作符
 *
 * @author luguosong
 * @date 2022/9/15
 */
public class AutoInc {
    public static void main(String[] args) {
        int i1 = 1;
        System.out.println(i1);
        System.out.println(i1++); //先返回变量的值，然后再执行运算
        System.out.println(i1);

        int i2 = 1;
        System.out.println(i2);
        System.out.println(++i2); //先执行运算，然后返回生成的结果
        System.out.println(i2);
    }
}
```

# 关系操作符

关系操作符会根据操作数的值之间的关系生成一个`布尔结果`。

`equals()`方法的默认行为是比较`引用`。如果只想比较内容，你必须重写`equals()`方法。

```java
/**
 * 对象是否相等，Integer为例
 *
 * @author luguosong
 * @date 2022/9/15
 */
public class Equivalence {
    static void show(String desc, Integer n1, Integer n2) {
        System.out.println(desc + ":");
        System.out.printf(
                "%d==%d %b %b%n", n1, n2, n1 == n2, n1.equals(n2));
    }

    /**
     * [1] 自动转换为Integer。这其实是通过对Integer.valueOf()的自动调用来完成的。
     * [2] 使用标准的对象创建语法new。这是以前创建“包装/装箱”Integer对象的首选方法。
     * [3] 从Java 9开始，valueOf()优于[2]。如果尝试在Java 9中使用方式[2]，你将收到警告，并被建议使用[3]代替。很难确定[3]是否的确优于[1]，不过[1]看起来更简洁。
     * [4] 基本类型int也可以当作整数值对象使用。
     *
     * @param value
     */
    //@SuppressWarnings("deprecation")
    public static void test(int value) {
        Integer i1 = value;                             // [1]
        Integer i2 = value;
        show("Automatic", i1, i2);
        // 在Java 9及更新版本中已被弃用的旧方式：
        Integer r1 = new Integer(value);                // [2]
        Integer r2 = new Integer(value);
        show("new Integer()", r1, r2);
        // Java 9及更新版本中提倡的方式：
        Integer v1 = Integer.valueOf(value);            // [3]
        Integer v2 = Integer.valueOf(value);
        show("Integer.valueOf()", v1, v2);
        // 基本类型不能使用equals()方法:
        int x = value;                                  // [4]
        int y = value;
        // x.equals(y); // 无法编译
        System.out.println("Primitive int:");
        System.out.printf("%d==%d %b%n", x, y, x == y);
    }

    public static void main(String[] args) {
        test(127);
        System.out.println("----------");

        //出于效率原因，Integer会通过享元模式来缓存范围在-128~127内的对象
        // 因此多次调用Integer.valueOf(127)生成的其实是同一个对象。
        // 而在此范围之外的值则不会这样
        test(128);
    }
}
```

```shell
Automatic:
127==127 true true
new Integer():
127==127 false true
Integer.valueOf():
127==127 true true
Primitive int:
127==127 true
----------
Automatic:
128==128 false true
new Integer():
128==128 false true
Integer.valueOf():
128==128 false true
Primitive int:
128==128 true
```

# 逻辑操作符

逻辑操作符`与（&&）`、`或（||）`和`非（!）`可以根据参数的逻辑关系，生成一个true或false的布尔值结果。

逻辑操作符只能应用于`布尔值`

逻辑操作符支持一种称为`短路`的现象。一旦表达式当前部分的计算结果能够明确无误地确定整个表达式的值，表达式余下部分就不会被执行了。

# 按位操作符

按位操作符用来操作整数基本数据类型中的`单个二进制位（bit）`。

按位操作符会对两个参数中对应的二进制位执行布尔代数运算，并生成一个结果。

```java
/**
 * 按位操作符
 * @author luguosong
 * @date 2022/9/15
 */
public class Bitwise {
    public static void main(String[] args) {
        //11
        System.out.println(Integer.toBinaryString(3));
        //101
        System.out.println(Integer.toBinaryString(5));
        //按位“与”操作符
        //11 & 101 = 1
        System.out.println(Integer.toBinaryString(3 & 5));
        //按位“或”操作符
        //11 | 101 =111
        System.out.println(Integer.toBinaryString(3 | 5));
        //按位“异或”操作符,两边不一样返回1
        //11^101 =110
        System.out.println(Integer.toBinaryString(3 ^ 5));
        //按位“非 ”操作符
        //~101 = 11111111111111111111111111111010
        System.out.println(Integer.toBinaryString(~5));
    }
}
```

# 移位操作符

移位操作符也操纵二进制位，它们只能用来处理基本类型里的整数类型。

`左移位操作符（<<）`:将操作符左侧的操作数向左移动，移动的位数在操作符右侧指定（低位补0）。

`有符号的右移位操作符（>>）`:则按照操作符右侧指定的位数将操作符左侧的操作数向右移动。如果符号为正，则在高位插入`0`
，否则在高位插入`1`。

`无符号的右移位操作符（>>>）`:使用“零扩展”：无论符号为正还是为负，都在高位插入`0`。

# 三元操作符

`三元操作符`也叫`条件操作符`，它比较特别，因为有三个操作数。

```
boolean-exp ? value0 : value1
```

如果`boolean-exp`运行的结果为`true`，`value0`就会被执行，其结果会被当作这个三元操作符的结果值。如果boolean-exp的结果为false，value1就会被执行，其结果同样会被当作最终结果。

# 字符串操作符+和+=

`+`和`+=`操作符都可以连接字符串。

如果与字符串连接的内容不是字符串，会自动转换为字符串。

# 类型转换操作符

`窄化转型（narrowing conversion）`
是将能容纳更多信息的数据类型转换成无法容纳那么多信息的数据类型。此时，编译器会要求我们进行`强制类型转换`。

而对于`宽化转型（widening conversion）`，则不必显式地进行类型转换，因为新类型可以容纳比原来的类型更多的信息，而不会造成任何信息的丢失。

Java可以把`任何基本类型`转换成别的`基本类型`，但`boolean除外`，它不允许进行任何类型的转换处理。

```java
public class Casting {
    public static void main(String[] args) {
        int i = 200;
        long lng = (long) i;
        lng = i; // 宽化，因此不需要强制类型转换
        long lng2 = (long) 200;
        lng2 = 200;
        // 一个窄化转型
        i = (int) lng2; // 需要强制类型转换
    }
}
```

将`float`或`double`转型为整型值时，总是对该数值执行`截尾`。

```java
public class CastingNumbers {
    public static void main(String[] args) {
        double above = 0.7;
        System.out.println("(int)above: " + (int) above);
    }
}
/* 输出：
(int)above: 0
*/
```

如果想要对结果进行四舍五入，就需要使用java.lang.Math中的`round()`方法

```java
public class RoundingNumbers {
    public static void main(String[] args) {
        double above = 0.7;
        System.out.println("Math.round(above): " + Math.round(above));
    }
}
/* 输出：
Math.round(above): 1
*/
```

对结果进行向上取整，就需要使用java.lang.Math中的`ceil()`方法

```java
public class CeilingNumbers {
    public static void main(String[] args) {
        double d = 0.4;
        System.out.println((int) Math.ceil(d));
    }
}

/* 输出：
1
*/
```

如果对小于int类型的基本数据类型（即char、byte或者short）执行`算术运算`或`按位运算`，运算`执行前`这些值就会被`自动提升为int`，`结果也是int类型`。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220919163854.png)

表达式里出现的`最大的数据类型`决定了表达式`最终结果的数据类型`。如果将一个`float`类型的值与一个`double`类型的值相乘，结果就是`double`类型。如果将一个`int`值和一个`long`值相加，则结果为`long`类型。


