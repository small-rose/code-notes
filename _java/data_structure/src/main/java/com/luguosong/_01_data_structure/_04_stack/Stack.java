package com.luguosong._01_data_structure._04_stack;

import com.luguosong._01_data_structure.MyList;
import com.luguosong._01_data_structure._02_arraylist.DynamicArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * 栈
 *
 * @author luguosong
 * @date 2022/10/28
 */
public class Stack<E> {

    /**
     * 通过组合动态数组的方式实现栈
     */
    private MyList<E> list = new DynamicArrayList<>();

    /**
     * 清空栈所有元素
     */
    public void clear() {
        list.clear();
    }

    /**
     * 获取栈元素个数
     *
     * @return
     */
    public int size() {
        return list.size();
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 入栈
     *
     * @param element
     */
    public void push(E element) {
        list.add(element);
    }

    /**
     * 出栈
     *
     * @return
     */
    public E pop() {
        return list.remove(list.size() - 1);
    }

    /**
     * 获取栈顶元素
     *
     * @return
     */
    public E top() {
        return list.get(list.size() - 1);
    }


}
