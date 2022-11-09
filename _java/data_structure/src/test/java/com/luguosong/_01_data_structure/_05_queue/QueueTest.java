package com.luguosong._01_data_structure._05_queue;

import org.junit.jupiter.api.Test;

/**
 * @author luguosong
 * @date 2022/10/30
 */
public class QueueTest {

    /**
     * 循环队列测试
     */
    @Test
    public void testCircleQueue() {
        CircleQueue<Object> queue = new CircleQueue<>();
        //测试入队
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
        //测试出队
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }
        System.out.println(queue);
        //测试扩容
        for (int i = 10; i < 16; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
    }


    /**
     * 循环双端链表
     */
    @Test
    public void testCircleDoubleEndedQueue(){
        CircleDoubleEndedQueue<Object> queue = new CircleDoubleEndedQueue<>();
        //测试入队
        for (int i = 0; i < 10; i++) {
            queue.enQueueRear(i);
        }
        System.out.println(queue);
        //测试出队
        for (int i = 0; i < 5; i++) {
            queue.deQueueFront();
        }
        System.out.println(queue);
        //测试扩容
        for (int i = 10; i < 16; i++) {
            queue.enQueueFront(i);
        }
        System.out.println(queue);
    }

}
