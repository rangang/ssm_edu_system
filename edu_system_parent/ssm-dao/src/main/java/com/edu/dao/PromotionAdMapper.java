package com.edu.dao;

import com.edu.domain.PromotionAd;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 4:23 下午
 * @Description:
 */
public interface PromotionAdMapper {

    /**
     * 分页获取所有的广告列表
     * @return
     */
    public List<PromotionAd> findAllAdByPage();

    /**
     * 添加广告
     * @param promotionAd
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告
     * @param promotionAd
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据ID查询广告信息
     * @param id
     * @return
     */
    public PromotionAd findPromotionAdById(int id);

    /**
     * 修改广告状态
     * @param promotionAd
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
