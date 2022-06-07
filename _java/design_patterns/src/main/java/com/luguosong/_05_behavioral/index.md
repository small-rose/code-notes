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

- `纯的职责链模式`：一个纯的职责链模式要求一个具体处理者对象只能在两个行为中选择一个：要么`承担全部责任`，要么`将责任推给下家`。不允许出现某一个具体处理者对象在承担了一部分或全部责任后又将责任向下传递的情况。而且在纯的职责链模式中，要求一个请求必须被某一个处理者对象所接收，`不能出现某个请求未被任何一个处理者对象处理的情况`。在前面的采购单审批实例中应用的是纯的职责链模式。
- `不纯的职责链模式`：在一个不纯的职责链模式中，`允许某个请求被一个具体处理者部分处理后再向下传递`，或者`一个具体处理者处理完某请求后其后继处理者可以继续处理该请求`，而且一个请求`可以最终不被任何处理者对象所接收`。

## 效果

- 优点
  - 职责链模式使得一个对象无须知道是其他哪一个对象处理其请求。对象仅需知道该请求会被处理即可，接收者和发送者都没有对方的明确信息，且链中的对象不需要知道链的结构。由客户端负责链的创建，降低了系统的耦合度。
  - 请求处理对象仅需维持一个指向其后继者的引用，而不需要维持它对所有的候选处理者的引用，可简化对象的相互连接。
  - 在给对象分派职责时，职责链可以提供更多的灵活性，可以通过在运行时对链进行动态的增加或修改来增加或改变处理一个请求的职责。
  - 在系统中增加一个新的具体请求处理者时无须修改原有系统的代码，只需要在客户端重新建链即可，从这一点来看是符合开闭原则的。
- 缺点
  - 由于一个请求没有明确的接收者，那么就不能保证它一定会被处理，该请求可能一直到链的末端都得不到处理。一个请求也可能因职责链没有被正确配置而得不到处理。
  - 对于比较长的职责链，请求的处理可能涉及多个处理对象，系统性能将受到一定影响，而且在进行代码调试时不太方便。
  - 如果建链不当，可能会造成循环调用，将导致系统陷入死循环。

## 模式适用性

- 有多个对象可以处理同一个请求，具体哪个对象处理该请求待运行时刻再确定。客户端只需将请求提交到链上，而无须关心请求的处理对象是谁以及它是如何处理的。
- 在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。
- 可动态指定一组对象处理请求。客户端可以动态创建职责链来处理请求，还可以改变链中处理者之间的先后次序。

# 命令模式（Command）

## 别名

动作（Action）模式

事务（Transaction）模式

## 模式分类

行为型模式

对象模式

## 模式概述

将一个请求封装为一个对象，从而可用不同的请求对客户进行参数化；对请求排队或者记录请求日志，以及支持可撤销的操作。

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220607103034.png)

- `Command（抽象命令类）`：抽象命令类一般是一个抽象类或接口，在其中声明了用于执行请求的execute（）等方法，通过这些方法可以调用请求接收者的相关操作。
- `ConcreteCommand（具体命令类）`：具体命令类是抽象命令类的子类，实现了在抽象命令类中声明的方法。它对应具体的接收者对象，将接收者对象的动作绑定其中。在实现execute（）方法时，将调用接收者对象的相关操作（Action）。
- `Invoker（调用者）`：调用者即请求发送者，它通过命令对象来执行请求。一个调用者并不需要在设计时确定其接收者，因此它只与抽象命令类之间存在关联关系。在程序运行时可以将一个具体命令对象注入其中，再调用具体命令对象的execute（）方法，从而实现间接调用请求接收者的相关操作。
- `Receiver（接收者）`：接收者执行与请求相关的操作，它具体实现对请求的业务处理。

## 实例代码

> 为了用户使用方便，某系统提供了一系列功能键，用户可以自定义功能键的功能，例如功能键 FunctionButton 可以用于退出系统（由SystemExitClass类来实现），也可以用于显示帮助文档（由DisplayHelpClass类来实现）。
用户可以通过修改配置文件改变功能键的用途，现使用命令模式设计该系统，使得功能键类与功能类之间解耦，可为同一个功能键设置不同的功能。

### 初始设计

功能键类FunctionButton充当请求的发送者，帮助文档处理类HelpHandler充当请求的接收者，在发送者FunctionButton的onClick（）方法中将调用接收者HelpHandler的display（）方法。

```java
//功能键类，请求发送者
class FunctionButton{
    private DisplayHelpClass help; //帮助文档处理类，请求接收者
    
    //调用DisplayHelpClass的display方法
    public void onClick(){
        help = new DisplayHelpClass();
        hele.display();  //显示帮助文档，方法耦合
    }
}
```

### 存在问题

- 由于请求发送者和请求接收者之间存在方法的直接调用，耦合度很高，更换请求接收者必须修改发送者的源代码。例如，若需要将请求接收者HelpHandler改为WindowHandler（窗口处理类），则需要修改FunctionButton的源代码，这违背了开闭原则。
- FunctionButton类在设计和实现时功能已被固定。如果增加一个新的请求接收者，在不修改原有FunctionButton类的前提下，必须增加一个新的与FunctionButton功能类似的类，这将导致系统中类的个数急剧增加。由于请求接收者HelpHandler、WindowHandler等类之间可能不存在任何关系，它们没有共同的抽象层，因此也很难依据依赖倒转原则来设计FunctionButton。
- 用户无法按照自己的需要来设置某个功能键的功能。一个功能键类的功能一旦固定，在不修改源代码的情况下无法更换其功能，系统缺乏灵活性。

### 使用命令模式实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/75826efe604351feef3120ecfdc71c6d)


```java
/**
 * 功能按键类，充当请求调用者
 * @author luguosong
 * @date 2022/6/7 10:43
 */
public class FunctionButton {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void click(){
        System.out.print("单击功能键：");
        command.execute();
    }
}

/**
 * 抽象命令类
 * @author luguosong
 * @date 2022/6/7 11:15
 */
public abstract class Command {
  public abstract void execute();
}

/**
 * 退出命令类，充当具体命令类
 * @author luguosong
 * @date 2022/6/7 11:20
 */
public class ExitCommand extends Command{
  private SystemExitClass seObj; //维持对请求接收者的引用

  public ExitCommand() {
    seObj=new SystemExitClass();
  }

  /**
   * 命令执行方法，将调用请求接收者的业务方法
   */
  @Override
  public void execute() {
    seObj.exit();
  }
}

/**
 * 帮助命令类，充当具体命令类
 *
 * @author luguosong
 * @date 2022/6/7 11:25
 */
public class HelpCommand extends Command {

  private DisplayHelpClass hcObj; //维持对请求接收者的引用

  public HelpCommand() {
    hcObj = new DisplayHelpClass();
  }

  /**
   * 命令执行方法，将调用请求接收者的业务方法
   */
  @Override
  public void execute() {
    hcObj.display();
  }
}

/**
 * 退出系统模拟实现类，充当请求接收者
 * @author luguosong
 * @date 2022/6/7 11:02
 */
public class SystemExitClass {
  public void exit(){
    System.out.println("退出系统！");
  }
}

/**
 * 显示帮助文档模拟实现类，充当请求接收者
 * @author luguosong
 * @date 2022/6/7 11:10
 */
public class DisplayHelpClass {
  public void display(){
    System.out.println("显示帮助文档！");
  }
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<config>
    <className>com.luguosong._05_behavioral._02_command_pattern.ExitCommand</className>
</config>
```

```java
/**
 * @author luguosong
 * @date 2022/6/7 17:36
 */
public class Demo {
    public static void main(String[] args) {
        FunctionButton fb = new FunctionButton();
        fb.setCommand((Command) XMLUtil.getBean("_java/design_patterns/src/main/java/com/luguosong/_05_behavioral/_02_command_pattern/config.xml").get(0));
        fb.click();
    }
}
```

```text
单击功能键：退出系统！
```

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
