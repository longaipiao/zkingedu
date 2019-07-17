package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSON;
import com.zking.zkingedu.common.model.Answer;
import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.model.Title;
import com.zking.zkingedu.common.service.AnswerService;
import com.zking.zkingedu.common.service.CategoryService;
import com.zking.zkingedu.common.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 题目Controller
 */
@Controller
public class TitleController {

    @Autowired
    private TitleService titleService;
    @Autowired
    private AnswerService answerService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 数据转换
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getall")
    public List<Map<String, Object>> formatJson(Title title2, String sid) {
        if (sid != null && sid.length() != 0) {
            title2.setTitleCid(Integer.parseInt(sid));
        }
        if (sid.equals("0")) {
            title2.setTitleCid(null);
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        List<Title> all = titleService.getAll(title2);//所有的题目
        for (Title title : all) {//题目对于的答案
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", title.getTitleID());
            map.put("pid", 0);
            map.put("title", title.getTitleContent());
            map.put("state", title.getTitleState());
            map.put("abcd", title.getTitleContent());
            maps.add(map);
            List<Answer> all1 = answerService.getAll(title.getTitleID());
            for (Answer answer : all1) {
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("id", answer.getAnswerID());
                map2.put("pid", answer.getAnswerTid());
                map2.put("title", answer.getAnswerContent());
                map2.put("state", answer.getAnswerState());
                map2.put("abcd", answer.getAnswerAbcd());
                maps.add(map2);
            }
        }
        return maps;
    }

    /**
     * 修改绑定数据
     */
    @RequestMapping(value = "/updateall")
    @ResponseBody
    public List<Map<String, Object>> formatJsons(String tid) {
        List<Map<String, Object>> map = new ArrayList<>();
        List<Title> all = titleService.getAlls(Integer.parseInt(tid));
        Map<String, Object> maps = null;
        for (Title title : all) {
            //打印父类别
            maps = new LinkedHashMap<>();
            //把数据加到maps集合里面
            maps.put("titleID", title.getTitleID());
            maps.put("titleCid", title.getTitleCid());
            maps.put("titleContent", title.getTitleContent());
            maps.put("titleDescribe", title.getTitleDescribe());
            //打印子级
            List<Answer> allfid = answerService.getAll(title.getTitleID());
            List<Map<String, Object>> treelist = new ArrayList<>();
            Map<String, Object> Treemap = null;
            for (Answer answer : allfid) {
                Treemap = new LinkedHashMap<>();
                Treemap.put("answerAbcd", answer.getAnswerAbcd());
                Treemap.put("answerContent", answer.getAnswerContent());
                Treemap.put("answerState", answer.getAnswerState());
                treelist.add(Treemap);
            }
            maps.put("treelist", treelist);
            map.add(maps);
        }

        return map;
    }

    /**
     * 增加题目
     *
     * @return
     */
    @RequestMapping(value = "/addtm")
    @ResponseBody
    public String addTm(Title title, String a, String b, String c, String d, String tm, String s, String titleDescribe, String titleContent) {
        Answer a1 = new Answer();//答案a的对象
        Answer b2 = new Answer();//答案b的对象
        Answer c2 = new Answer();//答案d的对象
        Answer d3 = new Answer();//答案d的对象
        //增加题目
        title.setTitleCid(Integer.parseInt(tm));
        title.setTitleContent(titleContent);
        title.setTitleDescribe(titleDescribe);
        title.setTitleTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        title.setTitleState(0);
        Integer integer = titleService.addTitle(title);
        List<Answer> ls = new ArrayList<>();

        if (integer > 0) {//增加答案
            System.out.println(title.getTitleID());
            a1.setAnswerAbcd("A");
            a1.setAnswerContent(a);
            a1.setAnswerState(0);
            a1.setAnswerTid(title.getTitleID());
            b2.setAnswerAbcd("B");
            b2.setAnswerContent(b);
            b2.setAnswerState(0);
            b2.setAnswerTid(title.getTitleID());

            c2.setAnswerContent(c);
            c2.setAnswerTid(title.getTitleID());
            c2.setAnswerAbcd("C");
            c2.setAnswerState(0);

            d3.setAnswerAbcd("D");
            d3.setAnswerContent(d);
            d3.setAnswerTid(title.getTitleID());
            d3.setAnswerState(0);
            if (s.equals("A")) {
                a1.setAnswerState(1);
            }
            if (s.equals("B")) {
                b2.setAnswerState(1);
            }
            if (s.equals("C")) {
                c2.setAnswerState(1);
            }
            if (s.equals("D")) {
                d3.setAnswerState(1);
            }
            ls.add(a1);
            ls.add(b2);
            ls.add(c2);
            ls.add(d3);
            Integer integer1 = answerService.addAnswer(ls);
            if (integer1 > 0) {
                return "1";
            }
        }
        return "";
    }

    /**
     * 开启
     */
    @RequestMapping(value = "/kqtm")
    @ResponseBody
    public String kq(String tid) {
        Integer updatekq = titleService.updatekq(Integer.parseInt(tid));
        if (updatekq > 0) {
            return "1";
        }
        return "2";
    }

    /**
     * 关闭
     */
    @RequestMapping(value = "/gbtm")
    @ResponseBody
    public String gb(String tid) {
        Integer updategb = titleService.updategb(Integer.parseInt(tid));
        if (updategb > 0) {
            return "1";
        }
        return "2";
    }

    /**
     * 删除题目和答案
     */
    @RequestMapping(value = "/sctm")
    @ResponseBody
    public String deletetm(String tid, HttpServletResponse response) {
        answerService.deletedaan(Integer.parseInt(tid));
        Integer deletetim = titleService.deletetim(Integer.parseInt(tid));
        if (deletetim > 0) {
            return "1";
        }
        return "2";
    }

    /**
     * 修改题目和答案
     */
    @RequestMapping(value = "/updatetm")
    @ResponseBody
    public String updatetm(Title title, String a, String b, String c, String d, String s, String titleDescribe, String titleContent, String tid) {
        //增加题目
        title.setTitleDescribe(titleDescribe);
        title.setTitleContent(titleContent);
        title.setTitleID(Integer.parseInt(tid));
        titleService.updatetim(title);
        Answer a1 = new Answer();//答案a的对象
        Answer b2 = new Answer();//答案b的对象
        Answer c2 = new Answer();//答案d的对象
        Answer d3 = new Answer();//答案d的对象
        a1.setAnswerContent(a);
        b2.setAnswerContent(b);
        c2.setAnswerContent(c);
        d3.setAnswerContent(d);
        a1.setAnswerTid(Integer.parseInt(tid));
        a1.setAnswerAbcd("A");
        a1.setAnswerState(0);
        b2.setAnswerTid(Integer.parseInt(tid));
        b2.setAnswerAbcd("B");
        b2.setAnswerState(0);
        c2.setAnswerTid(Integer.parseInt(tid));
        c2.setAnswerAbcd("C");
        c2.setAnswerState(0);
        d3.setAnswerTid(Integer.parseInt(tid));
        d3.setAnswerAbcd("D");
        d3.setAnswerState(0);

        if (s.equals("As")) {
            a1.setAnswerState(1);
        }
        if (s.equals("Bs")) {
            b2.setAnswerState(1);
        }
        if (s.equals("Cs")) {
            c2.setAnswerState(1);
        }
        if (s.equals("Ds")) {
            d3.setAnswerState(1);
        }
        //增加答案

        Integer updatedanana = answerService.uppdatedaana(a1);//a的方法
        Integer updatedananb = answerService.uppdatedaanb(b2);//b的方法
        Integer updatedananc = answerService.uppdatedaanc(c2);//c的方法
        Integer updatedanand = answerService.uppdatedaand(d3);//d的方法
        if (updatedanana > 0 || updatedananb > 0 || updatedananc > 0 || updatedanand > 0) {
            return "1";
        }

        return "2";
    }


    /**
     * 阅卷
     *
     * @param str
     * @return
     */
    @RequestMapping("/pingfen")
    @ResponseBody
    public Map pingfen(String str, HttpSession session) {
        Map<String, Object> map = new HashMap();
        Map maps = (Map) JSON.parse(str);
        int score = 0;
        List<Title> titles = (List<Title>) session.getAttribute("titles");
        List<Integer> cuoti = new ArrayList<>();//实例化一个装错误答案题号的集合
        int count = titles.size() - maps.size();//未做题目
        for (int i = 0; i < titles.size(); ++i) {
            if (maps.get(titles.get(i).getTitleID() + "") != null) {//答案的json中存在
                for (Answer answer : titles.get(i).getAnswerss()) {//遍历该题目的答案
                    if (answer.getAnswerState() == 1) {//从4个答案中找出真确的答案
                        if (maps.get(titles.get(i).getTitleID() + "").equals(answer.getAnswerAbcd())) {//答案的json的值等于正确答案的话
                            score += 2;//分数加2
                        } else {
                            cuoti.add(answer.getAnswerTid());//错误的加入集合
                        }
                    }
                }
            }
        }
        StringBuffer ct = new StringBuffer();
        for (Integer integer : cuoti) {
            ct.append("第" + integer + "题  ");
        }
        map.put("score", score);
        map.put("cuoti", ct);
        map.put("weizuo", count);
        return map;
    }

}
