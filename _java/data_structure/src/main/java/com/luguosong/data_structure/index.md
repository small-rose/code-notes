---
layout: default
title: 数据结构实现
nav_order: 10
parent: 数据结构和算法
---

# 抽象数据类型

Abstract Data Type(ADT)，数据集合及其对应的操作。与 `程序设计语言`和 `具体的实现方式`无关。

# 数据结构

Data Structure，`基于特定的语言，实现ADT的一整套完整的算法`

考虑两方面问题：

- 如何根据统一的接口规范，来定制并实现一个数据结构。
- 如何通过更加有效的算法，使得对外的接口能够更加高效的工作。

Java类库中就对ADT进行了实现，这篇博客将从头将这些实现写一遍

# Iterable接口

`Iterable接口`是java 集合框架的顶级接口,实现此接口使集合对象可以通过迭代器遍历自身元素。

```java
public interface Iterable<T> {
    
    //实现Iterable接口的集合必须提供个称为iterator的方法
    //该方法返回一个Iterator类型的对象。
    Iterator<T> iterator();
    
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
    
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
```

# Iterator接口

集合中的迭代器

`Iterator`在Java集合框架中取代了`Enumeration`。

迭代器在两个方面不同于枚举：
- 迭代器允许调用者在具有明确定义的语义的迭代期间从底层集合中删除元素。
- 方法名称已得到改进

```java
public interface Iterator<E> {

    boolean hasNext();
    
    E next();
    
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
    
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

# Collection接口

所有集合的`根接口`，对所有集合应具备的操作功能进行了规定。
