package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 题目表
 * @ClassName Title
 * @Author likai
 **/
@Data
@Component
public class Title implements Serializable {
    private static final long serialVersionUID = -3523542992767880892L;
    //
    private Integer titleID;
    //
    private Integer titleCid;
    //
    private String titleContent;
    //
    private String titleDescribe;
    //
    private String titleTime;
    //
    private Integer titleState;
}
