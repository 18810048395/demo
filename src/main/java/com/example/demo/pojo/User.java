package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 性别
     */
    @TableField(value = "user_sex")
    private String userSex;

    /**
     * 地址
     */
    @TableField(value = "user_address")
    private String userAddress;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}