package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 论坛类别表
 *
 * @ClassName Sort
 * @Author likai
 **/
@Data
@Component
public class Sort implements Serializable {
    private static final long serialVersionUID = -7939735725919904701L;
    //类别id
    private Integer sortID;
    //类别名
    private String sortName;
}
