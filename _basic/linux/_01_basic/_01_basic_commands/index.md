---
layout: default
title: 基本命令
nav_order: 10
parent: Linux基础
grand_parent: Linux
---

# 使用注意

- Linux严格区分大小写，Windows不区分大小写
- Linux中 `文件类型`不通过扩展名(后缀名)区分，通过 `权限位标识`区分文件类型
- Linux中所有内容以文件形式存储
- Linux中存储设备必须进行挂载之后才能使用

# 目录结构

- `/`:根目录
    - `/bin`:系统命令
    - `/sbin`:只有管理员能执行的系统命令
    - `/boot`:系统盘
    - `/dev`:设备文件目录(硬盘，u盘，光驱)，挂载后才能使用
    - `/etc`:配置文件目录
    - `/home`:普通用户家目录
    - `/root`:管理员家目录
    - `/lib`:32位系统库目录
    - `/lib64`:64位系统库目录
    - `/media`:挂载目录
    - `/mnt`: 挂载目录，建议挂载额外设备，如U盘移动硬盘
    - `/opt`:第三方软件
    - `/usr`:系统软件目录
        - `/usr/local`:第三方软件
    - `/proc`:虚拟文件目录，该目录数据是存在内存中的而不是硬盘上，用于保存CPU信息，设备驱动列表等信息
    - `/sys`:虚拟文件目录，保存与内核相关信息
    - `/run`:程序运行时产生的文件目录
    - `/srv`:服务数据目录
    - `/tmp`:临时目录，每次开机会清理
    - `/var`:经常变化的文件，如日志

# 基础命令

## 命令补全

- `Tab键盘`：命令，路径补全

## su-用户切换

- `su(选项)(参数)`:用于切换当前用户身份到其他用户身份，变更时须输入所要变更的用户帐号与密码。
    - 选项
        - `-c<指令>或--command=<指令>`：执行完指定的指令后，即恢复原来的身份；
        - `-f或——fast`：适用于csh与tsch，使shell不用去读取启动文件；
        - `-, -l, --login`：改变身份时，也同时变更工作目录，以及HOME,SHELL,USER,logname。此外，也会变更PATH变量；
        - `-m,-p或--preserve-environment`：变更身份时，不要变更环境变量；
        - `-s<shell>或--shell=<shell>`：指定要执行的shell；
        - `--help`：显示帮助；
        - `--version`；显示版本信息。
    - 参数
        - 用户：指定要切换身份的目标用户。

```shell
# 变更帐号为root并在执行ls指令后退出变回原使用者：
su -c ls root
# 变更帐号为root并传入-f选项给新执行的shell：
su root -f
# 变更帐号为test并改变工作目录至test的家目录：
su -test
```

## uname-系统信息

- `uname [OPTION]...`:打印系统信息。
    - `-a, --all              `    按顺序打印全部信息，如果 -p 和 -i 的信息是未知，那么省略。
    - `-s, --kernel-name      `    打印内核名称。
    - `-n, --nodename         `    打印网络节点主机名称。
    - `-r, --kernel-release   `    打印内核release。
    - `-v, --kernel-version   `    打印内核版本。
    - `-m, --machine          `    打印机器名称。
    - `-p, --processor        `    打印处理器名称。
    - `-i, --hardware-platform`    打印硬件平台名称。
    - `-o, --operating-system `    打印操作系统名称。
    - `--help                 `    显示帮助信息并退出。
    - `--version              `    显示版本信息并退出。

## ls-显示目录内容列表

- `ls [选项]... [文件]...`:**ls命令**就是list的缩写，用来显示目标列表，在Linux中是使用率较高的命令。ls命令的输出信息可以进行彩色加亮显示，以分区不同类型的文件。
    - `-l`                      使用较长格式列出信息
    - `-h, --human-readable`    与 -l 和 -s 一起，以易于阅读的格式输出文件大小（例如 1K 234M 2G等）
    - `-a, --all`               不隐藏任何以 . 开始的项目

## pwd-显示当前工作目录的绝对路径

- `pwd`:print working director

## cd-切换用户当前工作目录

```shell
cd    # 进入用户主目录；
cd /  # 进入根目录
cd ~  # 进入用户主目录；
cd ..  # 返回上级目录（若当前目录为“/“，则执行完后还在“/"；".."为上级目录的意思）；
cd ../..  # 返回上两级目录；
cd !$  # 把上个命令的参数作为cd参数使用。
```

## clear-清屏

```shell
clear
```

## whoami-打印用户名

- 该命令等价于 id -un。
- 注意区分 whoami 和 logname 这两个命令；比如我们以用户 root 打开的终端，然后切换到了用户 user2。此时， whoami返回的是当前用户
  user2, logname 返回的是 root，大家可以自行实践验证一下。
- 该命令是GNU coreutils包中的命令，相关的帮助信息请查看`man -s 1 whoami`，`info coreutils 'whoami invocation'`。

## reboot-重启

- `reboot`:reboot命令 用来重新启动正在运行的Linux操作系统。
    - `-d`：重新开机时不把数据写入记录文件/var/tmp/wtmp。本参数具有“-n”参数效果；
    - `-f`：强制重新开机，不调用shutdown指令的功能；
    - `-i`：在重开机之前，先关闭所有网络界面；
    - `-n`：重开机之前不检查是否有未结束的程序；
    - `-w`：仅做测试，并不真正将系统重新开机，只会把重开机的数据写入/var/log目录下的wtmp记录文件。

## shutdown-关机

- shutdown(选项)(参数)
    - 选项
        - `-c`：当执行“shutdown -h 11:50”指令时，只要按+键就可以中断关机的指令；
        - `-f`：重新启动时不执行fsck；
        - `-F`：重新启动时执行fsck；
        - `-h`：将系统关机；
        - `-k`：只是送出信息给所有用户，但不会实际关机；
        - `-n`：不调用init程序进行关机，而由shutdown自己进行；
        - `-r`：shutdown之后重新启动；
        - `-t<秒数>`：送出警告信息和删除信息之间要延迟多少秒。
    - 参数
        - [时间]：设置多久时间后执行shutdown指令；
        - [警告信息]：要传送给所有登入用户的信息。

