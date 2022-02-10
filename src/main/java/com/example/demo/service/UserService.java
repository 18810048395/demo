package com.example.demo.service;

import com.example.demo.pojo.User;

/**
 * UserService
 * @author v_jingwen
 *
 */
public interface UserService {

    /**
     * 根据ID查询用户信息(通过mybatis-generator生成的代码查询)
     * @param id
     * @return
     */
    public User selectUserById(Integer id);

    /**
     * 根据ID查询用户信息(通过mybatis-plus的API查询)
     * @param id
     * @return
     */
    public User selectMyUserById(Integer id);
}