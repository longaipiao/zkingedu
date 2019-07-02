package com.zking.zkingedu.common.dao;


import com.zking.zkingedu.common.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 消息接口
 */
public interface MessageDao {

    /**
     * 增加消息的方法
     * @param message 消息对象
     * @return
     */
    public int addMessage(Message message);


    /**
     * 根据用户id查询所以的消息通知
     * 阿飘
     */
    public List<Map<String,Object>> findMessage(@Param("userID") Integer userID);


    /**
     * 修改未读状态为已读状态
     * 阿飘
     */
    public int updateState(@Param("messageid")Integer messageid);


}
