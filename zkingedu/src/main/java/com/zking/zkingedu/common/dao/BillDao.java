package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 账单表
 */
public interface BillDao {

    /**
     * 添加账单表的方法
     */
    public void addBill(Bill bill);


    /**
     * 根据用户id查询所以的账单信息数据
     */
    public List<Bill> findAll(Integer userId);


    /**
     * 根据用户名称查询出账单信息
     */
    public List<Map<String,Object>> findBillUname(@Param("userName") String userName);


}
