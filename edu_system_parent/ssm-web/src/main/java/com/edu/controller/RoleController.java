package com.edu.controller;

import com.edu.domain.*;
import com.edu.service.MenuService;
import com.edu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 3:26 下午
 * @Description:
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 查询角色列表
     * @param role
     * @return
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {

        List<Role> allRole = roleService.findAllRole(role);

        ResponseResult result = new ResponseResult(true,200,"响应成功",allRole);

        return result;

    }

    /**
     * 查询全部的父子菜单信息
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {

        // -1表示查询所有菜单数据
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }

    /**
     * 根据角色ID查询菜单信息
     * @param roleId
     * @return
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {

        List<String> menuList = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"响应成功",menuList);

    }

    /**
     * 角色菜单关联
     * @param roleMenuVO
     * @return
     */
    @RequestMapping("/roleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO) {

        roleService.roleContextMenu(roleMenuVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", "");
        return result;

    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {

        roleService.deleteRole(id);

        return new ResponseResult(true,200,"响应成功","");

    }

    /**
     * 添加&修改角色
     * @param role
     * @return
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {

        if (role.getId() == null) {
            roleService.saveRole(role);
        } else {
            roleService.updateRole(role);
        }
        return new ResponseResult(true,200,"响应成功","");
    }

    /**
     * 获取当前角色拥有的 资源信息(包括资源分类以及资源信息)
     * @param roleId
     * @return
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(int roleId){

        //1.获取角色拥有的资源分类信息
        List<ResourceCategory> categoryList = roleService.findRoleHaveResource(roleId);

        ResponseResult result = new ResponseResult(true,200,"响应成功",categoryList);
        return result;
    }

    /**
     * 角色分配资源
     * @param roleResourceVo
     * @return
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){
        roleService.roleContextResource(roleResourceVo);
        ResponseResult result = new ResponseResult(true,200,"响应成功","");
        return result;
    }

}
