package com.edu.service.impl;

import com.edu.dao.RoleMapper;
import com.edu.domain.*;
import com.edu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 3:26 下午
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        // 清空中间表
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        for (Integer mid : roleMenuVO.getMenuIdList()) {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
            roleMenuRelation.setRoleId(roleMenuVO.getRoleId());
            roleMenuRelation.setMenuId(mid);
            Date date = new Date();
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedBy("system");
            roleMapper.roleContextMenu(roleMenuRelation);
        }

    }

    @Override
    public void deleteRole(Integer id) {

        // 清空中间表
        roleMapper.deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);

    }

    @Override
    public void saveRole(Role role) {

        // 补全信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);

        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {

        // 补全信息
        role.setUpdatedTime(new Date());

        roleMapper.updateRole(role);

    }

    @Override
    public List<ResourceCategory> findRoleHaveResource(int id) {

        //1.获取角色拥有的资源分类数据
        List<ResourceCategory> categoryList = roleMapper.findRoleHaveResourceCate(id);

        //2.获取角色拥有的资源数据
        List<Resource> resourceList = roleMapper.findRoleHaveResource(id);

        //3.将资源数据封装到对应分类下
        for (ResourceCategory category : categoryList) {
            for (Resource resource : resourceList) {
                //判断
                if(category.getId() == resource.getCategoryId()){
                    //将资源保存到集合中
                    category.getResourceList().add(resource);
                }
            }
        }

        //4.返回资源分类集合
        return categoryList;

    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        //根据角色id 清空中间表
        Integer roleId = roleResourceVo.getRoleId();
        roleMapper.deleteRoleContextResource(roleId);

        //获取分配资源的id集合
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();

        //向中间表插入最新的关联信息
        for (Integer resId : resourceIdList) {
            RoleResourceRelation relation = new RoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resId);
            Date date = new Date();
            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);
            relation.setCreatedBy("system");
            relation.setUpdatedBy("system");

            roleMapper.roleContextResource(relation);
        }

    }


}
