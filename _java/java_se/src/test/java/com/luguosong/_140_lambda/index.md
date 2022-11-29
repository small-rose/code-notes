---
layout: default
title: Lambda
nav_order: 140
parent: JavaSE
---

# 匿名类

{: .new-title}
> 匿名类组成
>
> - new运算符
> - 实现的接口或继承的类名
> - 将参数包含入构造器的括号
> - 类声明体

当`接口`、`抽象类`的实现类，在整个项目中只要使用一次，可以考虑使用`匿名类`。

性质和[局部类](../_130_nested_classes/index.html#局部类)类似

{: .note-title}
> 匿名类和局部类
>
> `局部类`往往是在类的设计阶段使用，`匿名类`是在类的使用阶段使用。

**常见用途：**

- 代码传递

```java
class Demo {
    interface Runnable {
        void run();
    }

    /**
     * 计算末段代码的执行时间
     *
     * @param r
     */
    private void executionTime(Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        long end = System.currentTimeMillis();
        System.out.println("代码执行时间：" + (end - start));
    }

    /**
     * 匿名类用来传递代码
     */
    @Test
    public void testCodeTransfer() {
        executionTime(new Runnable() {
            @Override
            public void run() {
                String s = "";
                for (int i = 0; i < 10000; i++) {
                    s += i;
                }
            }
        });
    }
}
```

- 过滤器

```java
class Demo {
    public interface Filter {
        boolean accept(String filename);
    }

    private static void getAllFilenames(String dir, Filter filter) {
        //获取所有文件名集合
        //File file = new File(dir);
        //String[] list = file.list();

        String[] list = {"aaa.jpg", "bbb.dwg", "ccc.dwg"};
        //过滤适合的文件名
        for (String filename : list) {
            if (filter.accept(filename)) {
                System.out.println(filename);
            }
        }
    }

    /**
     * 匿名类用作过滤器
     */
    @Test
    public void testFilter() {
        getAllFilenames("E:/", new Filter() {
            @Override
            public boolean accept(String filename) {
                return filename.contains(".dwg");
            }
        });
    }
}
```

- 回调

```java
class Demo {
    interface Callback {
        //成功回调
        void success();

        //失败回调
        void fail();
    }

    /**
     * 模拟请求调用
     *
     * @param url
     * @param callback
     */
    private void get(String url, Callback callback) {
        boolean ret = true;
        if (ret) {
            callback.success();
        } else {
            callback.fail();
        }
    }

    /**
     * 匿名类用于回调
     */
    @Test
    public void testCallBack() {
        get("xxx", new Callback() {
            @Override
            public void success() {
                System.out.println("请求成功");
            }

            @Override
            public void fail() {
                System.out.println("请求失败");
            }
        });
    }
}
```

# Lambda

Java8开始才有的语法

{: .new-title}
> 函数式接口
> 
> 只包含一个抽象方法的接口

当匿名类实现的是`函数式接口`时，可以使用`Lambda表达式`进行简化。

放Lambda中只调用一个方法，可以使用`方法引用`

# 匿名类和Lambda的区别

- `Lambda`只能用于创建函数式接口对象
- `匿名类`引入了新的作用域，`Lambda`没有引入新的作用域
