package cn.com.lgs.io.file;

import java.io.File;

/**
 * 构建File对象
 *
 * @author luguosong
 * @date 2022/4/1 9:41
 */
public class FileDemo1 {
    public static void main(String[] args) {
        File f1 = new File("E:\\folder\\test.txt");
        System.out.println(f1);
    }
}
