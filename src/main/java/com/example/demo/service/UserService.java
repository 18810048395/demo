package com.example.demo.service;

import com.example.demo.pojo.User;

/**
 * UserService
 * @author v_jingwen
 *
 */
public interface UserService {

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */
    public User selectUserById(Integer userId);
}