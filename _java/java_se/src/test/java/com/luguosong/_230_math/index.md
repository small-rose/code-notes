---
layout: default
title: 数学类
nav_order: 230
parent: JavaSE
---

# Math类

## 常数

```java
class Math {
    //自然常数，自然对数函数的底数
    public static final double E = 2.7182818284590452354;
    //圆周率
    public static final double PI = 3.14159265358979323846;
}
```

## 常用方法

```java
class Demo {
    public static void main(String[] args) {
        Math.abs(-100); //求绝对值
        Math.max(100, 200); //求最大值：200
        Math.min(100, 200); //求最小值：100
        Math.floor(3.9); //向下取整：3.0
        Math.ceil(3.1); //向上取整：4.0
        Math.round(3.5); //四舍五入：4
        Math.pow(4, 2); //4的2次方：16.0
        Math.sqrt(16); //16的平方根：4.0

        Math.exp(2); //E的2次方
        Math.log(8); //求ln8的值，以E为底数、8为真数的对数
        double degree = 90; //角度
        double radian = Math.toRadians(degree); //弧度
        //三角函数
        Math.sin(radian);
        Math.cos(radian);
        Math.tan(radian);

        Math.random(); //生成[0.0，1.0)范围的随机数
    }
}
```

# Random类

```java
import java.util.Random;

class Demo {
    public static void main(String[] args) {
        Random random = new Random();
        random.nextInt(100);  //生成[0,100)之间的随机数
    }
}
```

# UUID类

```java
import java.util.UUID;

class Demo {
    public static void main(String[] args) {
        UUID.randomUUID();  //生成UUID
    }
}
```

# 高精度计算

```java
class Demo {
    /**
     * 高精度计算
     */
    @Test
    public void testBigDecimal() {
        System.out.println("不使用精确计算无法正确的表示小数：" + 0.7 * 0.7);
        System.out.println("精度计算：" + new BigDecimal("0.7").multiply(new BigDecimal("0.7")));
    }
}
```

```text
不使用精确计算无法正确的表示小数：0.48999999999999994
精度计算：0.49
```

