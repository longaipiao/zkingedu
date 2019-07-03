package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSONObject;
//import com.qq.connect.QQConnectException;
//import com.qq.connect.api.OpenID;
//import com.qq.connect.javabeans.AccessToken;
//import com.qq.connect.javabeans.qzone.UserInfoBean;
//import com.qq.connect.oauth.Oauth;
//import com.zhenzi.sms.ZhenziSmsClient;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.service.impl.UserServiceImpl;
import com.zking.zkingedu.common.utils.IpAddress;
import com.zking.zkingedu.common.utils.transferImgForRoundImgage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private User user;

    // 短信平台相关参数
   /* private String apiUrl = "https://sms_developer.zhenzikj.com";
    private String appId = "101158";
    private String appSecret ="NmNhYzRmMmUtNGQ0NS00MmJlLWFjNDYtNWYyOGQyMTYyYWRl";*/
    @Autowired
    private UserService userService;

    //用户注册
    @ResponseBody
    @RequestMapping(value = "/zc")
    public String zc(String phone,String password)throws Exception{
        //手机号
        user.setUserPhone(phone);
        //注册时间
        user.setUserPassword(password);
        //注册时间
        user.setUserRegTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //状态
        user.setUserState(0);
        //积分
        user.setUserIntegrsl(0);
        //昵称
        user.setUserName(getRandomJianHan(3));
        //图片
        user.setUserImg("share-reports.png");
        //错误次数
        user.setUserCwcs(0);
        Integer n = userService.add(user);
        if(n>0){
            return "1";
        }
        return "2";




    }

    //判断手机号不能重复
    @ResponseBody
    @RequestMapping(value = "/cf")
    public String cf(String phone){
        System.out.println(phone);
        String pdcf = userService.pdcf(phone);
        if(pdcf==null){
            //没有重复
            return "1";
        }
        //重复了
        return "2";
    }



    /**
     * 测试
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cs")
    public String cs(){
        User user = userService.getUser(42);

        System.out.println(user.getUserIP()+"ip地址");
        return null;
    }

    /**
     * 手机号修改密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePhonepassword")
    public String updatePhonepassword(String phone,HttpServletRequest request,String password2){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer n = userService.updatePhonePassword(phone, password2);

        if(n>0){
            session.setAttribute("user",null);
            return "1";
        }
        return null;

    }

    @ResponseBody
    @RequestMapping(value = "/Hqyzm")
    public String Hqyzm(HttpServletRequest request, HttpServletResponse response,String phone) throws Exception {
        JSONObject json = null;
        //生成6位验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        //发送短信
       /* ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        String result = client.send(phone, "您正在注册巨好贷用户，验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次!");*/

        //将验证码存到session中,同时存入创建时间
        //以json存放，这里使用的是阿里的fastjson
       /* json = new JSONObject();
        json.put("mobile", mobile);
        json.put("verifyCode", verifyCode);
        json.put("createTime", System.currentTimeMillis());*/
        // 将认证码存入SESSION
        System.out.println(verifyCode);
        return verifyCode;

    }

    /**
     * 修改头像
     * @param request
     * @param response
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateupload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response,String upload) throws Exception {
        System.out.println(upload);
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        Integer n = userService.updateupload(user.getUserID(), upload);
        if(n>0){
            //在根据手机号查一遍
            User userlogin = userService.userlogin(user);
            session.setAttribute("user",null);
            session.setAttribute("user",userlogin);
            return "1";
        }
        return "2";

    }


    /**
     * qq接口
     * @param request
     * @param response
     * @return
     * @throws QQConnectException
     */
    /*@ResponseBody
    @RequestMapping(value = "/LoginCallback2")
    public String  LoginCallback(HttpServletRequest request, HttpServletResponse response) throws QQConnectException {
        System.out.println("进来了");
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            System.out.println("Token:"+accessTokenObj.getAccessToken());
            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
            } else {
                //1.根据code获取access_token
                String token = accessTokenObj.getAccessToken();
                //2.根据access_token获得openId
                OpenID openIDobj = new OpenID(token);
                String userOpenID = openIDobj.getUserOpenID();
                //3.去数据库查找有没有openId如果有，登陆过，没有则是新用户
                com.qq.connect.api.qzone.UserInfo qzoneUserInfo = new com.qq.connect.api.qzone.UserInfo(token, userOpenID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                System.out.println("openid:"+userOpenID+"————用户昵称："+userInfoBean.getNickname());
                //4.根据openId和access_Token获取用户信息
                System.out.println(userOpenID);

            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "user/index";
    }*/

    /**
     * qq登入绑定新用户
     */
    @RequestMapping(value = "/qqlogin")
    @ResponseBody
    public String qqlogin(String phone,String upwd,HttpServletRequest request){

        HttpSession session = request.getSession();
        //qqid
        String userOpenID=(String) session.getAttribute("openid");
        //qq头像
        String Avatar=(String) session.getAttribute("Avatar");
        //qq名字
        String nickName=(String) session.getAttribute("nickName");
        //qqid
        user.setUserOpenID(userOpenID);
        //注册时间
        user.setUserRegTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //状态
        user.setUserState(0);
        //积分
        user.setUserIntegrsl(0);
        //昵称
        user.setUserName(nickName);
        //图片
        // System.out.println(new transferImgForRoundImgage().transferImgForRoundImgage(userInfoBean.getAvatar().getAvatarURL100()));

        user.setUserImg(Avatar);
        //错误次数
        user.setUserCwcs(0);
        //ip
        user.setUserIP(IpAddress.getIpAddr(request));
        //增加这个openid
        Integer add = userService.add(user);
        if(add>0){
            Integer integer = userService.updateOpenid(userOpenID,phone,upwd);//修改成功后在根据openid查询用户数据
            if(integer!=null){
                System.out.println(integer);
                User qquser = userService.getopenid(userOpenID);
                session.setAttribute("user",qquser);
                return "1";
            }
        }

        return "2";
    }
    /**
     * qq登入绑定老用户
     */
    @RequestMapping(value = "/yjqqlogin")
    @ResponseBody
    public String yjqqlogin(String phone,HttpServletRequest request){
        HttpSession session = request.getSession();
        String openid=(String) session.getAttribute("openid");
        Integer integer = userService.updateOpenids(phone,openid,IpAddress.getIpAddr(request));//根据用户手机号增加openid
        if(integer!=null){
            System.out.println(integer);
            User qquser = userService.getopenid(openid);
            session.setAttribute("user",qquser);
            return "1";
        }
        return "2";
    }

    //用户登入
    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(String userPhone,String upwd,HttpServletRequest request,HttpServletResponse response)throws Exception{


        HttpSession session = request.getSession();
        //手机号
        user.setUserPhone(userPhone);
        //密码
        user.setUserPassword(upwd);
        User userlogin = userService.userlogin(user);
        if(userlogin!=null){
            Integer integer = userService.updateipaddrlastTime(userlogin.getUserID(), IpAddress.getIpAddr(request), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            if(integer>0){
                session.setAttribute("user",userlogin);
                return "1";
                }
           }
        //密码错误
         if(userlogin==null){
             //修改错误次数加1
             userService.updateCwcs(userPhone);
             //查询错误次数
             Integer cwcs = userService.getCwcs(userPhone);
             if(cwcs>5){
                //修改用户的状态然后启动定时任务


             }
         }
        return "";
    }
    /**
     * 注销
     */
    @RequestMapping(value = "/zhuxiao")
    public String zhuxiao(HttpServletRequest request){
        HttpSession session = request.getSession();
        //清空用户的session
        session.setAttribute("user",null);
        session.setAttribute("b",null);
        session.setAttribute("a",null);
        session.setAttribute("nickName",null);
        session.setAttribute("Avatar",null);
        session.setAttribute("openid",null);

        return "user/index";
    }

    /**
     * 用户点击qq联合登陆
     * @return
     */
    @RequestMapping("/qqLogin")
    /*public String requestQQLogin(HttpServletRequest request) throws QQConnectException {
        //自动组装qq登陆连接，重定向到qq登陆页面
        String authorizeURL = new Oauth().getAuthorizeURL(request);
        System.out.println("联合登陆请求地址:"+authorizeURL);
        return "redirect:"+authorizeURL;
    }*/


    //随机昵称
    public static String getRandomJianHan(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

}
