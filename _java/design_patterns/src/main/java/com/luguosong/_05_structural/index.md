---
layout: default
title: 结构型模式（Structural patterns）
nav_order: 4
parent: 设计模式（Design Pattern）
---

# 适配器模式（Adapter）

## 别名

包装器（Wrapper）模式

## 模式分类

结构型模式

既可以作为 `类模式`，也可以作为 `对象模式`

## 模式概述

将一个类的接口转换成客户希望的另一个接口。适配器模式让那些接口不兼容的类可以一起工作

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/2cc49c5e2612df2547699803e2270fd3)

适配器模式包括 `类适配`器和 `对象适配器`：

- `类适配器`：`适配器`与 `适配者`之间是继承或实现关系
- `对象适配器`：`适配器`与 `适配者`之间是关联关系

组成成分：

- `Target(目标抽象类)`：定义客户端所需要的接口。如果是 `类适配器`模式，由于Java中不能多继承，因此 `目标抽象类`只能是接口
- `Adapter(适配器类)`：将 `Adaptee(适配者类)`与 `Target(目标抽象类)`进行关联与适配
- `Adaptee(适配者类)`：被适配的对象。这个类中的方法实现具体功能，但可能 `目标抽象类`的接口不匹配,因此需要适配器类进行匹配

## 实例代码

> 某公司开发一款儿童汽车，在移动过程中伴随着灯光闪烁和声音提示。该公司在以往产品中已经实现了警灯闪烁和警笛声的程序。
> 为了重用先前的代码并且使汽车控制软件具有更好的灵活性和拓展性，使用适配器模式设计该玩具汽车控制软件。

```java
/**
 * 汽车控制类，充当目标抽象类
 *
 * @author 10545
 * @date 2022/5/2 17:07
 */
public abstract class CarController {
    public void move() {
        System.out.println("玩具汽车移动！");
    }

    public abstract void phonate();

    public abstract void twinkle();
}

/**
 * 警笛声，充当适配者
 *
 * @author 10545
 * @date 2022/5/2 17:43
 */
public class PoliceSound {
    public void alarmSound() {
        System.out.println("发出警笛声");
    }
}

/**
 * 警灯类，充当适配者
 *
 * @author 10545
 * @date 2022/5/2 17:53
 */
public class PoliceLamp {
    public void alarmLamp() {
        System.out.println("呈现警灯闪烁");
    }
}

/**
 * 警车适配器，充当适配器
 *
 * @author 10545
 * @date 2022/5/2 20:49
 */
public class PoliceCarAdapter extends CarController {
    private PoliceSound sound;
    private PoliceLamp lamp;

    /**
     * 适配器构造
     */
    public PoliceCarAdapter() {
        sound = new PoliceSound();
        lamp = new PoliceLamp();
    }


    @Override
    public void phonate() {
        //调用适配者类PoliceSound的方法
        sound.alarmSound();
    }

    @Override
    public void twinkle() {
        //调用适配者类PoliceLamp的方法
        lamp.alarmLamp();
    }
}
```

```xml
<?xml version="1.0" encoding="utf-8" ?>
<config>
    <className>cn.com.lgs.adapter_pattern.PoliceCarAdapter</className>
</config>
```

```java
/**
 * 工具类
 *
 * @author 10545
 * @date 2022/5/2 21:03
 */
public class XMLUtil {
    /**
     * 从xml配置文件中提取具体类的类名，并返回一个实例对象
     *
     * @return
     */
    public static Object getBean() {
        try {
            //创建DOM文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("_java/design-pattern/src/main/java/cn/com/lgs/adapter_pattern/config.xml"));

            //获取包含类名的文件节点
            NodeList n1 = doc.getElementsByTagName("className");
            Node classNode = n1.item(0).getFirstChild();
            String cName = classNode.getNodeValue();

            //通过类名创建实例对象并返回
            Class c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
```

```java
/**
 * 客户端测试类
 *
 * @author 10545
 * @date 2022/5/2 21:12
 */
public class Demo {
    public static void main(String[] args) {
        CarController car;
        car = (CarController) XMLUtil.getBean();
        car.move();
        car.phonate();
        car.twinkle();
    }
}
```

```text
玩具汽车移动！
发出警笛声
呈现警灯闪烁
```

## 模式拓展

### 缺省适配器模式

当不需要实现一个接口所提供的所有方法时，可先设计一个抽象类实现该接口，并为接口中每个方法提供一个默认实现（空方法），
那么该抽象类的子类可以选择性地覆盖父类的某些方法来实现需求，它适用于不想使用一个接口中的所有方法的情况，
又称为单接口适配器模式。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220502223530.png)

### 双向适配器

如果在适配器中同时包含对 `目标类`和 `适配者类`的 `引用`，适配者可以通过它调用目标类中的方法，
目标类也可以通过它调用适配者类中的方法，那么该适配器就是一个双向适配器。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220502224133.png)

## 效果

- 优点
    - 将 `目标类`和 `适配者类`解耦，通过引入一个适配器类来重用现有适配者类，无需修改原有结构
    - 将业务的具体实现封装在 `适配者类`中,对于客户端而言是透明的，提高了复用性。同一个 `适配者类`可以在多个不同的系统中复用。
    - 可以通过配置文件修改 `适配器类`，满足开闭原则，拓展性好。
    - `类适配器`是 `适配者类`的子类,因此可以在 `适配器类`中置换一些适配者的方法，使得适配器的灵活性更强。
    - `对象适配器`可以把多个不同的适配者适配到同一目标
    - `对象适配器`可以适配 `适配者类`的子类。
- 缺点
    - `类适配器`对于不支持多继承的语言，一次只能适配一个适配者类。
    - `类适配器`模式下，`适配者类`不能为最终类，例如Java中不能为final。
    - `类适配器`模式下,`目标抽象类`只能为接口，不能为类，有一定的局限性。
    - 在 `对象适配器`模式下,想要对 `适配者类`中的方法进行修改，需要做一个 `适配者类的子类`，在子类中进行方法的置换。然后再把 `适配者类的子类`当成真正的适配者进行适配。实现过程较为复杂

## 模式适用性

- 系统需要使用一些现有的类，而这些类的接口（例如方法名）不符合系统的需要，甚至没有这些类的源代码。
- 想创建一个可以重复使用的类，用于和一些彼此之间没有太大关联的类一起工作。

# 桥接模式（Bridge）

## 模式分类

结构型模式

对象模式

## 模式概述

将抽象部分与它的实现部分解耦，使得两者都能够独立变化

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220504175917.png)

- `Abstraction(抽象类)`:用于定义抽象类的接口，它一般是抽象类而不是接口，其中定义了一个 `Implementor（实现类接口）类型的对象`并可以维护该对象，
  它与Implementor之间具有关联关系，它既可以包含抽象业务方法，也可以包含具体业务方法。
- `RefinedAbstraction(扩充抽象类)`：扩充由Abstraction定义的接口，通常情况下它不再是抽象类而是具体类，它实现了在Abstraction中声明的抽象业务方法，
  在RefinedAbstraction中可以调用在Implementor中定义的业务方法。
- `Implementor(实现类接口)`:定义实现类的接口，这个接口不一定要与Abstraction的接口完全一致，事实上这两个接口可以完全不同，一般而言，Implementor接口仅提供基本操作，
  而Abstraction定义的接口可能会做更多更复杂的操作。Implementor接口对这些基本操作进行了声明，而具体实现交给其子类。通过关联关系，在Abstraction中不仅拥有自己的方法，
  还可以调用到Implementor中定义的方法，使用关联关系来替代继承关系。
- `ConcreteImplementor(具体实现类)`:具体实现Implementor接口，在不同的ConcreteImplementor中提供基本操作的不同实现，在程序运行时，
  ConcreteImplementor对象将替换其父类对象，提供给抽象类具体的业务操作方法。

## 实例代码

> Sunny软件公司欲开发一个跨平台图像浏览系统，要求该系统能够显示BMP、JPG、GIF、PNG等多种格式的文件，并且能够在Windows、Linux、Unix等多个操作系统上运行。
> 系统首先将各种格式的文件解析为像素矩阵(Matrix)，然后将像素矩阵显示在屏幕上，
> 在不同的操作系统中可以调用不同的绘制函数来绘制像素矩阵。系统需具有较好的扩展性以支持新的文件格式和操作系统。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220504230832.png)

```java
/**
 * 像素矩阵类，这是一个辅助类。各种格式的图像文件最终都被转化为像素矩阵，不同的操作系统提供不同的方法显示像素矩阵
 *
 * @author 10545
 * @date 2022/5/4 23:06
 */
public class Matrix {
    //代码省略
}

/**
 * 抽象操作系统实现类，充当实现类接口
 *
 * @author 10545
 * @date 2022/5/4 23:11
 */
public interface ImageImp {
    //显示图像矩阵m
    public void doPaint(Matrix m);
}

/**
 * Windows操作系统实现类，充当具体实现类
 *
 * @author 10545
 * @date 2022/5/4 23:13
 */
public class WindowsImp implements ImageImp {
    @Override
    public void doPaint(Matrix m) {
        //调用windows系统的绘制函数绘制像素矩阵
        System.out.print("在windows系统中显示图像:");
    }
}

/**
 * Linux操作系统实现类，充当具体实现类
 *
 * @author 10545
 * @date 2022/5/4 23:22
 */
public class LinuxImp implements ImageImp {
    @Override
    public void doPaint(Matrix m) {
        //调用Linux系统的绘制函数绘制像素矩阵
        System.out.print("在Linux操作系统中显示图像:");
    }
}

/**
 * UNIX操作系统实现类，充当具体实现类
 *
 * @author 10545
 * @date 2022/5/4 23:26
 */
public class UnixImp implements ImageImp {
    @Override
    public void doPaint(Matrix m) {
        //调用UNIX系统的绘制函数绘制像素矩阵
        System.out.print("在UNIX系统中显示图像:");
    }
}

/**
 * 抽象图像类，充当抽象类
 *
 * @author 10545
 * @date 2022/5/4 23:33
 */
public abstract class Image {
    protected ImageImp imp;

    //注入实现类接口对象
    public void setImageImp(ImageImp imp) {
        this.imp = imp;
    }

    public abstract void parseFile(String fileName);
}

/**
 * JPG格式图像类，充当扩充抽象类
 *
 * @author 10545
 * @date 2022/5/4 23:50
 */
public class JPGImage extends Image {
    @Override
    public void parseFile(String fileName) {
        //模拟解析JPG文件并获得一个像素矩阵对象m
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + ",格式为JPG");
    }
}

/**
 * PNG格式图像类，充当扩充抽象类
 *
 * @author 10545
 * @date 2022/5/7 21:20
 */
public class PNGImage extends Image {

    @Override
    public void parseFile(String fileName) {
        //模拟解析PNG文件并获得一个像素矩阵对象m
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + ",格式为PNG");
    }
}

/**
 * BMP格式图像类，充当扩充抽象类
 *
 * @author 10545
 * @date 2022/5/7 21:25
 */
public class BMPImage extends Image {
    @Override
    public void parseFile(String fileName) {
        //模拟解析BMP文件并获得一个像素矩阵对象m
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + ",格式为BMP");
    }
}

/**
 * GIF格式图像类，充当扩充抽象类
 *
 * @author 10545
 * @date 2022/5/7 21:32
 */
public class GIFImage extends Image {
    @Override
    public void parseFile(String fileName) {
        //模拟解析GIF文件并获得一个像素矩阵对象m
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + ",格式为GIF");
    }
}
```

```xml
<?xml version="1.0" encoding="utf-8" ?>
<config>
    <!--RefinedAbstraction-->
    <className>cn.com.lgs.bridge_pattern.JPGImage</className>
    <!--ConcreteImplementor-->
    <className>cn.com.lgs.bridge_pattern.WindowsImp</className>
</config>
```

```java
/**
 * 工具类
 *
 * @author 10545
 * @date 2022/5/8 20:53
 */
public class XMLUtil {
    /**
     * 该方法用于从XML配置文件中提取具体类的类名，并返回一个实例对象
     *
     * @param args
     * @return
     */
    public static Object getBean(String args) {
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("_java/design-pattern/src/main/java/cn/com/lgs/bridge_pattern/config.xml"));
            NodeList n1 = null;
            Node classNode = null;
            String cName = null;
            n1 = doc.getElementsByTagName("className");

            //获取第一个包含类名的结点，即扩充抽象类
            if (args.equals("image")) {
                classNode = n1.item(0).getFirstChild();
            }

            //获取第二个包含类名的结点，即具体实现类
            if (args.equals("os")) {
                classNode = n1.item(1).getFirstChild();
            }

            cName = classNode.getNodeValue();
            Class c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

```java
/**
 * 测试类
 *
 * @author 10545
 * @date 2022/5/8 23:03
 */
public class Demo {
    public static void main(String[] args) {
        Image image;
        ImageImp imp;
        image = (Image) XMLUtil.getBean("image");
        imp = (ImageImp) XMLUtil.getBean("os");
        //依赖注入
        image.setImageImp(imp);

        image.parseFile("小龙女");
    }
}
```

运行结果：

```text
在windows系统中显示图像:小龙女,格式为JPG
```

## 模式拓展

### 桥接模式与适配器模式配合使用

桥接模式和适配器模式用于设计的不同阶段，桥接模式用于系统的初步设计，
对于存在两个独立变化维度的类可以将其分为抽象化和实现化两个角色，使它们可以分别进行变化；
而在初步设计完成之后，当发现系统与已有类无法协同工作时，可以采用适配器模式。
但有时候在设计初期也需要考虑适配器模式，特别是那些涉及到大量第三方应用接口的情况。

举例说明：在某系统的报表处理模块中，需要将 `报表显示`和 `数据采集`分开，系统可以有 `多种报表显示方式`也可以有 `多种数据采集方式`，
如可以从文本文件中读取数据，也可以从数据库中读取数据，还可以从Excel文件中获取数据。如果需要从Excel文件中获取数据，
则需要调用与Excel相关的API，而这个API是现有系统所不具备的，该API由厂商提供。使用适配器模式和桥接模式设计该模块。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202205091052857.png)

## 效果

- 优点

    - 使用 `对象间的关联关系`解耦了 `抽象`和 `实现`之间固有的绑定关系。使得抽象和实现可以沿着各自的维度来变化。
      所谓抽象和实现沿着各自维度的变化，也就是说抽象和实现不再在同一个继承层次结构中，而是 `子类化`它们，
      使它们各自都具有自己的子类，以便任何组合子类，从而获得多维度组合对象。
    - 在很多情况下，桥接模式可以取代多层继承方案，多层继承方案违背了 `单一职责原则`，复用性较差，且类的个数非常多，
      桥接模式是比多层继承方案更好的解决方法，它极大减少了子类的个数。
    - 桥接模式提高了系统的可扩展性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统，符合 `开闭原则`。
- 缺点

    - 桥接模式的使用会增加系统的理解与设计难度，由于关联关系建立在抽象层，要求开发者一开始就针对抽象层进行设计与编程。
    - 桥接模式要求正确识别出系统中两个独立变化的维度，因此其使用范围具有一定的局限性，如何正确识别两个独立维度也需要一定的经验积累。

## 模式适用性

- 如果一个系统需要在抽象化和具体化之间增加更多的灵活性，避免在两个层次之间建立静态的继承关系，通过桥接模式可以使它们在抽象层建立一个关联关系。
- `抽象部分`和 `实现部分`可以以继承的方式独立扩展而互不影响，在程序运行时可以动态将一个抽象化子类的对象和一个实现化子类的对象进行组合，即系统需要对抽象化角色和实现化角色进行动态耦合。
- 一个类存在两个（或多个）独立变化的维度，且这两个（或多个）维度都需要独立进行扩展。
- 对于那些不希望使用继承或因为多层继承导致系统类的个数急剧增加的系统，桥接模式尤为适用。

# 组合模式（Composite）

## 模式分类

结构型模式

对象模式

## 模式概述

组合多个对象形成树形结构以表示具有 `部分—整体`关系的层次结构。
组合模式对 `单个对象`（即叶子对象）和 `组合对象`（即容器对象）的使用具有 `一致性`，
又可以称为 `部分—整体`（Part-Whole）模式，它是一种对象结构型模式。

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220509235030.png)

- `Component（抽象构件）`：它可以是 `接口`或 `抽象类`，为 `叶子构件`和 `容器构件`对象声明接口，
  在该角色中可以包含所有子类共有行为的声明和实现。在抽象构件中定义了 `访问及管理它的子构件的方法`，
  例如增加子构件、删除子构件、获取子构件等。
- `Leaf（叶子构件）`：它在组合模式结构中表示叶子节点对象。叶子节点没有子节点，它实现了在抽象构件中定义的行为。
  对于那些访问及管理子构件的方法，可以通过捕获异常等方式进行处理。
- `Composite（容器构件）`：它在组合模式结构中表示容器节点对象。容器节点包含子节点，其子节点可以是叶子节点，
  也可以是容器节点。它提供一个集合用于存储子节点，实现了在抽象构件中定义的行为，包括那些访问及管理子构件的方法，
  在其业务方法中可以递归调用其子节点的业务方法。

## 实例代码

> Sunny软件公司欲开发一个杀毒（AntiVirus）软件，该软件既可以对某个文件夹（Folder）杀毒，
> 也可以对某个指定的文件（File）进行杀毒。该杀毒软件还可以根据各类文件的特点，为不同类型的文件提供不同的杀毒方式，
> 例如图像文件（ImageFile）和文本文件（TextFile）的杀毒方式就有所差异。现需要提供该杀毒软件的整体框架设计方案。

### 初始设计

```java
//为了突出核心框架代码，我们对杀毒过程的实现进行了大量简化

import java.util.*;

//图像文件类
class ImageFile {
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    public void killVirus() {
        //简化代码，模拟杀毒
        System.out.println("----对图像文件'" + name + "'进行杀毒");
    }
}

//文本文件类
class TextFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public void killVirus() {
        //简化代码，模拟杀毒
        System.out.println("----对文本文件'" + name + "'进行杀毒");
    }
}

//文件夹类
class Folder {
    private String name;
    //定义集合folderList，用于存储Folder类型的成员
    private ArrayList<Folder> folderList = new ArrayList<Folder>();
    //定义集合imageList，用于存储ImageFile类型的成员
    private ArrayList<ImageFile> imageList = new ArrayList<ImageFile>();
    //定义集合textList，用于存储TextFile类型的成员
    private ArrayList<TextFile> textList = new ArrayList<TextFile>();

    public Folder(String name) {
        this.name = name;
    }

    //增加新的Folder类型的成员
    public void addFolder(Folder f) {
        folderList.add(f);
    }

    //增加新的ImageFile类型的成员
    public void addImageFile(ImageFile image) {
        imageList.add(image);
    }

    //增加新的TextFile类型的成员
    public void addTextFile(TextFile text) {
        textList.add(text);
    }

    //需提供三个不同的方法removeFolder()、removeImageFile()和removeTextFile()来删除成员，代码省略

    //需提供三个不同的方法getChildFolder(int i)、getChildImageFile(int i)和getChildTextFile(int i)来获取成员，代码省略

    public void killVirus() {
        System.out.println("****对文件夹'" + name + "'进行杀毒");  //模拟杀毒

        //如果是Folder类型的成员，递归调用Folder的killVirus()方法
        for (Object obj : folderList) {
            ((Folder) obj).killVirus();
        }

        //如果是ImageFile类型的成员，调用ImageFile的killVirus()方法
        for (Object obj : imageList) {
            ((ImageFile) obj).killVirus();
        }

        //如果是TextFile类型的成员，调用TextFile的killVirus()方法
        for (Object obj : textList) {
            ((TextFile) obj).killVirus();
        }
    }
}
```

```java
//客户端测试代码进行测试
class Client {
    public static void main(String args[]) {
        Folder folder1, folder2, folder3;
        folder1 = new Folder("Sunny的资料");
        folder2 = new Folder("图像文件");
        folder3 = new Folder("文本文件");

        ImageFile image1, image2;
        image1 = new ImageFile("小龙女.jpg");
        image2 = new ImageFile("张无忌.gif");

        TextFile text1, text2;
        text1 = new TextFile("九阴真经.txt");
        text2 = new TextFile("葵花宝典.doc");

        folder2.addImageFile(image1);
        folder2.addImageFile(image2);
        folder3.addTextFile(text1);
        folder3.addTextFile(text2);
        folder1.addFolder(folder2);
        folder1.addFolder(folder3);

        folder1.killVirus();
    }
}
```

运行结果如下：

```text
****对文件夹'Sunny的资料'进行杀毒
****对文件夹'图像文件'进行杀毒
----对图像文件'小龙女.jpg'进行杀毒
----对图像文件'张无忌.gif'进行杀毒
****对文件夹'文本文件'进行杀毒
----对文本文件'九阴真经.txt'进行杀毒
----对文本文件'葵花宝典.doc'进行杀毒
```

### 存在的问题

- 文件夹类Folder的设计和实现都非常复杂，需要定义多个集合存储不同类型的成员，而且需要针对不同的成员提供增加、删除和获取等管理和访问成员的方法，存在大量的冗余代码，系统维护较为困难。
- 由于系统没有提供抽象层，客户端代码必须有区别地对待充当容器的文件夹Folder和充当叶子的ImageFile和TextFile，无法统一对它们进行处理。
- 系统的灵活性和可扩展性差，如果需要增加新的类型的叶子或容器都需要对原有代码进行修改。例如，如果需要在系统中增加一种新类型的视频文件VideoFile，则必须修改Folder类的源代码，否则无法在文件夹中添加视频文件。

### 使用组合模式实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202205100943698.png)

```java
/**
 * 抽象文件类，充当抽象构件类
 *
 * @author luguosong
 * @date 2022/5/10 16:53
 */
public abstract class AbstractFile {
    public abstract void add(AbstractFile file);

    public abstract void remove(AbstractFile file);

    public abstract AbstractFile getChild(int i);

    public abstract void killVirus();
}

/**
 * 图像文件类，充当叶子构建类
 *
 * @author luguosong
 * @date 2022/5/10 16:59
 */
public class ImageFile extends AbstractFile {

    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    @Override
    public void remove(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    @Override
    public AbstractFile getChild(int i) {
        System.out.println("对不起，不支持该方法！");
        return null;
    }

    @Override
    public void killVirus() {
        //模拟杀毒
        System.out.println("---对图像文件'" + name + "'进行杀毒");
    }
}

/**
 * 文本文件类，充当叶子构件类
 *
 * @author luguosong
 * @date 2022/5/10 17:02
 */
public class TextFile extends AbstractFile {

    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    @Override
    public void remove(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    @Override
    public AbstractFile getChild(int i) {
        System.out.println("对不起，不支持该方法！");
        return null;
    }

    @Override
    public void killVirus() {
        //模拟杀毒
        System.out.println("---对文本文件'" + name + "'进行杀毒");
    }
}

/**
 * 视频文件类，充当叶子构件类
 *
 * @author luguosong
 * @date 2022/5/10 17:23
 */
public class VideoFile extends AbstractFile {

    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    @Override
    public void remove(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    @Override
    public AbstractFile getChild(int i) {
        System.out.println("对不起，不支持该方法！");
        return null;
    }

    @Override
    public void killVirus() {
        //模拟杀毒
        System.out.println("---对视频文件'" + name + "'进行杀毒");
    }
}

/**
 * 文件夹类，充当容器构件类
 *
 * @author luguosong
 * @date 2022/5/10 17:29
 */
public class Folder extends AbstractFile {

    //定于集合fileList,用于存储AbstractFile类型的成员
    private List<AbstractFile> fileList = new ArrayList<AbstractFile>();

    private String name;

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        fileList.add(file);
    }

    @Override
    public void remove(AbstractFile file) {
        fileList.remove(file);
    }

    @Override
    public AbstractFile getChild(int i) {
        return fileList.get(i);
    }

    @Override
    public void killVirus() {
        //模拟杀毒
        System.out.println("***对文件夹'" + name + "'进行杀毒");

        //递归调用成员构件的killVirus()方法
        for (AbstractFile abstractFile : fileList) {
            abstractFile.killVirus();
        }
    }
}
```

```java
/**
 * 客户端测试类
 *
 * @author luguosong
 * @date 2022/5/10 17:42
 */
public class Demo {
    public static void main(String[] args) {
        //针对抽象构件编程
        AbstractFile file1, file2, file3, file4, file5, folder1, folder2, folder3, folder4;

        folder1 = new Folder("Sunny的资料");
        folder2 = new Folder("图像文件");
        folder3 = new Folder("文本文件");
        folder4 = new Folder("视频文件");

        file1 = new ImageFile("小龙女.jpg");
        file2 = new ImageFile("张无忌.gif");
        file3 = new TextFile("九阴真经.txt");
        file4 = new TextFile("葵花宝典.doc");
        file5 = new VideoFile("笑傲江湖.rmvb");

        folder2.add(file1);
        folder2.add(file2);
        folder3.add(file3);
        folder3.add(file4);
        folder4.add(file5);
        folder1.add(folder2);
        folder1.add(folder3);
        folder1.add(folder4);

        //从“Sunny的资料”节点开始进行杀毒操作
        folder1.killVirus();
    }
}
```

运行结果：

```text
***对文件夹'Sunny的资料'进行杀毒
***对文件夹'图像文件'进行杀毒
---对图像文件'小龙女.jpg'进行杀毒
---对图像文件'张无忌.gif'进行杀毒
***对文件夹'文本文件'进行杀毒
---对文本文件'九阴真经.txt'进行杀毒
---对文本文件'葵花宝典.doc'进行杀毒
***对文件夹'视频文件'进行杀毒
---对视频文件'笑傲江湖.rmvb'进行杀毒
```

## 模式拓展

### 透明组合模式

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220510211437.png)

`抽象构件Component`中声明了所有用于管理成员对象的方法，包括add（）、remove（）以及getChild（）等方法

透明组合模式也是组合模式的`标准形式`

- 优点
    - 确保所有的构件类都有相同的接口。在客户端看来，`叶子对象`与`容器对象`所提供的方法是`一致的`，客户端可以相同地对待所有的对象。
- 缺点
    - 对于`叶子对象`而言add（）、remove（）以及getChild（）等方法是没有意义的

### 安全组合模式

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220510211451.png)

`抽象构件Component`中没有声明任何用于管理成员对象的方法，而是在Composite类中声明并实现这些方法。

- 优点
    - 这种做法是安全的，因为根本不向叶子对象提供这些管理成员对象的方法，对于叶子对象，客户端不可能调用到这些方法，这就是解决方案2所采用的实现方式。
- 缺点
    - 且容器构件中那些用于管理成员对象的方法没有在抽象构件类中定义，因此客户端不能完全针对抽象编程。

## 效果

- 优点
    - 组合模式的关键是定义了一个 `抽象构件类`，它既可以代表叶子，又可以代表容器。客户端针对该抽象构件类进行编程，
      无须知道它到底表示的是叶子还是容器，可以对其进行统一处理。
    - `容器对象`与 `抽象构件类`之间还建立一个 `聚合关联关系`，在容器对象中既可以包含叶子，也可以包含容器，以此实现递归组合，形成一个树形结构。
    - 组合模式可以清楚地定义分层次的复杂对象，表示对象的全部或部分层次。它让客户端忽略了层次的差异，方便对整个层次结构进行控制。
    - 客户端可以一致地使用一个组合结构或其中单个对象，不必关心处理的是单个对象还是整个组合结构，`简化了客户端代码`。
    - 在组合模式中增加新的容器构件和叶子构件都很方便，无须对现有类库进行任何修改，符合开闭原则。
    - 组合模式为树形结构的面向对象实现提供了一种灵活的解决方案。通过叶子对象和容器对象的递归组合，可以形成复杂的树形结构，但对树形结构的控制却非常简单。
- 缺点
    - 在`增加新构件`时很难对容器中的构件类型`进行限制`。例如在某个文件夹中只能包含文本文件。使用组合模式时，不能依赖类型系统来施加这些约束，因为它们都来自相同的抽象层。
      在这种情况下，必须通过在运行时进行类型检查来实现，这个实现过程较为复杂。

## 模式适用性

1. 在具有整体和部分的层次结构中，希望通过一种方式忽略整体与部分的差异，客户端可以一致性地对待它们。
2. 在一个使用面向对象语言开发的系统中需要处理一个树形结构。
3. 在一个系统中能够分离出叶子对象和容器对象，而且它们的类型不固定，将来需要增加一些新的类型。

## 模式应用

- Java SE中的AWT
- Swing包的设计
- 在XML解析
- 组织结构树处理
- 文件系统设计

# 装饰模式（Decorator）

## 模式分类

对象型模式

结构型模式

## 模式概述

动态地给一个对象增加一些额外的职责，就增加对象功能来说，装饰模式比生成子类实现更为灵活。

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220512225601.png)

- `Component（抽象构件）`：它是`具体构件`和`抽象装饰类`的共同父类，声明了在`具体构件`中实现的业务方法。
  它的引入可以使客户端以一致的方式处理未被装饰的对象以及装饰之后的对象，实现客户端的透明操作。
- `ConcreteComponent（具体构件）`：它是抽象构件类的子类，用于定义`具体的构件对象`，实现了在`抽象构件`中声明的方法，
  装饰器可以给它增加额外的职责（方法）。
- `Decorator（抽象装饰类）`：它也是抽象构件类的子类，用于给具体构件`增加职责`，但是具体职责在其子类中实现。
  它维护一个指向抽象构件对象的引用，通过该引用可以`调用装饰之前构件对象的方法`，并通过其子类扩展该方法，以达到装饰的目的。
- `ConcreteDecorator（具体装饰类）`：它是抽象装饰类的子类，负责`向构件添加新的职责`。
  每一个具体装饰类都定义了一些新的行为，可以调用在抽象装饰类中定义的方法，
  并可以增加新的方法用以扩充对象的行为。


## 实例代码

> Sunny软件公司基于面向对象技术开发了一套图形界面构件库Visual Component，该构件库提供了大量基本构件，
> 如窗体、文本框、列表框等。由于在使用该构件库时，用户经常要求定制一些特殊的显示效果，例如带滚动条的窗体、
> 带黑色边框的文本框、既带滚动条又带黑色边框的列表框等，因此经常需要对该构件库进行扩展以增强其功能

### 初始设计

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220512220137.png)

### 存在的问题

- 系统扩展麻烦，在某些编程语言中无法实现。如果用户需要一个既带滚动条又带黑色边框的窗体，
  需要多重继承。但现在很多面向对象编程语言，如Java、C＃等都不支持多重类继承。
- 代码重复。不只是窗体需要设置滚动条，文本框、列表框等都需要设置滚动条，
  这些具体实现过程基本相同，代码重复，不利于对系统进行修改和维护。
- 系统庞大，类的数目非常多。

### 不合理原因

- 不应该使用继承进行复用，而是应该多用关联少用继承

### 使用装饰模式实现

```java
/**
 * 抽象界面构件类，充当抽象构件类
 *
 * @author luguosong
 * @date 2022/5/15 16:17
 */
public abstract class Component {
    public abstract void display();
}

/**
 * 窗体类，充当具体构件类
 *
 * @author luguosong
 * @date 2022/5/15 16:19
 */
public class Windows extends Component{
  @Override
  public void display() {
    System.out.println("显示窗体");
  }
}

/**
 * 文本框类，充当具体构件类
 *
 * @author luguosong
 * @date 2022/5/15 16:20
 */
public class TextBox extends Component{
  @Override
  public void display() {
    System.out.println("显示文本框");
  }
}

/**
 * 列表框类，充当具体构件类
 *
 * @author luguosong
 * @date 2022/5/15 16:22
 */
public class ListBox extends Component{
  @Override
  public void display() {
    System.out.println("显示列表框");
  }
}

/**
 * 构件装饰类，充当抽象装饰类
 *
 * @author luguosong
 * @date 2022/5/15 16:24
 */
public class ComponentDecorator extends Component {

  /**
   * 维持对抽象构件类型对象的引用
   */
  private Component component;

  //注入抽象构件类型的对象
  public ComponentDecorator(Component component) {
    this.component = component;
  }

  @Override
  public void display() {
    component.display();
  }
}

/**
 * 滚动条装饰类，充当具体装饰类
 *
 * @author luguosong
 * @date 2022/5/15 16:28
 */
public class ScrollBarDecorator extends ComponentDecorator{

  public ScrollBarDecorator(Component component) {
    super(component);
  }

  /**
   * 重写抽象装饰类方法
   */
  @Override
  public void display() {
    this.setScrollBar();
    super.display();
  }

  /**
   * 装饰方法
   */
  public void setScrollBar(){
    System.out.println("为构件增加滚动条");
  }
}

/**
 * 黑色边框装饰类，充当具体装饰类
 *
 * @author luguosong
 * @date 2022/5/15 16:38
 */
public class BlackBorderDecorator extends ComponentDecorator{
  public BlackBorderDecorator(Component component) {
    super(component);
  }

  /**
   * 重写抽象装饰类方法
   */
  @Override
  public void display() {
    setBlackBorder();
    super.display();
  }

  /**
   * 装饰方法
   */
  public void setBlackBorder(){
    System.out.println("为构件增加黑色边框");
  }
}
```

```java
/**
 * 测试
 * 
 * @author luguosong
 * @date 2022/5/15 16:41
 */
public class Demo {
    public static void main(String[] args) {
        Component component,componentSB;
        component=new Windows();
        componentSB=new ScrollBarDecorator(component);
        componentSB.display();
    }
}
```

```text
为构件增加滚动条
显示窗体
```

## 模式拓展

### 透明装饰模式

在透明装饰模式中，要求客户端完全针对抽象编程。
装饰模式的透明性要求客户端程序不应该将对象声明为具体构件类型或具体装饰类型，
而应该全部声明为抽象构件类型。对于客户端而言，具体构件对象和具体装饰对象没有任何区别。

### 半透明装饰模式

用具体装饰类型来定义装饰之后的对象，而具体构件类型还是可以使用抽象构件类型来定义，
这种装饰模式即为半透明装饰模式。

## 效果

- 优点
    - 对于扩展一个对象的功能，装饰模式比继承更加灵活性，不会导致类的个数急剧增加。
    - 可以通过一种动态的方式来扩展一个对象的功能。通过配置文件可以在运行时选择不同的具体装饰类，
      从而实现不同的行为。
    - 可以对一个对象进行多次装饰。通过使用不同的具体装饰类以及这些装饰类的排列组合，
      可以创造出很多不同行为的组合，得到功能更为强大的对象。
    - 具体构件类与具体装饰类可以独立变化，用户可以根据需要增加新的具体构件类和具体装饰类，
      原有类库代码无须改变，符合开闭原则。
- 缺点
    - 使用装饰模式进行系统设计时将产生很多小对象。这些对象的区别在于它们之间相互连接的方式有所不同，
      而不是它们的类或者属性值有所不同。大量小对象的产生势必会占用更多的系统资源，在一定程度上影响程序的性能。
    - 装饰模式提供了一种比继承更加灵活机动的解决方案，但同时也意味着比继承更加易于出错，排错也很困难。
      对于多次装饰的对象，调试时寻找错误可能需要逐级排查，较为烦琐。

## 模式适用性

- 在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。
- 当不能采用继承的方式对系统进行扩展或者采用继承不利于系统扩展和维护时可以使用装饰模式。
  不能采用继承的情况主要有两类：第1类是系统中存在大量独立的扩展，
  为支持每一种扩展或者扩展之间的组合将产生大量的子类，使得子类数目呈爆炸性增长；
  第2类是因为类已定义为不能被继承（如Java语言中的final类）。

# 外观模式（Facade）

## 别名

门面模式

## 模式分类

结构模式

对象模式

## 模式概述

外部与一个子系统的通信通过一个统一的外观角色进行，
为子系统中的一组接口提供一个一致的入口。外观模式定义了一个高层接口，
这个接口使得子系统更加容易使用。

## 模式结构与实现

外观模式没有一个一般化的类图描述，通常使用示意图来表示外观模式

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202205151814148.png)

- `Facade（外观角色）`：在客户端可以调用这个角色的方法，
  在外观角色中可以知道相关的（一个或者多个）子系统的功能和责任。在正常情况下，
  它将所有从客户端发来的请求委派到相应的子系统中去，传递给相应的子系统对象处理。
- `SubSystem（子系统角色）`：在软件系统中可以有一个或者多个子系统角色。
  每个子系统可以不是一个单独的类，而是一个类的集合，它实现子系统的功能。
  每个子系统都可以被客户端直接调用，或者被外观角色调用，
  它处理由外观类传过来的请求。子系统并不知道外观的存在，对于子系统而言，
  外观角色仅仅是另外一个客户端而已。


## 实例代码

> Sunny软件公司欲开发一个可应用于多个软件的文件加密模块，
> 该模块可以对文件中的数据进行加密并将加密之后的数据存储在一个新文件中。
> 具体的流程包括3个部分，分别是读取源文件、加密、保存加密之后的文件。其中，
> 读取文件和保存文件使用流来实现，加密操作通过求模运算实现。这3个操作相对独立，
> 为了实现代码的独立重用，让设计更符合单一职责原则，这3个操作的业务代码封装在3个不同的类中。

### 初始设计

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202205151731356.png)

### 存在问题

- FileReader类、CipherMachine类和FileWriter类经常会作为一个整体同时出现，
  但是如果按照上述方案进行设计和实现，在每次使用这3个类时，
  客户端代码需要与它们逐个进行交互，导致客户端代码较为复杂，
  且在每次使用它们时很多代码都将重复出现。
- 如果需要更换一个加密类，例如将CipherMachine类改为NewCipherMachine类，
  则所有使用该文件加密模块的代码都需要进行修改，系统维护难度增大，
  灵活性和可扩展性较差。

### 使用外观模式实现

```java
/**
 * 文件读取类，充当子系统
 *
 * @author luguosong
 * @date 2022/5/15 19:32
 */
public class FileReader {
    public String read(String fileNameSrc){
        System.out.print("读取文件，获取明文：");
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream inFs = new FileInputStream(fileNameSrc);
            int data;
            while((data=inFs.read())!=-1){
                sb=sb.append((char)data);
            }
            inFs.close();
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.out.println("文件操作错误");
        }
        return sb.toString();
    }
}

/**
 * 数据加密类，充当子系统类
 *
 * @author 10545
 * @date 2022/5/16 21:33
 */
public class CipherMachine {
  public String encrypt(String plainText) {
    System.out.print("数据加密，将明文转换为密文：");
    String es = "";
    for (int i = 0; i < plainText.length(); i++) {
      String c = String.valueOf(plainText.charAt(i) % 7);
      es += c;
    }
    System.out.println(es);
    return es;
  }
}

/**
 * 文件保存类，充当子系统类
 *
 * @author 10545
 * @date 2022/5/16 22:11
 */
public class FileWriter {
  public void write(String encryptStr,String fileNameDes){
    System.out.print("保存密文，写入文件。");
    try {
      FileOutputStream outFs = new FileOutputStream(fileNameDes);
      outFs.write(encryptStr.getBytes());
      outFs.close();
    } catch (FileNotFoundException e) {
      System.out.println("文件不存在！");
    } catch (IOException e) {
      System.out.println("文件操作错误！");
    }
  }
}

/**
 * 加密外观类，充当外观类
 *
 * @author 10545
 * @date 2022/5/16 22:19
 */
public class EncryptFacade {
  //维持对子系统对象的引用
  private FileReader reader;
  private CipherMachine cipher;
  private FileWriter writer;

  public EncryptFacade() {
    reader = new FileReader();
    cipher = new CipherMachine();
    writer = new FileWriter();
  }

  //调用子系统对象的业务方法
  public void fileEncrypt(String fileNameSrc, String fileNameDes) {
    String plainStr = reader.read(fileNameSrc);
    String encryptStr = cipher.encrypt(plainStr);
    writer.write(encryptStr, fileNameDes);
  }
}
```

```text
hello world!
```

```java
/**
 * 客户端测试类
 *
 * @author 10545
 * @date 2022/5/16 22:34
 */
public class Demo {
    public static void main(String[] args) {
        EncryptFacade ef = new EncryptFacade();
        ef.fileEncrypt("_java/design-pattern/src/main/java/cn/com/lgs/facade_pattern/src.txt","_java/design-pattern/src/main/java/cn/com/lgs/facade_pattern/des.txt");
    }
}
```

```text
读取文件，获取明文：hello world!
数据加密，将明文转换为密文：63336406232563
保存密文，写入文件。
```

## 模式拓展

### 抽象外观类

在标准的外观模式结构图中，如果需要增加、删除或更换与外观类交互的子系统类，必须修改外观类或客户端的源代码，
这将违背开闭原则。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202205171111086.png)

### 外观角色设计补充说明

- 可以使用单例设计外观类，从而确保系统中只有唯一一个访问子系统的入口，并降低对系统资源的消耗
- 在一个系统中可以设计多个外观类，每个外观类都负责和一些特定的子系统交互，向客户端提供相应的业务功能。

## 效果

- 优点
    - 对客户端屏蔽了子系统组件，减少了客户端所需处理的对象数目并使得子系统使用起来更加容易。通过引入外观模式，
      客户端代码将变得很简单，与之关联的对象也很少。
    - 实现了子系统与客户端之间的松耦合关系，这使得子系统的变化不会影响到调用它的客户端，只需要调整外观类即可。
    - 一个子系统的修改对其他子系统没有任何影响，而且子系统内部变化也不会影响到外观对象。
    - 只是提供了一个访问子系统的统一入口，并不影响客户端直接使用子系统类。
- 缺点
    - 不能很好地限制客户端直接使用子系统类，如果对客户端访问子系统类做太多的限制则减少了可变性和灵活性。
    - 如果设计不当，增加新的子系统可能需要修改外观类的源代码，这违背了开闭原则。

## 模式适用性

- 当要为访问一系列复杂的子系统提供一个简单入口时可以使用外观模式。
- 客户端程序与多个子系统之间存在很大的依赖性。引入外观类可以将子系统与客户端解耦，从而提高子系统的独立性和可移植性。
- 在层次化结构中，可以使用外观模式定义系统中每一层的入口，层与层之间不直接产生联系，而通过外观类建立联系，降低层之间的耦合度。
- 试图通过外观类为子系统增加新行为的做法是错误的。外观模式的用意是为子系统提供一个集中化和简化的沟通渠道，
  而不是向子系统加入新行为。新行为的增加应该通过修改原有子系统类或增加新的子系统类来实现，不能通过外观类来实现。


# 享元模式（Flyweight）

## 别名

轻量级模式

## 模式分类

结构型模式

对象模式

## 模式概述

运用共享技术有效地支持大量细粒度对象的复用。系统只使用少量的对象，而这些对象都很相似，状态变化很小，
可以实现对象的多次复用。

## 模式结构与实现

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202205171442597.png)

- `Flyweight（抽象享元类）`：通常是一个接口或抽象类，在抽象享元类中声明了具体享元类公共的方法，
  这些方法可以向外界提供享元对象的内部数据（内部状态），同时也可以通过这些方法来设置外部数据（外部状态）。
- `ConcreteFlyweight（具体享元类）`：它实现了抽象享元类，其实例称为享元对象。在具体享元类中为内部状态提供了存储空间。
  通常，可以结合单例模式来设计具体享元类，为每个具体享元类提供唯一的享元对象。
- `UnsharedConcreteFlyweight（非共享具体享元类）`：并不是所有的抽象享元类的子类都需要被共享，不能被共享的子类可设计为非共享具体享元类。
  当需要一个非共享具体享元类的对象时可以直接通过实例化创建。
- `FlyweightFactory（享元工厂类）`：享元工厂类用于创建并管理享元对象，它针对抽象享元类编程，将各种类型的具体享元对象存储在一个享元池中。
  享元池一般设计为一个存储“键值对”的集合（也可以是其他类型的集合），可以结合工厂模式进行设计。当用户请求一个具体享元对象时，
  享元工厂提供一个存储在享元池中已创建的实例，或者创建一个新的实例（如果不存在的话）并返回新创建的实例，同时将其存储在享元池中。

## 实例代码

> Sunny软件公司欲开发一个围棋软件。通过对围棋软件进行分析，发现在围棋棋盘中包含大量的黑子和白子，它们的形状、大小都一模一样，只是出现的位置不同而已。
> 如果将每个棋子都作为一个独立的对象存储在内存中，将导致该围棋软件在运行时所需内存空间较大。如何降低运行代价、提高系统性能是Sunny公司开发人员需要解决的一个问题。
> 为了解决这个问题，Sunny公司开发人员决定使用享元模式来设计该围棋软件的棋子对象。




## 模式拓展

## 效果

## 模式适用性

# 代理模式（Proxy）

## 别名

## 模式分类

## 模式概述

## 模式结构与实现

## 实例代码

## 模式拓展

## 效果

## 模式适用性
