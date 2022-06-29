package com.edu.service;

import com.edu.domain.ResourceCategory;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/29 9:58 上午
 * @Description:
 */
public interface ResourceCategoryService {

    /**
     * 查询所有资源分类
     * @return
     */
    public List<ResourceCategory> findAllResourceCategory();

    /**
     * 添加资源分类
     * @param resourceCategory
     */
    public void saveResourceCategory(ResourceCategory resourceCategory);

    /**
     * 修改资源分类
     * @param resourceCategory
     */
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /**
     * 删除资源分类
     * @param id
     */
    public void deleteResourceCategory(Integer id);

}
