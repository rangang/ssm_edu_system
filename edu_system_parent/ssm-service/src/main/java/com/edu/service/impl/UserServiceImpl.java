package com.edu.service.impl;

import com.edu.dao.UserMapper;
import com.edu.domain.*;
import com.edu.service.UserService;
import com.edu.utils.Md5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 9:13 上午
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {

        // 使用pageHelper
        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());
        List<User> allUser = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<>(allUser);

        System.out.println("总条数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("当前页：" + pageInfo.getPageNum());
        System.out.println("每页显示长度：" + pageInfo.getPageSize());
        System.out.println("是否第一页：" + pageInfo.isIsFirstPage());
        System.out.println("是否最后一页：" + pageInfo.isIsLastPage());

        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id,status);
    }

    @Override
    public User login(User user) {
        User user2 = userMapper.login(user);

        if (user2 != null && Md5.verify(user.getPassword(),Md5.md5key,user2.getPassword())) {
            return user2;
        }
        return null;
    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        return userMapper.findUserRelationRoleById(id);
    }

    @Override
    public void userContextRole(UserVO userVO) {

        // 根据用户ID清空中间表的关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        // 向中间表添加记录
        for (Integer roleId : userVO.getRoleIdList()) {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(userVO.getUserId());
            userRoleRelation.setRoleId(roleId);
            Date date = new Date();
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);

            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedBy("system");

            userMapper.userContextRole(userRoleRelation);
        }

    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {

        // 1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        // 2.获取角色ID，保存到list
        List<Integer> list = new ArrayList<>();

        for (Role role : roleList) {
            list.add(role.getId());
        }

        // 3.根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);

        // 4.封装父菜单下的菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        // 5.获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        // 6.封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenu); // menuList:菜单权限数据
        map.put("resourceList",resourceList); // resourceList:资源权限数据

        return new ResponseResult(true,200,"响应成功",map);

    }
}
