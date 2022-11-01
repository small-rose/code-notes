package com.luguosong._01_data_structure._03_linkedlist;

import com.luguosong._01_data_structure.MyAbstractList;

/**
 * 双向链表
 *
 * @author luguosong
 * @date 2022/10/25
 */
public class DoublyLinkedList<E> extends MyAbstractList<E> {

    /**
     * 链表第一个元素
     */
    protected Node<E> first;

    /**
     * 链表中最后一个元素
     */
    protected Node<E> last;

    /**
     * 内部类,表示链表中的元素
     *
     * @param <E>
     */
    protected static class Node<E> {
        E element;
        Node<E> next;

        Node<E> prev;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * 根据指定索引查找节点
     *
     * @param index
     * @return
     */
    protected Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = null;
        //index小于size一半从最前面开始查找元素
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        //如果index大于size一半从最后开始查找元素
        else {
            node = last;
            for (int i = size - 1; i > index; i++) {
                node = node.prev;
            }
        }
        return node;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * 获取指定位置元素
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 设置指定位置元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    /**
     * 向指定位置添加元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {

        if (index == size) {
            Node<E> prev = last;
            Node<E> node = new Node<>(element, prev, null);
            last = node;
            //当链表为空，添加第一个元素的情况
            if (prev == null) {
                //将第一个元素指向node
                first = node;
            } else {
                prev.next = node;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(element, next.prev, next);
            next.prev = node;
            //当index为0，没有prev
            if (prev == null) {
                first = node;
            } else {
                prev.next = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {

        Node<E> node = node(index);
        E old = node.element;

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            last = node.prev;
        }

        size--;
        return old;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            builder.append(node.element);
            if (i < size - 1) {
                builder.append(",");
            }
            node = node.next;
        }

        builder.append("]");
        return builder.toString();
    }


}
