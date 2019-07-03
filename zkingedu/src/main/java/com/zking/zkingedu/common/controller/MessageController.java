package com.zking.zkingedu.common.controller;


import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.service.MessageService;
import com.zking.zkingedu.common.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.resources.Messages;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息的接口 操作
 */
@Controller
@Transactional
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/findMessage")
    @ResponseBody
    public Map<String,Object> findmessage(Integer page,Integer limit){
        System.out.println("page:"+page+"        *****        limit:"+limit);
        log.info("*********开始查询消息的数据**********");
        PageInfo<Map<String, Object>> message = messageService.findMessage(2,page,limit);
        System.err.println(message);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","success");
        maps.put("code",0);
        maps.put("count",message.getPages());
        maps.put("data",message.getList());
        return maps;
    }

    /**
     * 修改未读状态为已读状态
     */
    @RequestMapping(value = "/updateMessageState")
    @ResponseBody
    public int updateState(Integer messageid){
        log.info("**********开始修改已读状态*********");
        int state = messageService.updateState(messageid);
        if(state==1){
            log.info("修改成功");
        }else{
            log.info("修改失败");
        }
        return state;
    }



}
