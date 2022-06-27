package com.edu.controller;

import com.edu.domain.Course;
import com.edu.domain.CourseVO;
import com.edu.domain.ResponseResult;
import com.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.edu.utils.Constants.LOCAL_URL;

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

    /**
     * 图片上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) {

        try {
            // 1.判断文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }

            // 2.获取项目部署路径
            String realPath = request.getServletContext().getRealPath("/");
            String webappsPath = realPath.substring(0, realPath.indexOf("ssm_web"));

            // 3.获取原文件名
            String fileName = file.getOriginalFilename();

            // 4.新文件名
            String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

            // 5.上传文件
            String uploadPath = webappsPath + "upload";
            File filePath = new File(uploadPath,newFileName);

            // 如果目录不存在就创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录：" + filePath);
            }
            file.transferTo(filePath);

            // 6.将文件名和文件路径返回
            Map<String,String> map = new HashMap<>();
            map.put("fileName",newFileName);
            map.put("filePath",LOCAL_URL + "/upload/" + newFileName);
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 保存&修改课程信息
     * @param courseVO
     * @return
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) {

        if (courseVO.getId() == null) {
            // 保存课程信息
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        } else {
            // 修改课程信息
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result = new ResponseResult(true,200,"响应成功",null);
            return result;
        }

    }

    /**
     * 根据id获取课程信息
     * @param id
     * @return
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int id) {

        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseVO);
        return result;

    }

    /**
     * 修改课程状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id,@RequestParam int status) {

        // 执行修改操作
        courseService.updateCourseStatus(id,status);

        // 保存修改后的状态，并返回
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;

    }


}
