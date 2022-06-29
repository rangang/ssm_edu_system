package com.edu.dao;

import com.edu.domain.*;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 3:25 下午
 * @Description:
 */
public interface RoleMapper {

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
     * 删除角色菜单关联信息
     * @param id
     */
    public void deleteRoleContextMenu(Integer id);

    /**
     * 角色菜单关联
     * @param roleMenuRelation
     */
    public void roleContextMenu(RoleMenuRelation roleMenuRelation);

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

    /**
     * 获取角色拥有的资源分类数据
     * @param id
     * @return
     */
    public List<ResourceCategory> findRoleHaveResourceCate(int id);

    /**
     * 获取角色拥有的资源数据
     * @param id
     * @return
     */
    public List<Resource> findRoleHaveResource(int id);

    /**
     * 删除角色和资源的关联信息
     * @param roleId
     */
    public void deleteRoleContextResource(Integer roleId);

    /**
     * 为角色分配资源
     * @param resourceRelation
     */
    public void roleContextResource(RoleResourceRelation resourceRelation);

}
