package com.luguosong._01_data_structure._02_arraylist;

import org.junit.jupiter.api.Test;

/**
 * @author luguosong
 * @date 2022/10/25
 */
public class ArrayListTest {

    /**
     * 动态数组测试
     */
    @Test
    public void dynamicArrayListTest(){
        //创建动态数组
        DynamicArrayList<Integer> list = new DynamicArrayList<>(5);
        //向动态数组中添加元素
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        list.add(70);
        //移除指定位置的元素
        list.remove(2);
        //向指定位置插入元素
        list.add(2, 444);
        //打印数组
        System.out.println(list);
    }
}
