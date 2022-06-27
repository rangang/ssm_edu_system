package com.edu.dao;

import com.edu.domain.PromotionSpace;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 3:31 下午
 * @Description:
 */
public interface PromotionSpaceMapper {

    /**
     * 获取所有的广告位
     * @return
     */
    public List<PromotionSpace> findAllPromotionSpace();

}
