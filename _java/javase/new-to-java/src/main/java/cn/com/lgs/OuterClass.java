package cn.com.lgs;

/**
 * @author luguosong
 * @date 2021/10/28 10:28
 */
public class OuterClass {

    String outerField = "Outer field";
    static String staticOuterField = "Static outer field";

    /**
     * 静态嵌套类
     */
    static class StaticNestedClass {
        void accessMembers(OuterClass outer) {
            // Compiler error: Cannot make a static reference to the non-static
            // field outerField
            // System.out.println(outerField);
            System.out.println(outer.outerField);
            System.out.println(staticOuterField);
        }
    }

    /**
     * 内部类
     */
    class InnerClass {
        void accessMembers() {
            System.out.println(outerField);
            System.out.println(staticOuterField);
        }
    }

    public void fun(int num1) {
        int num2 = 2;

        class LocalClass {
            void accessMembers() {
                //访问参数或者局部变量都需要是 final 或有效 final，这里不能修改
                //num1=3;
                //num2=3;

                System.out.println(num1);
                System.out.println(num2);
            }
        }

        new LocalClass().accessMembers();
    }
}
