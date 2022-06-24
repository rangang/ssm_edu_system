package com.edu.service.impl;

import com.edu.dao.CourseMapper;
import com.edu.domain.Course;
import com.edu.domain.CourseVO;
import com.edu.domain.Teacher;
import com.edu.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/24 10:00 上午
 * @Description:
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        return courseMapper.findCourseByCondition(courseVO);
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {

        try {
            // 封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVO);

            // 补全信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            // 保存课程
            courseMapper.saveCourse(course);

            // 获取新插入数据的id
            int id = course.getId();

            // 封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVO);

            // 补全信息
            teacher.setCourseId(id);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);

            // 保存讲师信息
            courseMapper.saveTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public CourseVO findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {

        try {
            // 封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course,courseVO);

            // 补全信息
            Date date = new Date();
            course.setUpdateTime(date);

            // 更新课程
            courseMapper.updateCourse(course);

            // 封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher,courseVO);

            // 补全信息
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);

            // 更新讲师信息
            courseMapper.updateTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
