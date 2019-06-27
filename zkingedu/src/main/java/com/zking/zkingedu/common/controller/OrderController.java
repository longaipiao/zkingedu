package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Bill;
import com.zking.zkingedu.common.model.Charge;
import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Order;
import com.zking.zkingedu.common.service.BillService;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.OrderService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IdGeneratorUtils;
import com.zking.zkingedu.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 阿飘制作
 *
 * 订单的controller  操作
 *
 */

@Controller
@Slf4j
@Transactional
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private CourseService courseService;
    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;
    @Autowired
    private Order order;
    @Autowired
    private Bill bill;
    @Resource
    private IdGeneratorUtils idGeneratorUtils;




    /**
     * 购买整套视频的方法
     * @param courseID  课程id
     * @param integral  课程所需要的积分
     * @return
     */
    @RequestMapping(value = "/purchase")
    @ResponseBody
    public String purchaseVideo(Model model,Integer courseID, Integer integral, HttpServletResponse response) throws Exception {

        //1：首先查询出用户的积分够不够买整套视频，如果不够的话，就提示用户需要充值积分购买。
        //2：购买成功后，如果用户第二次进去观看，根据课程id和用户id去订单表中查询出此用户有购买的话，就直接放行去观看，
        //3:根据课程id和用户id查询订单表的


        log.info("***************开始查询这个课程需要多少积分*******************");
        Integer courseInte = courseService.findCourseInte(courseID);
        System.err.println("课程的是否需要积分：" + courseInte);


        log.info("***************开始查询这个用户剩下多少积分*******************");
        int userintegrsl = userService.findIntegrsl(2);//用户id
        System.err.println("用户剩下积分为：" + userintegrsl);


        if (userintegrsl < courseInte) {
            return "1";//积分不够的，需要去充值的
        } else  {
            log.info("*************开始购买整套视频的方法***********");
            order.setOrderID(idGeneratorUtils.nextId());//生成一个唯一的订单的id
            order.setOrderUid(2);//获取session中用户的id
            order.setOrderSid(courseID);//前台传过来的课程id
            order.setOrderCid(0);//购买整套视频章节id默认为0
            order.setOrderIntegral(integral);//整套视频的积分
            //订单生成的日期
            order.setChargeTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
            order.setOrderState(1);//状态
            int gmshiporder = orderService.gmshiporder(order);
            System.out.println("成功的：" + gmshiporder);

            //ResponseUtil.write(response,gmshiporder);

            log.info("*****************结束购买整套视频的方法************");

            log.info("********************开始生成账单表*********************");
            bill.setBillUid(2);//获取session中用户的id
            bill.setBillType(1);//类型为1的是：支付状态
            bill.setBillIntegral(integral);//支付的积分
            //账单生成的时间
            bill.setBillTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));

            //调用根据课程id查询的课程名称方法
            String courseName = courseService.findCourseName(courseID);
            //账单内容
            bill.setBillContent("您购买《" + courseName + "》,消费积分：" + integral);
            bill.setBillState(1);//状态
            billService.addBill(bill);
            log.info("********************结束生成账单标表*****************");
            return "2";//可以直接购买的
        }
    }


}
