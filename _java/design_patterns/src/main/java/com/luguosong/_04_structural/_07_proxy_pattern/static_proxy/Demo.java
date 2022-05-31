package com.luguosong._04_structural._07_proxy_pattern.static_proxy;

import com.luguosong._04_structural._07_proxy_pattern.Searcher;

/**
 * 测试类
 * @author luguosong
 * @date 2022/5/27 18:49
 */
public class Demo {
    public static void main(String[] args) {
        Searcher searcher;
        searcher=(Searcher) XMLUtil.getBean();
        String result = searcher.doSearch("杨过", "玉女心经");
    }
}
