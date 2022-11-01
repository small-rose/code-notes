package com.luguosong._01_data_structure._05_queue;

import com.luguosong._01_data_structure.MyList;
import com.luguosong._01_data_structure._03_linkedlist.DoublyLinkedList;

/**
 * 队列
 *
 * @author luguosong
 * @date 2022/10/29
 */
public class Queue<E> {

    /**
     * 使用双向链表，删除元素效率比动态数组高
     */
    private MyList list = new DoublyLinkedList();

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return list.size();
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 入队
     *
     * @param element
     */
    public void enQueue(E element) {
        list.add(element);
    }

    /**
     * 出队
     *
     * @return
     */
    public E deQueue() {
        return (E)list.remove(0);
    }

    /**
     * 获取队列头元素
     *
     * @return
     */
    public E front(){
        return (E)list.get(0);
    }

    /**
     * 清空
     */
    public void clear(){
        list.clear();
    }
}
