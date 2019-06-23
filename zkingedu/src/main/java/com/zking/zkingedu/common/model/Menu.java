package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 菜单表
 * @ClassName Menu
 * @Author likai
 **/
@Data
@Component
public class Menu implements Serializable {
    private static final long serialVersionUID = -1806337446997030316L;
    //菜单id
    private Integer menuID;
    //菜单名称
    private String menuName;
    //菜单父id
    private Integer menuFid;
    //菜单图标
    private String menuImg;
    //授权码(按钮)
    private String menuCode;
    //url
    private String menuURL;
    //序号
    private Integer menuRank;
    //类别0菜单1授权码（按钮)
    private Integer menuSort;
    //角色
    private Set<Role> roles=new HashSet<>();
}
