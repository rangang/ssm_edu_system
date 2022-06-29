package com.edu.service.impl;

import com.edu.dao.ResourceMapper;
import com.edu.domain.Resource;
import com.edu.domain.ResourceVO;
import com.edu.service.ResourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/29 9:12 上午
 * @Description:
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO) {

        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourceVO);

        PageInfo<Resource> resourcePageInfo = new PageInfo<>(allResource);

        return resourcePageInfo;
    }

    @Override
    public void saveResource(Resource resource) {

        // 补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        resourceMapper.saveResource(resource);

    }

    @Override
    public void updateResource(Resource resource) {

        // 补全信息
        resource.setUpdatedTime(new Date());

        resourceMapper.updateResource(resource);

    }

    @Override
    public void deleteResource(int id) {

        resourceMapper.deleteResource(id);

    }
}
