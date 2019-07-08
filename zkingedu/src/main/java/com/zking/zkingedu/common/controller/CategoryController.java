package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private  Category category;

    @RequestMapping(value = "/category")
    @ResponseBody
    public List<Map<String,Object>> getAll(){
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
            System.out.println(map);
        }

        return map;
    }


    /**
     * 增加体系
     */
    @RequestMapping(value = "/addCategory")
    @ResponseBody
    public String add(String categoryFid, String categoryName, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.getAttribute("emp");
        //获取后台用户的session
        category.setCategoryEid(1);
        category.setCategoryTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        category.setCategoryName(categoryName);
        category.setCategoryFid(Integer.parseInt(categoryFid));
        category.setCategoryState(0);
        Integer integer = categoryService.addCategory(category);
        if(integer>0){
            return "1";
        }
        return "2";
    }
    /**
     * 修改体系停用状态
     */
    @RequestMapping(value = "/ty")
    @ResponseBody
    public  String ty(String categoryID){
        System.out.println(categoryID);
        Integer updatety = categoryService.updatety(Integer.parseInt(categoryID));
        if(updatety>0){
            return "1";
        }
        return "2";
    }
    /**
     * 修改体系开启状态
     */
    @RequestMapping(value = "/kq")
    @ResponseBody
    public  String kq(String categoryID){
        Integer updatety = categoryService.updatekq(Integer.parseInt(categoryID));
        if(updatety>0){
            return "1";
        }
        return "2";
    }
    /**
     * 删除所有体系
     */
    @RequestMapping(value = "/sc")
    @ResponseBody
    public  String delete(String aid,String fid){
        System.out.println(aid);
        Integer updatety = categoryService.delete(Integer.parseInt(aid),Integer.parseInt(fid));
        if(updatety>0){
            return "1";
        }
        return "2";
    }
    /**
     * 修改体系名字
     */
    @RequestMapping(value = "/xg")
    @ResponseBody
    public  String updateName(String aid,String name,String fid){
        Integer updatetys = categoryService.updateName(Integer.parseInt(aid),name,Integer.parseInt(fid));
        if(updatetys>0){
            return "1";
        }
        return "2";
    }
    /**
     * 修改体系下面的名字
     *//*
    @RequestMapping(value = "/xgs")
    @ResponseBody
    public  String updateNames(String aid,String name,String){
        Integer updatety = categoryService.updateName(Integer.parseInt(aid),name);
        if(updatety>0){
            return "1";
        }
        return "2";
    }*/


}
