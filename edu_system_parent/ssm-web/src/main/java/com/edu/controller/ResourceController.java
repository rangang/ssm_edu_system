package com.edu.controller;

import com.edu.domain.Resource;
import com.edu.domain.ResourceVO;
import com.edu.domain.ResponseResult;
import com.edu.service.ResourceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/29 9:12 上午
 * @Description:
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 查询所有资源
     * @param resourceVO
     * @return
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO) {

        PageInfo<Resource> allResource = resourceService.findAllResource(resourceVO);

        return new ResponseResult(true,200,"响应成功",allResource);

    }

    /**
     * 添加&修改资源
     * @param resource
     * @return
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource) {

        if (resource.getId() == null) {
            resourceService.saveResource(resource);
        } else {
            resourceService.updateResource(resource);
        }

        return new ResponseResult(true,200,"响应成功","");
    }

    /**
     * 删除资源
     * @param id
     * @return
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(@RequestParam int id) {

        resourceService.deleteResource(id);

        return new ResponseResult(true,200,"响应成功","");
    }

}
