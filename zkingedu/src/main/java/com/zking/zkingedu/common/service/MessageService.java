package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消息接口
 */
public interface MessageService {

    /**
     * 增加消息的方法
     *
     * @param message 消息对象
     * @return
     */
    public int addMessage(Message message);

    /**
     * 根据用户id查询所以的消息通知
     * 阿飘
     */
    public PageInfo<Map<String, Object>> findMessage(Integer userID, Integer page, Integer limit);


    /**
     * 修改未读状态为已读状态
     * 阿飘
     */
    public int updateState(Integer messageid);

    /**
     * 查询有多少条消息记录
     * 阿飘
     */
    public int findCountjl(Integer userID);


    /**
     * 根据消息id查询评论id
     */
    public int findMessahePid(Integer messageId);

    /**
     * 根据用户id和消息id删除
     */
    public int deleteymID(Integer mid);

}
