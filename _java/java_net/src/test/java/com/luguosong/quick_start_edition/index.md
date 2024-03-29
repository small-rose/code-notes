---
layout: default
title: 快速入门版
nav_order: 1
parent: 网络编程
---

# 网络互联模型

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221215174416.png)

# TCP和UDP

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221215180948.png)

# 三次握手四次挥手

- 三次握手建立连接
    1. 启动`服务器`进行监听
    2. `客户端`发送连接请求给`服务器`:`SYN=1,序列号=x`
    3. `服务器`返回连接请求确认给`客户端`:`SYN=1,ACK=1,序列号=y,确认码=x+1`
    4. `客户端`向`服务器`发出确认:`ACK=1,序列号=x+1,确认码=y+1`
    5. 建立连接

- 四次挥手释放连接
    1. `客户端`向`服务端`发送连接释放请求：`FIN=1,ACK=1,序列号=u,确认码=v`
    2. `服务端`向`客户端`返回确认：`ACK=1,序列号=v,确认码=u+1`
    3. `服务端`向`客户端`发送连接释放请求：`FIN=1,ACK=1,序列号=w,确认码=u+1`
    4. `客户端`向`服务端`返回确认:`ACK=1,序列号=u+1,确认码=w+1`

{: .note-title}
> SYN
>
> 同步序列编号（Synchronize Sequence
>
Numbers）。是TCP/IP建立连接时使用的握手信号。在客户机和服务器之间建立正常的TCP网络连接时，客户机首先发出一个SYN消息，服务器使用SYN+ACK应答表示接收到了这个消息，最后客户机再以ACK消息响应。这样在客户机和服务器之间才能建立起可靠的TCP连接，数据才可以在客户机和服务器之间传递。

{: .note-title}
> ACK
>
> 确认消息也称为ACK消息，是在计算机网络中通信协议的一部分，是设备或是行程发出的消息，回复已收到资料。

# Socket



