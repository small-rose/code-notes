package cn.com.lgs.io.file;

import java.io.File;
import java.io.IOException;

/**
 * 通过File类创建文件或文件夹
 *
 * @author luguosong
 * @date 2022/4/1 10:45
 */
public class FileDemo2 {
    public static void main(String[] args) throws IOException {
        //createNewFile创建文件
        //文件已存在会返回false
        //文件所在目录不存在会报IOException异常
        File f1 = new File("_java/javase/src/main/java/cn/com/lgs/io/file/test.txt");
        System.out.println("创建文件:" + f1.createNewFile());

        //创建文件夹
        //文件夹已存在返回false
        //如果是多级文件夹会返回false
        File f2 = new File("_java/javase/src/main/java/cn/com/lgs/io/file/folder");
        System.out.println("创建文件夹：" + f2.mkdir());

        //创建多级文件夹
        File f3 = new File("_java/javase/src/main/java/cn/com/lgs/io/file/folder/folder1/folder2/");
        System.out.println("创建多级文件夹：" + f3.mkdirs());
    }
}
