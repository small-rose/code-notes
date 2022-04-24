package cn.com.lgs.data_structure;

import java.util.*;

/**
 * 动态数组
 *
 * @author 10545
 * @date 2022/4/17 22:43
 */
public class MyArrayList<AnyType> implements List<AnyType> {

    //默认数组容量
    private static final int DEFAULT_CAPACITY = 10;

    //元素个数
    //注意：这里theSize并不是数组theItems的容量，而是其中实际存放元素的个数
    private int theSize;
    //数组，用于存放元素
    private Object[] theItems;


    /**
     * 有参构造
     *
     * @param initialCapacity 数组容量
     */
    public MyArrayList(int initialCapacity) {
        //初始容量如果小于0，抛出异常
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        //初始化时容量小于DEFAULT_CAPACITY，则容量默认设置为DEFAULT_CAPACITY
        initialCapacity = (initialCapacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : initialCapacity;
        theItems = new Object[initialCapacity];
    }

    /**
     * 无参构造
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < theSize; i++) {
            theItems[i] = null;
        }
        theSize = 0;
    }

    /**
     * 动态数组中元素个数
     *
     * @return
     */
    @Override
    public int size() {
        return theSize;
    }

    /**
     * 判断动态数组中元素是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return theSize == 0;
    }

    /**
     * 判断是否包含元素o
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends AnyType> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends AnyType> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * 根据index获取元素
     *
     * @param index
     * @return
     */
    @Override
    public AnyType get(int index) {
        //检查index是否超出范围
        rangeCheck(index);

        return (AnyType) theItems[index];
    }

    /**
     * 设置index位置的元素,并返回原来元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public AnyType set(int index, AnyType element) {
        //检查index是否超出范围
        rangeCheck(index);

        AnyType old = (AnyType) theItems[index];
        theItems[index] = element;
        return old;
    }

    /**
     * 向末尾添加元素
     *
     * @param anyType
     * @return
     */
    @Override
    public boolean add(AnyType anyType) {
        //调用重载方法
        add(size(), anyType);
        return false;
    }

    /**
     * 向指定位置添加元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, AnyType element) {
        if (theSize == theItems.length)
            ensureCapacity(size() * 2 + 1);

        //将index位置之后的所以元素向后移一位
        for (int i = theSize; i > index; i--) {
            theItems[i] = theItems[i - 1];
        }

        //将index位置元素设置为插入值
        theItems[index] = element;

        //元素个数加一
        theSize++;
    }

    @Override
    public AnyType remove(int index) {
        return null;
    }

    /**
     * 查看元素第一次出现的索引
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < theSize; i++) {
                if (theItems[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < theSize; i++) {
                if (o.equals(theItems[i]))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<AnyType> listIterator() {
        return null;
    }

    @Override
    public ListIterator<AnyType> listIterator(int index) {
        return null;
    }

    @Override
    public List<AnyType> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * 扩容
     *
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity) {
        if (3 < theSize)
            return;
        Object[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    /**
     * index范围检查
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= theSize || index < 0)
            throw new IndexOutOfBoundsException("index超出范围");
    }

    /**
     * 重写toString方法，用于打印
     *
     * @return
     */
    @Override
    public String toString() {
        return "MyArrayList{" +
                "theSize=" + theSize +
                ", theItems=" + Arrays.toString(theItems) +
                '}';
    }
}
