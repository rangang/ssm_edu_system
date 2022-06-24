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

}