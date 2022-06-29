package com.edu.service;

import com.edu.domain.Resource;
import com.edu.domain.ResourceVO;
import com.github.pagehelper.PageInfo;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/29 9:11 上午
 * @Description:
 */
public interface ResourceService {

    /**
     * 查询所有资源
     * @param resourceVO
     * @return
     */
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO);

    /**
     * 添加资源
     * @param resource
     */
    public void saveResource(Resource resource);

    /**
     * 修改资源
     * @param resource
     */
    public void updateResource(Resource resource);

    /**
     * 删除资源
     * @param id
     */
    public void deleteResource(int id);

}
