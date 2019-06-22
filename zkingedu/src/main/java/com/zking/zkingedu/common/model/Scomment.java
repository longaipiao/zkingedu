package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 课程评论表
 * @ClassName Scomment
 * @Author likai
 **/
@Data
@Component
public class Scomment implements Serializable {
    private static final long serialVersionUID = -5112786057186631362L;
    //课程评论id
    private Integer scommentID;
    //课程id
    private Integer scommentCid;
    //用户id
    private Integer scommentUid;
    //评论内容
    private String scommentContent;
    //评论时间
    private String scommentTime;
}
