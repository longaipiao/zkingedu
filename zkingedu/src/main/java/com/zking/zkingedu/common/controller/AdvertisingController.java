package com.zking.zkingedu.common.controller;

import com.github.pagehelper.PageInfo;
import com.zking.zkingedu.common.model.Advertising;
import com.zking.zkingedu.common.service.AdvertisingService;
import com.zking.zkingedu.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 广告Config
 */
@Controller
public class AdvertisingController {

    @Autowired
    private AdvertisingService advertisingService;

    @Autowired
    private Advertising advertising;

    /**
     * 查询所有的广告信息
     * @return
     */
    @RequestMapping(value = "/findadvertising")
    @ResponseBody
    public Map<String,Object> findUser(Advertising advertising,Integer page, Integer limit, HttpServletRequest request){
        HttpSession session = request.getSession();
        PageInfo<Advertising> users = advertisingService.getAll(advertising,page,limit);
        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","");
        maps.put("code",0);
        maps.put("count",users.getTotal());
        maps.put("data",users.getList());
        session.setAttribute("gg",users.getTotal());
        return maps;
    }
    /**
     * 增加广告的方法
     */
    @RequestMapping(value = "/user/addadvertising")
    public void addadvertising(Advertising advertising, HttpServletResponse response) throws Exception {
        advertising.setAdvertisingTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
        //判断如果已经上架了五条广告改状态为0未上架
        advertising.setAdvertisingState(1);
        int t = advertisingService.addAdvertsing(advertising);
        ResponseUtil.write(response,t);
    }

    /**
     * 修改广告的上架状态
     */
    @RequestMapping(value = "/updateStatesj")
    @ResponseBody
    public String updatesj(Integer aid){
        Integer integer = advertisingService.updatesjState(aid);
        if(integer>0){
            return "1";
        }
        return "2";
    }
    /**
     * 修改广告的下架状态
     */
    @RequestMapping(value = "/updateStatexj")
    @ResponseBody
    public String updatexj(Integer aid){
        Integer integer = advertisingService.updatexjState(aid);
        if(integer>0){
            return "1";
        }
        return "2";
    }
    /**
     * 查询所有上架的广告不能超过五条
     */
    @RequestMapping(value = "/getsjzt")
    @ResponseBody
    public String getsjzt(){
        Integer getsjzt = advertisingService.getsjzt();
        if(getsjzt>=5){
            return "1";
        }
        return "2";
    }


    /**
     * 修改广告的方法
     */
    @RequestMapping(value = "/updateadvertising")
    public void updateTool(Advertising advertising,HttpServletResponse response) throws Exception {
        Integer advertsing = advertisingService.updateadvertsing(advertising);
        ResponseUtil.write(response,advertsing);
    }

    /**
     * 根据广告id删除广告
     */
    @RequestMapping(value = "/deleteadvertising")
    @ResponseBody
    public String deleteadvertising(Integer aid){
        Integer deleteadvertising = advertisingService.deleteadvertising(aid);
        if(deleteadvertising>0){
            return "1";
        }
        return "2";
    }

}
