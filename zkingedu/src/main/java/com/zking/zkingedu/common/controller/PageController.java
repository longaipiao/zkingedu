package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.SystemService;
import com.zking.zkingedu.common.model.Title;
import com.zking.zkingedu.common.service.AnswerService;
import com.zking.zkingedu.common.service.CategoryService;
import com.zking.zkingedu.common.service.TitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @Resource
    private EmpDao empDao;

    @ResponseBody
    @RequestMapping("/redis")
    public List<Emp> getEmps(){
        //log.info("请求成功。。。");
        return empDao.getemps("%%");
    }

    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    /**
     * 开发者
     * @return
     */
    @RequestMapping(value = "/developer")
    public String developer(){
        return "user/developer/index";
    }

    /**
     * qq登入成功页面
     * @return
     */
    @RequestMapping(value = "/binding")
    public String binding(){
        return "user/binding";
    }

    /**
     * qq绑定已注册的账号
     * @return
     */
    @RequestMapping(value = "/ybbinding")
    public String bindings(){
        return "user/ybbinding";
    }


    /**
     * 颜
     * @return
     * 视频播放页面跳转
     */
    @RequestMapping("videoshow")
    public String videoShow(){
        log.info("进来了");
        return "user/courses/show";
    }


    /**
     * 全部课程的路径
     */
    @RequestMapping(value = "/courses/index")
    public String courses(){
        return "user/courses/index";
    }


    /**
     * 个人中心的路径
     */
    @RequestMapping(value = "/userinfo/index")
    public String userinfo(){
        return "user/userinfo/userinfo";
    }


    /**
     * 忘记密码页面
     */
    @RequestMapping(value = "/zhpasssword")
    public String wjpassword(){
        System.out.println("进来了");
        return "user/zhpassword";
    }


    /**
     * 跳转值学习路径页面
     * yan
     * @return
     */
    @RequestMapping(value = "/paths/index")
    public String pagesIndex(Model model){
        model.addAttribute("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
        return "user/paths/index";
    }




    @RequestMapping(value = "/videos")
    public String pageVideo(){
        return "admin/course/video/videoUpload";
    }


    /**
     * 前台搜索  可搜索课程，论坛帖子
     * @param type  搜索类型  course课程，post论坛
     * @param SearchName  搜索的内容
     * @return
     */
    @RequestMapping("/PageSearch")
    public ModelAndView Search(String type, String SearchName, ModelAndView mv){

        if("course".equals(type)){//搜索课程
            mv.addObject("content",SearchName);
            mv.setViewName("/user/courses/index");
            mv.addObject("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
            return mv;//跳转至课程搜索页面
        }
        else  {
            mv.addObject("leibie",SearchName);
            mv.addObject("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
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
    public String grid(Title title, Model model, HttpSession session){
        List<Title> gettitles = titleService.gettitles(title);//获取所有的题目加答案
        Category getcat = categoryService.getcat(title.getTitleCid());//按照ID获取类别表的对象
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
    public String tiku(Model model,Integer categoryFID){
        List<Category> category = categoryService.getCategory();//获取所有的题库类别
        List<Category> gettikuzitype = categoryService.gettikuzitype(categoryFID);
        model.addAttribute("sysFive",systemService.getsystemsFive());//加载右侧最热体系数据
        model.addAttribute("gettikuzitype",gettikuzitype);//子
        model.addAttribute("category",category);//父
        return "/user/tiku";
    }




}
