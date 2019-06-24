
var s=0;
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
                $("#sd").html("手机号重复");
            }
            else{
                if(""==phone){
                    $("#sd").html("手机号不能为空");
                }
                else if(!pdphone.test(phone)){
                    $("#sd").html("手机号格式不对");
                }
                else if(""==password){
                    $("#sd").html("密码不能为空");
                }
                else if(password.length < 6 || password.length > 15){
                    $("#sd").html("密码小于6位或者大于24位");
                }
                else if(""==captcha_v){
                    $("#sd").html("验证码不能为空");
                }
                else{
                    if(s!=captcha_v){
                       alert("验证码错误")
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
                                    $("#sd").html("");
                                    alert("注册成功")
                                    location.href="/user/index";
                                }
                            },
                            error:function () {
                                alert("注册失败")
                            }
                        })
                    }


                }
            }
        }
    });
}

var time=60;
    $('#sendPhone').click(function(){
        var verifyCode=$("#captcha_v").val();
        //获得手机号文本框的值
        var phones=$("#phone").val();
        //手机号验证
        var pdphones=/^1[3-9]\d{9}$/;
        if(""==phones){
            alert("手机号不能为空")
        }
        else if(!pdphones.test(phones)){
            $("#sd").html("手机号格式不对");
        }
        else {
            $.ajax({
                url: "/user/Hqyzm",
                type: 'post',
                data: {
                    phone: phones,
                },
                success: function (n) {
                    s=n;
                    timeStart();
                },
                error: function () {
                    alert('注册失败');

                }
            });
        }
    });




function timeStart(){
    if(time>1){
        $('#sendPhone').css("pointer-events","none");
        time=time-1;
        $('#sendPhone').text("重新获取("+time+")");
        setTimeout(timeStart,1000);
    }else{
        time=60;
        $('#sendPhone').css("pointer-events","auto");
        $('#sendPhone').text("获取验证码");
    }
}
