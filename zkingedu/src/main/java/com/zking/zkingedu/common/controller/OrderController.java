package com.zking.zkingedu.common.controller;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.*;
import com.zking.zkingedu.common.service.BillService;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.OrderService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IdGeneratorUtils;
import com.zking.zkingedu.common.utils.ResponseUtil;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.System;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String purchaseVideo(Model model,Integer courseID, Integer integral,HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1：首先查询出用户的积分够不够买整套视频，如果不够的话，就提示用户需要充值积分购买。
        //2：购买成功后，如果用户第二次进去观看，根据课程id和用户id去订单表中查询出此用户有购买的话，就直接放行去观看，
        //3:根据课程id和用户id查询订单表的

        User user = (User) request.getSession().getAttribute("user");
        System.err.println("session中的值是："+user);
        //判断用户是否登录
        if(user==null){
            ResponseUtil.write(response,3);
        }
        else{
            //        log.info("***************开始查询这个课程需要多少积分*******************");
            Integer courseInte = courseService.findCourseInte(courseID);
            System.err.println("课程的是否需要积分：" + courseInte);


//        log.info("***************开始查询这个用户剩下多少积分*******************");
            int userintegrsl = userService.findIntegrsl(SessionUtil.getUserById());//用户id
            System.err.println("用户剩下积分为：" + userintegrsl);


            if (userintegrsl < courseInte) {
                return "1";//积分不够的，需要去充值的
            } else  {
//            log.info("*************开始购买整套视频的方法***********");
                order.setOrderID(idGeneratorUtils.nextId());//生成一个唯一的订单的id
                order.setOrderUid(SessionUtil.getUserById());//获取session中用户的id
                order.setOrderSid(courseID);//前台传过来的课程id
                order.setOrderCid(0);//购买整套视频章节id默认为0
                order.setOrderIntegral(integral);//整套视频的积分
                //订单生成的日期
                order.setChargeTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
                order.setOrderState(1);//状态
                int gmshiporder = orderService.gmshiporder(order);
//            System.out.println("成功的：" + gmshiporder);



//            log.info("****************开始变动这个用户的积分*************"+courseInte);
                userService.updateUserIntegral(integral,SessionUtil.getUserById());
//            log.info("****************结束变动这个用户的积分*************");


//            log.info("*****************结束购买整套视频的方法************");

//            log.info("********************开始生成账单表*********************");
                bill.setBillUid(SessionUtil.getUserById());//获取session中用户的id
                bill.setBillType(2);//类型为1的是：支付状态
                bill.setBillIntegral(integral);//支付的积分
                //账单生成的时间
                bill.setBillTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));

                //调用根据课程id查询的课程名称方法
                String courseName = courseService.findCourseName(courseID);
                //账单内容
                bill.setBillContent("您购买《" + courseName + "》,消费积分：" + integral);
                bill.setBillState(1);//状态
                billService.addBill(bill);
//            log.info("********************结束生成账单标表*****************");
                return "2";//可以直接购买的
            }
        }
        return null;
    }


    /**
     * 根据用户id查询自己的订单
     * 阿飘
     */
    @RequestMapping(value = "/orderUid")
    @ResponseBody
    public Map<String,Object> findorderUid(Integer page,Integer limit){
//        log.info("**************开始查询自己的订单的方法****************");
        Map<String,Object> maps = new HashMap<>();
        PageInfo<Map<String, Object>> orderUid = orderService.findOrderUid(SessionUtil.getUserById(), page, limit);
        maps.put("msg","success");
        maps.put("code",0);
        maps.put("count",orderUid.getTotal());
        maps.put("data",orderUid.getList());
        return maps;
    }


    /**
     * 购买单个章节的方法
     * 阿飘
     * @param sid 课程id
     * @param id  章节id
     * @param courseInte 单章视频的积分
     */
    @RequestMapping(value = "/gmdangeship")
    public void gmdgzhangjie(Model model,HttpServletRequest request, HttpServletResponse response, Integer sid, Integer id, Integer courseInte) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        System.err.println("session中的值是："+user);
        //判断用户是否登录
        if(user==null){
            ResponseUtil.write(response,4);
        }else{
            //用户的积分
            int userintegrsl = userService.findIntegrsl(SessionUtil.getUserById());
            System.err.println("用户积分是："+userintegrsl);
            //查询订单表中是否存在
            Integer s = orderService.finduidsidcid(SessionUtil.getUserById(), sid, id);
            System.err.println("是否存在："+s);
            //购买过的视频
            if(s==1){
                ResponseUtil.write(response,1);//已经购买过的视频，直接放行观看。
            }
            else{
                //判断用户积分是否足够买视频
                if(userintegrsl<courseInte){
                    ResponseUtil.write(response,2);//提示用户积分不足，快去充值吧。
                }else{
                    ResponseUtil.write(response,3);//购买成功
                }
            }
        }
    }

    //购买视频的方法
    @RequestMapping(value = "/gmsp")
    public void gmship(HttpServletRequest request, HttpServletResponse response, Integer sid, Integer id, Integer courseInte) throws Exception {
//        log.info("**********开始购买章节视频的方法***********");
        order.setOrderID(idGeneratorUtils.nextId());//生成一个唯一的订单的id
        order.setOrderUid(SessionUtil.getUserById());//获取session中用户的id
        order.setOrderSid(sid);//前台传过来的课程id
        order.setOrderCid(id);//购买视频的章节id
        order.setOrderIntegral(courseInte);//单章节视频的积分
        //订单生成的日期
        order.setChargeTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
        order.setOrderState(1);//状态
        int gmdgsection = orderService.gmdgsection(order);
//        System.out.println("成功的：" + gmdgsection);

//        log.info("****************开始变动这个用户的积分*************"+courseInte);
        userService.updateUserIntegral(courseInte,SessionUtil.getUserById());
//        log.info("****************结束变动这个用户的积分*************");


//        log.info("********************开始生成账单表*********************");
        bill.setBillUid(SessionUtil.getUserById());//获取session中用户的id
        bill.setBillType(2);//类型为1的是：支付状态
        bill.setBillIntegral(courseInte);//支付的积分
        //账单生成的时间
        bill.setBillTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));

        //调用根据课程id查询的课程名称方法
        String courseName = courseService.findCourseName(sid);
        //账单内容
        bill.setBillContent("您购买《" + courseName + "》,消费积分：" + courseInte);
        bill.setBillState(1);//状态
        billService.addBill(bill);
        ResponseUtil.write(response,4);//购买成功
//        log.info("********************结束生成账单标表*****************");
    }


    /**
     * 后台的订单查询记录
     * @param userName 用户名称
     * @param orderId 订单id
     * @param page 页面
     * @param limit 每页多少条
     * @return
     */
    @RequestMapping(value = "/findOrder")
    @ResponseBody
    public Map<String,Object> findorder(String userName, String orderId, Integer page, Integer limit){
        PageInfo<Map<String, Object>> orders = orderService.findOrder(userName, orderId, page, limit);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","success");
        maps.put("code",0);
        maps.put("count",orders.getTotal());
        maps.put("data",orders.getList());
        return maps;
    }


    /**
     * 根据用户id查询自己的资源
     * 阿飘
     */
    @RequestMapping(value = "/findziyuanuid")
    @ResponseBody
    public Map<String,Object> findziyuanUid(Integer page,Integer limit){
//        log.info("**********开始查询自己的资源**************");
        PageInfo<Map<String, Object>> ziyuanuids = orderService.findziyuanUid(SessionUtil.getUserById(), page, limit);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","success");
        maps.put("code",0);
        maps.put("count",ziyuanuids.getTotal());
        maps.put("data",ziyuanuids.getList());
        return maps;
    }


    /**
     * 平台收入的图形报表功能
     */
    @RequestMapping(value = "/findbbiao")
    @ResponseBody
    public Map<String,Object> findbbiao(){

        //订单收入的总金额
        Integer orderSum = orderService.orderSum();
        //System.err.println("orderSum:"+orderSum);
        //用户充值的积分总数
        Integer billIntegraltype1 = billService.billIntegraltype1();
        //System.err.println("billIntegraltype1:"+billIntegraltype1);
        //用户消费的积分总数
        Integer billIntegraltype0 = billService.billIntegraltype0();
        //System.err.println("billIntegraltype0:"+billIntegraltype0);

        Map<String,Object> maps = new HashMap<>();
        maps.put("orderSum",orderSum);
        maps.put("billIntegraltype1",billIntegraltype1);
        maps.put("billIntegraltype0",billIntegraltype0);
        return maps;
    }






}
