package com.luguosong._04_structural._07_proxy_pattern;

/**
 * 抽象查询类，充当抽象主题角色
 * @author luguosong
 * @date 2022/5/27 17:10
 */
public interface Searcher {
    public String doSearch(String userId,String keyword);
}
