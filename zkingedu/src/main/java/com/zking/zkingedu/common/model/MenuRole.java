package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 题库类别表
 * @ClassName Category
 * @Author likai
 **/
@Data
@Component
public class MenuRole implements Serializable {
    private static final long serialVersionUID = -7110932634854036303L;
    //题库类别id
    private Integer roleID;
    //题库名称
    private Integer menuID;

    public MenuRole(Integer roleID, Integer menuID) {
        this.roleID = roleID;
        this.menuID = menuID;
    }

    public MenuRole() {
    }
}
