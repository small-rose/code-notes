---
layout: default
title: 数组
nav_order: 180
parent: JavaSE
---

# 概述

数组是`固定数目`的`单一数据类型`值的容器对象。创建数组时要指定其长度。创建后，其长度就固定了。

数组中的每个项都称作`元素(element)`,每个元素都用数字`索引(index)`访问。

# 数组声明

数组声明包含两个组件：`数组类型`+`数组名`

数组的类型写作：`type[]`

```java
class Demo {
    int[] anArray;
}
```

# 创建、初始化和访问数组

```java
public class HelloArrays {

    public static void main(String[] args) {
        //方式一
        //创建数组
        int[] anArray1 = new int[10];
        //初始化数组
        anArray1[0] = 100;
        //访问数组
        System.out.println(anArray1[0]);

        //方式二
        //创建并初始化数组
        int[] anArray2 = {100, 200, 300};
        //访问数组
        System.out.println(anArray2[0]);
    }
}
```

# 多维数组

可以用两个或多个方括号声明`多维数组`。

```java
class MultiDimArrayDemo {
    public static void main(String[] args) {
        String[][] names = {
            {"Mr. ", "Mrs. ", "Ms. "},
            {"Smith", "Jones"}
        };
        // Mr. Smith
        System.out.println(names[0][0] + names[1][0]);
        // Ms. Jones
        System.out.println(names[0][2] + names[1][1]);
        //2
        System.out.println(names.length);
    }
}
```

# 数组复制

`System类`有`arraycopy方法`，可用于数组之间数据的高效复制。

```java
public final class System {
    /**
     * src     - 源数组。 
     * srcPos  – 源数组中的起始位置。 
     * dest    - 目标数组。 
     * destPos – 目标数据中的起始位置。 
     * length  – 要复制的数组元素的数量
     */
    public static native void arraycopy(Object src,  
                                        int  srcPos,
                                        Object dest, 
                                        int destPos,
                                        int length);
}
```

```java
/**
 * 数组复制
 *
 * @author luguosong
 * @date 2022/9/14
 */
public class ArrayCopyDemo {
    public static void main(String[] args) {
        char[] copyFrom = {
                'd', 'e', 'c', 'a', 'f', 'f', 'e', 'i', 'n', 'a', 't', 'e', 'd' };
        char[] copyTo = new char[7];
        //调用arraycopy复制数组
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        System.out.println(new String(copyTo));
    }
}
```

```shell
caffein
```


# 数组操作

`java.util.Arrays`类中提供了一些数组的操作方法

```java
/**
 * 数组操作工具类
 *
 * @author luguosong
 * @date 2022/9/14
 */
public class ArraysUtilDemo {
    public static void main(String[] args) {
        char[] copyFrom = {
                'd', 'e', 'c', 'a', 'f', 'f', 'e', 'i', 'n', 'a', 't', 'e', 'd'};
        char[] copyTo = new char[7];

        //数组复制
        copyTo = Arrays.copyOfRange(copyFrom, 2, 9);
        System.out.println(new String(copyTo));

        //在数组中搜索特定值，并返回位置索引
        int index = Arrays.binarySearch(copyFrom, 'e');
        System.out.println(index);

        //比较两个数组是否相等
        boolean equals = Arrays.equals(copyFrom, copyTo);
        System.out.println(equals);

        //将指定位置上的元素替换成指定值
        Arrays.fill(copyTo, 0, 2, 'z');
        System.out.println(new String(copyTo));

        //将全部元素替换成指定值
        Arrays.fill(copyTo,'x');
        System.out.println(new String(copyTo));

        //排序
        Arrays.sort(copyFrom);
        //多处理器系统中大数组的并发排序比顺序排序要快
        Arrays.parallelSort(copyFrom);
        System.out.println(new String(copyFrom));
    }
}
```
