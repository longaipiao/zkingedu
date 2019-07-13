package com.zking.zkingedu.common.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponsUtil {


    //响应 数据到前端
    public static void printout(HttpServletResponse response, Object str) throws Exception {

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;carset=utf-8");

        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        String s = gson.toJson(str);

        out.print(s);
        out.flush();
        out.close();
    }


}
