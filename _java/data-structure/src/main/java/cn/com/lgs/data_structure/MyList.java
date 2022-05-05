package cn.com.lgs.data_structure;

import java.util.ArrayList;

/**
 * @author 10545
 * @date 2022/4/29 22:07
 */
public interface MyList<AnyType> {
    /**
     * 向末尾添加元素
     *
     * @param anyType
     * @return
     */
    public boolean add(AnyType anyType);

    /**
     * 向指定位置添加元素
     *
     * @param index
     * @param element
     */
    public void add(int index, AnyType element);

    /**
     * 删除指定位置元素
     *
     * @param index
     * @return
     */
    public AnyType remove(int index);

    /**
     * 删除第一次出现的指定元素
     *
     * @param o
     * @return
     */
    public boolean remove(Object o);


    /**
     * 清除所有元素
     */
    public void clear();

    /**
     * 设置index位置的元素,并返回原来元素
     *
     * @param index
     * @param element
     * @return
     */
    public AnyType set(int index, AnyType element);

    /**
     * 元素个数
     *
     * @return
     */
    public int size();

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * 判断是否包含元素o
     *
     * @param o
     * @return
     */
    public boolean contains(Object o);

    /**
     * 根据index获取元素
     *
     * @param index
     * @return
     */
    public AnyType get(int index);

    /**
     * 查看元素第一次出现的索引
     *
     * @param o
     * @return
     */
    public int indexOf(Object o);

    /**
     * 查看元素最后一次出现的位置
     *
     * @param o
     * @return
     */
    public int lastIndexOf(Object o);

    /**
     * 扩容
     *
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity);

}
