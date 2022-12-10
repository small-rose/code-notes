---
layout: default
title: 异常
nav_order: 210
parent: JavaSE
---

# 使用异常机制的好处

- 将错误代码与普通代码分隔开
- 将错误信息传播到调用堆栈中（往调用者传递）
- 能对错误信息进行区分和分组

# 异常分类

`检查型异常(Checked Exception)`：`编译器`会进行检查，如果没有手动处理这些异常，编译器将会报错
`非检查型异常（UnChecked Exception）`：`编译器`不会进行检查，如果开发者没有处理这些异常，编译器不会报错

下图`绿色`部分为`检查型异常`，`蓝色`部分为`非检查型异常`：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221201225559.png)

# 异常处理

只有`检查型异常`才需要处理

遇到异常不处理，程序会`终止运行`

# try-catch

捕获异常进行处理

try内部的代码会`中断执行`，try-catch之后的代码可以`继续执行`

`printStackTrace()`:打印调用轨迹
`getMessage()`:返回详细消息

# throw

手动向外部抛出异常

除了finally中的代码，之后的代码都不会再执行了。

```java
class Demo {
    @Test
    public void testThrow() throws ClassNotFoundException {
        try {
            Class.forName("AAA");
            System.out.println("异常后的方法");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("手动抛出非检查型异常");
        } finally {
            System.out.println("finally执行");
        }
        System.out.println("外部方法");
    }
}
```

# throws

将异常抛给调用者处理

# finally

try或catch正常执行完毕后，一定会执行finally中的代码。

一般在其中编写关闭、释放资源的代码。

# 自定义异常

- 自定义检查型异常
  - 继承`Exception`
- 自定义非检查型异常
  - 继承`RuntimeException`
