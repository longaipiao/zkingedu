
var s=0;
var time=60;
function updateEmail() {
    //获得邮箱文本框的值
    var Email = $("#userEmail").val();
    //判断重复
    var captcha_v5 = $("#userEmailcaptcha_v").val();
    if(""==Email){
        alert("邮箱不能为空");
    }else{
        $.ajax({
            url: "/user/Email",
            type: "post", //请求方式
            data:{
                Email:Email,
            },
            success: function (data) {
                if (data == 2) {
                    alert("该邮箱重复了")
                } else {
                    if (s != captcha_v5) {
                        alert("验证码错误")
                    } else {
                        $.ajax({
                            url: "/user/updateEmail",
                            type: "post",
                            data: {
                                newEmail:Email,
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



$('#sendPhone7').click(function(){
    //获得手机号文本框的值
    var Email=$("#userEmail").val();
    //手机号验证
    var Emails=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    if(""==Email){
        alert("邮箱不能为空")
    }
    else if(!Emails.test(Email)){
        alert("邮箱格式不对");
    }
    else {
        $.ajax({
            url: "/user/Emailjk",
            type: 'post',
            data: {
                phone: Email,
            },
            success:function(n) {
                alert(n);
                s=n;
                timeStart7();
            },
            error: function () {
                alert('注册失败');
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

