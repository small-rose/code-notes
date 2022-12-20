---
layout: default
title: 对象之间的关系
nav_order: 120
parent: JavaSE
---

# 依赖

如果修改一个类的定义可能会造成另一个类的变化，那么这两个类之间就存在`依赖关系`。

当你在代码中`使用`具体类的名称时，通常意味着存在`依赖关系`。

比如方法参数列表中使用，方法内局部变量使用，字段使用等等。都属于`依赖关系`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220919101906.png)

通常情况下，UML图`不会展示所有依赖`——它们在真实代码中的`数量太多`了。为了不让依赖关系破坏UML图，你必须对其进行精心选择，仅展示那些对于沟通你的想法来说重要的依赖关系。

# 关联

关联是一个对象使用另一对象或与另一对象进行交互的关系。

关联可视为一种特殊类型的依赖，即一个对象`总是`拥有访问与其交互的对象的权限，而简单的`依赖关系`
并`不会在对象间建立永久性的联系`。比如说局部变量并非永久性联系，只有使用到该方法时才会建立依赖关系。

一般来说可以使用关联关系来表示`类成员变量`

```java
class Professor {
    //教授和学生时关联关系
    private Student student;

    //教授和课程时依赖关系
    public void teach(Course c) {
        this.student.remember(c.getKnowledge);
    }
}
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220919112441.png)

# 聚合

通常在`聚合关系`中，一个对象`拥有一组`其他对象，并扮演着容器或集合的角色。

`聚合`是一种特殊类型的`关联`

`关联`和`被关联`对象之间没有包含的关系，而`聚合`有这层关系。

下图`院系`包含`教授`:

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220919141943.png)

# 组合

组合是一种特殊类型的聚合，其中一个对象由一个或多个其他对象实例`构成`。组合与其他关系的区别在于组件仅能作为容器的一部分存在。

`被聚合`可以独立于`聚合`存在，而`被组合`是`组合`的一部分不能独立存在。

下图`大学`由`院系`构成,院系不能独立于大学独立存在：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220919145612.png)

# 总结

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220919144812.png)

- `依赖`：对类 B 进行修改会影响到类 A 。 
- `关联`：对象 A 知道对象 B。类 A 依赖于类 B。
- `聚合`：对象 A 知道对象 B 且由 B 构成。类 A 依赖于类 B。 
- `组合`：对象 A 知道对象 B、由 B 构成而且管理着 B 的生命周期。类 A 依赖于类 B。 
- `实现`：类 A 定义的方法由接口 B 声明。对象 A 可被视为对象B。类 A 依赖于类 B。 
- `继承`：类 A 继承类 B 的接口和实现， 但是可以对其进行扩展。对象 A 可被视为对象 B。类 A 依赖于类 B。


举例说明：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/design_14.png)
