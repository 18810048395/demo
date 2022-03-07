package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 测试菜单表
 * @TableName menu
 */
@TableName(value ="menu")
@Data
public class Menu implements Serializable {
    /**
     * 菜单id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 菜单父id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}