package com.zking.zkingedu.common.Aop;

import com.zking.zkingedu.common.model.Advertising;
import com.zking.zkingedu.common.service.AdvertisingService;
import com.zking.zkingedu.common.service.LogService;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@Log4j
@Transactional
public class job {
    @Autowired
    private AdvertisingService advertisingService;

    @Autowired
    private Advertising advertising;

    @Autowired
    private LogService logService;

    /**
     * 首页
     * @param m
     * @param request
     * @return
     */
    @RequestMapping(value = "/")
    public String tz(Model m,HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        List<Advertising> alls = advertisingService.getAlls();
        m.addAttribute("ps",alls);
        return "user/index";
    }



    @RequestMapping(value = "/user/upload", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public ResultUtil uploadFile(MultipartFile file, HttpServletRequest req) {
        //获取服务器路径
        String contextPath = req.getSession().getServletContext().getRealPath("/");
        if (file == null) {

        }
        String fileSub = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
                .toLowerCase();
        if (".jpg".equals(fileSub) || ".jpeg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)) {
            Random d = new Random();
            String img = UUID.randomUUID().toString().replace("-", "") + d.nextInt(10000) + "" + fileSub;
            try {
                // uploadFile.transferTo(new
                // File(req.getServletContext().getRealPath("WEB-INF/upload"),img));
                File f=new File(contextPath+"/imgs");
                if(!f.exists()){
                    f.mkdirs();
                }
                file.transferTo(new File(f, img));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, String> map = new HashMap<>();
            map.put("src", "/imgs/"+img);
            return ResultUtil.ok(map);
        } else {
            return ResultUtil.error("文件格式不支持,请重新选择！");
        }
    }



    /**
     * 定时删除所有日志  每个月的每月的最后一天的11：59触发 0 59 23 L * ?
     */
    @Scheduled(cron = "0 59 23 * * ?")
    public void dshirw(){
        int lid = logService.deleLog();
        log.info("定时删除日志成功！！！"+lid);
    }
}
