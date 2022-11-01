package com.luguosong._01_data_structure._03_linkedlist;

import com.luguosong._01_data_structure.MyList;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author luguosong
 * @date 2022/10/25
 */
public class LinkedListTest {


    /**
     * 链表测试方法
     */
    private void listTest(MyList list) {
        //末尾插入元素
        list.add(30);
        list.add(40);
        list.add(50);
        System.out.println(list);
        //头部插入元素
        list.add(0, 10);
        System.out.println(list);
        //指定位置插入元素
        list.add(1, 20);
        System.out.println(list);

        //删除头部元素
        list.remove(0);
        System.out.println(list);

        //删除尾部元素
        list.remove(list.size() - 1);
        System.out.println(list);

        //获取指定元素位置
        System.out.println(list.indexOf(40));

    }

    /**
     * JDK自带链表测试
     */
    @Test
    public void linkedListTest() {
        List<Integer> list = new LinkedList<>();
        //末尾插入元素
        list.add(20);
        list.add(30);
        //指定位置插入元素·
        list.add(0, 10);
        list.add(list.size(), 40);
        System.out.println(list);

        //删除指定位置元素
        list.remove(1);
        System.out.println(list);
    }

    /**
     * 单向链表测试
     */
    @Test
    public void singlyLinkedListTest() {
        listTest(new SinglyLinkedList<>());
    }

    /**
     * 双向链表
     */
    @Test
    public void doublyLinkedListTest() {
        listTest(new DoublyLinkedList<>());
    }

    /**
     * 单向循环链表
     */
    @Test
    public void singlyCircleLinkedListTest() {
        listTest(new SinglyCircleLinkedList());
    }

    /**
     * 双向循环列表
     */
    @Test
    public void doublyCircleLinkedListTest() {
        listTest(new DoublyCircleLinkedList());
    }
}
