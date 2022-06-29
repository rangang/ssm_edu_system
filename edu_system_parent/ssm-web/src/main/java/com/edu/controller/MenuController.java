package com.edu.controller;

import com.edu.domain.Menu;
import com.edu.domain.ResponseResult;
import com.edu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 5:20 下午
 * @Description:
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {

        List<Menu> list = menuService.findAllMenu();
        return new ResponseResult(true,200,"响应成功",list);

    }

    /**
     * 回显菜单信息（包括父子菜单的全部信息）
     * @param id
     * @return
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam int id) {

        if (id == -1) {

            // 添加操作 回显不需要查询menu信息
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            // 封装数据
            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);

            return new ResponseResult(true,200,"响应成功",map);

        } else {

            // 修改操作 回显
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);

            return new ResponseResult(true,200,"响应成功",map);

        }

    }

    /**
     * 添加&修改菜单
     * @param menu
     * @return
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu) {

        if (menu.getId() == null) {
            menuService.saveMenu(menu);
        } else {
            menuService.updateMenu(menu);
        }

        return new ResponseResult(true,200,"响应成功","");
    }


}
