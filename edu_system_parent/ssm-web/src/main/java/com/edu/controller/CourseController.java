package com.edu.controller;

import com.edu.domain.Course;
import com.edu.domain.CourseVO;
import com.edu.domain.ResponseResult;
import com.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/24 1:13 下午
 * @Description:
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 多条件课程列表查询
     * @param courseVO
     * @return
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {

        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);

        return result;
    }

}
