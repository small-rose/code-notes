package com.luguosong._01_data_structure._05_queue;

import java.util.Arrays;

/**
 * 循环队列
 *
 * @author luguosong
 * @date 2022/10/29
 */
public class CircleQueue<E> {

    //队头索引
    private int front;
    private int size;
    private E[] elements;

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
                newElements[i] = elements[index(i)];
            }
            elements = newElements;
            //重置front
            front = 0;
        }
    }

    private int index(int index) {
        index += front;
        return index - (elements.length > index ? 0 : elements.length);
    }

    public CircleQueue() {
        elements = (E[]) new Object[10];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    public E deQueue() {
        E old = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return old;
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public E front() {
        return elements[front];
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
