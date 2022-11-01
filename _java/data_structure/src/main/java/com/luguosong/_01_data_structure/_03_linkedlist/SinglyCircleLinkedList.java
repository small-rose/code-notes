package com.luguosong._01_data_structure._03_linkedlist;

/**
 * 单向循环链表
 *
 * @author luguosong
 * @date 2022/10/26
 */
public class SinglyCircleLinkedList<E> extends SinglyLinkedList<E> {
    @Override
    public void add(int index, E element) {
        if (index == 0) {
            //创建新节点
            Node<E> newNode = new Node<E>(element, first);


            //获取最后位置的节点
            Node<E> last = (size == 0) ? newNode : node(size - 1);

            //将新节点作为第一个节点
            first = newNode;

            //将最后一个元素的下一个节点指向第一个节点
            last.next = first;
        } else {
            //获取上一个节点
            Node<E> prev = node(index - 1);
            //创建新节点，将新节点的下一个节点设置为原prev的下一个节点
            Node<E> newNode = new Node<>(element, prev.next);
            //将prev的下一个节点设置为新节点
            prev.next = newNode;
        }
        size++;

    }

    @Override
    public E remove(int index) {
        //索引检查
        rangeCheck(index);

        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                first = first.next;
                last.next = first;
            }
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }
}
