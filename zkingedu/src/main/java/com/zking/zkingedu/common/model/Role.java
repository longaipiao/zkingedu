package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 角色表
 * @ClassName Role
 * @Author likai
 **/
@Data
@Component
public class Role implements Serializable {
    private static final long serialVersionUID = 1812009755321021468L;
    //角色id
    private Integer roleID;
    //角色名
    private String roleName;
}
