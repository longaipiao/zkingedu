
    var s = 0;
    var time = 60;
    var y = 0;
    var x = 0;
    var a = 0;

    function updatePhonepassword() {
        //获得手机号和邮箱文本框的值
        var phones = $("#phoneEmail").val();
        //获得密码的值
        var userpassword = $("#userpassword").val();
        //获得确认密码的值
        var newuserpassword = $("#usercaptcha_v").val();
        if ("" == userpassword) {
            alert("密码不能为空");
        } else if ("" == newuserpassword) {
            alert("确认密码不能为空");
        } else if (userpassword.length <=6 && userpassword.length >=18) {
            alert("密码只能6到1位")
        } else if (userpassword != newuserpassword) {
            alert("两次密码不一样");
        } else {
            $.ajax({
                url: "/user/zhphonepassoword",
                type: "post", //请求方式
                data: {
                    phone: phones,
                    userpasswordss: userpassword,
                },
                success: function (data) {
                    if (data == 1) {
                        alert("找回成功");
                        location.href = "/";
                    }
                }
            })
        }


    }

//确定按钮
    function ms6() {
        //获得手机号和邮箱文本框的值
        var phones = $("#phoneEmail").val();
        //获得验证码
        var captcha_v = $("#captcha_v").val();
        //邮箱认证
        var Emails = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        //手机号验证
        var pdphones = /^1[3-9]\d{9}$/;
        if ("" == phones) {
            alert("手机号和邮箱不能为空");
        }
        else if (pdphones.test(phones)) {//如果是手机号码
            $.ajax({
                url: "/user/cf",
                type: "post", //请求方式
                data: {
                    phone: phones,
                },
                success: function (data) {
                    if (data == 1) {
                        alert("该手机号没有注册")
                        //给x赋值
                        x = 1;
                    }
                    else {
                        if (s != captcha_v) {
                            alert("验证码错误")
                        }
                        else {
                            tk();//弹框
                        }
                    }
                },
            });
        }
        else if (Emails.test(phones)) {
            $.ajax({
                url: "/user/Email",
                type: "post", //请求方式
                data: {
                    Email: phones,
                },
                success: function (data) {
                    if (data == 1) {
                        alert("该邮箱没有注册");
                        //给y赋值
                        y = 1;
                    } else {
                        if (s != captcha_v) {
                            alert("验证码错误")
                        }
                        else {
                            tk();
                        }
                    }

                },
            });
        }

    }

    function tk() {
        layui.use('layer', function () {
            var layer = layui.layer;
            var index = layer.open({
                type: 1,
                title: '找回密码',
                content: $('#xgphones'),
                area: ['500px', '400px'],
                shadeClose: true,
                end: function () {
                    $("#xgphones").attr("style", "display:none;");//隐藏
                }
            });
        });
    }


    $('#sendPhone9').click(function () {
        //获得手机号和邮箱文本框的值
        var phones = $("#phoneEmail").val();
        //邮箱认证
        var Emails = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        //手机号验证
        var pdphones = /^1[3-9]\d{9}$/;
        if ("" == phones) {
            alert("手机号和邮箱不能为空")
        }
        else if (pdphones.test(phones)) {//如果是手机号码
            $.ajax({
                url: "/user/Hqyzm",
                type: 'post',
                data: {
                    phone: phones,
                },
                success: function (n) {
                    s = n;
                    timeStart9();
                },
                error: function () {
                    alert('注册失败');
                }
            });
        }
        else if (Emails.test(phones)) {
            $.ajax({
                url: "/user/Emailjk",
                type: 'post',
                data: {
                    phone: phones,
                },
                success: function (n) {
                    s = n;
                    timeStart9();
                },
                error: function () {
                    alert('注册失败');
                }
            });
        }
    });


    function timeStart9() {
        if (time > 1) {
            $('#sendPhone9').css("pointer-events", "none");
            time = time - 1;
            $('#sendPhone9').text("重新获取(" + time + ")");
            setTimeout(timeStart9, 1000);
        } else {
            time = 60;
            $('#sendPhone9').css("pointer-events", "auto");
            $('#sendPhone9').text("获取验证码");
        }
    }


