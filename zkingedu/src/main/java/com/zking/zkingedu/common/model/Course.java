package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 课程表
 * @ClassName Course
 * @Author likai
 **/
@Data
@Component
public class Course implements Serializable {
    private static final long serialVersionUID = -1842141320487532709L;
    //'课程id
    private Integer courseID;
    //课程体系id
    private Integer courseSid;
    //讲师id
    private Integer courseEid;
    //课程名
    private String courseName;
    //课程介绍
    private String courseDesc;
    //课程图片
    private String courseImg;
    //课程状态0正常1封禁
    private Integer courseState;
    //免费章节数
    private Integer courseFree;
    //整套课程积分
    private Integer courseInte;
    //学习人数
    private Integer courseNum;
    //发布时间
    private String courseTime;
}
