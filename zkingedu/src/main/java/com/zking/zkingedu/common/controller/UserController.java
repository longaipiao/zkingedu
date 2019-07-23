package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.zhenzi.sms.ZhenziSmsClient;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Log;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.LogService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.utils.IpAddress;
import com.zking.zkingedu.common.utils.MailUtil;
import com.zking.zkingedu.common.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private User user;

    @Autowired
    private LogService logService;
    @Autowired
    private Log mylog;

    //获取系统当前时间
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String time=dateFormat.format(new Date());

    // 短信平台相关参数
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    private String appId = "101153";
    private String appSecret ="ZjkxNTMwOGEtMTQzMS00N2IxLWFmYjEtN2MwMGE3ZDAyZmU0";
    @Autowired
    private UserService userService;

    //用户注册
    @ResponseBody
    @RequestMapping(value = "/zc")
    public String zc(String phone,String password)throws Exception{
        //手机号
        user.setUserPhone(phone);
        //密码
        user.setUserPassword(password);
        //注册时间
        user.setUserRegTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //状态
        user.setUserState(0);
        //积分
        user.setUserIntegrsl(500);
        //昵称
        user.setUserName(getRandomJianHan(3));
        //图片
        user.setUserImg("/user/img/u=2245612340,853442449&fm=26&gp=0.png");
        //错误次数
        user.setUserCwcs(0);
        Integer n = userService.add(user);
        if(n>0){
            return "1";
        }
        return "2";
    }

    /**
     * 判断邮箱不能重复
     * @param
     * @return
     */
    @RequestMapping(value = "/Email")
    @ResponseBody
    public String cfEmail(HttpServletRequest request,String Email){
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        String s = userService.cfEamil(Email);
        if (s==null){
            //没有重复
            return "1";
        }
        return "2";
    }
    /**
     * 修改邮箱
     */
    @RequestMapping(value = "/updateEmail")
    @ResponseBody
    public String updateEmail(String newEmail,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        Integer integer = userService.updateEamil(user.getUserID(), newEmail);
        if(integer>0){
            User user1 = userService.getUser(user.getUserID());
            session.setAttribute("user",null);
            session.setAttribute("user",user1);
            return "1";
        }
        return "2";
    }
    /**
     * 邮箱接口
     * @param
     * @return
     */
    @RequestMapping(value = "/Emailjk")
    @ResponseBody
    public String Emailjk(HttpServletRequest request, HttpServletResponse response,String phone) {
        JSONObject json = null;
        //生成6位验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);

        try {
            MailUtil.configMail(phone,verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //发送短信
       /* ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        String result = client.send(phone, "您正在注册巨好贷用户，验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次!");*/


        // 将认证码存入SESSION
        return verifyCode;
    }

    /**
     * 查询所有的用户信息
     * @return
     */
    @RequestMapping(value = "/findUser")
    @ResponseBody
    public Map<String,Object> findUser(Integer page, Integer limit,HttpServletRequest request,String sid,String text){
        user.setUserPhone("");
        if(sid!=null){
            if(text!=""){
                if(sid.equals("0")){//查询名字
                    user.setUserName(text);

                }
                else if(sid.equals("1")){//查询手机号
                    user.setUserPhone(text);

                }
                else if(sid.equals("2")){//查询邮箱号
                    user.setUserEmail(text);
                }
            }
            else{
                user.setUserEmail("");
                user.setUserPhone("");
                user.setUserName("");
            }

        }
        HttpSession session = request.getSession();
        PageInfo<User> users = userService.getAll(user,page, limit);
        List<User> list = users.getList();
        for (User user1 : list) {

        }

        Map<String,Object> maps = new HashMap<>();
        maps.put("msg","");
        maps.put("code",0);
        maps.put("count",users.getTotal());
        maps.put("data",users.getList());
        session.setAttribute("zs",users.getTotal());
        return maps;
    }

    /**
     * 冻结
     * @param uid
     * @return
     */
    @RequestMapping(value = "/dj")
    @ResponseBody
    public String dj(Integer uid,HttpServletRequest request){
        Integer integer = userService.updateSpase(uid);
        if(integer>0){
            //放入日志
            Emp emp =(Emp) request.getSession().getAttribute("emp");
            mylog.setEmp(emp);
            mylog.setLogTime(time);
            StringBuilder stringBuilder = new StringBuilder(emp.getEmpName()+"冻结了一个用户，用户id为："+uid);
            mylog.setLogDetails(stringBuilder.toString());
            logService.addLog(mylog);
            //放入日志结束
            return "1";
        }
        return "2";
    }
    /**
     * 解封
     * @param uid
     * @return
     */
    @RequestMapping(value = "/jf")
    @ResponseBody
    public String jf(Integer uid,HttpServletRequest request){
        Integer integer = userService.updatejf(uid);
        if(integer>0){
            //放入日志
            Emp emp =(Emp) request.getSession().getAttribute("emp");
            mylog.setEmp(emp);
            mylog.setLogTime(time);
            StringBuilder stringBuilder = new StringBuilder(emp.getEmpName()+"解封了一个用户，用户id为："+uid);
            mylog.setLogDetails(stringBuilder.toString());
            logService.addLog(mylog);
            //放入日志结束
            return "1";
        }
        return "2";
    }

    /**
     * 根据手机号和邮箱找回密码
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/zhphonepassoword")
    public String zhphonepassoword(HttpServletRequest request,String phone,String userpasswordss){
        HttpSession session = request.getSession();
        Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(14[0-9])|(16[0-9])|(19[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Pattern e = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        if(p.matcher(phone).matches()){//是手机号就根据手机号修改密码
            Integer integer = userService.updatePhonePassword(phone, userpasswordss);
            if(integer>0){
                session.setAttribute("user",null);
                return "1";
            }
        }
        if(e.matcher(phone).matches()){//是邮箱就根据手机号修改密码
            Integer integer = userService.updateEmmitPassword(phone, userpasswordss);
            if(integer>0){
                session.setAttribute("user",null);
                return "1";
            }
        }

        return "2";
    }

    //判断手机号不能重复
    @ResponseBody
    @RequestMapping(value = "/cf")
    public String cf(String phone){
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

    /**
     * 查看图片
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ckimg")
    public  String ckimg(String uid,HttpServletRequest request){
        User user = userService.getUser(Integer.parseInt(uid));

        return user.getUserImg();
    }

    @ResponseBody
    @RequestMapping(value = "/Hqyzm")
    public String Hqyzm(HttpServletRequest request, HttpServletResponse response,String phone) throws Exception {
        JSONObject json = null;
        //生成6位验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        //发送短信
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        String result = client.send(phone, "您正在注册zking在线课堂用户，验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次!");

        //将验证码存到session中,同时存入创建时间
        //以json存放，这里使用的是阿里的fastjson
       /* json = new JSONObject();
        json.put("mobile", mobile);
        json.put("verifyCode", verifyCode);
        json.put("createTime", System.currentTimeMillis());*/
        // 将认证码存入SESSION
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
    @ResponseBody
    @RequestMapping(value = "/LoginCallback2")
    public boolean LoginCallback(HttpServletRequest request, HttpServletResponse response) throws QQConnectException {
        HttpSession session = request.getSession();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            if (accessTokenObj.getAccessToken().equals("")) {
            } else {
                //1.根据code获取access_token
                String token = accessTokenObj.getAccessToken();
                //2.根据access_token获得openId
                OpenID openIDobj = new OpenID(token);
                String userOpenID = openIDobj.getUserOpenID();
                //3.去数据库查找有没有openId如果有，登陆过，没有则是新用户
                com.qq.connect.api.qzone.UserInfo qzoneUserInfo = new com.qq.connect.api.qzone.UserInfo(token, userOpenID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                //4.根据openId和access_Token获取用户信息
                //查询数据库里面有没有这个openId如果有把这个用户的信息拿出来没有就添加
                User qquser = userService.getopenid(userOpenID);

                session.setAttribute("nickName",userInfoBean.getNickname());
                session.setAttribute("Avatar",userInfoBean.getAvatar().getAvatarURL50());
                session.setAttribute("openid",userOpenID);
                if(qquser==null){
                    try{
                        response.sendRedirect("/user/binding");
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(qquser.getUserPhone()==null){
                    try{
                        response.sendRedirect("/user/binding");
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                   session.setAttribute("user",qquser);
                    User user=(User) session.getAttribute("user");
                    //修改ip地址
                    userService.updateipaddrlastTime(user.getUserID(),IpAddress.getIpAddr(request),new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    try{
                        response.sendRedirect("/");//登入界面  （原来：/user/）
                       return false;
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return true;
    }


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
        user.setUserIntegrsl(500);
        //昵称
        user.setUserName(nickName);
        //图片

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
        Integer userphone = userService.getUserphone(userPhone);
        if(userlogin!=null){
            if(userphone==0){
                Integer integer = userService.updateipaddrlastTime(userlogin.getUserID(), IpAddress.getIpAddr(request), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                if(integer>0){
                    session.setAttribute("user",userlogin);
                    return "1";
                }
                else{
                    return "2";
                }
            }
        }
        else{
            return "3";
        }
        return "";
    }
    /**
     * 注销
     */
    @RequestMapping(value = "/zhuxiao")
    public void zhuxiao(HttpServletResponse response,HttpServletRequest request){
        HttpSession session = request.getSession();
        //清空用户的session
        session.setAttribute("user",null);
        session.setAttribute("b",null);
        session.setAttribute("a",null);
        session.setAttribute("nickName",null);
        session.setAttribute("Avatar",null);
        session.setAttribute("openid",null);
        try{
            response.sendRedirect("/");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 用户点击qq联合登陆
     * @return
     */
    @RequestMapping("/qqLogin")
    public String requestQQLogin(HttpServletRequest request) throws QQConnectException {
        //自动组装qq登陆连接，重定向到qq登陆页面
        String authorizeURL = new Oauth().getAuthorizeURL(request);
        return "redirect:"+authorizeURL;
    }

    /**
     * 修改手机号
     * @param
     * @return
     */
    @RequestMapping("/updatePhone")
    @ResponseBody
    public String updatePhone(String phone,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        Integer integer = userService.updatePhone(user.getUserPhone(), phone);
        if(integer>0){
            User user1 = userService.getUser(user.getUserID());
            session.setAttribute("user",null);
            session.setAttribute("user",user1);
            return "1";
        }

        return "2";
    }

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

    @RequestMapping(value = "/findinteginte")
    @ResponseBody
    public Map<String, Object> findintegIntl(){
        int integrsl = userService.findIntegrsl(SessionUtil.getUserById());
        Map<String,Object> maps = new HashMap<>();
        maps.put("jfs",integrsl);
        return maps;
    }


}
