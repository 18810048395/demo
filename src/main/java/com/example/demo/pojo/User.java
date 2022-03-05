package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
@Builder
@ApiModel("用户实体类")
public class User implements Serializable {
    /**
     * id
     */

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @TableField(value = "user_name")
    private String userName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @TableField(value = "user_sex")
    private String userSex;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    @TableField(value = "user_address")
    private String userAddress;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}