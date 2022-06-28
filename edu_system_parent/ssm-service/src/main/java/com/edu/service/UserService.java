package com.edu.service;

import com.edu.domain.ResponseResult;
import com.edu.domain.Role;
import com.edu.domain.User;
import com.edu.domain.UserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 9:13 上午
 * @Description:
 */
public interface UserService {

    /**
     * 查询所有用户
     * @param userVO
     * @return
     */
    public PageInfo findAllUserByPage(UserVO userVO);

    /**
     * 修改用户状态
     * @param id
     * @param status
     */
    public void updateUserStatus(int id, String status);

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
     * 分配角色
     * @param userVO
     */
    public void userContextRole(UserVO userVO);

    /**
     * 获取用户权限
     * @param id
     * @return
     */
    public ResponseResult getUserPermissions(Integer id);

}
