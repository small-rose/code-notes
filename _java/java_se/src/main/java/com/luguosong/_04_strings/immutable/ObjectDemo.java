package com.luguosong._04_strings.immutable;

/**
 * @author luguosong
 * @date 2022/8/23
 */
public class ObjectDemo {
    public static People setAge(People people) {
        people.setAge(20);
        return people;
    }

    public static void main(String[] args) {
        People people = new People();
        people.setName("张三");
        people.setAge(10);
        System.out.println(people); //age=10

        People modifiedPeople = setAge(people);
        System.out.println(modifiedPeople); //age=20

        System.out.println(people); //age=20
    }
}
