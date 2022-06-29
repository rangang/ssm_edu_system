package com.edu.service.impl;

import com.edu.dao.MenuMapper;
import com.edu.domain.Menu;
import com.edu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 3:52 下午
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

   @Autowired
   private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        return menuMapper.findSubMenuListByPid(pid);
    }

    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    @Override
    public Menu findMenuById(int id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public void saveMenu(Menu menu) {

        // 补全信息
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");

        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {

        // 补全信息
        menu.setUpdatedTime(new Date());

        menuMapper.updateMenu(menu);
    }
}
