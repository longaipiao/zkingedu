package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 章节表
 *
 * @ClassName Section
 * @Author likai
 **/
@Data
@Component
public class Section implements Serializable {
    private static final long serialVersionUID = 4529055276637295352L;
    //章节id
    private Integer sectionID;
    //课程id'
    private Integer sectionCid;
    //章节名称
    private String sectionName;
    //发布时间
    private String sectionTime;
    //顺序号
    private Integer sectionSeq;
    //单个视频积分
    private Integer sectionInte;
    //优酷视频id
    private String videoId;
}
