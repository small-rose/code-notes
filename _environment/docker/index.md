---
layout: docs
title: docker
nav_order: 20
---

# 概述

## 版本

- `CE`:社区版
- `EE`:企业版

# 架构

## 镜像和容器

- 镜像
  - 将应用程序及所需要的依赖、函数库、环境、配置文件打包在一起
- 容器
  - 镜像中的应用程序运行后形成的进程就是容器。Docker会给容器做隔离，对外不可见
  - 容器会对镜像的的文件进行复制，不会对镜像中的文件产生污染

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111291653776.png)

## Docker Registry

- 服务端
  - Docker守护进程，负责处理Docker指令，管理镜像，容器等
- 服务端
  - 通过命令或RestAPI向Docker服务端发送指令。可以在本地或远程向服务器发送指令


# 基本操作

## 安装

```shell
apt-get install docker.io
```

## 启动

```shell
# 启动
systemctl start docker

# 查看启动状态
systemctl status docker
```

## 配置docker镜像源

[阿里云教程](https://cr.console.aliyun.com/cn-shanghai/instances/mirrors)

```shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://oagsu5t7.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

