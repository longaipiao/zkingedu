package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 视频表
 * @ClassName Video
 * @Author likai
 **/
@Data
@Component
public class Video implements Serializable {
    private static final long serialVersionUID = 161374555455570047L;
    //视频id
    private Integer videoID;
    //章节id
    private Integer videoSid;
    //视频时长
    private String videoDuration;
    //url
    private String videoURL;
}
