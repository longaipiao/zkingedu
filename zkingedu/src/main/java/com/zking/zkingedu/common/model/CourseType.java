package com.zking.zkingedu.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 *课程类型
 * yan
 */
@Data
public class CourseType implements Serializable {

    //类别id
    private Integer tId;
    //类别名称
    private String tName;
}
