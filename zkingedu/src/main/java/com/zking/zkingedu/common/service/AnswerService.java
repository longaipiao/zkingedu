package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 答案接口
 */
public interface AnswerService {
    //根据题目id查看所有的题目
    List<Answer> getAll(@Param("tid") Integer tid);

    //批量增加答案
    Integer addAnswer(@Param("ls") List<Answer> ls);

    //根据阶段id删除答案
    Integer deletedaan(@Param("tid") Integer tid);

    //修改答案A
    Integer uppdatedaana(Answer answer);

    //修改答案B
    Integer uppdatedaanb(Answer answer);

    //修改答案C
    Integer uppdatedaanc(Answer answer);

    //修改答案D
    Integer uppdatedaand(Answer answer);
}
