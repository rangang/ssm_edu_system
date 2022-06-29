package com.edu.service.impl;

import com.edu.dao.ResourceCategoryMapper;
import com.edu.domain.ResourceCategory;
import com.edu.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/29 9:58 上午
 * @Description:
 */
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        // 补全信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        resourceCategoryMapper.saveResourceCategory(resourceCategory);

    }

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        // 补全信息
        resourceCategory.setUpdatedTime(new Date());

        resourceCategoryMapper.updateResourceCategory(resourceCategory);

    }

    @Override
    public void deleteResourceCategory(Integer id) {

        resourceCategoryMapper.deleteResourceCategory(id);

    }
}
