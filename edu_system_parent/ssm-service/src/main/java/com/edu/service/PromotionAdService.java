package com.edu.service;

import com.edu.domain.PromotionAd;
import com.edu.domain.PromotionAdVO;
import com.github.pagehelper.PageInfo;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 4:23 下午
 * @Description:
 */
public interface PromotionAdService {

    /**
     * 分页获取所有的广告列表
     * @param adVO
     * @return
     */
    public PageInfo findAllAdByPage(PromotionAdVO adVO);

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
     * @param id
     * @param status
     */
    public void updatePromotionAdStatus(int id, int status);

}
