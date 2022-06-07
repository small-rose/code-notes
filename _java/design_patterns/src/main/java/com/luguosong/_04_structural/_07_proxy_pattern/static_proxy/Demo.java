package com.luguosong._04_structural._07_proxy_pattern.static_proxy;

import com.luguosong._04_structural._07_proxy_pattern.Searcher;
import com.luguosong.util.XMLUtil;

/**
 * 测试类
 * @author luguosong
 * @date 2022/5/27 18:49
 */
public class Demo {
    public static void main(String[] args) {
        Searcher searcher;
        searcher=(Searcher) XMLUtil.getBean("_java/design_patterns/src/main/java/com/luguosong/_04_structural/_07_proxy_pattern/static_proxy/config.xml").get(0);
        String result = searcher.doSearch("杨过", "玉女心经");
    }
}
