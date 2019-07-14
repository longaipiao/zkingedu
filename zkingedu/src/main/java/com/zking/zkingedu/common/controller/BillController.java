package com.zking.zkingedu.common.controller;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.service.BillService;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿飘  制作
 *
 * 2019:6:15
 *
 * 账单表的操作
 */

@Controller
@Slf4j
@Transactional
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/findBill")
    @ResponseBody
    public Map<String,Object> findbill(Integer page,Integer limit){
        //需要修改用户id的   后期用户id得从session中获取
        PageInfo<Bill> bills = billService.findAll(SessionUtil.getUserById(), page, limit);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","success");
        maps.put("code",0);
        maps.put("count",bills.getTotal());
        maps.put("data",bills.getList());
        return maps;
    }


    @RequestMapping(value = "/findBillUname")
    @ResponseBody
    public Map<String,Object> findbillUname(String userName,Integer page,Integer limit){
        PageInfo<Map<String, Object>> billUname = billService.findBillUname(userName, page, limit);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","success");
        maps.put("code",0);
        maps.put("count",billUname.getTotal());
        maps.put("data",billUname.getList());
        return maps;
    }









}
