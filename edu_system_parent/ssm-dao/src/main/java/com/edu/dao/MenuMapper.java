package com.edu.dao;

import com.edu.domain.Menu;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 3:51 下午
 * @Description:
 */
public interface MenuMapper {

    /**
     * 查询全部的父子菜单信息
     * @param pid
     * @return
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单列表
     * @return
     */
    public List<Menu> findAllMenu();

    /**
     * 根据ID查询菜单信息
     * @param id
     * @return
     */
    public Menu findMenuById(int id);


}
