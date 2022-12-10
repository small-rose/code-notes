---
layout: default
title: 正则表达式
nav_order: 290
parent: JavaSE
---
# 单字符匹配

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221207165841.png)

# 预定义字符

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221207165913.png)

# 量词

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221207181621.png)

- `贪婪`：先将整个字符串吞掉进行匹配，匹配失败吐出最后一个字符再次尝试
- `勉强`：先吞掉第一个字符匹配，匹配失败再吞进下一个字符
- `独占`：吞掉整个字符串，进行唯一一次匹配

# Patten类、Matcher类

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



 
