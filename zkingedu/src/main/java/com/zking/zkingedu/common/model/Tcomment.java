package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    //评论对应的uid,及用户
    private User user2;

    //某条评论下面的所有评论
    List<Tcomment> chirden=new ArrayList<>();
    //配关系
    private User user;
    //楼主顺序
    private  Integer tcommentLounum;
    //要回复谁的id
    private  Integer tcommentUid2;

}
