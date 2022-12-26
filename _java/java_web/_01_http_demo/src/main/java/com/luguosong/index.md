---
layout: default
title: 概述
nav_order: 10
parent: JavaWeb
---

# web简介

## 概述

Web是一种分布式应用架构，旨在共享分布在网络上的各个Web服务器中所有互相连接的信息。Web采用`客户/服务器`通信模式，客户与服务器之间用`HTTP协议`通信。Web使用`超级文本技术（HTML）`来连接网络上的信息。信息存放在服务器端，客户机通过`浏览器`（例如IE 或 Chrome）就可以查找网络中各个Web服务器上的信息。

从抽象层面上理解，Web是一个巨大的信息集合，Web的首要任务就是向人们提供信息服务。这些相互连接的信息尽管在物理上分布在网络的不同机器节点上，但是，对于访问信息的用户而言，用户可以用统一的方式在浏览器上访问来自世界各地的信息。因此在用户眼里，这些信息在逻辑上是一个相互连接的统一整体。

web最初由Tim Berners-Lee发明。1980年，Tim Berners-Lee负责一个名为`Enquire`（Enquire Within Upon Everything）的项目，该项目确立了Web的雏形。1990年11月，第一个名为“nxoc01.cern.ch”的Web服务器开始运行，Tim Berners-Lee在自己编写的图形化Web浏览器“WorldWideWeb”上看到了最早的Web页面。1991年，`CERN（European Laboratory for Particle Physics）组织`正式发布了Web技术标准。目前，与Web相关的各种技术标准都由著名的`W3C组织（World Wide Web Consortium）`来管理和维护。

`WWW`是指全球范围内的Web，它以Internet作为网络平台，Internet是来自世界各地的众多相互连接的计算机和其他设备的集合，而WWW则是Internet上的一种分布式应用架构

## 信息表达-HTML

用超级文本技术（HTML）来表达信息，以及建立`信息与信息`的连接

信息可以用文本、图片、声音和图像等形式来表示。用HTML语言编写的文档，即HTML文档，不仅可以直接包含文本内容，还可以把其他形式的信息包含进来。

- 允许直接包含纯文本形式的信息。
- 利用`<img>`、`<audio>`和`<video>`等标记来包含图片、声音和视频等多媒体形式的信息。
- 利用`<table>`、`<p>`和`<b>`等标记来设定信息在浏览器中的展示格式。
- 利用超链接标记`<a>`来连接其他信息。

`浏览器`能够解析HTML文档中的标记，然后在自己的窗口中直观地展示HTML文档。

## 信息定位-URL

用统一资源定位技术（URL）来实现网络上`信息的精确定位`

`统一资源定位器（Uniform Resource Locator，URL）`是专为标记网络上资源的位置而设的一种编址方式。

URL一般由以下3个部分组成:
- 应用层协议
- 主机IP地址或域名
- 资源所在路径/文件名

## 信息传输-HTTP

### 概述

用网络应用层协议（HTTP）来规范浏览器与Web服务器之间的`通信过程`

`超文本传输协议（Hypertext Transfer Protocol，HTTP）`是关于如何在网络上传输超级文本（即HTML文档）的协议。HTTP规定了Web的基本运作过程，以及浏览器与Web服务器之间的通信细节。

HTTP采用`客户/服务器`通信模式，服务器端为`HTTP服务器`，`HTTP服务器`也称作`Web服务器`；客户端为`HTTP客户程序`，`浏览器`是最常见的`HTTP客户程序`。

Http一次通讯过程包括：
1. 客户端与服务器端建立TCP连接。
2. 客户端发出HTTP请求。
3. 服务器端发回相应的HTTP响应。
4. 客户端与服务器之间的TCP连接关闭。

从`HTTP/1.1`版本开始，为了提高服务器端响应客户端请求的性能，在一个TCP连接中，允许处理多个HTTP请求。

### 客户端与服务端

浏览器具备以下功能：
- 请求与Web服务器建立TCP连接。
- 创建并发送HTTP请求。
- 接收并解析HTTP响应。
- 在窗口中展示HTML文档。

Web服务器具备以下功能：
- 接收来自浏览器的TCP连接请求。
- 接收并解析HTTP请求。
- 创建并发送HTTP响应。

### HTTP请求

HTTP请求由如下3部分构成：
- 请求方法、URI和HTTP的版本。
- 请求头（Request Header）。
- 请求正文（Request Content）。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220628220702.png)

HTTP请求可以使用多种方式：
- `GET`：这种请求方式最为常见，客户程序通过这种请求方式`访问服务器上的一个文档`，服务器`把文档发送给客户程序`。
- `POST`：客户程序可以通过这种方式`发送大量信息给服务器`。在HTTP请求中除了包含要访问的文档的URI，还包括大量的`请求正文`，这些请求正文中`通常会包含HTML表单数据`。
- `HEAD`：客户程序和服务器之间`交流一些内部数据`，服务器不会返回具体的文档。当使用GET和POST方法时，服务器最后都将特定的文档返回给客户程序。而HEAD请求方式则不同，它仅仅交流一些内部数据，这些数据不会影响用户浏览网页的过程，可以说对用户是透明的。HEAD请求方式通常不单独使用，而是对其他请求方式起辅助作用。一些搜索引擎使用HEAD请求方式来`获得网页的标志信息`，还有一些HTTP服务器进行`安全认证`时，用这个方式来`传递认证信息`。
- `PUT`：客户程序通过这种方式把`文档上传给服务器`。
- `DELETE`：客户程序通过这种方式来`删除远程服务器上的某个文档`。客户程序可以利用PUT和DELETE请求方式来管理远程服务器上的文档。

`统一资源定位符（Universal Resource Identifier，URI）`用于标记要访问的网络资源。在HTTP请求中，通常只要给出相对于服务器根目录的相对目录即可，相对目录以“/”开头。

HTTP请求的第一行的最后一部分内容为客户程序使用的`HTTP的版本`。

`请求头`包含许多有关客户端环境和请求正文的有用信息。例如，请求头可以声明`浏览器的类型`、`所用的语言`、`请求正文的类型`，`以及请求正文的长度`等。

HTTP规定，`请求头`和`请求正文`之间必须以空行分隔（即只有CRLF符号的行），这个空行非常重要，它表示请求头已经结束，接下来是请求正文。

`CRLF（Carriage Return Linefeed）`是指回车符和行结束符“\r\n”。

### HTTP响应

HTTP响应也由3部分构成:
- HTTP的版本、状态代码和描述。
- 响应头（Response Header）。
- 响应正文（Response Content）。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220628222034.png)

状态代码是一个3位整数，以1、2、3、4或5开头:
- `1xx`：信息提示，表示临时的响应。
- `2xx`：响应成功，表明服务器成功地接收了客户端请求。
- `3xx`：重定向。
- `4xx`：客户端错误，表明客户端可能有问题。
- `5xx`：服务器错误，表明服务器由于遇到某种错误而不能响应客户请

`响应头`也和请求头一样包含许多有用的信息，例如`服务器类型`、`正文类型`和`正文长`度等。

`响应正文`就是服务器返回的具体数据，它是浏览器真正请求访问的信息。HTTP响应头与响应正文之间也必须用空行分隔。当浏览器接收到HTTP响应后，会根据响应正文的`不同类型来进行不同的处理`。

### 正文部分的MIME类型

MIME协议由W3C组织制定，[RFC2045文档](http://www.ietf.org/rfc/rfc2045.txt)对MIME协议做了详细阐述。`MIME（Multipurpose Internet Mail Extension）`是指多用途网络邮件扩展协议，这里的邮件不单纯地指E-Mail，还可以包括通过各种`应用层协议`在网络上`传输的数据`。因此，也可以将HTTP中的请求正文和响应正文看作邮件。MIME规定了邮件的标准数据格式，从而使得接收方能“看懂”发送方发送的邮件。

在HTTP请求头和HTTP响应头中都有一个`Content-type`项，用来指定请求正文部分或响应正文部分的MIME类型。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220628230428.png)

### 各版本特性

- HTTP/0.9
    - 于1991年发布，是最简单的HTTP。
    - HTTP请求中不包含HTT的版本号和头部信息
    - HTTP请求只有一个GET方法
    - HTTP响应结果只能包含HTML文档
    - 不允许包含多媒体文件
    - HTTP/0.9很快就被 HTTP/1.0取代
- HTTP/1.0
    - 于1996年发布，它在HTTP/0.9的基础上做了很大的改进
    - HTTP请求不仅支持GET方法，还支持POST和HEAD方法
    - HTTP响应结果中可以包含HTML文档、图片、视频或其他类型的数据
    - 请求和响应都增加了版本号和头部信息
    - 响应结果中包含状态码、授权认证、缓存和内容编码等信息
    - 在一个TCP连接中`只能发出一次HTTP请求`，即针对每个HTTP请求都需要重新建立一个 TCP 连接。而频繁建立客户端与服务器端的TCP连接很耗资源，会减缓服务端响应客户端请求的速度。
- HTTP/1.1
    - 于1999年发布，它在HTTP/1.0的基础上做了很大改进
    - 持久TCP连接
        - `Connection:keep-Alive`
        - `Keep_Alive:max=5,timeout=120`
        - 客户端如果想要关闭连接，可以在最后一个请求的请求头中，加上`Connection：close`选项来指定安全关闭这个连接
    - `管道机制`：在一个TCP连接中，客户端发出一个HTTP请求后，不需要等待收到服务器端的HTTP响应后，才发送下一个请求，客户端可以连续发送几个请求，服务端按照接受请求的先后顺序，依次把响应返回给客户端。
    - 增加`PUT`和`DELETE`请求方法
    - 虽然支持持久TCP连接，并引入了管道机制。但按照先后顺序来处理HTTP请求的，并依次返回响应内容。只有前一个HTTP响应生成完毕，才能生成下一个响应。如果生成前一个响应非常慢，那么后面的响应任务只能等待，这样会导致响应任务队列堵塞。
- HTTP/2.0
    - 低延时传输
    - 二进制协议
        - 将数据分成一个一个的帧，头部帧存储元数据（即头部信息），数据帧存放正文数据。HTTP/1.1请求和响应的头部信息是文本，正文数据则既可以是文本，也可以是二进制数据；而`HTTP/2.0`请求和响应的头部和正文部分都是二进制数据。
    - 多路复用
        - 在同一个TCP连接中，可以并发传输多个响应的结果二进制数据流，这就解决了HTTP/1.1中的响应任务队列堵塞问题。
    - 头部压缩
        - 一个客户端不断访问服务器时，有很多重复的数据,会增加带宽的使用以及延迟。为了解决这个问题，HTTP/2.0引入了头部压缩机制
        - 使用霍夫曼编码对文本值进行编码的，所有头部信息都被放在一张头部信息表里面，由客户端和服务器共同维护，
    - 推送
        - 当服务器知道客户端将要请求某个资源时，就会主动将该资源推送到客户端，甚至不需要客户端主动发出请求。
    - 请求优先级
        - 客户端可以在请求头里添加一个优先级信息来为请求数据的二进制流分配优先级。
        - 如果二进制流没有任何优先级信息，则服务器异步处理请求，即不分优先顺序来处理。
        - 如果二进制流有优先级，那么服务器会根据这个优先级信息来决定处理这个请求的轻重缓急程度。
    - 安全
        - 默认情况下，HTTP/2.0都基于`安全传输层协议（Transport Layer Security，TLS）`来进行安全的通信。

## 手写客户端与服务端

```java
/**
 * HTTP服务端
 * @author luguosong
 * @date 2022/7/7
 */
public class HTTPServer {
    public static void main(String args[]) {
        int port;
        ServerSocket serverSocket;

        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("port = 8080 (默认)");
            port = 8080; //默认端口为8080
        }
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务器正在监听端口："
                    + serverSocket.getLocalPort());

            while (true) { //服务器在一个无限循环中不断接收来自客户的TCP连接请求
                try {
                    //等待客户的TCP连接请求
                    final Socket socket = serverSocket.accept();
                    System.out.println("建立了与客户的一个新的TCP连接，"
                            + "该客户的地址为："
                            + socket.getInetAddress() + ":" + socket.getPort());

                    service(socket);  //响应客户请求
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("客户端请求的资源不存在");
                }
            } //#while
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 响应客户的HTTP请求
     */
    public static void service(Socket socket) throws Exception {

        /*读取HTTP请求信息*/
        InputStream socketIn = socket.getInputStream(); //获得输入流
        Thread.sleep(500);  //睡眠500毫秒，等待HTTP请求
        int size = socketIn.available();
        byte[] buffer = new byte[size];
        socketIn.read(buffer);
        String request = new String(buffer);
        System.out.println(request); //打印HTTP请求数据

        /*解析HTTP请求*/
        //获得HTTP请求的第一行
        int endIndex = request.indexOf("\r\n");
        if (endIndex == -1)
            endIndex = request.length();
        String firstLineOfRequest =
                request.substring(0, endIndex);

        //解析HTTP请求的第一行
        String[] parts = firstLineOfRequest.split(" ");
        String uri = "";
        if (parts.length >= 2)
            uri = parts[1]; //获得HTTP请求中的uri

        /*决定HTTP响应正文的类型，此处作了简化处理*/
        String contentType;
        if (uri.indexOf("html") != -1 || uri.indexOf("htm") != -1)
            contentType = "text/html";
        else if (uri.indexOf("jpg") != -1 || uri.indexOf("jpeg") != -1)
            contentType = "image/jpeg";
        else if (uri.indexOf("gif") != -1)
            contentType = "image/gif";
        else
            contentType = "application/octet-stream";  //字节流类型

        /*创建HTTP响应结果 */
        //HTTP响应的第一行
        String responseFirstLine = "HTTP/1.1 200 OK\r\n";
        //HTTP响应头
        String responseHeader = "Content-Type:" + contentType + "\r\n\r\n";
        //获得读取响应正文数据的输入流
        System.out.println(uri);
        InputStream in = HTTPServer
                .class
                .getResourceAsStream("/root" + uri);

        /*发送HTTP响应结果 */
        OutputStream socketOut = socket.getOutputStream(); //获得输出流
        //发送HTTP响应的第一行
        socketOut.write(responseFirstLine.getBytes());
        //发送HTTP响应的头
        socketOut.write(responseHeader.getBytes());
        //发送HTTP响应的正文
        int len = 0;
        buffer = new byte[128];
        while ((len = in.read(buffer)) != -1)
            socketOut.write(buffer, 0, len);

        Thread.sleep(1000);  //睡眠1秒，等待客户接收HTTP响应结果
        socket.close(); //关闭TCP连接
    }
}
```

```java
/**
 * Http客户端
 * @author luguosong
 * @date 2022/7/7
 */
public class HTTPClient {
    public static void main(String args[]){
        //确定HTTP请求的uri
        String uri="/index.htm";
        if(args.length !=0)uri=args[0];

        doGet("localhost",8080,uri); //按照GET请求方式访问HTTPServer
    }

    /** 按照GET请求方式访问HTTPServer */
    public static void doGet(String host,int port,String uri){
        Socket socket=null;

        try{
            socket=new Socket(host,port); //与HTTPServer建立FTP连接
        }catch(Exception e){e.printStackTrace();}

        try{
            /*创建HTTP请求 */
            StringBuffer sb=new StringBuffer("GET "+uri+" HTTP/1.1\r\n");
            sb.append("Accept: */*\r\n");
            sb.append("Accept-Language: zh-cn\r\n");
            sb.append("Accept-Encoding: gzip, deflate\r\n");
            sb.append("User-Agent: HTTPClient\r\n");
            sb.append("Host: localhost:8080\r\n");
            sb.append("Connection: Keep-Alive\r\n\r\n");

            /*发送HTTP请求*/
            OutputStream socketOut=socket.getOutputStream(); //获得输出流
            socketOut.write(sb.toString().getBytes());

            Thread.sleep(2000); //睡眠2秒，等待响应结果

            /*接收响应结果*/
            InputStream socketIn=socket.getInputStream(); //获得输入流
            int size=socketIn.available();
            byte[] buffer=new byte[size];
            socketIn.read(buffer);
            System.out.println(new String(buffer)); //打印响应结果

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                socket.close();
            }catch(Exception e){e.printStackTrace();}
        }
    } //#doGet()
}
```

# web发展历程

## 第一个阶段

发布`静态HTML`文档。指事先存放在Web服务器端的文件系统中的HTML文档。当用户在浏览器中输入指向特定HTML文档的URL时，Web服务器就会把该HTML文档的数据发送到浏览器端。这个阶段HTML文档只能包含`文本`及`图片`。

## 第二个阶段

发布静态`多媒体信息`。信息可以用`文本`、`图片`、`动画`、`声音`和`视频`等形式来表示。

这个阶段主要增强了浏览器的功能，要求浏览器能集成一些插件，利用这些插件来展示特定形式的信息。

## 第三个阶段

提供浏览器端与用户的动态`交互功能`。该功能的实现主要归功于JavaScript和VBScript等脚本语言的问世。此外，浏览器必须能够解析和运行用脚本语言编写的小程序。

## 第四个阶段

提供服务器端与用户的动态交互功能。

- 第一种方式：完全用编程语言编写的程序，例如CGI（Common Gateway Interface）程序和用Java编写的Servlet程序。
- 第二种方式：嵌入了程序代码的HTML文档，如PHP、ASP和JSP文档。JSP文档是指嵌入了Java程序代码的HTML文档。

## 第五个阶段

发布基于Web的应用程序，即Web应用。

## 第六个阶段

发布Web服务。

## 第七个阶段

先后推出Web 2.0以及Web 3.0。Web 2.0是全民共建的Web，用户共同为Web提供丰富的内容。在Web 3.0中，网络为用户提供更智能、更个性化的服务。
