    var s=0;
    function loginChak() {
        var userPhone=$("#userPhone").val();
        var upwd=$("#upwd").val();
        if(""==userPhone){
            alert("手机号不能为空");
        }
        else if (""==upwd){
            alert("密码不能为空");
        }else {
            $.ajax({
                url: "/user/login",
                type: "post",
                data: {
                    userPhone: $("#userPhone").val(),
                    upwd: $("#upwd").val()
                },
                success: function (data) {
                    if (data == 1) {
                        location.href = "/";
                    } else {
                        alert("账户或密码错误");
                    }
                }
            })
        }
    }

