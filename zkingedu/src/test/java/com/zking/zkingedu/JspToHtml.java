package com.zking.zkingedu;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;

/**
 * Filename: JspToHtml.java <br>
 * Ttitle: jsp转换成html<br>
 * De.ion: 把动态网页转换成静态网页<br>
 * Copyright:        Copyright (c) 2002-2008 BocSoft,Inc.All Rights Reserved. <br>
 * Company:         BocSoft<br>
 * Author:            <a href="mailto:sgicer@163.com">阿汐</a> <br>
 * Date: 2006-6-19 <br>
 * Time: 16:41:09 <br>
 * Version: 2.0.0 <br>
 */
public class JspToHtml {

    private static String title ="标题测试";
    private static String context ="标题测试";
    private static String editer ="标题测试";

    /**
     * 根据本地模板生成静态页面
     * @param filePath    jsp路经
     * @param HtmlFile html路经
     * @return
     */
    public static boolean JspToHtmlFile(String filePath, String HtmlFile) {
        String str = "";
        long beginDate = (new Date()).getTime();
        try {
            String tempStr = "";
            FileInputStream is = new FileInputStream(filePath);//读取模块文件
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((tempStr = br.readLine()) != null)
                str = str + tempStr ;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {

            str = str.replaceAll("###title###",
                    title);
            str = str.replaceAll("###content###",
                    context);
            str = str.replaceAll("###author###",
                    editer);//替换掉模块中相应的地方

            File f = new File(HtmlFile);
            BufferedWriter o = new BufferedWriter(new FileWriter(f));
            o.write(str);
            o.close();
            System.out.println("共用时：" + ((new Date()).getTime() - beginDate) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据url生成静态页面
     *
     * @param u        动态文件路经 如：[url]http://www.163.com/x.jsp[/url]
     * @param path 文件存放路经如：x:\\abc\bbb.html
     * @return
     */
    public static boolean JspToHtmlByURL(String u, String path) {
        //从utl中读取html存为str
        String str = "";
        try {
            URL url = new URL(u);
            URLConnection uc = url.openConnection();
            InputStream is = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                str += br.readLine() + "\n";

            }
            is.close();
            //写入文件
            File f = new File(path);
            BufferedWriter o = new BufferedWriter(new FileWriter(f));
            o.write(str);
            o.close();
            str = "";
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据url生成静态页面
     *
     * @param url 动态文件路经 如：[url]http://www.163.com/x.jsp[/url]
     * @return d
     */
    public static StringBuffer getHtmlTextByURL(String url) {
        //从utl中读取html存为str
        StringBuffer sb = new StringBuffer();
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            InputStream is = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                sb.append(br.readLine() + "\n");
            }
            is.close();
            return sb;
        } catch (Exception e) {
            e.printStackTrace();
            return sb;
        }
    }

    /**
     * 测试main 函数
     *
     * @param arg
     */
    public static void main(String[] arg) {
        long begin = System.currentTimeMillis();
        //循环生成20个html文件
        for (int k = 0; k < 20; k++) {
            String url = "D:\\ai\\mb.html";//模板文件地址
            String savepath = "d:/ai/" + k + ".html";//生成文件地址
            JspToHtmlFile(url, savepath);
        }
        System.out.println("用时:" + (System.currentTimeMillis() - begin) + "ms");
    }
}

