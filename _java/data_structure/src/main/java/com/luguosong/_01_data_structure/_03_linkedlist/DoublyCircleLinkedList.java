package com.luguosong._01_data_structure._03_linkedlist;

/**
 * 双向循环链表
 *
 * @author luguosong
 * @date 2022/10/26
 */
public class DoublyCircleLinkedList<E> extends DoublyLinkedList<E> {
    @Override
    public void add(int index, E element) {
        if (index == size) {
            Node<E> prev = last;
            Node<E> node = new Node<>(element, prev, first);
            last = node;
            if (size == 0) {
                first = node;
            } else {
                prev.next = node;
                first.prev = last;
            }

        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(element, next.prev, next);
            next.prev = node;
            prev.next = node;
            //当index为0，没有prev
            if (prev == last) {
                first = node;
            }
        }
        size++;
    }

    /**
     * 删除指定位置的元素
     * 特殊情况：删除最后一个，删除第一个，只有一个元素删除
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        Node<E> node = node(index);
        E old = node.element;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            if (node.prev == last) {
                first = node.next;
            }
            if (node.next == first) {
                last = node.prev;
            }

            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        size--;
        return old;
    }
}
