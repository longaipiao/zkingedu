function loginChak() {
    $.ajax({
        url:"/user/login",
        type:"post",
        data:{
            userPhone:$("#userPhone").val(),
            upwd:$("#upwd").val()
        },
        success:function (data) {

        }
    })
}