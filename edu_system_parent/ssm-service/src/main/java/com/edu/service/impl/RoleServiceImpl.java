package com.edu.service.impl;

import com.edu.dao.RoleMapper;
import com.edu.domain.Menu;
import com.edu.domain.Role;
import com.edu.domain.RoleMenuRelation;
import com.edu.domain.RoleMenuVO;
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


}
