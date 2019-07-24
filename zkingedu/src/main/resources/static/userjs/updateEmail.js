

    var layer;//layer插件

    layui.use('layer',function () {
        layer = layui.layer;
    });


    var s=0;
    var time=60;
    var phone23=0;
    function updateEmail() {
        if(s==0){
            s="ss";
        }
        //获得邮箱文本框的值
        var Email = $("#userEmail").val();
        //判断重复
        var captcha_v5 = $("#userEmailcaptcha_v").val();
        if(""==Email){
            layer.msg("邮箱不能为空");
        }else if(Email.match(" ")) {
            layer.msg("邮箱不能有空格")
        }
        else{
            $.ajax({
                url: "/user/Email",
                type: "post", //请求方式
                data:{
                    Email:Email,
                },
                success: function (data) {
                    if (data == 2) {
                        layer.msg("该邮箱重复了")
                    } else {
                        if (s != captcha_v5) {
                            layer.msg("验证码错误")
                        } else {
                            if (Email != phone23) {
                                layer.msg("验证码错误")
                            } else {
                                $.ajax({
                                    url: "/user/updateEmail",
                                    type: "post",
                                    data: {
                                        newEmail: Email,
                                    },
                                    success: function (sh) {
                                        if (sh == 1) {
                                            layer.msg("修改成功");
                                            location.href = "/user/userinfo/index";
                                        } else if (sh == 2) {
                                            layer.msg("修改失败")
                                        }
                                    }
                                })
                            }
                        }
                    }
                },
            })
        }
    }

    //修改昵称
    function updatename(){
        var phone = $("#phone").val();
        var username = $("#username").val();
        if(username==""){
            layer.msg("名字不能为空");
        }
        else if(username.match(" ")){
            layer.msg("不能有空格");
        }
        else if(username.length<3 || username.length>16){
            layer.msg("昵称必须3到16位");
        }
        else {
            $.ajax({
                url: "/user/updatename",
                type: "post",
                data: {
                    phone: phone,
                    name: username,
                },
                success: function (data) {
                    if (data == 1) {
                        layer.msg("修改成功");
                        location.href = "/user/userinfo/index";
                    } else if (data == 2) {
                        layer.msg("修改失败")
                    }
                }
            })
        }
    }



    $('#sendPhone7').click(function(){
        //获得手机号文本框的值
        var Email=$("#userEmail").val();
        //手机号验证
        var Emails=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if(""==Email){
            layer.msg("邮箱不能为空")
        }
        else if(Email.match(" ")) {
            layer.msg("邮箱不能有空格")
        }
        else if(!Emails.test(Email)){
            layer.msg("邮箱格式不对");
        }
        else {
            $.ajax({
                url: "/user/Emailjk",
                type: 'post',
                data: {
                    phone: Email,
                },
                success:function(n) {
                    s=n;
                    phone23=Email;
                    timeStart7();
                },
                error: function () {
                    layer.msg('注册失败');
                }
            });
        }
    });


    function timeStart7(){
        if(time>1){
            $('#sendPhone7').css("pointer-events","none");
            time=time-1;
            $('#sendPhone7').text("重新获取("+time+")");
            setTimeout(timeStart7,1000);
        }else{
            time=60;
            $('#sendPhone7').css("pointer-events","auto");
            $('#sendPhone7').text("获取验证码");
        }
    }



