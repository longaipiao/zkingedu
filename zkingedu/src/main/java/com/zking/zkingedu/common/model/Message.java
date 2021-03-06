package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 消息表
 * @ClassName Message
 * @Author likai
 **/
@Data
@Component
public class Message implements Serializable {
    private static final long serialVersionUID = 4754628991979091255L;
    //消息id
    private Integer messageID;
    //session中的用户id
    private Integer messageUid1;
    //回复某个用户id
    private Integer messageUid2;
    //帖子id
    private Integer messagePid;
    //消息标题
    private String messageName;
    //消息内容
    private String messageContent;
    //消息发布时间
    private String messageTime;
    //状态1正常2下架
    private Integer messageState;

}
