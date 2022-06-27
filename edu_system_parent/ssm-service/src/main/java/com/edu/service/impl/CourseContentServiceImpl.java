package com.edu.service.impl;

import com.edu.dao.CourseContentMapper;
import com.edu.domain.Course;
import com.edu.domain.CourseLesson;
import com.edu.domain.CourseSection;
import com.edu.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 11:08 上午
 * @Description:
 */
@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper contentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        List<CourseSection> sectionList = contentMapper.findSectionAndLessonByCourseId(courseId);
        return sectionList;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        return contentMapper.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection section) {

        // 补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        section.setStatus(2);

        contentMapper.saveSection(section);

    }

    @Override
    public void updateSection(CourseSection section) {

        // 补全信息
        section.setUpdateTime(new Date());

        contentMapper.updateSection(section);

    }

    @Override
    public void updateSectionStatus(int id, int status) {

        //封装数据
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());

        contentMapper.updateSectionStatus(section);
    }

    @Override
    public void saveLesson(CourseLesson lesson) {

        // 补全信息
        Date date = new Date();
        lesson.setUpdateTime(date);
        lesson.setCreateTime(date);

        contentMapper.saveLesson(lesson);

    }

    @Override
    public void updateLesson(CourseLesson lesson) {

        // 补全信息
        lesson.setUpdateTime(new Date());
        contentMapper.updateLesson(lesson);

    }
}
