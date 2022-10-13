package com.luguosong._200_io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

/**
 * @author luguosong
 * @date 2022/9/28
 */
public class FileDemo {
    public static void main(String[] args) {

        //File类可以表示目录
        File folder1 = new File("_java/java_se/src/main/resources/folder1");
        File folder2 = new File("_java/java_se/src/main/resources/f1/f2");
        //File类表示文件
        File file1 = new File("hello.txt");
        File file2 = new File("_java/java_se/src/main/resources", "hello2.txt");
        File file3 = new File(folder1, "hello3.txt");

        //文件夹创建
        System.out.println("mkdir创建文件夹：" + folder1.mkdir());
        System.out.println("mkdirs创建多级文件夹：" + folder2.mkdirs());
        //文件创建
        try {
            System.out.println("createNewFile创建文件:" + file1.createNewFile());
            System.out.println("delete删除文件：" + file1.delete());
        } catch (IOException e) {
            System.out.println("createNewFile异常:" + e);
        }

        //获取绝对路径
        System.out.println(file2.getAbsolutePath());
        //获取路径(构造中是相对路径是相对路径，构造中是绝对路径则是绝对路径)
        System.out.println(file2.getPath());
        //获取文件名
        System.out.println(file2.getName());
        //获取上层文件目录路径，若无返回null
        System.out.println(file2.getParent());
        //获取文件长度（字节数）
        System.out.println(file2.length());
        //最后一次修改时间
        System.out.println(file2.lastModified());
        //目录下文件列表
        System.out.println(folder1.list());
        System.out.println(folder1.listFiles());

        //将hello2.txt重命名到hello4.txt,hello2.txt将会消失
        //要求file2存在，hello4.txt不存在
        System.out.println(file2.renameTo(new File(folder1, "hello4.txt")));

        //判断是否是文件目录
        System.out.println(file3.isDirectory());
        //判断是否是文件
        System.out.println(file3.isFile());
        //判断文件是否存在
        System.out.println(file3.exists());
        //判断是否可读
        System.out.println(file3.canRead());
        //判断是否可写
        System.out.println(file3.canWrite());
        //判断是否隐藏
        System.out.println(file3.isHidden());
    }
}
