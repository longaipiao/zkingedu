package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 点赞表
 * @ClassName Give
 * @Author likai
 **/
@Data
@Component
public class Give implements Serializable {
    private static final long serialVersionUID = -6858152555774330714L;
    //点赞id
    private Integer giveID;
    //帖子id
    private Integer givePid;
    //用户id
    private Integer giveUid;
    //点赞时间
    private String giveTime;
}
