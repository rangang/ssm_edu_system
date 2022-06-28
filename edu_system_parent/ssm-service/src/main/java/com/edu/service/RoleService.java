package com.edu.service;

import com.edu.domain.Menu;
import com.edu.domain.Role;
import com.edu.domain.RoleMenuRelation;
import com.edu.domain.RoleMenuVO;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 3:25 下午
 * @Description:
 */
public interface RoleService {

    /**
     * 查询角色列表
     * @param role
     * @return
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色ID查询菜单信息
     * @param roleId
     * @return
     */
    public List<String> findMenuByRoleId(Integer roleId);

    /**
     * 角色菜单关联
     * @param roleMenuVO
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(Integer id);

    /**
     * 添加角色
     * @param role
     */
    public void saveRole(Role role);

    /**
     * 修改角色
     * @param role
     */
    public void updateRole(Role role);

}
