package com.luguosong._130_nested_classes.inner_class;

/**
 * 内部类
 *
 * @author luguosong
 * @date 2022/11/23
 */
public class Company {
    private String name = "xx公司";

    public void testEmployee(Employee employee) {
        //外部类可以直接访问内部类实例的成员变量、方法（即使被声明为private）
        System.out.println("外部类访问内部类中的成员：" + employee.no3);
    }

    /**
     * 内部类
     */
    public class Employee {
        //内部类可以定义编译时常量
        public static final int NO1 = 10;

        //不可以定义静态字段
        //public static int no2=10;

        //内部类中的普通字段
        private int no3 = 20;

        public Employee() {
            System.out.println("内部类Employee被创建");
        }

        public void testCompany() {
            //内部类可以直接访问外部类中的所有成员（即使被声明为private）
            System.out.println("内部类访问外部类中的成员：" + name);
        }
    }
}
