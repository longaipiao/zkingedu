package com.zking.springbootelement.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
public class User {

    private Integer Uid;
    private String Uname;
    private String Upwd;

}
