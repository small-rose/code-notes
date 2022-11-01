package com.luguosong._01_data_structure._03_linkedlist;

import com.luguosong._01_data_structure.MyAbstractList;
import com.luguosong._01_data_structure.MyList;

/**
 * 单向链表
 *
 * @author luguosong
 * @date 2022/10/20
 */
public class SinglyLinkedList<E> extends MyAbstractList<E> {

    /**
     * 链表第一个元素
     */
    protected Node<E> first;

    /**
     * 内部类,表示链表中的元素
     *
     * @param <E>
     */
    protected static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 获取指定索引节点
     *
     * @param index
     * @return
     */
    protected Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }


    /**
     * 获取指定位置元素
     * 复杂度：O(n)
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        //返回节点元素
        return node(index).element;
    }

    /**
     * 修改指定位置元素
     * 复杂度：O(n)
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        //获取节点原来的元素
        E old = node(index).element;
        //设置节点元素
        node(index).element = element;
        //返回旧节点元素
        return old;
    }

    /**
     * 向指定位置添加元素
     * 复杂度：O(n)
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        if (index == 0) {
            //创建新节点
            Node<E> newNode = new Node<E>(element, first);
            //将新节点作为第一个节点
            first = newNode;
        } else {
            //获取上一个节点
            Node<E> prev = node(index - 1);
            //创建新节点，将新节点的下一个节点设置为原prev的下一个节点
            Node<E> newNode = new Node<>(element, prev.next);
            //将prev的下一个节点设置为新节点
            prev.next = newNode;
        }

        //元素个数加一
        size++;
    }

    /**
     * 删除指定位置元素
     * 复杂度：O(n)
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        //索引检查
        rangeCheck(index);

        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }

        size--;

        return node.element;
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
