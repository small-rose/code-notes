---
layout: docs
title: linux
nav_order: 5
mermaid: true
---

# 参考书推荐

[操作系统设计与实现](https://book.douban.com/subject/2044818/) 当年Linus就是参考这本书附录中12000行Minix源代码写出了Linux内核

# 概述

本文基本使用kali linux作为系统环境

## 发展史

类Unix系统版本很多，初学者很多搞不清各个版本血统。画张图整理一下主要分支

点击图片查看大图

![](https://edrawcloudpubliccn.oss-cn-shenzhen.aliyuncs.com/viewer/self/1059758/share/2021-11-2/1635844893/main.svg)

Linux发行版时间线：
<hb></hb>
![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111030900997.svg)
<he></he>

## Unix特征

- `多任务`：同时运行不止一个程序
- `多用户`：可以同时支持不止一个用户

## 内核

作用是控制计算机，是操作系统的核心。`内核`总是一直运行直到关闭计算机。

- 内核的任务
  - 内存管理
  - 进程管理
  - 进程间通讯
  - 输入/输出
  - 文件管理
  - 安全和访问控制
  - 网络访问

# Linux与Windows区别

|                    | Linux              | Windows          |
| ------------------ | ------------------ | ---------------- |
| 是否区分文件大小写 | 区分               | 不区分           |
| 文件类型           | 通过权限位标识区分 | 通过文件后缀区分 |
| 元素               | 一切皆文件         | 文件+进程+等等   |
| 硬盘使用           | 分区+挂载          | 分区+格式化      |
| 分区目录           | 都在 `/根目录`下 | C盘 D盘          |

# 根目录文件夹说明

| 目录        | 说明                                                                                     |
| ----------- | ---------------------------------------------------------------------------------------- |
| /bin        | 存放系统命令                                                                             |
| /sbin       | 超级管理员root才能执行的系统命令                                                         |
| /boot       | 系统目录，相当于Windows的C盘                                                             |
| /dev        | 设备文件目录，`当中的存储设备无法直接使用，需要先挂载再使用`                           |
| /etc        | 系统的配置文件目录                                                                       |
| /home       | 普通用户的用户目录                                                                       |
| /root       | 超级管理员的用户目录                                                                     |
| /lib        | 系统库目录                                                                               |
| /64lib      | 64位系统库目录                                                                           |
| /media      | 早期挂载目录，挂载光盘，软盘                                                             |
| /mnt        | 挂载额外设备，如U盘，移动硬盘，其它操作系统分区                                          |
| /opt        | 第三方软件保存位置，但一般习惯安装到/usr/local目录下                                     |
| /usr        | 程序目录                                                                                 |
| /proc       | 虚拟文件系统，该目录数据保存在内存中，而不是硬盘上。保存进程，外部设备状态和网络状态等。 |
| /sys        | 虚拟文件目录，该目录数据保存在内存中，而不是硬盘上。保存与内核相关的信息                 |
| /run        | 程序在运行时产生的目录                                                                   |
| /srv        | 主要用来存储本机或本服务器提供的服务或数据。                                             |
| /tmp        | 临时文件目录，每次开机会清理                                                             |
| /var        | 经常变化的文件目录，数据文件，日志文件等                                                 |
| /lost+found | 当发生错误时， 将一些遗失的片段放置到这个目录下。                                        |

# 基本命令

- Tab键盘，双击Tab键：命令补全，不全文件路径
- --help选项可以查看命令帮助文档

## 切换用户

`-` 代表切换用户时切换根目录

```shell
# su - 用户名称
su - root
# 或
su - luguosong
```

## 查看系统信息

```shell
# uname [选项]

# 查看全部系统信息
uname -a
uname --all
```

## 列出给定文件信息

```shell
# ls [选项]... [文件]...

# 查看所有文件包括隐藏文件
ls -a
ls -all

# 查看文件详细信息
ls -l
ll

# 易于阅读的格式输出文件大小
ls -lh
ls -sh
```

## 打印当前位置

```shell
# pwd
# 全称是：print working directory

pwd
```

## 切换目录

```shell
# cd [目录]

cd ~
```

## 清屏

```shell
# 清屏
clear
```

## 重启

```shell
# 重启
reboot
````

## 关机

```shell
# 立刻关机
shutdown -h 0
shutdown -h now
halt -p

# 延迟关机
shutdown -h 分钟数

# 取消延迟关机
shutdown -c
```

## 查看命令类型

```shell
# 命令类型
type 命令
```

## 查看历史输入命令

```shell
# 历史输入命令
history
```

## 更改主机名称

```shell
# 方法一：更改 /etc/hostname

# hostnamectl set-hostname 主机名称
hostnamectl set-hostname kali
```

# 文件操作命令

Linux中文件区分大小写

## 目录创建

```shell
# mkdir [选项]... 目录...
mkdir mydir

# -p选项 递归创建目录
mkdir -p mydir/sub
```

## 目录删除

```shell
# 删除空目录
# rmdir [选项]... 目录...
rmdir dir

# -p 选项 递归删除空目录
rmdir -p dir/sub_dir

# 递归删除文件夹中的文件和文件夹
rm -r dir
```

## 文件创建

```shell
# touch [选项]... 文件...
touche hello

# 根据序号创建多个文件
touch hello{1..5}
```

## 文件删除

```shell
# rm [选项]... [文件]...
rm hello
```

## 复制并粘贴

```shell
# cp [选项]... 源文件... 目录
cp hello.txt dir

# 复制并重命名
cp hello.txt hello2.txt

# -r选项 复制文件夹及内部内容
# 酱dir目录下的所有文件复制到/tmp目录下
cp -r dir /tmp
```

## 剪切并粘贴

```shell
# mv [选项]... 源文件... 目录
mv dir /tmp
```

## 重命名

```shell
# 重命名文件或文件夹

# 重命名同样是mv命令
mv readme readme.txt
```

## 打包并压缩

Linux中一批文件无法直接压缩，需要先打包成一个文件

```shell
# tar [选项...] [FILE]...

# 打包文件
# 将a.txt,b.txt,c.txt打包成abc.tar
# -c,--create 表示打包
# -v,--verbose 表示详细打印出处理文件
# -f,--file 打包文件
tar -cvf abc.tar a.txt b.txt c.txt

# 打包，并使用gzip压缩工具将打包后的文件压缩成.gz格式
tar -zcf abc.tar.gz a.txt b.txt c.txt

# 打包，并使用bzip2压缩工具将打包后的文件压缩成.bz2格式
tar -jcf abc.tar.bz2 a.txt b.txt c.txt

# 打包，并使用xz压缩工具将打包后的文件压缩成.xz格式
tar -Jcf abc.tar.xz a.txt b.txt c.txt

# zip压缩文件
zip a.zip a.txt

# zip递归压缩文件夹
zip -r abc.zip ./folder

# 查看打包后的文件
# -t,--list 表示查看打包文件内容
# -f,-- file 表示打包后的文件名
tar -tf abc.tar

# 向打包后的文件插入新文件
# -u,-update 表示更新
# -f,--file 归档文件
tar -uf abc.tar d.txt
```

## 解压并释放文件

```shell
# tar [选项...] [FILE]...

# 将打包好的文件释放出来
# -x,--extract,--get
# -f,--file 表示打包后的文件
tar -xf abc.tar

# 解压.tar.gz格式
tar -zxf dab.tar.gz
tar -zxf dab.tar.gz -C /tmp

# 解压.tar.bz2格式
tar -jxf abc.tar.bz2
tar -jxf abc.tar.bz2 -C /tmp

# 解压.tar.xz格式
tar -Jxf abc.tar.xz
tar -Jxf abc.tar.xz -C /tmp

# 解压.zip文件格式
unzip abc.zip
unzip abc.zip -d /tmp
```

# Vim

Vim(Vi improved)是Linux中的文件编辑器。

前身：vi（visual editor）

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211108233825.png)

## 一般模式

vim打开文件后的默认模式

## 编辑模式

`i:`

`I:`

`a:`

`A:`



## 命令行模式

输入`:`进入命令行模式，命令行模式一般是对`文件`进行操作，而不是`文件内容`。

### 文件保存与退出

命令行模式下

```vim
# 保存
:w

# 强制退出不保存
:q!

# 保存并退出
:wq

# 强制保存并退出
:wq!
```

## 可视化模式
 

# 用户管理

# 管道命令

# 基本设置

## 显示设置

### 显示管理器设置

X window ->显示管理器 -> 桌面环境

显示管理器是桌面环境的上层

- 选择显示管理器

```shell
# dpkg-reconfigure 显示管理器名称
dpkg-reconfigure gdm3
# 或者
dpkg-reconfigure lightdm

# 重启生效
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111031846515.png)

### 桌面环境安装

```shell
# 查看哪些桌面环境包，以kali为例
apt search kali-desktop

# 安装想要的包

# 配置默认桌面环境
update-alternatives --config x-session-manager
```
