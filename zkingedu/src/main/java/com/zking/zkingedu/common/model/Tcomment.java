package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 帖子评论表
 * @ClassName Tcomment
 * @Author likai
 **/
@Data
@Component
public class Tcomment implements Serializable {
    private static final long serialVersionUID = -6015599453427748055L;
    //帖子评论id'
    private Integer tcommentID;
    //帖子id
    private Integer tcommentCid;
    //用户id
    private Integer tcommentUid;
    //评论内容
    private String tcommentContent;
    //评论时间
    private String tcommentTime;
    //评论回复父id
    private Integer tcommentFid;
}
