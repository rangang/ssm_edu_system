package com.edu.controller;

import com.edu.domain.ResponseResult;
import com.edu.domain.Role;
import com.edu.domain.User;
import com.edu.domain.UserVO;
import com.edu.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 9:14 上午
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @param userVO
     * @return
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {

        PageInfo pageInfo = userService.findAllUserByPage(userVO);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", pageInfo);

        return result;

    }

    /**
     * 修改用户状态
     * ENABLE能登录，DISABLE不能登录
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status) {
        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }

        userService.updateUserStatus(id,status);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", status);
        return result;
    }

    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) {

        User login = userService.login(user);

        ResponseResult result = null;
        if (login != null) {
            // 保存access_token
            Map<String,Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id", login.getId());

            HttpSession session = request.getSession();
            session.setAttribute("user_id",login.getId());
            session.setAttribute("access_token",access_token);

            result = new ResponseResult(true,1,"响应成功",map);
        } else {
            result = new ResponseResult(true,1,"用户名密码错误",null);
        }

        return result;

    }

    /**
     * 根据ID查询用户当前角色
     * @param id
     * @return
     */
    @RequestMapping("/findUserRelationRoleById")
    public ResponseResult findUserRelationRoleById(int id) {
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"根据ID查询用户当前角色",roleList);
    }

    /**
     * 分配角色
     * @param userVO
     * @return
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO) {

        userService.userContextRole(userVO);

        return new ResponseResult(true,200,"分配角色",null);

    }

    /**
     * 获取用户权限
     * @param request
     * @return
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(@RequestParam Integer userId, HttpServletRequest request) {

        // // 获取请求头中的token
        // String token = request.getHeader("Authorization");
        //
        // // 获取session中的access_token
        // HttpSession session = request.getSession();
        // String access_token = (String) session.getAttribute("access_token");
        //
        // // 判断
        // if (token.equals(access_token)) {
        //     int user_id = (Integer)session.getAttribute("user_id");
            return userService.getUserPermissions(userId);
        // } else {
        //     return new ResponseResult(false,400,"获取失败","");
        // }

    }

}
