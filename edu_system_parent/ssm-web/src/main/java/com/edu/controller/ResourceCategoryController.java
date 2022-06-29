package com.edu.controller;

import com.edu.domain.ResourceCategory;
import com.edu.domain.ResponseResult;
import com.edu.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/29 9:59 上午
 * @Description:
 */
@RestController
@RequestMapping("resourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 查询所有资源分类
     * @return
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {

        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();

        return new ResponseResult(true,200,"响应成功",list);

    }

    /**
     * 添加&修改资源分类
     * @param resourceCategory
     * @return
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory) {

        if (resourceCategory.getId() == null) {
            resourceCategoryService.saveResourceCategory(resourceCategory);
        } else {
            resourceCategoryService.updateResourceCategory(resourceCategory);
        }

        return new ResponseResult(true,200,"响应成功","");
    }

    /**
     * 删除资源分类
     * @param id
     * @return
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(@RequestParam int id) {

        resourceCategoryService.deleteResourceCategory(id);

        return new ResponseResult(true,200,"响应成功","");
    }

}
