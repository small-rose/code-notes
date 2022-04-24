package cn.com.lgs.test;

import cn.com.lgs.data_structure.MyArrayList;

/**
 * 测试动态数组
 *
 * @author 10545
 * @date 2022/4/20 0:03
 */
public class ArrayListTest {
    public static void main(String[] args) {
        MyArrayList<Integer> arrays = new MyArrayList<Integer>();
        arrays.add(1);
        arrays.add(2);
        arrays.add(3);
        arrays.add(4);
        arrays.add(5);
        System.out.println("add方法添加元素：" + arrays);

        arrays.add(2, 100);
        System.out.println("add方法在指定位置添加元素：" + arrays);

        System.out.println("index为5位置的元素："+arrays.get(5));

        
    }
}
