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
