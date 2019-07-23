
var layer;//layer插件

layui.use('layer',function () {
    layer = layui.layer;
});

    var s=0;
    function loginChak() {
        var userPhone=$("#userPhone").val();
        var upwd=$("#upwd").val();
        if(""==userPhone){
            layer.msg("手机号不能为空");
        }
        else if (""==upwd){
            layer.msg("密码不能为空");
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
                    }
                    if(data==3){
                        layer.msg("用戶被封禁");
                    }
                    if(data==2) {
                        layer.msg("账户或密码错误");
                    }
                }
            })
        }
    }

