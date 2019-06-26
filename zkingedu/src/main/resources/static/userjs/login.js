var s=0;
function loginChak() {
    $.ajax({
        url:"/user/login",
        type:"post",
        data:{
            userPhone:$("#userPhone").val(),
            upwd:$("#upwd").val()
        },
        success:function (data) {
            if(data==1){
                alert("登入成功")
                location.href="/user/";
            }
            else{
                alert("登入失败")
            }
        }
    })
}
