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
