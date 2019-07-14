package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.*;
import com.zking.zkingedu.common.service.*;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.System;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @email 2417902780@qq.com
 * @author:阿飘
 * @company 飘飘公司
 * @create 2019-06-23 9:32
 */
@Controller
@RequestMapping(value = "/user")
@Slf4j
public class PageController {
    @Autowired
    private SystemService systemService;

    @Autowired
    private TitleService titleService;
    @Autowired
    private AnswerService answerService;

    @Autowired
    private CategoryService categoryService;

    //充值记录表的注入
    @Resource
    private ChargeService chargeService;
    //账单表的记录
    @Resource
    private BillService billService;

    @Resource
    private UserService userService;

    @Autowired
    private Charge charge;
    @Autowired
    private Bill bill;

    @Resource
    private EmpDao empDao;

    @ResponseBody
    @RequestMapping("/redis")
    public List<Emp> getEmps() {
        //log.info("请求成功。。。");
        return empDao.getemps("%%");
    }

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "user/index";
    }

    /**
     * 开发者
     *
     * @return
     */
    @RequestMapping(value = "/developer")
    public String developer() {
        return "user/developer/index";
    }

    /**
     * qq登入成功页面
     *
     * @return
     */
    @RequestMapping(value = "/binding")
    public String binding() {
        return "user/binding";
    }

    /**
     * qq绑定已注册的账号
     *
     * @return
     */
    @RequestMapping(value = "/ybbinding")
    public String bindings() {
        return "user/ybbinding";
    }


    /**
     * 颜
     *
     * @return 视频播放页面跳转
     */
    @RequestMapping("videoshow")
    public String videoShow() {
        log.info("进来了");
        return "user/courses/show";
    }


    /**
     * 全部课程的路径
     */
    @RequestMapping(value = "/courses/index")
    public String courses() {
        return "user/courses/index";
    }


    /**
     * 个人中心的路径
     */
    @RequestMapping(value = "/userinfo/index")
    public String userinfo(HttpServletRequest request) {
        if(request.getParameter("total_amount")!=null){
            String total_amount = request.getParameter("total_amount");
            //System.err.println("回调之后的金额："+total_amount);
            double Integral = Double.parseDouble(total_amount) * 10;
            //System.err.println("回调之后的积分："+Integral);

            String integrala = String.valueOf(Integral);
            String integral = integrala.substring(0,integrala.lastIndexOf("."));

            //System.out.println("截取后的积分："+integral);
            //        //log.info("获取金额和积分");
//        //log.info("开始增加充值记录表的数据");
            charge.setChargeUid(SessionUtil.getUserById());//用户id
            charge.setChargeMoney(Double.parseDouble(total_amount));//收入金额

            charge.setChargeIntegral(Integer.parseInt(integral));//充值积分
            //充值时间
            charge.setChargeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            charge.setChargeState(1);//状态
            int i = chargeService.addCharge(charge);//开始执行充值的方法
//        //log.info("结束增加充值记录表的数据");
            if (i > 0) {
                log.info("****充值记录表成功******");
                //修改用户的积分的方法
                int i1 = userService.updateIntegral(Integer.parseInt(integral), SessionUtil.getUserById());
                if (i1 > 0) {
                    log.info("*****修改用户积分成功******");
                } else {
                    log.info("****充值记录表失败******");
                }
            } else {
                log.info("****充值记录表失败******");
            }

//        //log.info("开始增加账单表的数据");
            bill.setBillUid(SessionUtil.getUserById());//用户id
            bill.setBillType(1);//充值状态
            bill.setBillIntegral(Integer.parseInt(integral));//充值积分
            //账单时间
            bill.setBillTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //账单内容
            bill.setBillContent("本次在本平台消费金额为" + Double.parseDouble(total_amount) + "元，" + "充值积分为" + Integral + ",充值完成。");
            bill.setBillState(1);//状态
            billService.addBill(bill);
            //log.info("结束增加账单表的数据");
        }
        return "user/userinfo/userinfo";
    }


    /**
     * 忘记密码页面
     */
    @RequestMapping(value = "/zhpasssword")
    public String wjpassword() {
        System.out.println("进来了");
        return "user/zhpassword";
    }


    /**
     * 跳转值学习路径页面
     * yan
     *
     * @return
     */
    @RequestMapping(value = "/paths/index")
    public String pagesIndex(Model model){
        model.addAttribute("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
        return "user/paths/index";
    }




    @RequestMapping(value = "/videos")
    public String pageVideo() {
        return "admin/course/video/videoUpload";
    }


    /**
     * 前台搜索  可搜索课程，论坛帖子
     *
     * @param type       搜索类型  course课程，post论坛
     * @param SearchName 搜索的内容
     * @return
     */
    @RequestMapping("/PageSearch")
    public ModelAndView Search(String type, String SearchName, ModelAndView mv) {

        if ("course".equals(type)) {//搜索课程
            mv.addObject("content", SearchName);
            mv.setViewName("/user/courses/index");
            //mv.addObject("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
            return mv;//跳转至课程搜索页面
        }
        else  {
            mv.addObject("leibie",SearchName);
           // mv.addObject("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
            mv.setViewName("/user/questions/index");
            return mv;//搜索论坛帖子
        }
    }

    /**
     * 前台考试界面
     */


//    /**
//     * 按条件获取50题
//     * @param title
//     * @return
//     */
//    @RequestMapping("/gettitles")
//    public String page13(Title title, Model model,HttpSession session){
//        List<Title> gettitles = titleService.gettitles(title);//获取所有的题目加答案
//        Category getcat = categoryService.getcat(title.getTitleCid());//按照ID获取类别表的对象
//        model.addAttribute("getCategoryName",getcat.getCategoryName());//展示渲染
//        model.addAttribute("titles",gettitles);//数据渲染
//        session.setAttribute("titles",gettitles);//阅卷需要
//        model.addAttribute("size",gettitles.size());//判空
//        return "admin/jdy/grid";
//    }
    @RequestMapping(value = "/grids")
    public String grid(Title title, Model model, HttpSession session) {
        List<Title> gettitles = titleService.gettitles(title);//获取所有的题目加答案
        Category getcat = categoryService.getcat(title.getTitleCid());//按照ID获取类别表的对象
        model.addAttribute("getCategoryName", getcat.getCategoryName());//展示渲染
        model.addAttribute("titles", gettitles);//数据渲染
        session.setAttribute("titles", gettitles);//阅卷需要
        model.addAttribute("size", gettitles.size());//判空
        System.err.println(getcat + "哈哈哈哈哈");
        System.err.println(gettitles);
        System.err.println(gettitles.size());
        model.addAttribute("getCategoryName",getcat.getCategoryName());//展示渲染
        model.addAttribute("titles",gettitles);//数据渲染
        session.setAttribute("titles",gettitles);//阅卷需要
        model.addAttribute("size",gettitles.size());//判空
//        System.err.println(getcat+"哈哈哈哈哈");
//        System.err.println(gettitles);
//        System.err.println(gettitles.size());
        return "/user/grid";
    }

    /**
     * 前台题库界面
     */
    @RequestMapping(value = "/tiku")
    public String tiku(Model model, Integer categoryFID) {
        List<Category> category = categoryService.getCategory();//获取所有的题库类别
        List<Category> gettikuzitype = categoryService.gettikuzitype(categoryFID);
        //model.addAttribute("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
        model.addAttribute("gettikuzitype",gettikuzitype);//子
        model.addAttribute("category",category);//父
        return "/user/tiku";
    }


}
