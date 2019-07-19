package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 后台界面url
 */
@Controller
@Slf4j
public class UrlController {

    @Autowired
    private CategoryService categoryService;



    /**
     * 后台跳到用户列表
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/admin/user")
    public String user(){
        return "admin/html/member-user";
    }

    @RequestMapping(value = "/admin")
    public String alogin(){
        return "admin/login";
    }

    @RequestMapping(value = "/admin/index")
    public String aindex(){
        return "admin/index";
    }


    /**
     * 后台广告
     */
    @RequestMapping(value = "/admin/advertising")
    public String advertising(){
        return "admin/html/advertising-list";
    }
    /**
     * 后台增加广告
     */
    @RequestMapping(value = "/admin/addadvertising")
    public String advertisings(){
        return "admin/html/admin-addadvertising";
    }

    /**
     * 后台开发者工具跳转路径
     */
    @RequestMapping(value = "/amdin/tool")
    @RequiresAuthentication
    public String toolhref(){
        return "admin/html/integral-list";
    }


    /**
     * 后台增加开发者工具跳转路径
     */
    @RequestMapping(value = "/addtool1")
    @RequiresAuthentication
    public String addtool(){
        System.err.println("dsadasdasdsa");
        return "admin/html/admin-addtool";
    }


    /**
     * 后台题库管理界面
     */
    @RequestMapping(value = "/admin/category")
    public String tk(){
        return "admin/html/admin-category";
    }


    /**
     * 后台的订单列表跳转路径
     */
    @RequestMapping(value = "/amdin/orderList")
    public String orderlist(){
        return "admin/html/cate";
    }
    /**
     * 后台的题库跳转路径
     */
    @RequestMapping(value = "/admin/title")
    public String title(Model m){
        List<Map<String ,Object>> map=new ArrayList<>();
        List<Category> all = categoryService.getAll();
        Map<String,Object> maps=null;
        for (Category category : all) {
            //打印父类别
            maps= new LinkedHashMap<>();
            //把数据加到maps集合里面
            maps.put("categoryID",category.getCategoryID());
            maps.put("categoryName",category.getCategoryName());
            maps.put("categoryFid",category.getCategoryFid());
            maps.put("categoryTime",category.getCategoryTime());
            maps.put("categoryEid",category.getCategoryEid());
            maps.put("categoryRank",category.getCategoryRank());
            maps.put("categoryState",category.getCategoryState());
            //打印子级
            List<Category> allfid = categoryService.getAllfid(category.getCategoryID());
            List<Map<String ,Object>> treelist=new ArrayList<>();
            Map<String,Object> Treemap=null;
            for (Category category1 : allfid) {
                Treemap=new LinkedHashMap<>();
                Treemap.put("categoryID",category1.getCategoryID());
                Treemap.put("categoryName",category1.getCategoryName());
                Treemap.put("categoryFid",category1.getCategoryFid());
                Treemap.put("categoryTime",category1.getCategoryTime());
                Treemap.put("categoryEid",category1.getCategoryEid());
                Treemap.put("categoryRank",category1.getCategoryRank());
                Treemap.put("categoryState",category1.getCategoryState());
                treelist.add(Treemap);
            }
            maps.put("treelist",treelist);
            map.add(maps);
            m.addAttribute("ps",map);
            System.out.println(map);
        }
        return "admin/html/admin-title";
    }


    /**
     * 后台的账单列表的跳转路径
     */
    @RequestMapping(value = "/orderzhangdan")
    public String orderzhangdan(){
        return "admin/html/member-list";
    }
    /**
     * 后台的账单列表的跳转路径
     */
    @RequestMapping(value = "/ordershouru")
    public String ordersshouru(){
        return "admin/html/member-lists";
    }


    /**
     * 后台的日志列表的跳转路径
     */
    @RequestMapping(value = "/loglist")
    public String Loglist(){
        return "admin/html/log-list";
    }


}
