package com.edu.dao;

import com.edu.domain.Course;
import com.edu.domain.CourseLesson;
import com.edu.domain.CourseSection;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 10:29 上午
 * @Description:
 */
public interface CourseContentMapper {

    /**
     * 根据课程ID查询课程下的章节与课时信息
     * @param courseId
     * @return
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /**
     * 回显章节对应的课程信息
     * @param courseId
     * @return
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节
     * @param section
     */
    public void saveSection(CourseSection section);

    /**
     * 修改章节
     * @param section
     */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     * @param section
     */
    public void updateSectionStatus(CourseSection section);

    /**
     * 保存课时
     * @param lesson
     */
    public void saveLesson(CourseLesson lesson);

    /**
     * 修改课时
     * @param lesson
     */
    public void updateLesson(CourseLesson lesson);

}
