package cn.com.lgs.data_structure;

/**
 * 链表
 *
 * @author 10545
 * @date 2022/4/28 21:21
 */
public class MyLinkedList<AnyType> extends MyAbstractList<AnyType> {

    /**
     * 第一个元素
     */
    private Node<AnyType> first;

    /**
     * 最后一个元素
     */
    private Node<AnyType> last;

    /**
     * 指定位置添加元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, AnyType element) {
        if (index == 0) {
            Node<AnyType> node = new Node<>(null, element, first);
            first = node;
        } else {
            Node<AnyType> node1 = node(index - 1);
            Node<AnyType> node2 = new Node<AnyType>(node1, element, node1.next);
            node1.next = node2;
            node2.next.prev = node2;
        }
        theSize++;
    }

    @Override
    public AnyType remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    /**
     * 清空
     */
    @Override
    public void clear() {
        //清空元素个数
        theSize = 0;
        //清空第一项，后续项会被自动回收
        first = null;
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public AnyType set(int index, AnyType element) {
        //根据index获取节点
        Node<AnyType> node = node(index);

        //获取节点中的元素
        AnyType old = node.data;

        //将节点中的内容修改为新内容
        node.data = element;

        //将节点中原来的内容返回
        return old;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    @Override
    public AnyType get(int index) {
        return node(index).data;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public void ensureCapacity(int newCapacity) {

    }

    /**
     * 通过索引返回节点
     *
     * @param index
     * @return
     */
    private Node<AnyType> node(int index) {
        //边界检查
        rangeCheck(index);

        Node<AnyType> tempNode = first;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    /**
     * 节点类
     * 私有静态内部类
     *
     * @param <AnyTYpe>
     */
    private static class Node<AnyTYpe> {
        AnyTYpe data;
        Node<AnyTYpe> prev;
        Node<AnyTYpe> next;

        /**
         * 节点构造
         *
         * @param prev
         * @param data
         * @param next
         */
        public Node(Node<AnyTYpe> prev, AnyTYpe data, Node<AnyTYpe> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
