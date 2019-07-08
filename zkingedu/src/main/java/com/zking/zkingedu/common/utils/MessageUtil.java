package com.zking.zkingedu.common.utils;

import com.zking.zkingedu.common.model.Message;
import com.zking.zkingedu.common.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

@Component
@Slf4j
public class MessageUtil {
    @Autowired
    private MessageService messageService;

    public void addMessage(Message message){
        try {
            int i = messageService.addMessage(message);
            if(i>0){
                log.info("消息添加成功");
            }
            else{
                log.info("消息添加失败");
            }
        }catch (Exception e){
            log.info("**********消息异常抛出*******");
        }
    }


    public void addMessage(Integer messageUid1,Integer messageUid2,Integer messagePid,String msg){
        Message message = new Message();
        message.setMessageUid1(messageUid1);
        message.setMessageUid2(messageUid2);
        message.setMessagePid(messagePid);
        message.setMessageName("系统消息");
        message.setMessageContent(msg);//内容
        message.setMessageTime(new Date().toLocaleString());
        message.setMessageState(1);
        try {
            int i = messageService.addMessage(message);
            if(i>0){
                log.info("消息添加成功");
            }
            else{
                log.info("消息添加失败");
            }
        }catch (Exception e){
            log.info("**********消息异常抛出*******");
        }
    }

}
