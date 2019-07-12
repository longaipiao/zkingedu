package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程体系表
 *
 * @ClassName Advertising
 * @Author likai
 **/
@Data
@Component
public class System implements Serializable {

    private static final long serialVersionUID = -840955741477930223L;
    //体系ID
    private Integer systemID;
    //体系名称
    private String systemName;
    //父体系ID
    private Integer systemFid;
    //状态0上架1下架
    private Integer systemState;
    //体系描述
    private String systemDesc;
    //体系图片
    private String systemImg;

    //体系里面有多少个课程
    private Integer sourcesNum;

    //对应多个课程
    private List<Course> courses = new ArrayList<>();

}
