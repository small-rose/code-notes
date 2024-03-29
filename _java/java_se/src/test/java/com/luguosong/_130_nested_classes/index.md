---
layout: default
title: 嵌套类
nav_order: 130
parent: JavaSE
---

# 嵌套类

{: .new-title}
> 嵌套类
>
> 定义在另一个类中的类

```java
public class OuterClass {
    //静态嵌套类
    static class StaticNestedClass {

    }

    //非静态嵌套类（内部类）
    class InnerClass {

    }
}
```

{: .note-title}
> 为什么使用嵌套类
> 
> 对单一用途的类进行逻辑分类
> 
> 提高封装度
> 
> 提高代码的可读性和可维护性

# 内部类

{: .new-title}
> 内部类
>
> 没有被static修饰的嵌套类，非静态嵌套类

- 与外部类的实例相关联
- 必须先创建外部类实例，然后再用外部类实例创建内部类实例
- 内部类不能定义除编译时常量以外的任何static成员
  - `内部类的实例`存在于`外部类的实例`里。因为`内部类`与`实例`相关联，所以内部类本身不能定义任何静态成员。
- 内部类可以直接访问外部类中的所有成员（即使被声明为private）
- 外部类可以直接访问`内部类实例`的成员变量、方法（即使被声明为private）


# 静态嵌套类

{: .new-title}
> 静态嵌套类
>
> 被static修饰的嵌套类，静态嵌套类

- 行为上就是一个顶级类，只是定义的代码写在另一个类中
- 可以直接访问外部类的私有静态成员
- 可以通过外部类对象访问外部类中的私有成员

# 局部类

{: .new-title}
> 局部类
>
> 定义在代码块中的类

- 不能定义除编译时常量以外的任何static成员
- 可以访问外部类中的所有私有成员
  - 特殊情况：当局部类处于静态代码块中时（静态代码块，静态方法），不能访问外部类的实例成员
- 只能访问`final`或`有效final`的局部变量
  - 局部类的生命周期（堆空间中）比局部变量长
  - 当访问局部变量，实际是拷贝局部变量的值。当允许局部变量修改，会导致局部类中用到的变量与局部变量不一致

{: .note-title}
> 有效final
> 
> 只被赋值一次的变量，当被第二次赋值则不再是有效final
> 
> JDK8开始有`有效final`机制
