package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户 Controller
 *
 * @author yh
 * @date 2020年9月9日 下午15:18:08
 *
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
@Api(tags = "用户管理相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * 根据ID查询用户信息
     * @param id
     */
    @GetMapping(value="/getUserById")
    @ApiOperation("根据id查询用户的接口")
    public User getUserById(@RequestParam Integer id){
        User user = userService.getById(id);
        return user;
    }
}
