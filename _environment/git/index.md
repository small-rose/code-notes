---
layout: default
title: git
nav_order: 20
mermaid: true
---
# 概述

## Git概述

Gt是一个免费的、开源的`分布式版本控制系统`。

[官网](https://git-scm.com/)

## 版本控制概述

`版本控制`:是一种记录文件内容变化，以便将来查阅特定版本修订情况的系统。版本控制其实最重要的是可以`记录文件修改历史记录``，从而让用户能够查看历史版本，方便版本切换。

# 常用命令

## 设置用户签名

签名的作用是`区分不同操作者身份`。用户的签名信息在`每一个版本的提交信息`中能够看到，以此确认本次提交是谁做的。

```shell
#查看用户签名
git config user.name
git config user.email

#修改用户签名
git config user.name 用户名
git config user.email 邮箱

#查看全局用户签名
git config --global user.name
git config --global user.email

#修改用户签名
git config --global user.name 用户名
git config --global user.email 邮箱
```

## 初始化本地库

```shell
#初始化本地库
git init
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220902191828.png)



## 查看本地库状态

```shell
#查看本地库状态
git status
```

## 工作区文件提交至暂存区

```shell
#工作区文件提交至暂存区
git add 文件
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220904102714.png)

## 删除暂存区文件

```shell
#删除暂存区文件
git rm --cached 文件
```

## 暂存区文件提交至本地仓库

```shell
#暂存区文件提交至本地仓库
git commit -m "提交信息" 文件
```

## 查看本地库提交信息

```shell
#查看本地库提交信息
git reflog

#查看本地库详细提交信息
git log
```

## 版本穿梭

```shell
git reset --hard 版本号
```

# 分支

在版本控制过程中，同时推进多个任务，为每个任务，我们都可以创建每个任务的单独`分支`,把自己的工作从主线上分离出来。

## 创建分支

```shell
#shell
git branch 分支名
```

idea新建分支（方式一）：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220904160319.png)

idea新建分支（方式二）：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220904163332.png)

## 查看分支

```shell
#查看所有分支
git branch

#查看所有分支详情
git branch -v
```

## 切换分支

```shell
#切换分支
git checkout 分支名
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220904163456.png)

## 分支合并

```shell
#分支合并
git merge 要合并的分支名
```

遇到有冲突的分支，需要手动合并



# 团队协作机制

## 为远程仓库创建别名

```shell
#为远程仓库创建别名
git remote add 别名 远仓库地址
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220902145043.png)

## 推送代码到远程库

```shell
#推送代码到远程库
git push 别名 分支名
```

## 拉取远程库代码

```shell
#拉取远程库代码
git pull 别名 分支名
```

## 项目克隆

克隆项目，不需要任何身份认证

克隆会做三件事：

- 拉取代码
- 初始化本地库
- 创建别名

```shell
#项目克隆
git clone 远程仓库地址
```

# 跨团队协作

## fork仓库

- 搜索想fork的项目

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220902163607.png)

- 进入项目，点击fork将其叉入自己的远程库中

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220902174133.png)

## 发送Pull requests

- 点击`New pull request`按钮向仓库管理员发送更改请求

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220902174810.png)

# 使用SSH登录远程仓库

- 使用`ssh -keygen`生成ssh公钥认证所需的公钥和私钥文件。并放入c盘用户目录下的`.ssh`文件夹中

```shell
ssh-keygen -t rsa -C "备注"
```

| 参数   | 解释                                                  |
| ------ | ----------------------------------------------------- |
| -b     | 采用长度1024bit的密钥对,b=bits,最长4096，不过没啥必要 |
| -t rsa | 采用rsa加密方式,t=type                                |
| -f     | 生成文件名,f=output_keyfiles                          |
| -C     | 备注，C=comment                                       |

- 将公钥信息复制到github

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220902190549.png)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20220902190740.png)


