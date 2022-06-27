package com.edu.service.impl;

import com.edu.dao.PromotionAdMapper;
import com.edu.domain.PromotionAd;
import com.edu.domain.PromotionAdVO;
import com.edu.service.PromotionAdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/27 4:23 下午
 * @Description:
 */
@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper adMapper;

    @Override
    public PageInfo findAllAdByPage(PromotionAdVO adVO) {

        PageHelper.startPage(adVO.getCurrentPage(),adVO.getPageSize());
        List<PromotionAd> allAd = adMapper.findAllAdByPage();
        PageInfo<PromotionAd> adPageInfo = new PageInfo<>(allAd);

        return adPageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        // 补全信息
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);
        adMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        // 补全信息
        Date date = new Date();
        promotionAd.setUpdateTime(date);
        adMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        return adMapper.findPromotionAdById(id);
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {

        // 封装信息
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        adMapper.updatePromotionAdStatus(promotionAd);

    }
}
