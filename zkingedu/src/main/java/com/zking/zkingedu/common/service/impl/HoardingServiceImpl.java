package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.dao.HoardingDao;
import com.zking.zkingedu.common.model.Hoarding;
import com.zking.zkingedu.common.service.HoardingService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 收藏接口实现层
 */
@Service("hoardingService")
public class HoardingServiceImpl implements HoardingService {

    @Resource
    private HoardingDao hoardingDao;

    /**
     * 根据前台用户id  查询收藏课程
     * yan  分页
     * @param uid
     * @return
     */
    @Override
    public List<Map<String, Object>> getHoardingsByUid(Integer uid) {
        return hoardingDao.getHoardingsByUid(uid);
    }


    /**
     * 根据 用户id  查询用户收藏的课程  分页
     * yan
     * @param pageBean
     * @return
     */
    @Override
    public ResultUtil getHoardingsAndPageByUid(PageBean<Integer> pageBean) {
        ResultUtil resultu;
        try {
            Page<Object> page = PageHelper.startPage(pageBean.getPageIndex(), pageBean.getPageSize());
            List<Map<String, Object>> hoardings = hoardingDao.getHoardingsByUid(pageBean.getT());
            resultu = new ResultUtil();
            resultu.setData(hoardings);
            resultu.setCount(String.valueOf(page.getTotal()));
            resultu.setCode(0);
            resultu.setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultUtil.error("数据异常："+e.getMessage());
        }
        return resultu;
    }


    /**
     * 查询课程id  有多少人收藏  统计
     * yan
     * @param courseId
     * @return
     */
    @Override
    public Integer getCourseNumber(Integer courseId) {
        return hoardingDao.getCourseNumber(courseId);
    }





    /**
     * 根据用户id  和课程id  查询表内是否有记录
     * yan
     * @param uid
     * @param cid
     * @return
     */
    @Override
    public Hoarding getHoardingByUidAndCid(Integer uid, Integer cid) {
        return hoardingDao.getHoardingByUidAndCid(uid,cid);
    }

    /**
     * 用户收藏课程  添加
     * yan
     * @param uid  用户id
     * @param cid  课程id
     * @return
     */
    @Override
    public int addHoardingAndCourse(Integer uid, Integer cid) {
        return hoardingDao.addHoardingAndCourse(uid,cid);
    }


    /**
     * 用户取消收藏课程
     * @param uid  用户id
     * @param cid  课程id
     * @return
     * yan
     */
    @Override
    public int delHoardingByuidAndCid(Integer uid, Integer cid) {
        return hoardingDao.delHoardingByuidAndCid(uid,cid);
    }



    /**
     * 用户收藏业务
     * 如果用户收藏了课程  则取消收藏
     * 如果用户没收藏课程   则收藏
     * yan
     * @param uid
     * @param cid
     * @return  code=1 收藏  code=2取消收藏
     */
    @Transactional
    @Override
    public ResultUtil UserAddHoardingAnddelByUidAndCid(Integer uid, Integer cid) {
        ResultUtil result = new ResultUtil();
        try {
            //1.查询用户是否收藏了该课程
            Hoarding hoarding = hoardingDao.getHoardingByUidAndCid(uid, cid);
            System.err.println("用户信息：===================="+hoarding);
            if(hoarding!=null){//收藏了
                //执行取消收藏
                int i = hoardingDao.delHoardingByuidAndCid(uid, cid);
                if(i>0){//取消收藏成功
                    result.setCode(2);
                    result.setMsg("取消收藏成功");
                }
            }
            else{//没有收藏  则收藏该课程
                int i = hoardingDao.addHoardingAndCourse(uid, cid);
                if(i>0){//收藏成功
                    result.setCode(1);
                    result.setMsg("收藏成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage());
            result.setCode(500);
        }
        return result;
    }
}
