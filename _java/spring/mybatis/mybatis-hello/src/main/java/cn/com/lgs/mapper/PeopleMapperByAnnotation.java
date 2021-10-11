package cn.com.lgs.mapper;

import cn.com.lgs.pojo.People;
import org.apache.ibatis.annotations.Select;

/**
 * @author luguosong
 * @date 2021/10/11 14:40
 */
public interface PeopleMapperByAnnotation {

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    @Select("select * from people where id=#{id}")
    public People getPeopleByAnnotation(int id);
}
