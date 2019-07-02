package com.zking.zkingedu;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZkingeduApplication.class)
public class ZkingeduApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void contextLoads() {
        System.out.println("爱情是什么");
        PageInfo<Map<String, Object>> orderUid = orderService.findOrderUid(2,1,2);
        List<Map<String, Object>> list = orderUid.getList();
        for (Map<String, Object> objectMap : list) {
            System.out.println(objectMap);
        }

    }


    @Test
    public void ss() {
        System.out.println("爱情是什么");
        PageInfo<Map<String, Object>> order = orderService.findOrder("", "", 1, 3);
        List<Map<String, Object>> list = order.getList();
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }

    }

}
