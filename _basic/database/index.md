---
layout: default
title: 数据库系统
nav_order: 4
---

# 参考资料

- 书籍
    - [MySQL必知必会](https://book.douban.com/subject/3354490/)
    - [数据库系统概念](https://book.douban.com/subject/10548379/)
    - [数据库系统实现](https://book.douban.com/subject/10548379/)
- 课程
    - [数据库系统（上）：模型与语言](https://www.xuetangx.com/course/HIT08091000101/10318348?channel=i.area.manual_search)
    - [数据库系统（中）：建模与设计](https://www.xuetangx.com/course/HIT08091000103/10322088?channel=i.area.manual_search)
    - [数据库系统（下）：管理与技术](https://www.xuetangx.com/course/HIT08091000102/10318351?channel=i.area.manual_search)


# 创建样例表

- [create.sql](create.sql):包含创建6个数据库表（包括所有主键和外键约束）的MySQL语句。
- [populate.sql](populate.sql):包含用来填充这些表的INSERT语句。

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

# MySQL

数据的所有存储、检索、管理和处理实际上是由数据库软件——`DBMS（Database Management Systems,数据库管理系统）`完成的。`MySQL`是一种`DBMS`，即它是一种数据库软件。

各个版本mysql主要更改：
- `4`：InnoDB引擎，增加事务处理、并、改进全文本搜索等的支持。
- `4.1`：对函数库、子查询、集成帮助等的重要增加。
- `5`：存储过程、触发器、游标、视图等。

# 使用Mysql

## 连接Mysql

连接mysql需要以下信息：
- 主机名
- 端口
- 用户名
- 用户口令

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

# 数据库相关信息查询

## 查看全部数据库

```shell
mysql> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| database_learning  |
| mysql              |
| performance_schema |
+--------------------+
4 rows in set (0.00 sec)
```

## 获取数据库内表的列表

```shell
mysql> SHOW TABLES;
+-----------------------------+
| Tables_in_database_learning |
+-----------------------------+
| customers                   |
| orderitems                  |
| orders                      |
| productnotes                |
| products                    |
| vendors                     |
+-----------------------------+
6 rows in set (0.00 sec)
```

## 显示表中的列的属性

```shell
mysql> SHOW COLUMNS FROM customers;
+--------------+-----------+------+-----+---------+----------------+
| Field        | Type      | Null | Key | Default | Extra          |
+--------------+-----------+------+-----+---------+----------------+
| cust_id      | int(11)   | NO   | PRI | NULL    | auto_increment |
| cust_name    | char(50)  | NO   |     | NULL    |                |
| cust_address | char(50)  | YES  |     | NULL    |                |
| cust_city    | char(50)  | YES  |     | NULL    |                |
| cust_state   | char(5)   | YES  |     | NULL    |                |
| cust_zip     | char(10)  | YES  |     | NULL    |                |
| cust_country | char(50)  | YES  |     | NULL    |                |
| cust_contact | char(50)  | YES  |     | NULL    |                |
| cust_email   | char(255) | YES  |     | NULL    |                |
+--------------+-----------+------+-----+---------+----------------+
9 rows in set (0.00 sec)
```

DESCRIBE customers可以作为SHOW COLUMNS FROM customers的一种快捷方式。

```shell
mysql> DESCRIBE customers;
+--------------+-----------+------+-----+---------+----------------+
| Field        | Type      | Null | Key | Default | Extra          |
+--------------+-----------+------+-----+---------+----------------+
| cust_id      | int(11)   | NO   | PRI | NULL    | auto_increment |
| cust_name    | char(50)  | NO   |     | NULL    |                |
| cust_address | char(50)  | YES  |     | NULL    |                |
| cust_city    | char(50)  | YES  |     | NULL    |                |
| cust_state   | char(5)   | YES  |     | NULL    |                |
| cust_zip     | char(10)  | YES  |     | NULL    |                |
| cust_country | char(50)  | YES  |     | NULL    |                |
| cust_contact | char(50)  | YES  |     | NULL    |                |
| cust_email   | char(255) | YES  |     | NULL    |                |
+--------------+-----------+------+-----+---------+----------------+
9 rows in set (0.01 sec)
```

## 显示广泛的服务器状态信息

```shell
mysql> SHOW STATUS;
```

## 显示数据库创建语句

```shell
mysql> SHOW CREATE DATABASE database_learning;
+-------------------+----------------------------------------------------------------------------+
| Database          | Create Database
    |
+-------------------+----------------------------------------------------------------------------+
| database_learning | CREATE DATABASE `database_learning` /*!40100 DEFAULT CHARACTER SET utf8 */ |
+-------------------+----------------------------------------------------------------------------+
1 row in set (0.00 sec)
```

## 显示建表语句

```shell
mysql> SHOW CREATE TABLE customers;
+-----------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Table     | Create Table                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
+-----------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| customers | CREATE TABLE `customers` (
              `cust_id` int(11) NOT NULL AUTO_INCREMENT,
              `cust_name` char(50) NOT NULL,
              `cust_address` char(50) DEFAULT NULL,
              `cust_city` char(50) DEFAULT NULL,
              `cust_state` char(5) DEFAULT NULL,
              `cust_zip` char(10) DEFAULT NULL,
              `cust_country` char(50) DEFAULT NULL,
              `cust_contact` char(50) DEFAULT NULL,
              `cust_email` char(255) DEFAULT NULL,
              PRIMARY KEY (`cust_id`)
              ) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8 |
+-----------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
1 row in set (0.04 sec)
```

## 显示授予用户的安全权限

```shell
mysql> SHOW GRANTS;
+----------------------------------------------------------------------------------------------------------------------------------------+
| Grants for root@localhost                                                                                                              |
+----------------------------------------------------------------------------------------------------------------------------------------+
| GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY PASSWORD '*84AAC12F54AB666ECFC2A83C676908C8BBC381B1' WITH GRANT OPTION |
| GRANT PROXY ON ''@'' TO 'root'@'localhost' WITH GRANT OPTION                                                                           |
+----------------------------------------------------------------------------------------------------------------------------------------+
2 rows in set (0.00 sec)
```

## 显示服务器错误信息

```shell
mysql> SHOW ERRORS;
+-------+------+--------------------------------------------------------------------------------------------------------------------------------------------------------+
| Level | Code | Message                                                                                                                                                |
+-------+------+--------------------------------------------------------------------------------------------------------------------------------------------------------+
| Error | 1064 | You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'root' at line 1 |
+-------+------+--------------------------------------------------------------------------------------------------------------------------------------------------------+
1 row in set (0.00 sec)
```

## 显示服务器警告信息

```shell
mysql> SHOW WARNINGS;
+-------+------+--------------------------------------------------------------------------------------------------------------------------------------------------------+
| Level | Code | Message                                                                                                                                                |
+-------+------+--------------------------------------------------------------------------------------------------------------------------------------------------------+
| Error | 1064 | You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'root' at line 1 |
+-------+------+--------------------------------------------------------------------------------------------------------------------------------------------------------+
1 row in set (0.00 sec)
```

## 显示SHOW语句的全部功能

```shell
mysql> HELP SHOW;
Name: 'SHOW'
Description:
SHOW has many forms that provide information about databases, tables,
columns, or status information about the server. This section describes
those following:

SHOW AUTHORS
SHOW {BINARY | MASTER} LOGS
SHOW BINLOG EVENTS [IN 'log_name'] [FROM pos] [LIMIT [offset,] row_count]
SHOW CHARACTER SET [like_or_where]
SHOW COLLATION [like_or_where]
SHOW [FULL] COLUMNS FROM tbl_name [FROM db_name] [like_or_where]
SHOW CONTRIBUTORS
SHOW CREATE DATABASE db_name
SHOW CREATE EVENT event_name
SHOW CREATE FUNCTION func_name
SHOW CREATE PROCEDURE proc_name
SHOW CREATE TABLE tbl_name
SHOW CREATE TRIGGER trigger_name
SHOW CREATE VIEW view_name
SHOW DATABASES [like_or_where]
SHOW ENGINE engine_name {STATUS | MUTEX}
SHOW [STORAGE] ENGINES
SHOW ERRORS [LIMIT [offset,] row_count]
SHOW EVENTS
SHOW FUNCTION CODE func_name
SHOW FUNCTION STATUS [like_or_where]
SHOW GRANTS FOR user
SHOW INDEX FROM tbl_name [FROM db_name]
SHOW MASTER STATUS
SHOW OPEN TABLES [FROM db_name] [like_or_where]
SHOW PLUGINS
SHOW PROCEDURE CODE proc_name
SHOW PROCEDURE STATUS [like_or_where]
SHOW PRIVILEGES
SHOW [FULL] PROCESSLIST
SHOW PROFILE [types] [FOR QUERY n] [OFFSET n] [LIMIT n]
SHOW PROFILES
SHOW SLAVE HOSTS
SHOW SLAVE STATUS
SHOW [GLOBAL | SESSION] STATUS [like_or_where]
SHOW TABLE STATUS [FROM db_name] [like_or_where]
SHOW [FULL] TABLES [FROM db_name] [like_or_where]
SHOW TRIGGERS [FROM db_name] [like_or_where]
SHOW [GLOBAL | SESSION] VARIABLES [like_or_where]
SHOW WARNINGS [LIMIT [offset,] row_count]

like_or_where:
    LIKE 'pattern'
  | WHERE expr

If the syntax for a given SHOW statement includes a LIKE 'pattern'
part, 'pattern' is a string that can contain the SQL "%" and "_"
wildcard characters. The pattern is useful for restricting statement
output to matching values.

Several SHOW statements also accept a WHERE clause that provides more
flexibility in specifying which rows to display. See
http://dev.mysql.com/doc/refman/5.5/en/extended-show.html.

URL: http://dev.mysql.com/doc/refman/5.5/en/show.html
```

# 查询数据

## 查询单个列

```shell
mysql> SELECT prod_name
    -> FROM products;
+----------------+
| prod_name      |
+----------------+
| .5 ton anvil   |
| 1 ton anvil    |
| 2 ton anvil    |
| Detonator      |
| Bird seed      |
| Carrots        |
| Fuses          |
| JetPack 1000   |
| JetPack 2000   |
| Oil can        |
| Safe           |
| Sling          |
| TNT (1 stick)  |
| TNT (5 sticks) |
+----------------+
14 rows in set (0.00 sec)
```

## 查询多列

```shell
mysql> SELECT prod_id,prod_name,prod_price
    -> FROM products;
+---------+----------------+------------+
| prod_id | prod_name      | prod_price |
+---------+----------------+------------+
| ANV01   | .5 ton anvil   |       5.99 |
| ANV02   | 1 ton anvil    |       9.99 |
| ANV03   | 2 ton anvil    |      14.99 |
| DTNTR   | Detonator      |      13.00 |
| FB      | Bird seed      |      10.00 |
| FC      | Carrots        |       2.50 |
| FU1     | Fuses          |       3.42 |
| JP1000  | JetPack 1000   |      35.00 |
| JP2000  | JetPack 2000   |      55.00 |
| OL1     | Oil can        |       8.99 |
| SAFE    | Safe           |      50.00 |
| SLING   | Sling          |       4.49 |
| TNT1    | TNT (1 stick)  |       2.50 |
| TNT2    | TNT (5 sticks) |      10.00 |
+---------+----------------+------------+
14 rows in set (0.00 sec)
```

## 查询所有列

```shell
mysql> SELECT *
    -> FROM products;
+---------+---------+----------------+------------+----------------------------------------------------------------+
| prod_id | vend_id | prod_name      | prod_price | prod_desc                                                      |
+---------+---------+----------------+------------+----------------------------------------------------------------+
| ANV01   |    1001 | .5 ton anvil   |       5.99 | .5 ton anvil, black, complete with handy hook                  |
| ANV02   |    1001 | 1 ton anvil    |       9.99 | 1 ton anvil, black, complete with handy hook and carrying case |
| ANV03   |    1001 | 2 ton anvil    |      14.99 | 2 ton anvil, black, complete with handy hook and carrying case |
| DTNTR   |    1003 | Detonator      |      13.00 | Detonator (plunger powered), fuses not included                |
| FB      |    1003 | Bird seed      |      10.00 | Large bag (suitable for road runners)                          |
| FC      |    1003 | Carrots        |       2.50 | Carrots (rabbit hunting season only)                           |
| FU1     |    1002 | Fuses          |       3.42 | 1 dozen, extra long                                            |
| JP1000  |    1005 | JetPack 1000   |      35.00 | JetPack 1000, intended for single use                          |
| JP2000  |    1005 | JetPack 2000   |      55.00 | JetPack 2000, multi-use                                        |
| OL1     |    1002 | Oil can        |       8.99 | Oil can, red                                                   |
| SAFE    |    1003 | Safe           |      50.00 | Safe with combination lock                                     |
| SLING   |    1003 | Sling          |       4.49 | Sling, one size fits all                                       |
| TNT1    |    1003 | TNT (1 stick)  |       2.50 | TNT, red, single stick                                         |
| TNT2    |    1003 | TNT (5 sticks) |      10.00 | TNT, red, pack of 10 sticks                                    |
+---------+---------+----------------+------------+----------------------------------------------------------------+
14 rows in set (0.00 sec)
```

>一般，除非你确实需要表中的每个列，否则最好别使用`*`通配符。虽然使用通配符可能会使你自己省事，不用明确列出所需列，但检索不需要的列通常会降低检索和应用程序的性能。

## 查询排除重复行

```shell
mysql> SELECT DISTINCT vend_id
    -> FROM products;
+---------+
| vend_id |
+---------+
|    1001 |
|    1002 |
|    1003 |
|    1005 |
+---------+
4 rows in set (0.01 sec)
```

```shell
mysql> SELECT DISTINCT vend_id,prod_id
    -> FROM products;
+---------+---------+
| vend_id | prod_id |
+---------+---------+
|    1001 | ANV01   |
|    1001 | ANV02   |
|    1001 | ANV03   |
|    1002 | FU1     |
|    1002 | OL1     |
|    1003 | DTNTR   |
|    1003 | FB      |
|    1003 | FC      |
|    1003 | SAFE    |
|    1003 | SLING   |
|    1003 | TNT1    |
|    1003 | TNT2    |
|    1005 | JP1000  |
|    1005 | JP2000  |
+---------+---------+
14 rows in set (0.00 sec)
```

> `不能部分使用DISTINCT` DISTINCT关键字应用于所有列而不仅是前置它的列。如果给出SELECT DISTINCT vend_id,prod_price，除非指定的两个列都相同，否则所有行都将被检索出来。

## LIMIT限制查询结果

一般用于分页查询

带一个值的LIMIT总是从第一行开始，给出的数为返回的行数。带两个值的LIMIT可以指定从行号为第一个值的位置开始。

`行0`:检索出来的第一行为行0而不是行1。因此，LIMIT 1, 1将检索出第二行而不是第一行。

查询不多于5行：

```shell
mysql> SELECT prod_name
    -> FROM products
    -> LIMIT 5;
+--------------+
| prod_name    |
+--------------+
| .5 ton anvil |
| 1 ton anvil  |
| 2 ton anvil  |
| Detonator    |
| Bird seed    |
+--------------+
5 rows in set (0.00 sec)
```

指定要检索的开始行和行数,比如查询回从行5开始的5行：

```shell
mysql> SELECT prod_name
    -> FROM products
    -> LIMIT 5,5;
+--------------+
| prod_name    |
+--------------+
| Carrots      |
| Fuses        |
| JetPack 1000 |
| JetPack 2000 |
| Oil can      |
+--------------+
5 rows in set (0.00 sec)
```

> MySQL 5支持LIMIT的另一种替代语法。LIMIT 4 OFFSET 3意为从行3开始取4行，就像LIMIT 3, 4一样。这种写法不容易混淆

## 使用完全限定的表名

多表查询为了防止列明重复，可以同时使用`表名+列名`进行查询

```shell
mysql> SELECT products.prod_name
    -> FROM products;
+----------------+
| prod_name      |
+----------------+
| .5 ton anvil   |
| 1 ton anvil    |
| 2 ton anvil    |
| Detonator      |
| Bird seed      |
| Carrots        |
| Fuses          |
| JetPack 1000   |
| JetPack 2000   |
| Oil can        |
| Safe           |
| Sling          |
| TNT (1 stick)  |
| TNT (5 sticks) |
+----------------+
14 rows in set (0.00 sec)
```

表名也可以是完全限定的:

```shell
mysql> SELECT products.prod_name
    -> FROM database_learning.products;
+----------------+
| prod_name      |
+----------------+
| .5 ton anvil   |
| 1 ton anvil    |
| 2 ton anvil    |
| Detonator      |
| Bird seed      |
| Carrots        |
| Fuses          |
| JetPack 1000   |
| JetPack 2000   |
| Oil can        |
| Safe           |
| Sling          |
| TNT (1 stick)  |
| TNT (5 sticks) |
+----------------+
14 rows in set (0.00 sec)
```

# 查询结果排序

如果不排序，数据一般将以它在底层表中出现的顺序显示。这可以是数据最初添加到表中的顺序。但是，如果数据后来进行过更新或删除，则此顺序将会受到MySQL重用回收存储空间的影响。因此，如果不明确控制的话，不能（也不应该）依赖该排序顺序。关系数据库设计理论认为，如果不明确规定排序顺序，则不应该假定检索出的数据的顺序有意义。

## 按单个列排序

```shell
mysql> SELECT prod_name
    -> FROM products
    -> ORDER BY prod_name;
+----------------+
| prod_name      |
+----------------+
| .5 ton anvil   |
| 1 ton anvil    |
| 2 ton anvil    |
| Bird seed      |
| Carrots        |
| Detonator      |
| Fuses          |
| JetPack 1000   |
| JetPack 2000   |
| Oil can        |
| Safe           |
| Sling          |
| TNT (1 stick)  |
| TNT (5 sticks) |
+----------------+
14 rows in set (0.01 sec)
```

## 按多个列排序

```shell
mysql> SELECT prod_id,prod_price,prod_name
    -> FROM products
    -> ORDER BY prod_price,prod_name;
+---------+------------+----------------+
| prod_id | prod_price | prod_name      |
+---------+------------+----------------+
| FC      |       2.50 | Carrots        |
| TNT1    |       2.50 | TNT (1 stick)  |
| FU1     |       3.42 | Fuses          |
| SLING   |       4.49 | Sling          |
| ANV01   |       5.99 | .5 ton anvil   |
| OL1     |       8.99 | Oil can        |
| ANV02   |       9.99 | 1 ton anvil    |
| FB      |      10.00 | Bird seed      |
| TNT2    |      10.00 | TNT (5 sticks) |
| DTNTR   |      13.00 | Detonator      |
| ANV03   |      14.99 | 2 ton anvil    |
| JP1000  |      35.00 | JetPack 1000   |
| SAFE    |      50.00 | Safe           |
| JP2000  |      55.00 | JetPack 2000   |
+---------+------------+----------------+
14 rows in set (0.01 sec)
```

重要的是理解在按多个列排序时，排序完全按所规定的顺序进行。换句话说，对于上述例子中的输出，仅在多个行具有相同的prod_price值时才对产品按prod_name进行排序。如果prod_price列中所有的值都是唯一的，则不会按prod_name排序。


## 指定排序方向


