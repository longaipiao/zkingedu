package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 开发者工具
 * @ClassName Tool
 * @Author likai
 **/
@Data
@Component
public class Tool implements Serializable {
    private static final long serialVersionUID = 7082837878193717520L;
    //工具id
    private Integer toolID;
    //工具标题
    private String toolName;
    //工具描述
    private String toolDescribe;
    //工具链接
    private String toolURL;
    //工具图片
    private String toolImg;
    //工具添加时间
    private String toolTime;
}
