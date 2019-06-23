package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Charge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @email 2417902780@qq.com
 * @author:阿飘
 * @company 飘飘公司
 * @create 2019-06-23 21:09
 */
@Controller
@RequestMapping(value = "/user")
@Slf4j
public class ChargeController {

    /**
     * 充值积分的方法
     * @return charge 充值积分的对象
     */
    @RequestMapping(value = "/integral")
    public String addintegral(Charge charge){
        log.info("--------------------充值积分的方法---------------------");

        return "user/userinfo/index?#get8";
    }


}
