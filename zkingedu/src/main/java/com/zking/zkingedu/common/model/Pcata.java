package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 帖子分类
 */
@Data
@Component
public class Pcata implements Serializable {
    private static final long serialVersionUID = 5388578685630623331L;
    private Integer pcid;
    private String pcname;
}
