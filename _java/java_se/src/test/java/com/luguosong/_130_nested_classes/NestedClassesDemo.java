package com.luguosong._130_nested_classes;

import com.luguosong._130_nested_classes.inner_class.Company;
import com.luguosong._130_nested_classes.static_nested_classes.Person;
import org.junit.jupiter.api.Test;

/**
 * @author luguosong
 * @date 2022/11/23
 */
public class NestedClassesDemo {

    /**
     * 创建内部类对象
     */
    @Test
    public void testInnerClassCreate() {
        //这样写感觉还是违背了内部类与实例关联的说法
        System.out.println("直接通过类获取内部类中的常量：" + Company.Employee.NO1);
        //先要创建外部类实例
        Company company = new Company();
        Company.Employee employee = company.new Employee();
        company.testEmployee(employee);
        employee.testCompany();
    }

    /**
     * 测试静态嵌套类
     */
    @Test
    public void testStaticNestedClasses(){
        Person.Car car = new Person.Car();
        car.test();
    }



}
