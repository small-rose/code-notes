---
layout: default
title: 行为模式(Behavioral Patterns)
nav_order: 5
parent: 设计模式（Design Pattern）
---

# 行为型模式概述

关注对象或类之间的相互作用和职责划分

- `类行为模式`：使用继承关系在几个类之间分配行为，主要通过多态等方式来分配父类与子类的职责
- `对象行为模式`：使用对象的关联关系来分配行为，主要通过对象关联等方式来分配两个或多个类的职责


# 职责链模式（Chain of Responsibility）

## 模式分类

行为模式

对象模式

## 模式概述

避免将请求发送者与接收者耦合在一起，让多个对象都有机会接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220606111102.png)

- `Handler（抽象处理者）`：它定义了一个处理请求的接口，一般设计为抽象类。由于不同的具体处理者处理请求的方式不同，因此在其中定义了`抽象请求处理方法`。因为每个处理者的下家还是一个处理者，因此在抽象处理者中定义了一个`抽象处理者类型的对象`（结构图中的successor），作为其对下家的引用。通过该引用，处理者可以连成一条链。
- `ConcreteHandler（具体处理者）`：它是抽象处理者的子类，可以处理用户请求。在具体处理者类中实现了抽象处理者中定义的抽象请求处理方法，在处理请求之前需要进行判断，看是否有相应的处理权限，如果可以处理请求就处理它，否则将请求转发给后继者。在具体处理者中可以访问链中下一个对象，以便请求的转发。

## 实例代码

> Sunny软件公司承接了某企业SCM（Supply Chain Management，供应链管理）系统的开发任务，其中包含一个采购审批子系统。该企业的采购审批是分级进行的，即根据采购金额的不同由不同层次的主管人员来审批。主任可以审批5万元以下（不包括5万元）的采购单，副董事长可以审批5万～10万元（不包括10万元）的采购单，董事长可以审批10万～50万元（不包括50万元）的采购单，50万元及以上的采购单就需要开董事会讨论决定。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220606112132.png)

### 初始方案

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220606112410.png)

### 存在问题

- PurchaseRequestHandler类较为庞大，各个级别的审批方法都集中在一个类中，`违反了单一职责原则`，测试和维护难度大。
- 如果需要增加一个新的审批级别或调整任何一级的审批金额和审批细节（例如将董事长的审批额度改为60万元）时都必须修改源代码并进行严格测试。此外，如果需要移除某一级别（例如金额为10万元及以上的采购单直接由董事长审批，不再设副董事长一职）时也必须对源代码进行修改，`违反了开闭原则`。
- 审批流程的设置`缺乏灵活性`。现在的审批流程是“主任→副董事长→董事长→董事会”，如果需要改为“主任→董事长→董事会”，在此方案中只能通过修改源代码来实现，客户端无法定制审批流程。

### 使用责任链模式

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220606112823.png)

```java
/**
 * 采购单类，充当请求类
 *
 * @author luguosong
 * @date 2022/6/6 13:39
 */
public class PurchaseRequest {
    private double amount; //采购金额
    private int number; //采购单编号
    private String purpose; //采购目的

    public PurchaseRequest(double amount, int number, String purpose) {
        this.amount = amount;
        this.number = number;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}

/**
 * 审批者类，充当抽象处理者
 *
 * @author luguosong
 * @date 2022/6/6 13:46
 */
public abstract class Approver {
    protected Approver successor;
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    //抽象请求处理方法
    public abstract void processRequest(PurchaseRequest request);
}

/**
 * 主任类，充当具体处理者
 * @author luguosong
 * @date 2022/6/6 14:08
 */
public class Director extends Approver {
    public Director(String name) {
        super(name);
    }

    /**
     * 具体请求处理方法
     * @param request
     */
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 50000) {
            System.out.println("主任" + this.name +
                    "审批采购单：" + request.getNumber() +
                    ",金额：" + request.getAmount() +
                    "元，采购目的：" + request.getPurpose());
            //处理请求
        } else {
            this.successor.processRequest(request);
        }
    }
}

/**
 * 副董事长类，充当具体处理者
 * @author luguosong
 * @date 2022/6/6 14:16
 */
public class VicePresident extends Approver{
    public VicePresident(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 100000) {
            System.out.println("副董事长" + this.name +
                    "审批采购单：" + request.getNumber() +
                    ",金额：" + request.getAmount() +
                    "元，采购目的：" + request.getPurpose());
            //处理请求
        } else {
            this.successor.processRequest(request);
        }
    }
}

/**
 * 董事长类，充当具体处理者
 * @author luguosong
 * @date 2022/6/6 14:26
 */
public class President extends Approver{
    public President(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 500000) {
            System.out.println("董事长" + this.name +
                    "审批采购单：" + request.getNumber() +
                    ",金额：" + request.getAmount() +
                    "元，采购目的：" + request.getPurpose());
            //处理请求
        } else {
            this.successor.processRequest(request);
        }
    }
}

/**
 * 董事会类，充当具体处理者
 * @author luguosong
 * @date 2022/6/6 14:37
 */
public class Congress extends Approver{
    public Congress(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        System.out.println("召开董事会审批采购单：" +
                request.getNumber() +
                ",金额：" + request.getAmount() +
                "元，采购目的：" + request.getPurpose());
        //处理请求
    }
}
```

```java
/**
 * 测试类
 * @author luguosong
 * @date 2022/6/6 18:32
 */
public class Demo {
    public static void main(String[] args) {
        Approver wjzhang,gyang,jguo,meeting;
        wjzhang = new Director("张无忌");
        gyang = new VicePresident("杨过");
        jguo = new President("郭靖");
        meeting = new Congress("董事会");

        //创建职责链
        wjzhang.setSuccessor(gyang);
        gyang.setSuccessor(jguo);
        jguo.setSuccessor(meeting);

        //创建采购单
        PurchaseRequest pr1 = new PurchaseRequest(45000,10001,"购买倚天剑");
        wjzhang.processRequest(pr1);

        PurchaseRequest pr2 = new PurchaseRequest(60000,10002,"购买《葵花宝典》");
        wjzhang.processRequest(pr2);

        PurchaseRequest pr3 = new PurchaseRequest(160000,10003,"购买《金刚经》");
        wjzhang.processRequest(pr3);

        PurchaseRequest pr4 = new PurchaseRequest(800000,10004,"购买桃花岛");
        wjzhang.processRequest(pr4);
    }
}
```

## 模式拓展

## 效果

## 模式适用性

# 命令模式（Command）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 解释器模式（Interpreter）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 迭代器模式（Iterator）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 中介者模式（Mediator）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 备忘录模式（Memento）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 观察者模式（Observer）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 状态模式（State）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 策略模式（Strategy）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 模板方法模式（Template Method）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性

# 访问者模式（Visitor）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性
