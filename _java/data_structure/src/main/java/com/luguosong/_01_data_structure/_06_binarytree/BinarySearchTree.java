package com.luguosong._01_data_structure._06_binarytree;

import java.util.Comparator;

/**
 * 二叉搜索树
 *
 * @author luguosong
 * @date 2022/11/15
 */
public class BinarySearchTree<E> {
    private int size;
    private Node<E> root;

    //自定义比较器
    private Comparator comparator;

    /**
     * 默认构造不设置自定义比较器
     */
    public BinarySearchTree(){
        this.comparator=null;
    }

    /**
     * 设置自定义比较器
     *
     * @param comparator
     */
    public BinarySearchTree(Comparator comparator){
        this.comparator=comparator;
    }

    /**
     * 节点
     *
     * @param <E>
     */
    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    /**
     * 检查元素是否为空
     *
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
    }

    private int compare(E e1, E e2) {
        if (comparator!=null){ //比较器不为空使用比较器比较
            return comparator.compare(e1,e2);
        }else { //比较器不为空,元素必须实现Comparable接口
           return  ((Comparable) e1).compareTo(e2);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        } else {
            Node<E> parent = null;
            Node<E> temp = root;
            int compare = 0;
            while (temp != null) {
                compare = compare(element, temp.element);
                parent = temp;
                if (compare > 0)
                    temp = temp.right;
                else if (compare < 0)
                    temp = temp.left;
                else
                    return;
            }
            if (compare > 0)
                parent.right = new Node<>(element, parent);
            else if (compare < 0)
                parent.left = new Node<>(element, parent);
            size++;
        }

    }

}
