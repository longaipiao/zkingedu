package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 日志表
 * @ClassName Log
 * @Author likai
 **/
@Data
@Component
public class Log implements Serializable {
    private static final long serialVersionUID = 3143819941258172884L;
    //ID
    private Integer logID;
    //员工
    private Emp emp;
    //操作详情
    private String logDetails;
    //操作时间
    private String logTime;

    public Log(Integer logID, Emp emp, String logDetails, String logTime) {
        this.logID = logID;
        this.emp = emp;
        this.logDetails = logDetails;
        this.logTime = logTime;
    }

    public Log(Emp emp, String logDetails, String logTime) {
        this.emp = emp;
        this.logDetails = logDetails;
        this.logTime = logTime;
    }

    public Log() {
    }

}
