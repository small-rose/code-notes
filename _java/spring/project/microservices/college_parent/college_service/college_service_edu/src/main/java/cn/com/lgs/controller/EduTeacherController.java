package cn.com.lgs.controller;

import cn.com.lgs.domain.EduTeacher;
import cn.com.lgs.domain.vo.TeacherQuery;
import cn.com.lgs.service.EduTeacherService;
import cn.com.lgs.utils.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luguosong
 * @date 2021/10/21 16:27
 */

@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询所有
     *
     * @return 所有教师
     */
    @GetMapping("findAll")
    public CommonResult findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return CommonResult.success(list);
    }

    /**
     * 分页查询
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("{current}/{size}")
    public CommonResult pageTeacher(@PathVariable long current, @PathVariable long size) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        return CommonResult.success(eduTeacherService.page(eduTeacherPage));
    }

    /**
     * @param current
     * @param size
     * @param teacherQuery
     * @return
     */
    @PostMapping("pageTeacherCondition/{current}/{size}")
    public CommonResult pageTeacherCondition(@PathVariable long current, @PathVariable long size,
                                             @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, size);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        return CommonResult.success(eduTeacherService.page(pageTeacher, wrapper));
    }

    /**
     * 添加讲师
     *
     * @return
     */
    @PostMapping("addTeacher")
    public CommonResult addTeacher(@RequestBody EduTeacher eduTeacher) {
        return CommonResult.success(eduTeacherService.save(eduTeacher));
    }

    /**
     * 根据讲师id查询
     *
     * @param id
     * @return
     */
    @GetMapping("getTeacher/{id}")
    public CommonResult getTeacher(@PathVariable String id) {
        return CommonResult.success(eduTeacherService.getById(id));
    }

    /**
     * 修改讲师
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("updateTeacher")
    public CommonResult updateTeacher(@RequestBody EduTeacher eduTeacher) {
        return CommonResult.success(eduTeacherService.updateById(eduTeacher));
    }


    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public CommonResult removeTeacher(@PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        return CommonResult.success(b);
    }


}
