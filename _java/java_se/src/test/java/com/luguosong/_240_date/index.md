---
layout: default
title: 时间和日期
nav_order: 220
parent: JavaSE
---

# Date类

```java
class Demo {
    @Test
    public void testDate() {
        //获取当前时间
        Date date1 = new Date();
        System.out.println("当前时间：" + date1);

        //分配一个Date对象
        // 并将其初始化以表示自称为“纪元”的标准基准时间
        // （即格林威治标准时间 1970 年 1 月 1 日 00:00:00）以来指定的毫秒数
        Date date2 = new Date(1000);
        System.out.println("林威治标准时间 + 100秒：" + date2);

        System.out.println("获取时间毫秒数：" + date1.getTime());
    }
}
```

{: .note-title}
> CST
>
> `中国标准时间`（China Standard Time，CST）,是中国大陆的标准时间，比世界协调时快八小时（即`UTC`+8），与港、澳、台及马来西亚、新加坡等地的标准时间相同。

# SimpleDateFormat类

用于日期格式化

```java
class Demo {
    @Test
    public void testSimpleDateFormat() throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        //Date转String
        String date = fmt.format(new Date());
        System.out.println(date);

        //String转Date
        Date date2 = fmt.parse("2020年12月10日 12:12:12");
        System.out.println(date2);
    }
}
```

# Calendar类

日期处理类，功能比`Date`类丰富

```java
class Demo {
    @Test
    public void testCalendar() {
        //Calendar是抽象类，不能直接new
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(calendar.YEAR));
    }
}
```
