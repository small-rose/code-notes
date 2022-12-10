package com.luguosong._240_date;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author luguosong
 * @date 2022/11/30
 */
public class DateDemo {

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

    @Test
    public void testCalendar(){
        //Calendar是抽象类，不能直接new
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(calendar.YEAR));
    }
}
