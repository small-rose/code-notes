package com.luguosong._01_data_structure._05_queue;

import com.luguosong._01_data_structure.MyList;
import com.luguosong._01_data_structure._03_linkedlist.DoublyLinkedList;

/**
 * 双端队列
 *
 * @param <E>
 * @author luguosong
 */
public class DoubleEndedQueue<E> {
    private MyList<E> list = new DoublyLinkedList<>();

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
     * 从队尾入队
     *
     * @param element
     */
    public void enQueueRear(E element) {
        list.add(element);
    }

    /**
     * 从队头出队
     *
     * @return
     */
    public E deQueueFront() {
        return list.remove(0);
    }

    /**
     * 从队头入队
     *
     * @param element
     */
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    /**
     * 从队尾出队
     *
     * @return
     */
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    /**
     * 获取队列头元素
     *
     * @return
     */
    public E front() {
        return list.get(0);
    }

    /**
     * 获取队列尾元素
     *
     * @return
     */
    public E rear() {
        return list.get(list.size() - 1);
    }
}
