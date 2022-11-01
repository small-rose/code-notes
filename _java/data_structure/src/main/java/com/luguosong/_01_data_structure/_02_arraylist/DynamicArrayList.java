package com.luguosong._01_data_structure._02_arraylist;

import com.luguosong._01_data_structure.MyAbstractList;
import com.luguosong._01_data_structure.MyList;

import java.util.Arrays;

/**
 * 动态数组
 *
 * @author luguosong
 * @date 2022/10/17
 */
public class DynamicArrayList<E> extends MyAbstractList<E> {

    /**
     * 数组默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 数组元素
     */
    private Object[] elementData;


    /**
     * 保证数组容量
     *
     * @param capacity
     */
    public void ensureCapacity(int capacity) {
        if (elementData.length < capacity) {
            //获取原数组容量
            int oldCapacity = elementData.length;
            //数组容量扩大1.5倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            //扩容数组
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
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

        for (int i = 0; i < size; i++) {
            builder.append(elementData[i]);
            if (i < size - 1) {
                builder.append(",");
            }
        }

        builder.append("]");
        return builder.toString();
    }


    /**
     * 构造函数，带初始容量
     *
     * @param initialCapacity
     */
    public DynamicArrayList(int initialCapacity) {
        //当数组容量小于默认容量，使用默认容量
        initialCapacity = (initialCapacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : initialCapacity;
        //根据数组容量大小，创建数组
        elementData = new Object[initialCapacity];
    }

    /**
     * 构造函数
     */
    public DynamicArrayList() {
        //调用带参数的构造函数
        this(DEFAULT_CAPACITY);
    }


    /**
     * 获取指定位置的元素
     * 复杂度：O(1)
     *
     * @param index
     * @return
     */
    public E get(int index) {
        //检查索引范围
        rangeCheck(index);

        return (E) elementData[index];
    }

    /**
     * 将指定位置的元素替换为指定元素
     * 复杂度：O(1)
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        //检查索引范围
        rangeCheck(index);

        //获取旧元素
        E oldValue = (E) elementData[index];
        //替换为新元素
        elementData[index] = element;
        //返回该位置旧元素
        return oldValue;
    }

    /**
     * 返回元素第一次出现的索引
     *
     * @param o
     * @return
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }


    /**
     * 清除所有元素
     */
    public void clear() {
        //将元素指向null，这样原本堆中的对象不再被引用会被自动清理掉
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        //设置元素个数为0
        size = 0;
    }

    /**
     * 在指定位置添加元素
     * 复杂度：O(n)
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        //数组扩容
        ensureCapacity(index + 1);
        //检查index范围
        rangeCheckForAdd(index);
        //将指定位置及之后的元素后移
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        //设置指定位置元素
        elementData[index] = element;
        size++;
    }

    /**
     * 删除元素
     * 复杂度：O(n)
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        //对索引进行判断
        rangeCheck(index);
        //获取要删除的元素
        Object old = elementData[index];
        //将要删除的元素之后的元素前移
        for (int i = index; i < (size - 1); i++) {
            elementData[i] = elementData[i + 1];
        }
        //主动将最后一个元素引用设置为null，让垃圾回收清理对应的对象
        elementData[--size] = null;
        return (E) old;
    }
}
