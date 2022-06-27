package com.edu.controller;

import com.edu.domain.PromotionAd;
import com.edu.domain.PromotionAdVO;
import com.edu.domain.ResponseResult;
import com.edu.service.PromotionAdService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.edu.utils.Constants.LOCAL_URL;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 4:24 下午
 * @Description:
 */
@RestController
@RequestMapping("/promotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService adService;

    /**
     * 分页获取所有的广告列表
     * @param adVO
     * @return
     */
    @RequestMapping("/findAllAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO adVO){

        PageInfo allAdByPage = adService.findAllAdByPage(adVO);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", allAdByPage);

        return result;

    }

    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/promotionAdUpload")
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
            File filePath = new File(uploadPath, newFileName);

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
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 添加&修改广告
     * @param promotionAd
     * @return
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {

        if (promotionAd.getId() == null) {
            adService.savePromotionAd(promotionAd);
        } else {
            adService.updatePromotionAd(promotionAd);
        }

        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;

    }

    /**
     * 根据ID查询广告信息
     * @param id
     * @return
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id) {

        PromotionAd promotionAd = adService.findPromotionAdById(id);

        return new ResponseResult(true,200,"响应成功",promotionAd);

    }

    /**
     * 修改广告状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam int id, @RequestParam int status) {

        adService.updatePromotionAdStatus(id,status);

        // 保存修改后的状态，并返回
        Map<String,Integer> map = new HashMap<>();
        map.put("status", status);

        return new ResponseResult(true,200,"响应成功",map);
    }

}
