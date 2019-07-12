
var s=0;
var time=60;
function updatePhone3() {
    //获得手机号文本框的值
    var phone = $("#userphone").val();
    //判断重复
    var captcha_v5 = $("#usercaptcha_v").val();
    if ("" === phone) {
        alert("手机号不能为空");
    } else if ("" === captcha_v5) {
        alert("验证码不能为空")
    } else {
        $.ajax({
            url: "/user/cf",
            type: "post", //请求方式
            data: {
                phone: phone,
            },
            success: function (data) {
                if (data == 2) {
                    alert("该手机号重复了")
                } else {
                    if (s != captcha_v5) {
                        alert("验证码错误")
                    } else {
                        $.ajax({
                            url: "/user/updatePhone",
                            type: "post",
                            data: {
                                phone: phone,
                            },
                            success: function (sh) {
                                if (sh == 1) {
                                    alert("修改成功");
                                    location.href = "/user/userinfo/index";
                                } else if (sh == 2) {
                                    alert("修改失败")
                                }
                            }
                        })
                    }
                }
            },
        })

    }
}



$('#sendPhone2').click(function(){
    //获得手机号文本框的值
    var phones=$("#userphone").val();
    //手机号验证
    var pdphones=/^1[3-9]\d{9}$/;
    if(""==phones){
        alert("手机号不能为空");
    }
    else if(!pdphones.test(phones)){
        alert("手机号格式不对");
    }
    else {
        $.ajax({
            url: "/user/Hqyzm",
            type: 'post',
            data: {
                phone: phones,
            },
            success:function(n) {
                alert(n);
                s=n;
                timeStart2();
            },
            error: function () {
                alert('注册失败');
            }
        });
    }
});


function timeStart2(){
    if(time>1){
        $('#sendPhone2').css("pointer-events","none");
        time=time-1;
        $('#sendPhone2').text("重新获取("+time+")");
        setTimeout(timeStart2,1000);
    }else{
        time=60;
        $('#sendPhone2').css("pointer-events","auto");
        $('#sendPhone2').text("获取验证码");
    }
}

