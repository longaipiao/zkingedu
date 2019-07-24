package com.zking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Controller
public class UploadpictureApplication {


    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(){
        return "index.jsp";
    }


    /**
     *
     * @param file 文件名称
     * @param req
     * @return 严
     */
    @RequestMapping(value = "/imgupload1", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public ResultUtil uploadFile(MultipartFile file, HttpServletRequest req) {
        String contextPath = req.getSession().getServletContext().getRealPath("/imgs");
        if (file == null) {
            return ResultUtil.error("文件不能为空！");
        }
        String fileSub = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
                .toLowerCase();
        if (".jpg".equals(fileSub) || ".jpeg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)) {
            Random d = new Random();
            String img = UUID.randomUUID().toString().replace("-", "") + d.nextInt(10000) + "" + fileSub;
            try {
                // uploadFile.transferTo(new
                // File(req.getServletContext().getRealPath("WEB-INF/upload"),img));
                File f = new File(contextPath);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.transferTo(new File(f, img));

                System.out.println("img是图片是：" + img);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, String> map = new HashMap<>();
            String requestURI = req.getRequestURI();
            String path = req.getContextPath();
            String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
            map.put("src", basePath+"/imgs/"+img);
            return ResultUtil.ok(map);
        } else {
            return ResultUtil.error("文件格式不支持,请重新选择！");
        }
    }

    /**
     * @param file 图片文件名
     * @param req
     * @return   徐
     */
    @RequestMapping(value = "/imgupload2", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public ResultUtil uploadFile1(MultipartFile file, HttpServletRequest req) {
        String contextPath = req.getSession().getServletContext().getRealPath("/imgs");
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
                File f=new File(contextPath);
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
            String requestURI = req.getRequestURI();
            String path = req.getContextPath();
            String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+requestURI;
            map.put("src", basePath+"/imgs/"+img);
            return ResultUtil.ok(map);
        } else {
            return ResultUtil.error("文件格式不支持,请重新选择！");
        }
    }



}
