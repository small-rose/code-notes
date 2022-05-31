package com.luguosong._04_structural._07_proxy_pattern.dynamic_proxy;

import com.luguosong._04_structural._07_proxy_pattern.AccessValidator;
import com.luguosong._04_structural._07_proxy_pattern.Logger;
import com.luguosong._04_structural._07_proxy_pattern.RealSearcher;
import com.luguosong._04_structural._07_proxy_pattern.Searcher;

import java.lang.reflect.Proxy;

/**
 * @author luguosong
 * @date 2022/5/31 16:34
 */
public class Demo {
    public static void main(String[] args) {
        RealSearcher realSearcher = new RealSearcher();
        AccessValidator accessValidator = new AccessValidator();
        Logger logger = new Logger();
        SearchHandler searchHandler = new SearchHandler(realSearcher, accessValidator, logger);

        Searcher searchProxy = (Searcher) Proxy.newProxyInstance(
                realSearcher.getClass().getClassLoader(),
                realSearcher.getClass().getInterfaces(),
                searchHandler);

        searchProxy.doSearch("杨过", "玉女心经");
    }
}
