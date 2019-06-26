
function aha(aa){
    $("#diyicheng").toggle();

}

 var huifuid="";//回复者的uid
 var plfuid="";//评论的父id

//回复评论的时候调用这个方法
function plkk(cs,cs2,cs3){

    $("#quxhuifu").show();//展示取消按钮

    huifuid=cs;     //给回复id赋值



    plfuid=cs3;
    alert("回复名字:"+cs2);
    alert("回复uid:"+huifuid);
    alert("评论父id"+plfuid);


}


var  id="";//获取从界面转过来的值
$(function () {


    var url=window.location.search; //获取url中"?"符后的字串  
    if(url.indexOf("?")!=-1){
        id = url.substr(url.indexOf("=")+1);

    }


  var tieuid="";//帖子uid

    //调用ajax
    $.ajax({
        type:"post",
        url:"/pst/tzxx",
        dataType:"json",
        data:{
           id:id
        },
        success:function(data){
            tieid=data.c;//给全局变量帖子uid赋值
            var content="";
            content+="<div class=\"question-headline\">\n" +
                "\n" +
                "        <span  class=\"question-title\">"+data.d+"</span>\n" +
                "        <span class=\"question-figure\">浏览量"+data.j+"</span><span class=\"question-figure\">点赞数 "+data.k+"</span>\n" +
                "    </div>\n" +
                "    <div class=\"question-author\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "        <div class=\"user-avatar \">\n" +
                "        <a class=\"avatar\" href=\"/user/347060\" target=\"_blank\">\n" +
                "        <img src=\"https://dn-simplecloud.shiyanlou.com/gravatarM7Q8G5B92TNJ.jpg?imageView2/1/w/200/h/200\">\n" +
                "        </a>\n" +
                "\n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "        <div class=\"user-username \">\n" +
                "        <a  class=\"username\" href=\"/user/347060\" target=\"_blank\" >\n" +
                "        "+data.f+"\n" +
                "        </a>\n" +
                "        <span class=\"user-level\">L4</span>\n" +
                "        </div>\n" +
                "\n" +
                "        <span>"+data.i+"</span>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <a href=\"#sign-modal\" data-toggle=\"modal\" data-sign=\"signin\" class=\"btn btn-primary collectBtn\">收藏</a>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "        </div>\n" +
                "        <div class=\"question-content markdown-body\">\n" +
                "        <p><div id='nr'>第一段代码中，步骤三第二行的libb.a应该是libb.o，同步骤二中的一致\n" +
                "    </div></p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <div class=\"labreport-detail-like\">\n" +
                "\n" +
                "        <span class=\"btn btn-default btn-weiboshare\">\n" +
                "        <i class=\"fa fa-share-alt\"></i>\n" +
                "        </span>\n" +
                "        </div>\n" +
                "\n" +
                "        </div>"

              $("#xxpost").append(content);
              $("#nr").html(data.a);
        },
        error:function(jqXHR){
            alert("发生错误："+ jqXHR.status);
        }
    });





    //开始请求ajax,获取所有评论
    initdata();





    var tcommentUid2=""; //回复对方的uid
    var tcommentFid="";  //回复的评论父id


//取消按钮的隐藏
    $("#quxhuifu").click(function () {
        $("#quxhuifu").hide();//隐藏取消回复
        tcommentUid2=id;   //恢复  作者的uid
        tcommentFid=0;     //回复  评论的父id
    });




//发表评论
    $("#fb").click(function () {
        //如果回复id为空，也就是一级评论。即对贴子的评论
        if (huifuid==""){
            tcommentUid2=id;
            tcommentFid=0;

        }
        //否者的话则是回复
        else{
            tcommentUid2=huifuid;
            tcommentFid=plfuid;
        }

        $.ajax({
            type:"post",
            url:"/pst/addTcomment",
            dataType:"json",
            data:{
                tcommentCid:id  //帖子id
                ,tcommentContent:$("#editor").val() //获取评论内容
                ,tcommentUid2:tcommentUid2   //回复谁的id
                ,tcommentFid:tcommentFid     //评论父id
            },
            success:function(data){
                if(data>0){
                    alert("评论成功");
                    initdata();
                    $("#quxhuifu").hide();//隐藏取消回复
                    tcommentUid2=id;   //恢复  作者的uid
                    tcommentFid=0;     //回复  评论的父id
                    $('#editor').val("");
                    //parent.layer.msg('评论成功！', {icon: 1,time:2000,shade:0.2});
                }else{
                    alert("评论失败");
                   // parent.layer.msg('评论失败！', {icon: 2,time:3000,shade:0.2});
                }
            },
            error:function(jqXHR){
                alert("发生错误："+ jqXHR.status);
            }
        });
        return false;
    });






















    });




function initdata() {
    //开始请求ajax,获取所有评论
    $.ajax({
        type: "post",
        url: "/pst/getAllTs",//请求接口
        dataType: "json",
        data: {
            id: id
        },
        success: function (data) {
            var content = ""
            $.each(data, function (i, obj) {
                content += "<div class=\"answer-item\">\n" +
                    "                    <div class=\"answer-head\">\n" +
                    "                            <div class=\"user-avatar \">\n" +
                    "                                        <a class=\"avatar\" href=\"/user/212008\" target=\"_blank\">\n" +
                    "                                            <img src=\"https://dn-simplecloud.shiyanlou.com/gravatarNTY0MzE5MjI0Njgz.png?v=1477283063583&amp;imageView2/1/w/200/h/200\">\n" +
                    "                                        </a>\n" +
                    "\n" +
                    "                                        <a class=\"member-icon\" href=\"/vip\" target=\"_blank\">\n" +
                    "\n" +
                    "                                <img src=\"\n" +
                    "                                            https://static.shiyanlou.com/img/vip-icon.png\n" +
                    "                                          \">\n" +
                    "\n" +
                    "                                        </a>\n" +
                    "\n" +
                    "                            </div>\n" +
                    "                     </div>\n" +
                    "              <div class=\"answer-detail\">\n" +
                    "\n" +
                    "                    <span class=\"comment-reply\">\n" +
                    "                            <div class=\"user-username \">\n" +
                    "                                <div class=\"username\" href=\"/user/212008\" target=\"_blank\">\n" +
                    "                                        " + obj.user.userName + "&nbsp;&nbsp;&nbsp;&nbsp;:\n" +
                    "                                    <span class=\"create-time\" id=\"ppnr\">" + obj.tcommentContent + "&nbsp;&nbsp;&nbsp;&nbsp;</span>\n" +
                    "                                    <span>(" + obj.tcommentTime + "&nbsp;&nbsp;&nbsp;&nbsp;#" + obj.tcommentLounum + "楼)</span>\n" +
                    "                                    &nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "                                    <a href=\"javascript:void(0);\"   onclick=\"aha('" + obj.tcommentID + "')\">查看回复</a>（20）\n" +
                    "                                    <a href=\"javascript:void(0);\" onclick=\"plkk('" + obj.user.userID + "','" + obj.user.userName + "','" + obj.tcommentID + "')\">回复</a>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                    </span>\n" +
                    "\n" +
                    "                    <div class=\"answer-content markdown-body\">\n" +
                    "                    </div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                    \n" +
                    "\n" +
                    "\n" +
                    "                        <div id=\"diyicheng\" style=\"display:block\">\n"

                $.each(obj.chirden, function (j, obj2) {

                    content += "<div class=\"answer-head\">\n" +
                        "                                    <div class=\"user-avatar \">\n" +
                        "                                        <a class=\"avatar\" href=\"/user/212008\" target=\"_blank\">\n" +
                        "                                            <img src=\"https://dn-simplecloud.shiyanlou.com/gravatarNTY0MzE5MjI0Njgz.png?v=1477283063583&amp;imageView2/1/w/200/h/200\">\n" +
                        "                                        </a>\n" +
                        "\n" +
                        "                                        <a class=\"member-icon\" href=\"/vip\" target=\"_blank\">\n" +
                        "\n" +
                        "                                            <img src=\"\n" +
                        "                                                    https://static.shiyanlou.com/img/vip-icon.png\n" +
                        "                                                  \">\n" +
                        "\n" +
                        "                                        </a>\n" +
                        "\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "\n" +
                        "                                <span class=\"comment-reply\">\n" +
                        "                                    <div class=\"user-username \">\n" +
                        "                                        <div class=\"username\" href=\"/user/212008\" target=\"_blank\">\n" +
                        "\n" +
                        "                                                " + obj2.user.userName + "&nbsp;&nbsp;&nbsp;&nbsp; 回复&nbsp;&nbsp;&nbsp;&nbsp; " + obj2.user2.userName + ":\n" +
                        "                                            <span class=\"create-time\">" + obj2.tcommentContent + "&nbsp;&nbsp;&nbsp;&nbsp;</span>\n" +
                        "                                            <span>(" + obj2.tcommentTime + "&nbsp;&nbsp;&nbsp;&nbsp;)</span>\n" +
                        "                                            <a href=\"javascript:void(0);\" onclick=\"plkk('" + obj2.user.userID + "','" + obj2.user.userName + "','" + obj2.tcommentFid + "')\">回复</a>\n" +
                        "\n" +
                        "\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                            </span>\n" +
                        "\n" +
                        "                                    <div class=\"answer-content markdown-body\">\n" +
                        "\n" +
                        "                                    </div>\n"

                });
                content += "                        </div>\n"
                content += "                               \n" +
                    "                        <hr/>\n" +
                    "                        \n" +
                    "                    \n" +
                    "                </div>\n" +
                    "                </div>\n"

            });

            $("#pp").html(content);

        },
        error: function (jqXHR) {
            alert("发生错误：" + jqXHR.status);
        }
    });
}













































