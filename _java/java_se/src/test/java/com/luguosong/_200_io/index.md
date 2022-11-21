---
layout: default
title: IO
nav_order: 200
parent: JavaSE
---

# File类

代表`文件`或`文件目录`

文件路径分隔符使用`/`或`\\`，单单一个`\`表示转义字符，会报错。

[File API文档](https://docs.oracle.com/javase/8/docs/api/)

```java
//相关方法示例
class Demo {
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
}
```

# IO流概述

`输入流`：`文件`写到`应用程序`

`输出流`：`应用程序`写到`文件`

# 字符集（Character Set）

{: .new-title}
> 字符集
>
> 由字符组成的集合

常见的字符集有：

- `ASCII`:128个字符（包含英文字母大小写，阿拉伯数字等）
- `ISO-8859-1`:支持欧洲的部分语言文字，在有些环境叫Latin-1
- `GB2312`:支持中文（包括了6763个汉字）
- `BIG5`:支持繁体中文（包括了13053个汉字）
- `GBK`:对`GB2312`和`BIG5`的扩充（包含21003个汉字）,支持中日韩
- `GB18030`:是对`GBK`的扩充(包括了27484个汉字)
- `Unicode`:包括世界上所有字符

# 字符编码（Character Encoding）

{: .new-title}
> 字符编码
>
> 每个字符集都有对应的字符编码 ， 它决定了每个字符如何转成二进制存储在计算机中

- `ASCII`:单字节编码，编码范围是0x00~0x7f
- `ISO-8859-1`：单字节编码，编码范围0x00~0xff
    - 0x00~0x7f和ASCII一致，0x80~0x9f是控制符，0xa0~0xff是文字符号
- `GB2312`、`BIG5`、`GBK`：使用双字节表示一个汉字
- `GB18030`:采用单字节、双字节、四字节表示一个字符
- `Unicode`:有Unicode、UTF-8、UTF-16、UTF-32等编码
    - UTF-8采用单字节、双字节、三字节、四字节表示一个字符

{: .warning-title}
> 默认编码
>
> Java的默认编码取决于main方法所在的文件编码

```java
class Demo {
    @Test
    public void testDefaultCharset() {
        System.out.println(Charset.defaultCharset());
    }
}
```

- `编码(Encode)`：一般将`字符串`转为`二进制`的过程
- `解码 (Decode)`：一般将`二进制`转为`字符串`的过程

**字符串编码**：

```java
class Demo {
    @Test
    public void testCharacterEncoding() throws UnsupportedEncodingException {
        String name = "java编程";
        System.out.println("默认编码：   " + bytesToHex(name.getBytes()));
        System.out.println("UTF-8：     " + bytesToHex(name.getBytes("UTF-8")));
        System.out.println("ASCII：     " + bytesToHex(name.getBytes("ASCII")));
        System.out.println("ISO-8859-1：" + bytesToHex(name.getBytes("ISO-8859-1")));
        System.out.println("GB2312：    " + bytesToHex(name.getBytes("GB2312")));
        System.out.println("BIG5：      " + bytesToHex(name.getBytes("BIG5")));
        System.out.println("GBK：       " + bytesToHex(name.getBytes("GBK")));
        System.out.println("GB18030：   " + bytesToHex(name.getBytes("GB18030")));
    }
}
```

```text
默认编码：   0x6a 0x61 0x76 0x61 0xe7 0xbc 0x96 0xe7 0xa8 0x8b 
UTF-8：     0x6a 0x61 0x76 0x61 0xe7 0xbc 0x96 0xe7 0xa8 0x8b 
ASCII：     0x6a 0x61 0x76 0x61 0x3f 0x3f 
ISO-8859-1：0x6a 0x61 0x76 0x61 0x3f 0x3f 
GB2312：    0x6a 0x61 0x76 0x61 0xb1 0xe0 0xb3 0xcc 
BIG5：      0x6a 0x61 0x76 0x61 0x3f 0xb5 0x7b 
GBK：       0x6a 0x61 0x76 0x61 0xb1 0xe0 0xb3 0xcc 
GB18030：   0x6a 0x61 0x76 0x61 0xb1 0xe0 0xb3 0xcc 
```

**字节数组解码**：

当解码方式与编码方式不一致，会出现乱码。

```java
class Demo {
    /**
     * 字节数组解码
     */
    @Test
    public void testByteDecoding() throws UnsupportedEncodingException {
        byte[] bytes = {106, 97, 118, 97, -25, -68, -106, -25, -88, -117};
        System.out.println(new String(bytes));
        System.out.println(new String(bytes, "GBK"));
    }
}
```

# 字节流（Byte Streams）

常用的字节流有`FileInputStream`和`FileOutputStream`,继承自`InputStream`和`OutputStream`。一次只读写一个字节。

```java
class Demo {
    /**
     * 字节流测试
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
}
```

# 字符流（Character Streams）

常用的字节流有`FileReader`和`FileWriter`,继承自`Reader`和`Writer`。一次只读写一个字符。

`FileReader`和`FileWriter`只适合`.txt`、`.java`这类文本文件。

```java
class Demo {
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
}
```

# 缓冲流（Buffered Stream）

`缓冲输入流`：从缓冲区读取数据，并且只有当缓冲区为空时才调用本地的输入API(NativelnputAPI)。常见的有`BufferedInputStream`
,`BufferedReader`

`缓冲输出流`：将数据写入缓冲区，并且只有当缓冲区已满时才调用本地的输出API(NativeOutputAPI).常见的有`BufferedOutputStream`
,`BufferedWriter`

```java
class Demo {
    /**
     * 缓冲流测试，缓冲流是普通输入输出流的装饰模式
     */
    @Test
    public void testFileBuffer() throws IOException {
        //确保文件存在
        File file = new File("src/main/resources/file_read_write/1.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();

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
}
```
