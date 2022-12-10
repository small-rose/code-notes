package com.luguosong._290_regular_expressions;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author luguosong
 * @date 2022/12/2
 */
public class RegularExpressionsDemo {


    /**
     * 判断字符是否为字母
     *
     * @param c
     * @return
     */
    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * 判断字符是否为数字
     *
     * @param c
     * @return
     */
    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }


    /**
     * 条件：6~18个字符，可以使用字母、数字、下划线，需要以字母开头
     *
     * @param email
     * @return
     */
    private boolean validate(String email) {
        if (email == null)
            return false;
        char[] chars = email.toCharArray();
        if (chars.length < 6 || chars.length > 18)
            return false;
        if (!isLetter(chars[0]))
            return false;

        for (char c : chars) {
            if (isLetter(c) || isNumber(c) || c == '_')
                continue;
            return false;
        }
        return true;
    }


    /**
     * 正则表达式测试
     */
    @Test
    public void testValidate() {
        String s = "luguosong_com";

        //不使用正则表达式进行判断
        System.out.println(validate(s));

        //使用正则表达式判断
        System.out.println(s.matches("[a-zA-Z][a-zA-Z0-9_]{5,17}"));

    }

    @Test
    public void testPatten() {

        //要求完全匹配方式一
        boolean b = "dbcacbd".matches("[abc]");

        //要求完全匹配方式二
        boolean matches = Pattern.matches("[abc]", "dbcacbd");

        Pattern pattern = Pattern.compile("[abc]");
        Matcher matcher = pattern.matcher("dbcacbd");

        //要求完全匹配方式三
        matcher.matches();

        //局部匹配
        if (matcher.find()) {
            System.out.println("位置：" + matcher.start() + "-" + matcher.end());
        }
    }
}
