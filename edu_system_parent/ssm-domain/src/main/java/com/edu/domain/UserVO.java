package com.edu.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: edu_system_parent
 * @Author: RG
 * @CreateTime: 2022/6/28 9:08 上午
 * @Description:
 */
public class UserVO {

    private Integer currentPage;
    private Integer pageSize;

    // 多条件查询：用户名（手机号）
    private String username;
    // 注册起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startCreateTime;
    // 注册结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    private List<Integer> roleIdList;
    private Integer userId;

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
