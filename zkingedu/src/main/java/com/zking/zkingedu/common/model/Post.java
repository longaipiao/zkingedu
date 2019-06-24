package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 帖子表
 * @ClassName Post
 * @Author likai
 **/
@Data
@Component
public class Post implements Serializable {
    private static final long serialVersionUID = 915849261054909969L;
    //帖子id
    private Integer postID;
    //用户id 
    private Integer postUid;
    //帖子标题
    private String postName;
    //帖子内容
    private String postContent;
    //帖子发布时间
    private String postTime;
    //阅读数
    private Integer postNum;
    //状态0正常1封禁
    private Integer postState;
    //类别
    private Integer postCategory;


}
