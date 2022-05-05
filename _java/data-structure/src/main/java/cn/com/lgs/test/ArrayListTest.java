package cn.com.lgs.test;

import cn.com.lgs.data_structure.MyArrayList;
import cn.com.lgs.data_structure.MyList;

/**
 * 测试动态数组
 *
 * @author 10545
 * @date 2022/4/20 0:03
 */
public class ArrayListTest {
    public static void main(String[] args) {

        MyList<Integer> arrays = new MyArrayList<Integer>();

        arrays.add(1);
        arrays.add(2);
        arrays.add(3);
        arrays.add(4);
        arrays.add(5);
        arrays.add(1);
        arrays.add(2);
        arrays.add(3);
        arrays.add(4);
        arrays.add(5);
        System.out.println("arrays.add(x):" + arrays);

        arrays.add(2, 100);
        System.out.println("arrays.add(2, 100)：" + arrays);

        System.out.println("arrays.remove(2)：" + arrays.remove(2) + "，" + arrays);

        System.out.println("arrays.remove(new Integer(2)):" + arrays.remove(new Integer(2)) + "," + arrays);

        System.out.println("arrays.set(1,2):" + arrays.set(1, 2) + "," + arrays);

        System.out.println("arrays.size():" + arrays.size());

        System.out.println("arrays.isEmpty():" + arrays.isEmpty());

        System.out.println("arrays.contains(6):" + arrays.contains(6));

        System.out.println("arrays.get(1):" + arrays.get(1));

        System.out.println("arrays.indexOf(5):" + arrays.indexOf(5));

        System.out.println("arrays.lastIndexOf(5):" + arrays.lastIndexOf(5));

        arrays.clear();
        System.out.println("arrays.clear()：" + arrays);
    }
}
