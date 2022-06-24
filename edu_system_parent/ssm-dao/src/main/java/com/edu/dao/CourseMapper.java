package com.edu.dao;

import com.edu.domain.Course;
import com.edu.domain.CourseVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/23 2:38 下午
 * @Description:
 */
public interface CourseMapper {

    /**
     * 多条件课程列表查询
     * @param courseVO
     * @return
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

}
