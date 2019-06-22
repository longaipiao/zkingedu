package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 收藏表
 * @ClassName Collection
 * @Author likai
 **/
@Data
@Component
public class Hoarding implements Serializable {
    private static final long serialVersionUID = -7641457772298888233L;
    //收藏id
    private Integer collectionId;
    //收藏的作品id
    private Integer collectionZpid;
    //用户id
    private Integer collectionUid;
    //类别0视频1帖子
    private Integer collectionState;
    //收藏时间
    private String collectionTime;
}
