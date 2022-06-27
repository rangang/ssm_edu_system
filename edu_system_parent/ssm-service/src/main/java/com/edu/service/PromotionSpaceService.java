package com.edu.service;

import com.edu.domain.PromotionSpace;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 3:36 下午
 * @Description:
 */
public interface PromotionSpaceService {

    /**
     * 获取所有的广告位
     * @return
     */
    public List<PromotionSpace> findAllPromotionSpace();

}
