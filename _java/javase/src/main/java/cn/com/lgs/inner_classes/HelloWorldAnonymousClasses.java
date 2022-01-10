package cn.com.lgs.inner_classes;

/**
 * 匿名类和局部类比较
 *
 * @author luguosong
 * @date 2022/1/10 9:57
 */
public class HelloWorldAnonymousClasses {
    interface HelloWorld {
        public void greet();
    }

    public void sayHello() {
        //局部类，是一个类的声明
        class LocalClassesGreeting implements HelloWorld {
            @Override
            public void greet() {
                System.out.println("Hello,局部类");
            }
        }
        //使用局部类
        LocalClassesGreeting localClassesGreeting = new LocalClassesGreeting();

        //匿名类不需要声明，而是作为表达式直接使用
        HelloWorld helloWorld = new HelloWorld() {
            @Override
            public void greet() {
                System.out.println("Hello,匿名类");
            }
        };

        //使用对象
        localClassesGreeting.greet();
        helloWorld.greet();
    }

    public static void main(String[] args) {
        HelloWorldAnonymousClasses worldAnonymousClasses = new HelloWorldAnonymousClasses();
        worldAnonymousClasses.sayHello();
    }
}
