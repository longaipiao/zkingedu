package com.zking.zkingedu.common.service;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Bill;

import java.util.List;

/**
 * 账单表
 */
public interface BillService {

    /**
     * 添加账单表的方法
     */
    public void addBill(Bill bill);



    /**
     * 根据用户id查询所以的账单信息数据
     */
    public PageInfo<Bill> findAll(Integer userId,Integer page,Integer limit);

}
