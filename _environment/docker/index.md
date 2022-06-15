---
layout: default
title: docker
nav_order: 10
mermaid: true
---

# 概述

docker是一个快速交付应用、运行应用的技术

软件的运行流程：

```mermaid
flowchart TB
    A[应用软件] == 应用调用操作系统函数库 ==> B[操作系统]
    B == 操作系统调用Linux内核指令 ==> C[Linux内核]
    C == 内核指令调用计算机硬件 ==> 计算机硬件
```

Docker的执行流程：

```mermaid
flowchart TB
    B[Docker将应用软件和系统函数库打包] == Docker直接调用Linux内核指令 ==> C[Linux内核]
    C == 内核指令调用计算机硬件 ==> 计算机硬件
```

# Docker与虚拟机的区别

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220612112956.png)

# 镜像和容器

镜像(Image)：Docker将应用程序及其所需的依赖、函数库、环境、配置等文件打包在一起，称为镜像

容器(Container):镜像中的应用程序运行后形成的进程就是容器

# DockerHub

Docker镜像托管平台

# Docker架构

- 服务端(server):Docker守护进程，负责处理Docker指令，管理镜像、容器等
- 客户端(client):通过命令或RestAPIl向Docker服务端发送指令。可以在本地或远程向服务端发送指令。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220612113558.png)

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

- 更新本地镜像源

```shell
# 设置docker镜像源
yum-config-manager \
    --add-repo \
    https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    
sed -i 's/download.docker.com/mirrors.aliyun.com\/docker-ce/g' /etc/yum.repos.d/docker-ce.repo

yum makecache fast
```

- 安装docker社区版

```shell
yum install -y docker-ce
```

# 启动Docker

## 关闭防火墙或配置防火墙端口

```sh
# 关闭
systemctl stop firewalld
# 禁止开机启动防火墙
systemctl disable firewalld
```

## 启动Docker

```shell
systemctl start docker  # 启动docker服务

systemctl stop docker  # 停止docker服务

systemctl restart docker  # 重启docker服务
```

## 阿里云镜像加速

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

# 镜像命令

## 概述

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220612124300.png)

## 本地构建镜像

## 从服务器拉取镜像

```shell
# 拉取nginx镜像
docker pull 镜像名:版本
```

## 查看镜像

```shell
docker images
```

## 导出镜像

```shell
docker save -o 导出文件名 镜像名:版本
```

## 导入镜像

```shell
docker load -i 导入文件名
```

## 删除镜像

```shell
docker rmi 镜像名:版本
```

# 容器命令

## 概述

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220612161438.png)

## 创建并运行容器

- `docker run`:创建并运行一个容器
- `--name`:给容器起一个名字，比如叫做mn
- `-v`: 目录挂载
  - 数据卷挂载：如果数据卷不存在，会自动创建数据卷
  - 宿主机目录挂载
  - 宿主机文件挂载
- `-p`：将宿主机端口与容器端口映射，冒号左侧是宿主机端口，右侧是容器端口
- `-d`:后台运行容器
- `镜像名称`:例如nginx

```shell
docker run --name 容器名称 -v 容器外目录:容器内路径 -p 外部端口:内部端口 -d 镜像名称
```

## 停止容器

```shell
docker stop 容器名称
```

## 启动容器

```shell
docker start 容器名称
```

## 删除容器

```shell
# 容器必须停止后才能删除
docker rm 容器名称
# 强制删除运行中的容器
docker rm -f 容器名称
```

## 查看所有容器状态

```shell
# 查看启动着的容器
docker ps
# 查看所有容器(包含停止的容器)
docker ps -a
```

## 查看容器日志

- `-f`:持续跟踪日志

```shell
docker logs 容器名 -f
```

## 进入容器

- 进入容器

```shell
docker exec -it 容器名 bash
```

- 因为容器中没有vim，进入指定指定目录，通过`sed`命令修改文件

## 退出容器

```shell
exit
```

# 数据卷

## 概述

`数据卷(volume)`:是一个虚拟目录，指向宿主机文件系统中的某个目录。

容器和容器中的数据是耦合的，存在以下问题：
- 不便于修改，修改容器内容需要进入容器内部
- 容器内的数据对外是不可见的，多个容器不可复用同一份数据
- 删除容器，其中的数据也会被彻底删除，这种操作很危险

## 创建数据卷

```shell
docker volume create 数据卷名称
```

## 查看所有数据卷

```shell
docker volume ls
```

## 查看数据卷详情

```shell
docker volume inspect 数据卷名称
```

## 删除所有未使用的卷

```shell
docker volume prune
```

## 删除指定卷

```shell
docker volume rm 数据卷名称
```

# 自定义镜像

`Dockerfile`就是一个文本文件，其中包含一个个的指令(Instruction),用指令来说明要执行什么操作来构建镜像。每一个指令都会形成一层Layer。

## 镜像结构

- 层(Layer)
  - 基础镜像层(BaseImage)
    - 系统函数库
    - 环境
    - 配置
    - 文件
  - 入口（Entrypoint）
    - 程序启动的脚本和参数

## dockerfile示例

```dockerfile
# 指定基础镜像
FROM ubuntu:16.04
# 配置环境变量，JDK的安装目录
ENV JAVA_DIR=/usr/local

# 拷贝jdk和java项目的包
COPY ./jdk8.tar.gz $JAVA_DIR/

# 安装JDK
RUN cd $JAVA_DIR \
 && tar -xf ./jdk8.tar.gz \
 && mv ./jdk1.8.0_144 ./java8

# 配置环境变量
ENV JAVA_HOME=$JAVA_DIR/java8
ENV PATH=$PATH:$JAVA_HOME/bin

COPY ./docker-demo.jar /tmp/app.jar

# 暴露端口
EXPOSE 8090
# 入口，java项目的启动命令
ENTRYPOINT java -jar /tmp/app.jar
```

上面的配置可以简写为下面部分：

```dockerfile
FROM java:8-alpine

COPY ./docker-demo.jar /tmp/app.jar

# 暴露端口
EXPOSE 8090
# 入口，java项目的启动命令
ENTRYPOINT java -jar /tmp/app.jar
```

输入命令生成docker镜像：

```shell
docker build -t 镜像名称:版本 镜像所在目录
```

# DockerCompose

Docker Compose可以基于Compose文件帮我们`快速的部署分布式应用`，而无需手动一个个创建和运行容器！

## 下载安装

```shell
curl -L https://github.com/docker/compose/releases/download/1.23.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
```

如果下载速度较慢，或者下载失败，可以将[docker-compose](docker-compose)文件直接上传到`/usr/local/bin/`目录

```shell
# 修改权限
chmod +x /usr/local/bin/docker-compose
```

```shell
# 补全命令
curl -L https://raw.githubusercontent.com/docker/compose/1.29.1/contrib/completion/bash/docker-compose > /etc/bash_completion.d/docker-compose
```

如果这里出现错误，需要修改自己的hosts文件：

```sh
echo "199.232.68.133 raw.githubusercontent.com" >> /etc/hosts
```

## DockerCompose文件示例

```yaml
version: "3.2"

services:
  nacos: # 服务名称
    image: nacos/nacos-server # 镜像名称
    environment: # 环境变量
      MODE: standalone
    ports: # 端口
      - "8848:8848"
  mysql: # 服务名称
    image: mysql:5.7.25 # 镜像名称
    environment:
      MYSQL_ROOT_PASSWORD: 123
    volumes: # 数据卷挂载
      - "$PWD/mysql/data:/var/lib/mysql"
      - "$PWD/mysql/conf:/etc/mysql/conf.d/"
  userservice:
    build: ./user-service
  orderservice:
    build: ./order-service
  gateway:
    build: ./gateway
    ports:
      - "10010:10010"
```

## 部署

创建镜像并构建容器

```shell
docker-compose build
```

```shell
docker-compose up -d
```

# 私有镜像仓库搭建

## 简化版docker仓库搭建

安装docker官方的镜像`registry`

```shell
docker run -d \
    --restart=always \
    --name registry	\
    -p 5000:5000 \
    -v registry-data:/var/lib/registry \
    registry
```

## 带图形化的版本

通过DockerCompose将`registry`和`joxit/docker-registry-ui`组合部署

```yaml
version: '3.0'
services:
  registry:
    image: registry
    volumes:
      - ./registry-data:/var/lib/registry
  ui:
    image: joxit/docker-registry-ui:static
    ports:
      - 8080:80
    environment:
      - REGISTRY_TITLE=传智教育私有仓库
      - REGISTRY_URL=http://registry:5000
    depends_on:
      - registry
```

## 配置Docker信任地址

需要镜像发布到私有的镜像仓库，本地需要配置仓库信任地址

```shell
# 打开要修改的文件
vi /etc/docker/daemon.json
# 添加内容：
"insecure-registries":["http://192.168.150.101:8080"]
# 重加载
systemctl daemon-reload
# 重启docker
systemctl restart docker
```

## 推送镜像到私有仓库

- 重命名镜像，因为此时本地docker配置了多个远程仓库，如果不重命名仓库可能发送冲突

```shell
docker tag 原镜像名:原版本 自建仓库ip/新镜像名:新版本
```

- 推送

```shell
docker push 自建仓库ip/新镜像名:新版本
```
