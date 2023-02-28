---
layout: default
title: 数据库概述
nav_order: 10
parent: 数据库
---

# 数据库相关概念

## 数据库

`数据库（database）`：保存有组织的数据的容器（通常是一个文件或一组文件）。

数据库是一个以某种有组织的方式存储的数据集合。

## 表

`表（table）`：某种特定类型数据的结构化清单。

## 列和数据类型

`列（column）`：表中的一个字段。所有表都是由一个或多个列组成的。

`数据类型（datatype）`：所容许的数据的类型。每个表列都有相应的数据类型，它限制（或容许）该列中存储的数据。

## 行

`行（row）`：表中的一个记录。

## 主键

`主键（primarykey）`：一列（或一组列），其值能够唯一区分表中每个行。

# 数据库管理系统

`数据库管理系统`（英语：database management system，缩写：DBMS） 是一种针对对象数据库，为管理数据库而设计的大型电脑软件管理系统。具有代表性的数据管理系统有：Oracle、Microsoft SQL Server、Access、MySQL及PostgreSQL等。通常数据库管理师会使用数据库管理系统来创建数据库系统。

分类：
- 基于共享文件系统：Microsoft Access、FileMaker
- 基于客户机—服务器：MySQL、Oracle、Microsoft SQL Server

# SQL(Structured Query Language)

`SQL`是一种专门用来与数据库通信的语言。

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

# MySQL

数据的所有存储、检索、管理和处理实际上是由数据库软件——`DBMS（Database Management Systems,数据库管理系统）`完成的。`MySQL`是一种`DBMS`，即它是一种数据库软件。

各个版本mysql主要更改：
- `4`：InnoDB引擎，增加事务处理、并、改进全文本搜索等的支持。
- `4.1`：对函数库、子查询、集成帮助等的重要增加。
- `5`：存储过程、触发器、游标、视图等。

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

- 初始化用户名密码

```shell
mysqladmin -u root password 1234
```



# 使用Mysql

## 连接Mysql

- -h 或 --host：指定 MySQL 服务器的主机名或 IP 地址。
- -u 或 --user：指定连接到 MySQL 服务器的用户名。
- -p 或 --password：指定连接到 MySQL 服务器的密码。请注意，不要在命令行中明文指定密码，而应该使用 -p 参数，然后在提示符下输入密码。
- -P 或 --port：指定 MySQL 服务器的端口号。默认情况下，MySQL 服务器使用端口 3306。
- -D 或 --database：指定连接到的默认数据库。
- --ssl-mode：指定使用 SSL 加密连接。可用值包括 DISABLED（不使用 SSL）、REQUIRED（必须使用 SSL）、PREFERRED（优先使用 SSL）和 VERIFY_CA（需要验证服务器证书）等。
- -v 或 --verbose：显示更详细的连接信息。
- -h 或 --help：显示帮助信息。

```shell
#完整参数
mysql -h 127.0.0.1 -P 3306 -u root -p

#本地连接可以省略-h和-P
mysql -u root -p
```

## 选择数据库

```shell
mysql> USE database_learning;
Database changed
```



