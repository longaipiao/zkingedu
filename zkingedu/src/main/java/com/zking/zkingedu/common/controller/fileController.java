package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.utils.ResultUtil;
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

/**
 * 文件上传
 * 颜
 */
@Controller
@RequestMapping("/file")
public class fileController {


    @RequestMapping(value = "/imgupload", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public ResultUtil uploadFile(MultipartFile file, HttpServletRequest req) {
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
                File f=new File("F:\\zkingedu\\zkingedu\\target\\classes\\static\\imgs");
                if(!f.exists()){
                    f.mkdirs();
                }
                file.transferTo(new File(f, img));

                System.out.println("img是图片是："+img);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, String> map = new HashMap<>();
            map.put("src", "http://localhost:8080/imgs/"+img);
            return ResultUtil.ok(map);
        } else {
            return ResultUtil.error("文件格式不支持,请重新选择！");
        }
    }


}
