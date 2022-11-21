package com.luguosong._200_io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

/**
 * IO示例
 *
 * @author luguosong
 * @date 2022/11/17
 */
public class IODemo {
    /**
     * File类相关方法调用
     *
     * @throws IOException
     */
    @Test
    public void testFile() throws IOException {
        File resources = new File("src/main/resources");
        System.out.println("resources路径是否存在：" + resources.exists());
        File folder1 = new File(resources, "folder1");
        if (!folder1.exists()) {
            System.out.println("创建folder1文件夹：" + folder1.mkdirs());
        }
        System.out.println("folder1下的文件列表：" + Arrays.toString(folder1.list()));
        File hello = new File(folder1, "hello");
        System.out.println("创建hello文件夹：" + hello.mkdir());
        File helloTxt = new File(folder1, "hello.txt");  //文件已存在
        if (!helloTxt.exists())
            System.out.println("创建hello.txt文件：" + helloTxt.createNewFile());
        System.out.println("hello.txt是否可读：" + helloTxt.canRead());
        System.out.println("hello.txt是否可写：" + helloTxt.canWrite());
        System.out.println("hello.txt是否为隐藏文件：" + helloTxt.isHidden());
        System.out.println("获取绝对路径:\n" + helloTxt.getAbsolutePath());
        System.out.println("获取路径(构造中是相对路径是相对路径，构造中是绝对路径则是绝对路径):\n" + helloTxt.getPath());
        System.out.println("获取文件名:" + helloTxt.getName());
        System.out.println("获取上层文件目录路径，若无返回null:" + helloTxt.getParent());
        System.out.println("文件大小：" + helloTxt.length());
        System.out.println("最后一次修改时间:" + new Date(helloTxt.lastModified()));
        File fileNew = new File(hello, "hello2.txt");
        System.out.println("文件剪切：" + helloTxt.renameTo(fileNew));
        System.out.println("删除hello2.txt文件：" + fileNew.delete());
        System.out.println("删除hello文件夹:" + hello.delete());
    }

    /**
     * 将字节数组以字节为单位转为16进制字符串
     *
     * @param bytes
     * @return
     */
    private String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            sb.append("0x");
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex + " ");
        }
        return sb.toString();
    }

    /**
     * 获取默认字符编码，与main方法所在文件编码有关
     */
    @Test
    public void testDefaultCharset() {
        System.out.println(Charset.defaultCharset());
    }

    /**
     * 字符编码测试
     */
    @Test
    public void testCharacterEncoding() throws UnsupportedEncodingException {
        String name = "java编程";
        System.out.println(Arrays.toString(name.getBytes()));
        System.out.println("默认编码：   " + bytesToHex(name.getBytes()));
        System.out.println("UTF-8：     " + bytesToHex(name.getBytes("UTF-8")));
        System.out.println("ASCII：     " + bytesToHex(name.getBytes("ASCII")));
        System.out.println("ISO-8859-1：" + bytesToHex(name.getBytes("ISO-8859-1")));
        System.out.println("GB2312：    " + bytesToHex(name.getBytes("GB2312")));
        System.out.println("BIG5：      " + bytesToHex(name.getBytes("BIG5")));
        System.out.println("GBK：       " + bytesToHex(name.getBytes("GBK")));
        System.out.println("GB18030：   " + bytesToHex(name.getBytes("GB18030")));
    }

    /**
     * 字节数组解码
     */
    @Test
    public void testByteDecoding() throws UnsupportedEncodingException {
        byte[] bytes = {106, 97, 118, 97, -25, -68, -106, -25, -88, -117};
        System.out.println(new String(bytes));
        System.out.println(new String(bytes, "GBK"));
    }

    /**
     * 字节流测试
     *
     * @throws IOException
     */
    @Test
    public void testFileStream() throws IOException {
        //确保文件存在
        File file = new File("src/main/resources/file_stream/1.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();

        //使用字节输出流向文件写入数据
        OutputStream os1 = new FileOutputStream(file);
        os1.write(72);
        os1.write(101);
        os1.write(108);
        os1.write(108);
        os1.write(111);
        os1.write(32);
        os1.close();

        //FileOutputStream第二个参数表示是否在原有文件上追加，不加则表示清空文件重新写入
        OutputStream os2 = new FileOutputStream(file, true);
        os2.write("Java编程".getBytes());
        os2.close();

        //字节输入流读取文件内容
        FileInputStream is = new FileInputStream(file);
        //打印每个字节
        //int content;
        //while ((content = is.read()) != -1) {
        //    System.out.println(content);
        //}

        //将文件一次性读出来
        byte[] bytes = new byte[(int) file.length()];
        int size = is.read(bytes);
        System.out.println(new String(bytes));

        is.close();

        //将测试文件清理掉
        file.delete();
        file.getParentFile().delete();
    }

    /**
     * 字符流测试
     */
    @Test
    public void testFileReadWrite() throws IOException {
        //确保文件存在
        File file = new File("src/main/resources/file_read_write/1.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileWriter fw = new FileWriter(file);
        fw.write("Java编程");
        fw.close();

        FileReader fr = new FileReader(file);
        int content;
        while ((content = fr.read()) != -1) {
            System.out.print((char) content + " -> ");
            System.out.println(Integer.toHexString(content));
        }
        fr.close();

        //将测试文件清理掉
        file.delete();
        file.getParentFile().delete();
    }

    /**
     * 缓冲流测试，缓冲流是普通输入输出流的装饰模式
     */
    @Test
    public void testFileBuffer() throws IOException {
        //确保文件存在
        File file = new File("src/main/resources/file_read_write/1.txt");
        file.getParentFile().mkdirs();
        //file.createNewFile();

        FileWriter fw = new FileWriter(file);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write("Java编程");
        //手动将数据从缓冲区写入文件
        bfw.flush();
        //bfw内部会调用flush将文件从缓冲区写入文件
        //BufferedWriter执行close会同时执行内部FileWriter的close
        bfw.close();

        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
        int content;
        while ((content = bfr.read()) != -1) {
            System.out.print((char) content);
        }
        bfr.close();

        //将测试文件清理掉
        file.delete();
        file.getParentFile().delete();
    }

    /**
     * 数据流测试，用于存储基本数据类型
     *
     * @throws IOException
     */
    @Test
    public void testDataStream() throws IOException {
        //确保文件存在
        File file = new File("src/main/resources/file_read_write/1.txt");
        file.getParentFile().mkdirs();

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        dos.writeInt(20);
        dos.writeDouble(1.75);
        dos.writeUTF("数据流");
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        System.out.println(dis.readInt());
        System.out.println(dis.readDouble());
        System.out.println(dis.readUTF());
        dis.close();

        //将测试文件清理掉
        file.delete();
        file.getParentFile().delete();
    }

    private static class Student implements Serializable {
        private String name;
        private Integer age;

        //被transient修饰的变量不会被序列化
        private transient Double height;

        public Student(String name, Integer age, Double height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", height=" + height +
                    '}';
        }
    }

    /**
     * 对象流
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testObjectStream() throws IOException, ClassNotFoundException {
        //确保文件存在
        File file = new File("src/main/resources/file_read_write/1.txt");
        file.getParentFile().mkdirs();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        Student student = new Student("张三", 19, 180.0);
        oos.writeObject(student);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        System.out.println((Student) ois.readObject());
        ois.close();

        //将测试文件清理掉
        file.delete();
        file.getParentFile().delete();
    }

}
