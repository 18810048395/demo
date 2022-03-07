package com.example.demo.pojo;



import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MenuDTO {

    private Long id;
    private Long pid;
    private String name;
    private Date createTime;
    private String createUser;
    private List<MenuDTO> children;
}
