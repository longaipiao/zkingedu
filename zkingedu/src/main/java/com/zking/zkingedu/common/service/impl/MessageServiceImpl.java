package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.MessageDao;
import com.zking.zkingedu.common.model.Message;
import com.zking.zkingedu.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 消息接口   实现层
 */
@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageDao messageDao;

    /**
     * 增加消息的方法
     * @param message 消息对象
     * @return
     */
    @Override
    public int addMessage(Message message) {
        return messageDao.addMessage(message);
    }

    /**
     * 根据用户id查询所以的消息通知
     * 阿飘
     */
    @Override
    public PageInfo<Map<String, Object>> findMessage(Integer userID, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Map<String, Object>> messages = messageDao.findMessage(userID);
        return new PageInfo<>(messages);
    }

    /**
     * 修改未读状态为已读状态
     * 阿飘
     */
    @Override
    public int updateState(Integer messageid) {
        return messageDao.updateState(messageid);
    }

    /**
     * 查询有多少条消息记录
     * 阿飘
     */
    @Override
    public int findCountjl(Integer userID) {
        return messageDao.findCountjl(userID);
    }
}
