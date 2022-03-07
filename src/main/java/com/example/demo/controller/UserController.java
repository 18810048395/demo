package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    /***
     * 测试时间接收的接口
     * @param startTime
     */
    @GetMapping(value="/getTime")
    @ApiOperation("测试时间接收的接口")
    public String getTime(@RequestParam String startTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(startTime);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
