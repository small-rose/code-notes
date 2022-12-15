---
layout: default
title: 正则表达式
nav_order: 290
parent: JavaSE
---

# Patten类、Matcher类

完全匹配和局部匹配

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Demo {
    public static void main(String[] args) {
        //要求完全匹配方式一
        boolean b = "dbcacbd".matches("[abc]");

        //要求完全匹配方式二
        boolean matches = Pattern.matches("[abc]", "dbcacbd");

        Pattern pattern = Pattern.compile("[abc]");
        Matcher matcher = pattern.matcher("dbcacbd");

        //要求完全匹配方式三
        matcher.matches();

        //局部匹配
        if (matcher.find()) {
            System.out.println("位置：" + matcher.start() + "-" + matcher.end());
        }
    }
}
```

# 单字符匹配

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221207165841.png)

# 预定义字符

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221207165913.png)

# 量词(Quantifier)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221207181621.png)

- `贪婪`：先将整个字符串吞掉进行匹配，匹配失败吐出最后一个字符再次尝试
- `勉强`：先吞掉第一个字符匹配，匹配失败再吞进下一个字符
- `独占`：吞掉整个字符串，进行唯一一次匹配

# 捕获组（Capturing Group）

```java
class Demo {
    /**
     * 捕获组
     */
    @Test
    public void testCapturingGroup() {
        System.out.println("dogdogdog".matches("(dog){3}"));  //true
        System.out.println("dog".matches("(o|d|g){3}")); //true
        //使用反斜杠+组编号引用组的内容
        System.out.println("mjPKPKmj".matches("([a-z]{2})([A-Z]{2})\\2\\1"));
        System.out.println("I Love You Love You Love You".matches("((I)( Love( You)))\\3{2}")); //true
    }
}
```

# 边界匹配符(Boundary Matcher)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221211224336.png)

```java
class Demo {
    /**
     * 边界匹配符
     */
    @Test
    public void testBoundaryMatcher() {
        //表示单词边界为d
        Pattern pattern = Pattern.compile("\\bd..");
        Matcher matcher = pattern.matcher("dog dad cat day");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
```

**结果：**

```text
dog
dad
day
```

# 常用模式

- `DOTALL`：单行模式（可以匹配任意字符，包括终止符）,等价于`(?s)`
- `MULTILINE`：多行模式（^、$才能真正匹配一行的开头和结尾）,等价于`(?m)`
- `CASE_INSENSITIVE`：不区分大小写,等价于`(?i)`

```java
class Demo {
    @Test
    public void testModel() {
        //不区分大小写，方式一
        System.out.println("Dog".matches("(?i)dog"));

        //不区分大小写：方式二
        Pattern pattern = Pattern.compile("dog", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("Dog");
        System.out.println(matcher.find());
    }
}
```
 
