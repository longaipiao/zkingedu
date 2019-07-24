
    var layer;//layer插件

    layui.use('layer',function () {
        layer = layui.layer;
    });


    var s=0;
    var phone23=0;
//用手机号修改密码
    function updatephonepassword() {
        if(s==0){
            s="ss";
        }
        //获得手机号文本框的值
        var phones=$("#phoneyhs").val();
        //邮箱认证
        var Emails=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        //获得用户手机号文本框的值
        var inputShou=$("#yhphone").val();
        //获得用户输入原来密码的的值
        var password1=$("#password1").val();
        //获得用户输入密码的值
        var password2=$("#password2").val();
        //获得密码的值
        var password3=$("#password3").val();
        //获得用户的密码
        var passwordy=$("#passwordy").val();
        //手机验证码
        var captcha_v=$("#captcha_v").val();
        var inputEmail=$("#inputEmail").html();
        //邮箱认证
        var Emails=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        //手机号验证
        var pdphone=/^1[3-9]\d{9}$/;
        if ("" == phones) {
            layer.msg("手机号不能为空")
        } else if("" == password1){
            layer.msg("原密码不能为空")
        } else if(""== captcha_v){
            layer.msg("验证码不能为空")
        } else  if(""== password2){
            layer.msg("修改的密码不能为空")
        } else if(""== password3){
            layer.msg("确认密码不能为空")
        } else if(password2==passwordy){
            layer.msg("跟上次密码不能一样")
        } else if(password2.length<6 || password2.length>16){
            layer.msg("密码只能在6到16位")
        }
        else if(phones.match(" ")) {
            layer.msg("邮箱和密码不能有空格")
        }
        else if(password2.match(" ")){
            layer.msg("密码不能输入空格");
        }
        else if (passwordy!=password1){
            layer.msg("跟原密码不一样")
        } else if(password2!=password3){
            layer.msg("两次密码不一样")
        }
        else {
            if(pdphone.test(phones)){//如果是手机号
                if (inputShou == phones) {
                    if (s != captcha_v) {
                        layer.msg("验证码错误")
                    } else {
                    if (phones != phone23) {
                        layer.msg("验证码错误")
                    } else {
                        //注册方法
                        $.ajax({
                            url: "/user/zhphonepassoword",
                            type: "post", //请求方式
                            data: {
                                phone: phones,
                                userpasswordss: password2,
                            },
                            success: function (data) {
                                if (data == 1) {
                                    layer.msg("修改成功")
                                    location.href = "/";
                                } else {
                                    layer.msg("修改失败")
                                }
                            },
                            error: function () {
                                layer.msg("修改失败")
                            }
                        })
                    }
                    }
                } else {
                    layer.msg("手机号不是绑定账号的手机号");

                }
            }

            if(Emails.test(phones)){//如果是邮箱
                if(inputEmail==phones){
                    if (s != captcha_v) {
                        layer.msg("验证码错误")
                    } else {
                        if (phones != phone23) {
                            layer.msg("验证码错误")
                        } else {
                            //注册方法
                            $.ajax({
                                url: "/user/zhphonepassoword",
                                type: "post", //请求方式
                                data: {
                                    phone: phones,
                                    userpasswordss: password2,
                                },
                                success: function (data) {
                                    if (data == 1) {
                                        layer.msg("修改成功")
                                        location.href = "/";
                                    }
                                },
                                error: function () {
                                    layer.msg("修改失败")
                                }
                            })
                        }
                    }
                }
                else {
                    layer.msg("邮箱不是绑定账号的邮箱号");
                }
            }





        }







    }

    var time=60;
    $(function () {
    $('#sendPhone10').click(function(){
        //获得手机号文本框的值
        var phones=$("#phoneyhs").val();
//邮箱认证
        var Emails=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        //手机号验证
        var pdphones=/^1[3-9]\d{9}$/;
        if(""==phones){
            layer.msg("手机号和邮箱不能为空")
        }
        else if(pdphones.test(phones)){//如果是手机号码
            $.ajax({
                url: "/user/Hqyzm",
                type: 'post',
                data: {
                    phone: phones,
                },
                success:function(n) {
                    s=n;
                    phone23=phones;
                    timeStart1();
                },
                error: function () {
                    layer.msg('注册失败');
                }
            });
        }
        else if(Emails.test(phones)) {
            $.ajax({
                url: "/user/Emailjk",
                type: 'post',
                data: {
                    phone: phones,
                },
                success:function(n) {
                    s=n;
                    phone23=phones;
                    timeStart1();
                },
                error: function () {
                    layer.msg('注册失败');
                }
            });
        }
    });

})

    function timeStart1(){
        if(time>1){
            $('#sendPhone10').css("pointer-events","none");
            time=time-1;
            $('#sendPhone10').text("重新获取("+time+")");
            setTimeout(timeStart1,1000);
        }else{
            time=60;
            $('#sendPhone10').css("pointer-events","auto");
            $('#sendPhone10').text("获取验证码");
        }
    }
