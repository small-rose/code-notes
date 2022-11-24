---
layout: default
title: 控制流语句
nav_order: 50
parent: JavaSE
---

# if-else

```
if(Boolean-expression)
    statement
else
    statement
```

# while

在循环刚开始时会计算一次布尔表达式的值，而在下一次迭代之前会再计算一次。

```
while(Boolean-expression) 
    statement
```

# do-while

`do-while`中的语句至少会执行一次，即便表达式的第一次计算结果就是false。

```
do
  statement
while(Boolean-expression);
```

# for

```
for(initialization; Boolean-expression; step)
  statement
```

通过使用`逗号操作符`，你可以在for语句里定义多个变量，但它们必须是相同的类型:

```java
public class CommaOperator {
  public static void main(String[] args) {
    for(int i = 1, j = i + 10; i < 5; i++, j = i * 2) {
      System.out.println("i = " + i + " j = " + j);
    }
  }
}
```

# for-in

Java 5引入了一种更加简洁的for语法，可以用于`数组`和`容器`。

这种语法有时候叫作`增强的for（enhanced for）`。

```java
import java.util.*;
public class ForInFloat {
  public static void main(String[] args) {
    Random rand = new Random(47);
    float[] f = new float[10];
    for(int i = 0; i < 10; i++)
      f[i] = rand.nextFloat();
    //for-in
    for(float x : f)
      System.out.println(x);
  }
}
```

# return、break和continue

- return
  - 指定一个方法的返回值（如果不存在就返回void）
  - 还会导致当前的方法退出，并且返回这个值
- break
  - 在任何迭代语句的主体部分,break会直接`退出循环`，不再执行循环里的剩余语句。
- continue
  - 在任何迭代语句的主体部分,continue则会`停止执行当前的迭代`，然后退回循环开始位置`执行下一次迭代`。

# 标签跳转

在Java里使用标签的唯一理由就是你用到了`嵌套循环`，而且你需要使用`break`或`continue`来跳出多层的嵌套。

在Java中，放置标签的`唯一地方`是在迭代语句之前。`不要在标签和迭代之间插入任何语句`。

在迭代之前使用标签的唯一原因是，你要在这个迭代里再嵌套一个迭代或一个switch（很快就会学到它）。这是因为break和continue通常只会中断当前循环，但和标签一起用时，它们可以中断这个嵌套的循环，直接跳转到标签所在的位置

```
label1:
outer-iteration {
  inner-iteration {
    // ...
    break;           // [1]
    // ...
    continue;        // [2]
    // ...
    continue label1; // [3]
    // ...
    break label1;    // [4]
  }
}
```

- `[1]` 这里的break中断内部迭代，回到外部迭代。
- `[2]` 这里的continue中断当前执行，回到内部迭代的开始位置。
- `[3]` 这里的continue label1会同时中断内部迭代以及外部迭代，直接跳到label1处，然后它实际上会`重新进入`外部迭代开始继续执行。
- `[4]` 这里的break label1也会中断所有迭代，跳回到label1处，不过它并不会重新进入外部迭代。它实际是完全跳出了两个迭代。


1. 普通的continue会跳到最内层循环的起始处，并继续执行。
1. 带标签的continue会跳到对应标签的位置，并`重新进入`这个标签后面的循环。
1. 普通的break会`跳出循环的底部`，也就是跳出当前循环。
1. 带标签的break会`跳出标签所指的循环`。

# switch

switch有时也叫作`选择语句`。根据整数表达式的值，switch语句从多个代码片段中选择一个去执行。

```
switch(integral-selector) {
  case integral-value1 : statement; break;
  case integral-value2 : statement; break;
  case integral-value3 : statement; break;
  case integral-value4 : statement; break;
  case integral-value5 : statement; break;
  // ...
  default: statement;
}
```

其中，`整数选择器（integral-selector）`是一个能生成整数值的表达式(在Java 7及之后的版本中，`字符串`也可以用作选择器)，switch将这个表达式的结果与每个`整数值（integral-value）`相比较。如果发现相等，就执行对应的语句（单条语句或多条语句，不要求使用花括号包围）。若没有发现相等，就执行`默认（default）`语句。定义里每个case都用一个`break`结尾，它会让执行流程跳到switch主体的末尾。





