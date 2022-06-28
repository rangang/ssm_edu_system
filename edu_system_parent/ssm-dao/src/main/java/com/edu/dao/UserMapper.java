package com.edu.dao;

import com.edu.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 9:13 上午
 * @Description:
 */
public interface UserMapper {

    /**
     * 查询所有用户
     * @param userVO
     * @return
     */
    public List<User> findAllUserByPage(UserVO userVO);

    /**
     * 修改用户状态
     * @param id
     * @param status
     */
    public void updateUserStatus(@Param("id") int id,@Param("status") String status);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 根据ID查询用户当前角色
     * @param id
     * @return
     */
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 根据用户ID清空中间表
     * @param userId
     */
    public void deleteUserContextRole(Integer userId);

    /**
     * 分配角色
     * @param userRoleRelation
     */
    public void userContextRole(UserRoleRelation userRoleRelation);

    /**
     * 根据角色ID，查询角色拥有的顶级菜单信息
     * @param ids
     * @return
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 根据PID查询子菜单信息
     * @param pid
     * @return
     */
    public List<Menu> findSubMenuByPid(int pid);

    /**
     * 获取用户拥有的资源权限信息
     * @param ids
     * @return
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
