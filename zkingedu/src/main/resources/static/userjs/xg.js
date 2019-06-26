var s=0;
//修改方法
function update() {

    //获得手机号文本框的值
    var phone=$("#phone").val();
    //获得用户手机号文本框的值
    var inputShou=$("#inputShou").val();
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
                if ("" == phone) {
                    alert("手机号不能为空")
                } else if (!pdphone.test(phone)) {
                    alert("手机号格式不对")
                } else if("" == password1){
                    alert("原密码不能为空")
                } else if(""== captcha_v){
                    alert("验证码不能为空")
                } else  if(""== password2){
                    alert("修改的密码不能为空")
                } else if(""== password3){
                    alert("确认密码不能为空")
                } else if(password2==passwordy){
                    alert("跟上次密码不能一样")
                } else if(password2.length>6 &&password2.length<18){
                    alert("密码只能在6到8位")
                }
                else if (passwordy!=password1){
                    alert("跟原密码不一样")
                } else if(password2!=password3){
                    alert("两次密码不一样")
                } else if(inputShou!= phone){
                    alert("手机号不是绑定账号的手机号");
                 }
                else {
                    if (s != captcha_v) {
                        alert("验证码错误")
                    } else {
                        //注册方法
                        $.ajax({
                            url: "/user/updatePhonepassword",
                            type: "post", //请求方式
                            data: {
                                phone: phone,
                                password2: password2,
                            },
                            success: function (data) {
                                if (data == 1) {
                                    $("#sd").html("");
                                    alert("修改成功")
                                    location.href = "/user/";
                                }
                            },
                            error: function () {
                                alert("修改失败")
                            }
                        })
                    }


                }
            }


    });
}
