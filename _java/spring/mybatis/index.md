---
layout: docs
title: MyBatis
nav_order: 1000
parent: Spring相关
---
# 概述

功能：访问数据库

**_特点：_**

- 不屏蔽SQL
- 提供强大灵活的映射机制
- 提供了使用Mapper的接口编程

案例版本：`3.5.7`

[中文文档](https://mybatis.org/mybatis-3/zh/index.html)

# MyBatis核心组件

- `SqlSessionFactoryBuilder`(构造器)：它会根据配置或者代码来生成SqlSessionFactory，采用分步构建的Builder模式。
- `SqlSessionFactory`(工厂接口)：依靠它来生成SqlSession，使用的是 `工厂模式`
- `SqlSession`(会话)
- `SQL Mapper`(映射器)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110091642870.png)

# MyBatis配置文件详解

`SqlSessionFactoryBuilder`可以通过MyBatis配置文件创建 `SqlSessionFactory`对象

**总览：**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--配置-->
<configuration>
    <!--属性-->
    <properties/>
    <!--设置-->
    <settings/>
    <!--属性命名-->
    <typeAliases/>
    <!--属性处理器-->
    <typeHandlers/>
    <!--对象工厂-->
    <objectFactory  type=""/>
    <!--插件-->
    <plugins/>
    <!--配置环境-->
    <environments default="">
        <!--环境变量-->
        <environment id="">
            <!--事务管理器-->
            <transactionManager type=""/>
            <!--数据源-->
            <dataSource type=""/>
        </environment>
    </environments>
    <!--数据库厂商标识-->
    <databaseIdProvider type=""/>
    <!--映射器-->
    <mappers/>
</configuration>
```

## properties

可以在XML或者properties中进行参数配置，方便参数修改

### property子元素

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--使用property配置数据库连接参数-->
    <properties>
        <property name="database.driver" value="com.mysql.jdbc.Driver"/>
        <property name="database.url" value="jdbc:mysql://localhost:3306/code_notes"/>
        <property name="database.username" value="root"/>
        <property name="database.password" value="12345678"/>
    </properties>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="${database.driver}"/>
                <property name="url" value="${database.url}"/>
                <property name="username" value="${database.username}"/>
                <property name="password" value="${database.password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mapper/PeopleMapper.xml"/>
    </mappers>
</configuration>
```

### properties

- 编写mybatis-properties2.properties配置文件

```properties
database.driver=com.mysql.jdbc.Driver
database.url=jdbc:mysql://localhost:3306/code_notes
database.username=root
database.password=12345678
```

- 引入配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--引入properties配置文件-->
    <properties resource="mybatis-properties2.properties"/>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="${database.driver}"/>
                <property name="url" value="${database.url}"/>
                <property name="username" value="${database.username}"/>
                <property name="password" value="${database.password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mapper/PeopleMapper.xml"/>
    </mappers>
</configuration>
```

### 参数使用前解密

像是数据库密码需要保密，这个时候，配置文件中的密码是保密的。

对于保密的配置文件，需要先读取properties文件，对加密字段进行解密

## setting

[setting详情](https://mybatis.org/mybatis-3/zh/configuration.html#settings)

**一个完整的setting如下图所示：**

```xml
<settings>
  <!--全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。-->
  <setting name="cacheEnabled" value="true"/>
  <!--延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置 fetchType 属性来覆盖该项的开关状态。-->
  <setting name="lazyLoadingEnabled" value="true"/>
  <!--是否允许单个语句返回多结果集（需要数据库驱动支持）。-->
  <setting name="multipleResultSetsEnabled" value="true"/>
  <!--使用列标签代替列名。实际表现依赖于数据库驱动，具体可参考数据库驱动的相关文档，或通过对比测试来观察。-->
  <setting name="useColumnLabel" value="true"/>
  <!--允许 JDBC 支持自动生成主键，需要数据库驱动支持。如果设置为 true，将强制使用自动生成主键。尽管一些数据库驱动不支持此特性，但仍可正常工作（如 Derby）。-->
  <setting name="useGeneratedKeys" value="false"/>
  <!--指定 MyBatis 应如何自动映射列到字段或属性。 NONE 表示关闭自动映射；PARTIAL 只会自动映射没有定义嵌套结果映射的字段。 FULL 会自动映射任何复杂的结果集（无论是否嵌套）。-->
  <setting name="autoMappingBehavior" value="PARTIAL"/>
  <!--指定发现自动映射目标未知列（或未知属性类型）的行为。-->
  <!--NONE: 不做任何反应-->
  <!--WARNING: 输出警告日志（'org.apache.ibatis.session.AutoMappingUnknownColumnBehavior' 的日志等级必须设置为 WARN）-->
  <!--FAILING: 映射失败 (抛出 SqlSessionException)-->
  <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
  <!--	配置默认的执行器。SIMPLE 就是普通的执行器；REUSE 执行器会重用预处理语句（PreparedStatement）； BATCH 执行器不仅重用语句还会执行批量更新。-->
  <setting name="defaultExecutorType" value="SIMPLE"/>
  <!--设置超时时间，它决定数据库驱动等待数据库响应的秒数。-->
  <setting name="defaultStatementTimeout" value="25"/>
  <!--为驱动的结果集获取数量（fetchSize）设置一个建议值。此参数只可以在查询设置中被覆盖。-->
  <setting name="defaultFetchSize" value="100"/>
  <!--是否允许在嵌套语句中使用分页（RowBounds）。如果允许使用则设置为 false。-->
  <setting name="safeRowBoundsEnabled" value="false"/>
  <!--是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。-->
  <setting name="mapUnderscoreToCamelCase" value="false"/>
  <!--MyBatis 利用本地缓存机制（Local Cache）防止循环引用和加速重复的嵌套查询。 默认值为 SESSION，会缓存一个会话中执行的所有查询。 若设置值为 STATEMENT，本地缓存将仅用于执行语句，对相同 SqlSession 的不同查询将不会进行缓存。-->
  <setting name="localCacheScope" value="SESSION"/>
  <!--当没有为参数指定特定的 JDBC 类型时，空值的默认 JDBC 类型。 某些数据库驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 NULL、VARCHAR 或 OTHER。-->
  <setting name="jdbcTypeForNull" value="OTHER"/>
  <!--指定对象的哪些方法触发一次延迟加载。-->
  <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
</settings>
```

## typeAliases

类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，`意在降低冗余的全限定类名书写`。

### 自定义别名

```xml
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
  <typeAlias alias="Blog" type="domain.blog.Blog"/>
  <typeAlias alias="Comment" type="domain.blog.Comment"/>
  <typeAlias alias="Post" type="domain.blog.Post"/>
  <typeAlias alias="Section" type="domain.blog.Section"/>
  <typeAlias alias="Tag" type="domain.blog.Tag"/>
</typeAliases>
```

当这样配置时，`Blog` 可以用在任何使用 `domain.blog.Blog` 的地方。

也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean，比如：

```xml
<typeAliases>
  <package name="domain.blog"/>
</typeAliases>
```

这样默认别名就是 `首字母小写的类名`，当然也可以 `自定义别名`：

```java
@Alias("myauthor")
public class Author {
  
}
```

### 内建别名

下面是一些为常见的 Java 类型内建的类型别名。它们都是不区分大小写的，注意，为了应对原始类型的命名重复，采取了特殊的命名风格。

| 别名       | Java类型   | 是否支持数组 |
| ---------- | ---------- | ------------ |
| _byte      | byte       | 是           |
| _long      | long       | 是           |
| _short     | short      | 是           |
| _int       | int        | 是           |
| _integer   | int        | 是           |
| _double    | double     | 是           |
| _float     | float      | 是           |
| _boolean   | boolean    | 是           |
| string     | String     | 是           |
| byte       | Byte       | 是           |
| long       | Long       | 是           |
| short      | Short      | 是           |
| int        | Integer    | 是           |
| integer    | Integer    | 是           |
| double     | Double     | 是           |
| float      | Float      | 是           |
| boolean    | Boolean    | 是           |
| date       | Date       | 是           |
| decimal    | BigDecimal | 是           |
| bigdecimal | BigDecimal | 是           |
| object     | Object     | 是           |
| map        | Map        | 否           |
| hashmap    | HashMap    | 否           |
| list       | List       | 否           |
| arraylist  | ArrayList  | 否           |
| collection | Collection | 否           |
| iterator   | Iterator   | 否           |
| ResultSet  | ResultSet  | 否           |

## typeHandler

承担 `jdbcType`和 `javaType`之间的互相转换，一般不需要去配置 `typeHandler`、`jdbcType`和 `javaType`,
因为Mybatis会探测应该使用什么类型的typeHandler进行处理，但有些场景无法探测到，这时需要自定义 `typeHandler`去处理。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110121125374.png)

### 内置typeHandler

mybatis内置typeHandler：

| 类型处理器                 | Java 类型                     | JDBC 类型                                                               |
| -------------------------- | ----------------------------- | ----------------------------------------------------------------------- |
| BooleanTypeHandler         | java.lang.Boolean, boolean    | 数据库兼容的 BOOLEAN                                                    |
| ByteTypeHandler            | java.lang.Byte, byte          | 数据库兼容的 NUMERIC 或 BYTE                                            |
| ShortTypeHandler           | java.lang.Short, short        | 数据库兼容的 NUMERIC 或 SMALLINT                                        |
| IntegerTypeHandler         | java.lang.Integer, int        | 数据库兼容的 NUMERIC 或 INTEGER                                         |
| LongTypeHandler            | java.lang.Long, long          | 数据库兼容的 NUMERIC 或 BIGINT                                          |
| FloatTypeHandler           | java.lang.Float, float        | 数据库兼容的 NUMERIC 或 FLOAT                                           |
| DoubleTypeHandler          | java.lang.Double, double      | 数据库兼容的 NUMERIC 或 DOUBLE                                          |
| BigDecimalTypeHandler      | java.math.BigDecimal          | 数据库兼容的 NUMERIC 或 DECIMAL                                         |
| StringTypeHandler          | java.lang.String              | CHAR, VARCHAR                                                           |
| ClobReaderTypeHandler      | java.io.Reader                | -                                                                       |
| ClobTypeHandler            | java.lang.String              | CLOB, LONGVARCHAR                                                       |
| NStringTypeHandler         | java.lang.String              | NVARCHAR, NCHAR                                                         |
| NClobTypeHandler           | java.lang.String              | NCLOB                                                                   |
| BlobInputStreamTypeHandler | java.io.InputStream           | -                                                                       |
| ByteArrayTypeHandler       | byte[]                        | 数据库兼容的字节流类型                                                  |
| BlobTypeHandler            | byte[]                        | BLOB, LONGVARBINARY                                                     |
| DateTypeHandler            | java.util.Date                | TIMESTAMP                                                               |
| DateOnlyTypeHandler        | java.util.Date                | DATE                                                                    |
| TimeOnlyTypeHandler        | java.util.Date                | TIME                                                                    |
| SqlTimestampTypeHandler    | java.sql.Timestamp            | TIMESTAMP                                                               |
| SqlDateTypeHandler         | java.sql.Date                 | DATE                                                                    |
| SqlTimeTypeHandler         | java.sql.Time                 | TIME                                                                    |
| ObjectTypeHandler          | Any                           | OTHER 或未指定类型                                                      |
| EnumTypeHandler            | Enumeration Type              | VARCHAR 或任何兼容的字符串类型，用来存储枚举的名称（而不是索引序数值）  |
| EnumOrdinalTypeHandler     | Enumeration Type              | 任何兼容的 NUMERIC 或 DOUBLE 类型，用来存储枚举的序数值（而不是名称）。 |
| SqlxmlTypeHandler          | java.lang.String              | SQLXML                                                                  |
| InstantTypeHandler         | java.time.Instant             | TIMESTAMP                                                               |
| LocalDateTimeTypeHandler   | java.time.LocalDateTime       | TIMESTAMP                                                               |
| LocalDateTypeHandler       | java.time.LocalDate           | DATE                                                                    |
| LocalTimeTypeHandler       | java.time.LocalTime           | TIME                                                                    |
| OffsetDateTimeTypeHandler  | java.time.OffsetDateTime      | TIMESTAMP                                                               |
| OffsetTimeTypeHandler      | java.time.OffsetTime          | TIME                                                                    |
| ZonedDateTimeTypeHandler   | java.time.ZonedDateTime       | TIMESTAMP                                                               |
| YearTypeHandler            | java.time.Year                | INTEGER                                                                 |
| MonthTypeHandler           | java.time.Month               | INTEGER                                                                 |
| YearMonthTypeHandler       | java.time.YearMonth           | VARCHAR 或 LONGVARCHAR                                                  |
| JapaneseDateTypeHandler    | java.time.chrono.JapaneseDate | DATE                                                                    |



# SqlSessionFactoryBuilder

**作用：**用于创建 `SqlSessionFactory`

**生命周期：**只存在于创建 `SqlSessionFactory`的方法，不要让其长期存在

# SqlSessionFactory

每一个MyBatis应用都是以一个 `SqlSessionFactory`的实例为中心的。

`SqlSessionFactory`的唯一作用：生产 `MyBatis`的核心接口对象 `SqlSession`，可以认为是一个数据库连接池

**生命周期：**存在于整个MyBatis应用中，一旦创建，长期保存

**`SqlSessionFactory`是一个接口，它有两个实现类：**

- `DefaultSqlSessionFactor`
- `SqlSessionManager`:使用在多线程环境中，具体实现依靠 `DefaultSqlSessionFactor`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110091650091.png)

## 创建SqlSessionFactory

- 在 `resources`目录下创建 `mybatis-config.xml`文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/code_notes"/>
                <property name="username" value="root"/>
                <property name="password" value="12345678"/>
            </dataSource>
        </environment>
    </environments>
</configuration>
```

- 通过xml文件创建SqlSessionFactory对象

```java
class Demo {
    public static void main(String[] args) {
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println(sqlSessionFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

# SqlSession

`SqlSession`是MyBatis的核心接口。相当于Connection对象

**作用(`类似JDBC的Connection对象`)：**

- 获取Mapper接口
- 发送SQL给数据库
- 控制数据库事务

**生命周期：**存活在一个业务请求中，处理完请求，应该关闭这条连接，让它归还SqlSessionFactory

**SqlSession有两个实现类：**

- DefaultSqlSession:单线程使用
- SqlSessionManager：多线程环境下使用

## 创建SqlSession

创建SqlSession对象，并设置事务：

```java
class Demo {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  
  
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
  
  
            //some code ...
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }
}
```

- SqlSession只是一个门面接口，真正干活的是Executor。
- 因为SqlSession对象代表一个资源，使用后要及时关闭。不关闭的话数据库的连接资源就会很快被耗光。

# 映射器Mapper

映射器的主要作用是将SQL查询结果映射为一个POJO，或者将POJO的数据插入到数据库中。代表一个请求中的业务处理

**生命周期：**由 `SqlSession`创建，所以生命周期小于等于 `SqlSession`。在一个请求中，一旦处理完相关业务就应该废弃它。

定义一个POJO：

```java
package cn.com.lgs.pojo;

import lombok.Data;

/**
 * @author luguosong
 * @date 2021/10/9 17:16
 */
@Data
public class People {
    private int id;
    private String name;
    private int age;
}
```

## 创建映射器

- 定义一个映射接口

```java
package cn.com.lgs.mapper;

import cn.com.lgs.pojo.People;

/**
 * 定义一个映射器接口
 * @author luguosong
 * @date 2021/10/11 11:01
 */
public interface PeopleMapper {
    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public People getPeople(int id);
}
```

- xml实现映射器

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.com.lgs.mapper.PeopleMapper">
    <select id="getPeople" parameterType="int" resultType="cn.com.lgs.pojo.People">
        select * from people where id = #{id}
    </select>
</mapper>
```

## 配置映射器到Mybatis中

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/code_notes"/>
                <property name="username" value="root"/>
                <property name="password" value="12345678"/>
            </dataSource>
        </environment>
    </environments>

    <!--将映射器配置到mybatis-->
    <mappers>
        <!--将xml实现的映射器配置到mybatis-->
        <mapper resource="mapper/PeopleMapper.xml"/>
        <!--将注解实现的映射器配置到mybatis-->
        <mapper class="cn.com.lgs.mapper.PeopleMapperByAnnotation"/>
    </mappers>
</configuration>
```

## 发送SQL

```java
class Demo {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();


            //使用Mapper发送SQL
            PeopleMapper mapper1 = sqlSession.getMapper(PeopleMapper.class);
            People people1 = mapper1.getPeople(1);
            System.out.println(people1);

            PeopleMapperByAnnotation mapper2 = sqlSession.getMapper(PeopleMapperByAnnotation.class);
            People people2 = mapper2.getPeopleByAnnotation(2);
            System.out.println(people2);

            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }
}
```

# 其它方法创建组件

以上示例都采用的是主流的做法，获取SqlSessionFactory、实现映射器、将映射器配置到Mybatis、发送SQL都有第二种实现方法。一般都是用上面的主流方法，以下方法不常用。两种做法写在一起我觉得会很混乱，单独整理出来。

`学习的时候只掌握上面主流的方法即可，两种方法一起学容易晕掉`

## 使用代码构建SqlSessionFactory

`缺点：`代码冗长，修改配置需要重新编译才能继续

```java
class Demo {
    public static void main(String[] args) {
        //数据库链接池信息
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/code_notes");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("12345678");
        pooledDataSource.setDefaultAutoCommit(false);
        //采用MyBatis的JDBC事务方式
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", jdbcTransactionFactory, pooledDataSource);
        //创建Configuration对象
        Configuration configuration = new Configuration(environment);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        System.out.println(sqlSessionFactory);
    }
}
```

## 使用注解实现映射器

`缺点：`对于稍微复杂一点的语句，Java注解不仅力不从心，还会让你本就复杂的 SQL 语句更加混乱不堪。 因此，如果你需要做一些很复杂的操作，`最好用XML来映射语句`。

```java
package cn.com.lgs.mapper;

import cn.com.lgs.pojo.People;
import org.apache.ibatis.annotations.Select;

/**
 * @author luguosong
 * @date 2021/10/11 14:40
 */
public interface PeopleMapperByAnnotation {

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    @Select("select * from people where id=#{id}")
    public People getPeopleByAnnotation(int id);
}
```

## 代码方式配置映射器到Mybatis

```java
class Demo {
    public static void main(String[] args) {
        //数据库链接池信息
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/code_notes");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("12345678");
        pooledDataSource.setDefaultAutoCommit(false);
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", jdbcTransactionFactory, pooledDataSource);
        //创建Configuration对象
        Configuration configuration = new Configuration(environment);


        //配置注解映射器
        configuration.addMapper(PeopleMapperByAnnotation.class);


        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        // 获取SqlSession对象执行sql
        // ...
    }
}
```

## SqlSession发送SQL

```java
class Demo {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();


            //使用SqlSession发送SQL
            //xml映射器
            People people1 = (People) sqlSession.selectOne("getPeople", 1);
            System.out.println(people1);
            //注解映射器
            Object people2 = sqlSession.selectOne("getPeopleByAnnotation", 2);
            System.out.println(people2);


            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }
}
```

# 生命周期图示

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110111753679.png)

# MyBatis-Plus

是一个Mybatis的增强工具，在MyBatis的基础上只做增强不做改变，为简化开发提高效率而生
