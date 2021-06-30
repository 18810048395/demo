package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller
 *
 * @author yh
 * @date 2020年9月9日 下午15:18:08
 *
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /***
     * 增加测试用户
     * @param request
     */
    @RequestMapping(value="/addUser")
    public Map<String,Object> addUser(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            User user = new User();
            user.setUserName("yanghe");
            user.setUserSex("M");// F=女性，M=男性，
            user.setUserAddress("北京市海淀区");
            int id = userService.addUser(user);
            resultMap.put("data",id);// 返回记录主键id
            resultMap.put("code","200");
            resultMap.put("msg","操作成功");
        } catch (Exception e) {
            logger.error("error", e);
            resultMap.put("code","500");
            resultMap.put("msg","操作失败"+e.getMessage());
        }
        return resultMap;
    }

    /***
     * 根据ID查询用户信息
     * @param request
     */
    @RequestMapping(value="/getUserById")
    public Map<String,Object> getUserById(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String userId = request.getParameter("userId");// 记录主键id
            User user = userService.selectUserById(Integer.parseInt(userId));
            resultMap.put("data",user);
            resultMap.put("code","200");
            resultMap.put("msg","操作成功");
        } catch (NumberFormatException e) {
            logger.error("error", e);
            resultMap.put("code","500");
            resultMap.put("msg","操作失败，userId必须为数字");
        } catch (Exception e) {
            logger.error("error", e);
            resultMap.put("code","500");
            resultMap.put("msg","操作失败"+e.getMessage());
        }
        return resultMap;
    }
}
