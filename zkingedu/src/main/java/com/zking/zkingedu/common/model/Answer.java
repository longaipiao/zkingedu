package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 答案表
 *
 * @ClassName Answer
 * @Author likai
 **/
@Data
@Component
public class Answer implements Serializable {
    private static final long serialVersionUID = -7885224199297492014L;
    //答案id
    private Integer answerID;
    //题目id
    private Integer answerTid;
    //答案编码
    private String answerAbcd;
    //答案内容
    private String answerContent;
    //状态0正常1下架
    private Integer answerState;
}
