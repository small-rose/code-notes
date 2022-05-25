---
layout: default
title: docker
nav_order: 10
---

# 概述

docker是一个快速交付应用、运行应用的技术

# 镜像和容器

镜像(Image)：Docker将应用程序及其所需的依赖、函数库、环境、配置等文件打包在一起，称为镜像

容器(Container):镜像中的应用程序运行后形成的进程就是容器

# DockerHub

Docker镜像托管平台


# Docker安装

## centos安装docker

- 卸载之前旧版本docker

```shell
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-selinux \
                  docker-engine-selinux \
                  docker-engine \
                  docker-ce
```

- 安装yum工具

```shell
yum install -y yum-utils \
           device-mapper-persistent-data \
           lvm2 --skip-broken
```

- 安装docker

```shell
yum install -y docker
```
