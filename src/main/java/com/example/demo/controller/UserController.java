package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /***
     * 根据ID查询用户信息(通过mybatis-generator生成的代码查询)
     * @param request
     */
    @RequestMapping(value="/getUserById")
    public Map<String,Object> getUserById(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String id = request.getParameter("id");// 记录主键id
            User user = userService.selectUserById(Integer.parseInt(id));
            resultMap.put("data",user);
            resultMap.put("code","200");
            resultMap.put("msg","操作成功");
        } catch (NumberFormatException e) {
            logger.error("error", e);
            resultMap.put("code","500");
            resultMap.put("msg","操作失败，id必须为数字");
        } catch (Exception e) {
            logger.error("error", e);
            resultMap.put("code","500");
            resultMap.put("msg","操作失败"+e.getMessage());
        }
        return resultMap;
    }

    /***
     * 根据ID查询用户信息(通过mybatis-plus的API查询)
     * @param request
     */
    @RequestMapping(value="/getMyUserById")
    public Map<String,Object> getMyUserById(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String id = request.getParameter("id");// 记录主键id
            User user = userService.selectMyUserById(Integer.parseInt(id));
            resultMap.put("data",user);
            resultMap.put("code","200");
            resultMap.put("msg","操作成功");
        } catch (NumberFormatException e) {
            logger.error("error", e);
            resultMap.put("code","500");
            resultMap.put("msg","操作失败，id必须为数字");
        } catch (Exception e) {
            logger.error("error", e);
            resultMap.put("code","500");
            resultMap.put("msg","操作失败"+e.getMessage());
        }
        return resultMap;
    }
}
