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
    //
    private Integer logID;
    //
    private Integer logEid;
    //
    private String logDetails;
    //
    private String logTime;
}
