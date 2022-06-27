package com.edu.service;

import com.edu.domain.Course;
import com.edu.domain.CourseVO;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/24 10:00 上午
 * @Description:
 */
public interface CourseService {

    /**
     * 多条件课程列表查询
     * @param courseVO
     * @return
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     * 保存课程信息
     * @param courseVO
     */
    public void saveCourseOrTeacher(CourseVO courseVO);

    /**
     * 根据id获取课程信息
     * @param id
     * @return
     */
    public CourseVO findCourseById(int id);

    /**
     * 修改课程信息
     * @param courseVO
     */
    public void updateCourseOrTeacher(CourseVO courseVO);

    /**
     * 修改课程状态
     * @param course
     */
    public void updateCourseStatus(int id, int status);

}
