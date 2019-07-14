package com.zking.zkingedu.common.Aop;

import com.zking.zkingedu.common.model.Advertising;
import com.zking.zkingedu.common.service.AdvertisingService;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
public class job {
    @Autowired
    private AdvertisingService advertisingService;

    @Autowired
    private Advertising advertising;

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

}
