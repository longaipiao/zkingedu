package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 题库类别表
 * @ClassName Category
 * @Author likai
 **/
@Data
@Component
public class Category implements Serializable {
    private static final long serialVersionUID = -8189966080659095127L;
    //题库类别id
    private Integer categoryID;
    //题库名称
    private String categoryName;
    //父题库id
    private Integer categoryFid;
    //创建时间
    private String categoryTime;
    //创建人eid
    private Integer categoryEid;
    //序号
    private Integer categoryRank;
    //状态0正常1下架
    private Integer categoryState;
    //装子ID的集合
    List<Category> categories = new ArrayList<>();
}
