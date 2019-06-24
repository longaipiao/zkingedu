package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.BillDao;
import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
