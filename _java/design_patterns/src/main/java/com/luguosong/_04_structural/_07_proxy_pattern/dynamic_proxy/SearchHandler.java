package com.luguosong._04_structural._07_proxy_pattern.dynamic_proxy;

import com.luguosong._04_structural._07_proxy_pattern.AccessValidator;
import com.luguosong._04_structural._07_proxy_pattern.Logger;
import com.luguosong._04_structural._07_proxy_pattern.Searcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author luguosong
 * @date 2022/5/31 16:09
 */
public class SearchHandler implements InvocationHandler {

    private Searcher searcher;

    private AccessValidator accessValidator;

    private Logger logger;

    /**
     * 构造方法进行依赖注入
     *
     * @param searcher
     * @param accessValidator
     * @param logger
     */
    public SearchHandler(Searcher searcher, AccessValidator accessValidator, Logger logger) {
        this.searcher = searcher;
        this.accessValidator = accessValidator;
        this.logger = logger;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (accessValidator.validate((String) args[0])) {
            Object invoke = method.invoke(searcher, args);
            logger.log((String) args[0]);
            return invoke;
        } else {
            return null;
        }
    }
}
