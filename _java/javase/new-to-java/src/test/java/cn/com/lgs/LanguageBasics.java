package cn.com.lgs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 语言基础
 *
 * @author 10545
 * @date 2021 /10/2 22:29
 */
class LanguageBasics {


    /**
     * 复制数组
     */
  @Test
  void arrayCopyDemo() {
        String[] copyFrom = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto" };

        String[] copyTo = new String[7];
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");
        }
    }


    /**
     *  java.util.Arrays工具类测试
     */
    @Test
    void arraysDemo(){
        String[] copyFrom1 = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo"};
        String[] copyForm2 = {
                "Marocchino", "Ristretto"
        };

        //查找
        System.out.println(Arrays.binarySearch(copyFrom1,"Cortado"));
        //复制
        String[] copyTo = Arrays.copyOfRange(copyFrom1, 2, 9);
        System.out.println(Arrays.toString(copyTo));
        //比较
        System.out.println(Arrays.equals(copyFrom1,copyForm2));
        // 在每个索引位置填上指定值
        Arrays.fill(copyTo,"Cortado");
        System.out.println(Arrays.toString(copyTo));
        //升序排序之顺序排序：
        int[] ints1 = {3, 5, 1, 2};
        Arrays.sort(ints1);
        System.out.println(Arrays.toString(ints1));
        //升序排序之
        int[] ints2 = {30, 50, 10, 20};
        Arrays.parallelSort(ints2);
        System.out.println(Arrays.toString(ints2));
    }
}
