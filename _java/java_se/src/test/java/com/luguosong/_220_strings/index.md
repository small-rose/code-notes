---
layout: default
title: 字符串
nav_order: 220
parent: JavaSE
---

# 概述

String类JDK1.8内部用`char[]`存储，自JDK1.9起采用`byte[]`存储

`字符串字面量`是`String类`的实例

# 字符串常量池（String Constant Pool）

JDK1.8中字符串常量池在堆空间中

当遇到`字符串字面量`时，会去查看常量池中是否有内容一致的对象，有直接返回。没有则在字符串常量池中创建新对象

{: .warning}
> 只有`字面量`会去常量池找，new出来的String对象并不在常量池中。

{: .note-title}
> intern()方法
>
> 查看常量池是否有一样的字符串对象
>
> 有，返回常量池对象。
>
> 没有,将对象放入常量池，返回常量池对象

# 格式化字符串

```java
class Demo {
    public static void main(String[] args) {
        System.out.println(String.format("my age is %d"), 18);
    }
}
```

# 字符串转数字

```java
class Demo {
    public static void main(String[] args) {
        Integer i1 = Integer.valueOf("12");  //通过包装类的valueOf方法
        int i2 = Integer.parseInt("12"); //使用包装类的parsexxx方法
    }
}
```

# 数字转字符串

```java
class Demo {
    public static void main(String[] args) {
        String value = String.valueOf(12.34);
        String s = Integer.toString(256);
    }
}
```

# String常用方法

```java
class Demo {
    public static void main(String[] args) {
        " 123 456 ".trim(); //去除左右空格:"123 456"
        "abc".toUpperCase(); //转为大写字母:"ABC"
        "ABC".toLowerCase(); //转为小写字母:"abc"

        "123456".contains("34"); //是否包含某个字符串：true
        "123456".indexOf("2"); //返回从前往后找子字符串位置
        "123456".lastIndexOf("2"); //返回从后往前找子字符串位置
        "123456".startsWith("123"); //是否以某个字符串开头
        "123456".endsWith("456"); //是否以某个字符串结尾

        "1_2_3_4".split("_"); //将字符串分割为数组：["1","2","3","4"]

        "abc".compareTo("adc"); //比较大小
        "abc".compareToIgnoreCase("ADC"); //忽略大小比较大小
        
        "123456".replace("123","111"); //字符串替换

        //字符串相等比较
        String s1 = "abc";
        String s2 = new String("abc");
        s1.equals(s2); //true
        "abc".equalsIgnoreCase("aBc"); //true

        "lgs123go".substring(3, 6); //字符串截取，左闭右开：123
    }
}
```

# StringBuilder类

在进行大量字符串改动操作时（拼接，替换）使用`StringBuilder类`

`StringBuilder`并不是`String`的子类，跟`String`没有关系

`StringBuilder`内部使用`字符数组`(类似于动态数组)存储内容，而不是将字符串存于`常量池`中

`StringBuilder`和`String`都实现了`CharSequence`接口。`CharSequence`是char值的可读序列,此接口提供对许多不同类型的char序列的统一、只读访问。

