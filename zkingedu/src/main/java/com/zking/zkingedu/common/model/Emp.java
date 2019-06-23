package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Emp
 * @Author likai
 **/
@Data
@Component
public class Emp implements Serializable {
    private static final long serialVersionUID = 7898137294395441421L;
    //员工id
    private Integer empID;
    //员工名
    private String empName;
    //密码
    private String empPassword;
    //头像
    private String empIntegral;
    //入职时间
    private String empTime;
    //状态
    private Integer empState;
    //错误次数
    private Integer empError;
    //员工角色
    private Set<Role> roles=new HashSet<>();

}
