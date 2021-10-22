package cn.com.lgs.controller;

import cn.com.lgs.domain.EduTeacher;
import cn.com.lgs.service.EduTeacherService;
import cn.com.lgs.utils.CommonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
