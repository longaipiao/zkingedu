
    var s=0;

    var layer;//layer插件

    layui.use('layer',function () {
        layer = layui.layer;

    });


//注册
    function zc() {
        //获得手机号文本框的值
        var phone=$("#phone").val();
        //获得密码的值
        var password=$("#password").val();
        //手机验证码
        var captcha_v=$("#captcha_v").val();
        //手机号验证
        var pdphone=/^1[3-9]\d{9}$/;
        //判断重复
        $.ajax({
            url:"/user/cf",
            type:"post", //请求方式
            data:{
                phone:phone,
            },
            success:function (data) {
                if(data==2){
                    layer.msg("手机号重复了");
                }
                else{
                    if(""==phone){
                        layer.msg("手机号不能为空");
                    }
                    else if(!pdphone.test(phone)){
                        layer.msg("手机号格式不对");
                    }
                    else if(""==password){
                        layer.msg("密码不能为空");
                    }
                    else if(password.length <=6 && password.length >=18){
                        layer.msg("密码只能在6到18位");
                    }
                    else if(""==captcha_v){
                        layer.msg("验证码不能为空");
                    }
                    else{
                        if(s!=captcha_v){
                            layer.msg("验证码错误")
                        }
                        else{
                            //注册方法
                            $.ajax({
                                url: "/user/zc",
                                type: "post", //请求方式
                                data: {
                                    phone: phone,
                                    password: password,
                                },
                                success: function (data) {
                                    if(data==1){
                                        location.href="/";
                                    }
                                },
                                error:function () {
                                    layer.msg("注册失败")
                                }
                            })
                        }
                    }
                }
            }
        });
    }

//qq绑定账号
    function qqlogin() {
        //获得手机号文本框的值
        var phone=$("#phone").val();
        var password=$("#password").val();
        //手机号验证
        var pdphone=/^1[3-9]\d{9}$/;
        //判断重复
        var captcha_v5=$("#captcha_v").val();
        if(""==phone){
            layer.msg("手机号不能为空");
        }
        else if(!pdphone.test(phone)){
            layer.msg("手机号格式不对");
        }
        else if(""==password){
            layer.msg("密码不能为空");
        }
        else if(password.length <=6 && password.length >=18){
            layer.msg("密码只能在6到18位");
        }
        else if(""==captcha_v){
            layer.msg("验证码不能为空");
        }
        else {
            $.ajax({
                url: "/user/cf",
                type: "post", //请求方式
                data: {
                    phone: phone,
                },
                success: function (data) {
                    if (data == 2) {
                        layer.msg("该手机号已经绑定了可以直接登入");
                    } else {
                        if (s != captcha_v5) {
                            layer.msg("验证码错误")
                        } else {
                            $.ajax({
                                url: "/user/qqlogin",
                                type: "post",
                                data: {
                                    phone: $("#phone").val(),
                                    upwd: $("#password").val()
                                },
                                success: function (sh) {
                                    layer.msg(sh)
                                    if (sh == 1) {
                                        layer.msg("绑定成功");
                                        location.href = "/";
                                    } else if (sh == 2) {
                                        layer.msg("登入失败")
                                    }
                                }
                            })
                        }
                    }
                },
            })
        }
    }
//qq绑定已有的账号
    function qqlogins() {
        //获得手机号文本框的值
        var phone=$("#phone").val();
        //判断重复
        var captcha_v5=$("#captcha_v").val();
        //手机号验证
        var pdphone=/^1[3-9]\d{9}$/;
        if(""==phone){
            layer.msg("手机号不能为空");
        }
        else if(!pdphone.test(phone)){
            layer.msg("手机号格式不对");
        }else if(""==captcha_v5){
            layer.msg("验证码不能为空");
        }else {
            $.ajax({
                url: "/user/cf",
                type: "post", //请求方式
                data: {
                    phone: phone,
                },
                success: function (data) {
                    if (data == 1) {
                        layer.msg("该手机号没有注册该平台")
                    } else {
                        $.ajax({
                            url:"/user/yjqqlogin",
                            type: "post", //请求方式
                            data:{
                                phone:phone,
                            },
                            success:function (data) {
                                if(data==2){
                                    layer.msg("该手机号已经绑定了qq号")
                                }
                                else{
                                    if (s != captcha_v5) {
                                        layer.msg("验证码错误")
                                    } else {
                                        $.ajax({
                                            url: "/user/yjqqlogin",
                                            type: "post",
                                            data: {
                                                phone: $("#phone").val(),
                                            },
                                            success: function (sh) {
                                                if (sh == 1) {
                                                    layer.msg("绑定成功")
                                                    location.href = "/";
                                                } else if (sh == 2) {
                                                    layer.msg("登入失败")
                                                }
                                            }
                                        })
                                    }
                                }
                            }
                        });
                    }
                },
            })
        }
    }


    var time=60;

    $(function () {
        $('#sendPhone').click(function(){
            //获得手机号文本框的值
            var phones=$("#phone").val();
            //手机号验证
            var pdphones=/^1[3-9]\d{9}$/;
            if(""==phones){
                layer.msg("手机号不能为空");
            }
            else if(!pdphones.test(phones)){
                layer.msg("格式不对");
            }
            else {
                $.ajax({
                    url: "/user/Hqyzm",
                    type: 'post',
                    data: {
                        phone: phones,
                    },
                    success:function(n) {
                        s=n;
                        timeStartss();
                    },
                    error: function () {
                        layer.msg('注册失败');
                    }
                });
            }
        });
    })



    function timeStartss(){
        if(time>1){
            $('#sendPhone').css("pointer-events","none");
            time=time-1;
            $('#sendPhone').text("重新获取("+time+")");
            setTimeout(timeStartss,1000);
        }else{
            time=60;
            $('#sendPhone').css("pointer-events","auto");
            $('#sendPhone').text("获取验证码");
        }
    }


