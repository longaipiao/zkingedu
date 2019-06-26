package com.zking.zkingedu.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.dao.BillDao;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账单表  实现
 */
@Service("billService")
public class BillServiceImpl implements BillService {

    @Resource
    private BillDao billDao;

    /**
     * 添加账单表的方法
     */

    @Override
    public void addBill(Bill bill) {
        billDao.addBill(bill);
    }




    /**
     * 根据用户id查询所以的账单信息数据
     * @param userId 用户id
     * @return
     */
    public PageInfo<Bill> findAll(Integer userId,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        List<Bill> bills = billDao.findAll(userId);
        return new PageInfo<>(bills);
    }

}
