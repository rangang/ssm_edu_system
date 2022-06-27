package com.edu.dao;

import com.edu.domain.Course;
import com.edu.domain.CourseVO;
import com.edu.domain.Teacher;
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

    /**
     * 保存课程信息
     * @param course
     * @return
     */
    public int saveCourse(Course course);

    /**
     * 保存讲师信息
     * @param teacher
     * @return
     */
    public int saveTeacher(Teacher teacher);

    /**
     * 根据id获取课程信息
     * @param id
     * @return
     */
    public CourseVO findCourseById(int id);

    /**
     * 修改课程信息
     * @param course
     */
    public void updateCourse(Course course);

    /**
     * 修改讲师信息
     * @param teacher
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     * @param course
     */
    public void updateCourseStatus(Course course);

}
