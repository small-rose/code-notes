package com.luguosong._01_data_structure._05_queue;

import java.util.Queue;

/**
 * 循环双端队列
 *
 * @author luguosong
 * @date 2022/11/2
 */
public class CircleDoubleEndedQueue<E> {
    //队头索引
    private int front;
    private int size;
    private E[] elements;


    public CircleDoubleEndedQueue() {
        elements = (E[]) new Object[10];
    }

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return size;
    }


    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 从队尾入队
     *
     * @param element
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size - 1)] = element;
        size++;
    }

    /**
     * 从队头出队
     *
     * @return
     */
    public E deQueueFront() {
        E old = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return old;
    }

    /**
     * 从队头入队
     *
     * @param element
     */
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 从队尾出队
     *
     * @return
     */
    public E deQueueRear() {
        E old = elements[index(size - 1)];
        elements[index(size - 1)] = null;
        size--;
        return old;
    }

    /**
     * 获取队列头元素
     *
     * @return
     */
    public E front() {
        return elements[front];
    }

    /**
     * 获取队列尾元素
     *
     * @return
     */
    public E rear() {
        return elements[index(size - 1)];
    }

    /**
     * 获取队列底层数组的真实坐标
     *
     * @param index
     * @return
     */
    private int index(int index) {
        index += front;
        if (index < 0) {
            index += elements.length;
        }
        return index - (elements.length > index ? 0 : elements.length);
    }

    /**
     * 清空
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    /**
     * 保证数组容量
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            //获取原数组容量
            int oldCapacity = elements.length;
            //数组容量扩大1.5倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            //创建新数组
            E[] newElements = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(i + front) % elements.length];
            }
            elements = newElements;
            //重置front
            front = 0;
        }
    }

    /**
     * 打印数组
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                builder.append("null");
            } else {
                builder.append(elements[i]);
            }
            if (i < elements.length - 1) {
                builder.append(",");
            }
        }

        builder.append("]");
        return builder.toString();
    }
}
