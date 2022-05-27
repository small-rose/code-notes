package com.luguosong._04_structural._07_proxy_pattern;

/**
 * @author luguosong
 * @date 2022/5/27 17:12
 */
public class RealSearcher implements Searcher{
    @Override
    public String doSearch(String userId, String keyword) {
        System.out.println("用户'"+userId+"'使用关键词'"+keyword+"'查询商务信息！");
        return "返回具体内容";
    }
}
