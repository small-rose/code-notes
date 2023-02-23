---
layout: default
title: 数据库概述
nav_order: 10
parent: 数据库
---

# 常见数据库简介

{: .note-title}
> 关系型
>
> - `MySQL`是一种关系型数据库，它使用SQL（Structured Query
    Language）语言进行查询和管理数据。MySQL性能表现良好，因为它支持高并发和复杂的查询。它也是免费的开源软件，但它有一个商业版MySQL
    Enterprise，提供更多的功能和支持。MySQL是最流行的数据库之一，被广泛应用于Web应用程序和其他数据密集型应用程序中。
> - `PostgreSQL`
    是另一种关系型数据库，它也使用SQL语言进行查询和管理数据。它比MySQL更复杂，但它提供了更多的功能和更好的可扩展性。它是免费的开源软件，没有商业版。PostgreSQL在大型数据集上表现出色，并被广泛用于企业级应用程序和高流量的网站中。
> - `Oracle`是一种高度可扩展的关系型数据库，它专门针对大型企业应用程序。它的性能非常强大，但它也是商业软件，需要付费才能使用。Oracle提供了许多高级功能，如数据分区、备份和恢复等。
    Oracle是企业级应用程序中最流行的数据库之一，被广泛应用于金融、医疗和零售行业。
> - `Microsoft SQL Server`是一种关系型数据库，它是专门为Windows操作系统设计的。它的性能和可扩展性非常强大，但它也是商业软件，需要付费才能使用。
    Microsoft SQL Server在企业级应用程序中非常受欢迎，特别是在微软的生态系统中。
> - `IBM DB2`是一种高度可扩展的关系型数据库，专门针对企业级应用程序。它的性能非常强大，但它也是商业软件，需要付费才能使用。它提供了许多高级功能，如分布式处理、数据复制、自动调整和多种编程接口。IBM
    DB2在企业级应用程序中非常受欢迎，特别是在金融、保险和电信行业。
> - `SQLite`是一种轻量级的关系型数据库，它在嵌入式系统和移动应用程序中非常受欢迎。它的性能出色，支持多种编程语言和操作系统。它是免费的开源软件，没有商业版。SQLite被广泛应用于桌面应用程序、移动应用程序和浏览器中。
> - `MariaDB`
    是一种开源的关系型数据库，它是MySQL的一个分支。它具有MySQL的所有功能，同时添加了一些新的功能，如存储过程、视图、JSON数据类型等。它是免费的开源软件，没有商业版。MariaDB在Web应用程序中非常受欢迎，特别是在开源社区中。


{: .note-title}
> 非关系型
>
> - `MongoDB`是一种非关系型数据库，它是文档型数据库。它的性能非常出色，并且它具有出色的可扩展性。它是免费的开源软件，并且没有商业版。
    MongoDB被广泛用于大型Web应用程序和实时数据分析中。
> - `Redis`
    是一种内存键值数据库，它支持多种数据结构，如字符串、哈希表、列表、集合和有序集合。它的性能非常出色，因为它将所有数据存储在内存中，可以快速地读写数据。它是免费的开源软件，没有商业版。Redis被广泛应用于缓存、消息队列、实时分析和实时数据处理中。它还可以作为数据库、搜索引擎和分布式锁的替代方案。由于其高性能和灵活性，Redis在大型Web应用程序和实时数据处理中非常受欢迎。

# Mysql安装

压缩版[下载地址](https://downloads.mysql.com/archives/community/)

安装班[下载地址](https://downloads.mysql.com/archives/installer/)

## 压缩版安装步骤

- 将bin目录配置到环境变量
- 配置配置文件

配置文件参数说明：

- client：客户端连接MySQL的配置项
  - port：MySQL服务监听的端口号，默认是3306
  - default-character-set：默认使用的字符集
- mysql：命令行工具的配置项
  - default-character-set：默认使用的字符集
- mysqld：MySQL服务器的配置项
  - port：MySQL服务监听的端口号，默认是3306
  - character-set-server：MySQL服务器默认使用的字符集
  - collation-server：MySQL服务器默认使用的排序规则
  - default-storage-engine：MySQL默认使用的存储引擎
  - datadir:设置数据存储位置

```ini
[client]
port = 3306
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqld]
port = 3306
character-set-server = utf8mb4
collation-server = utf8mb4_general_ci
default-storage-engine = INNODB
```

- 初始化数据库

```shell
mysqld --initialize-insecure
```

- 注册mysql为计算机服务

```shell
mysqld -install
```
