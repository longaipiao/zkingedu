package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 广告表
 * @ClassName Advertising
 * @Author likai
 **/
@Data
@Component
public class Advertising implements Serializable {

    private static final long serialVersionUID = -6806116958862239873L;
    //广告ID
    private Integer advertisingID;
    //广告图片
    private String advertisingImg;
    //广告链接
    private String advertisingURL;
    //广告状态0正常1封禁
    private Integer advertisingState;
    //广告标题
    private String advertisingName;
    //广告时间
    private String advertisingTime;

}
