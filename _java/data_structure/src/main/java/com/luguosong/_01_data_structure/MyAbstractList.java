package com.luguosong._01_data_structure;

/**
 * 存放链表公共重复的方法
 *
 * @author luguosong
 * @date 2022/10/22
 */
public abstract class MyAbstractList<E> implements MyList<E> {

    /**
     * 元素数量
     */
    protected int size;

    /**
     * 超出范围提示
     *
     * @param index
     * @return
     */
    protected String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * 检查索引是否在范围内
     *
     * @param index
     */
    protected void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * add 和 addAll 使用的 rangeCheck 版本
     * index允许等于size
     *
     * @param index
     */
    protected void rangeCheckForAdd(int index) {
        if (index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 返回元素数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断元素是否存在
     *
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    /**
     * 添加元素
     *
     * @param element
     */
    @Override
    public void add(E element) {
        add(size, element);
    }
}
