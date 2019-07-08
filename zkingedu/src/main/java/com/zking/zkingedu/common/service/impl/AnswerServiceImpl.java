package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.AnswerDao;
import com.zking.zkingedu.common.model.Answer;
import com.zking.zkingedu.common.service.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 答案接口服务
 */
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {
    @Resource
    private AnswerDao answerDao;

    @Override
    public List<Answer> getAll(Integer tid) {
        return answerDao.getAll(tid);
    }

    @Override
    public Integer addAnswer(List<Answer> ls) {
        return answerDao.addAnswer(ls);
    }

    @Override
    public Integer deletedaan(Integer tid) {
        return answerDao.deletedaan(tid);
    }

    @Override
    public Integer uppdatedaana(Answer answer) {
        return answerDao.uppdatedaana(answer);
    }

    @Override
    public Integer uppdatedaanb(Answer answer) {
        return answerDao.uppdatedaand(answer);
    }

    @Override
    public Integer uppdatedaanc(Answer answer) {
        return answerDao.uppdatedaanc(answer);
    }

    @Override
    public Integer uppdatedaand(Answer answer) {
        return answerDao.uppdatedaand(answer);
    }


}
