package cn.com.lgs.controller;

import cn.com.lgs.domain.EduTeacher;
import cn.com.lgs.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luguosong
 * @date 2021/10/21 16:27
 */

@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }
}
