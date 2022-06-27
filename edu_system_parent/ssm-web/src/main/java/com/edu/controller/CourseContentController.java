package com.edu.controller;

import com.edu.dao.CourseContentMapper;
import com.edu.domain.Course;
import com.edu.domain.CourseLesson;
import com.edu.domain.CourseSection;
import com.edu.domain.ResponseResult;
import com.edu.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 11:14 上午
 * @Description:
 */
@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService contentService;

    /**
     * 根据课程ID查询课程下的章节与课时信息
     * @param courseId
     * @return
     */
    @RequestMapping("/findSectionAndLessonByCourseId")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam int courseId) {

        // 调用service
        List<CourseSection> sectionList = contentService.findSectionAndLessonByCourseId(courseId);

        // 封装数据并返回
        ResponseResult result = new ResponseResult(true,200,"响应成功",sectionList);
        return result;

    }

    /**
     * 回显章节对应的课程信息
     * @param courseId
     * @return
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam int courseId) {

        // 调用service
        Course course = contentService.findCourseByCourseId(courseId);

        // 封装数据并返回
        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
        return result;

    }

    /**
     * 保存&修改章节
     * @param section
     * @return
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {

        // 判断携带ID是修改操作否则是插入操作
        if (section.getId() == null) {
            contentService.saveSection(section);
        } else {
            contentService.updateSection(section);
        }
        return new ResponseResult(true,200,"响应成功",null);

    }

    /**
     * 修改章节状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id, @RequestParam int status) {

        contentService.updateSectionStatus(id,status);

        // 封装最新的状态信息
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"响应成功",map);

    }

    /**
     * 保存&修改课时
     * @param lesson
     * @return
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson) {

        if (lesson.getId() == null) {
            contentService.saveLesson(lesson);
        } else {
            contentService.updateLesson(lesson);
        }
        return new ResponseResult(true,200,"响应成功",null);

    }

}
