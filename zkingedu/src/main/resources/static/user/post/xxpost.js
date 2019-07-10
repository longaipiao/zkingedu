
//查看回复的展开和收缩的方法
function aha(aa){
    $("#"+aa+"").toggle();

}



 var huifuid="";//回复者的uid
 var plfuid="";//评论的父id
 var userid="";//用户的ID

//定义全局，用来下拉刷新
var  current="";

//回复评论的时候调用这个方法
function plkk(cs,cs2,cs3){

    $("#quxhuifu").show();//展示取消按钮

    huifuid=cs;     //给回复id赋值


    plfuid=cs3;
    alert("回复名字:"+cs2);
    alert("回复uid:"+huifuid);
    alert("评论父id"+plfuid);


}


var  id="";//获取从界面转过来的值帖子id



//删除评论的方法
function delpl(a,b) {

    var t=confirm("你确定要删除吗？");
    if(t==true){
        $.ajax({
            type:"post",
            url:"/pst/delPl",
            dataType:"json",
            data:{
                uid:a,
                fid:b
            },
            success:function(data){
                if (data>0){
                    alert("删除成功");
                    initdata();
                }
            },
            error:function(jqXHR){
                alert("发生错误："+ jqXHR.status);
            }

        });

    }

}


//点赞和取消点赞
function dz(){
    $.ajax({
        type:"post",
        url:"/pst/addorDelGive",
        dataType:"json",
        data:{
            id:id
        },
        success:function(data){
            if(data==4){
                var r=parseInt($("#dzl").text());
                var g=r+1;
                $("#dzl").text(g);
                $("#tb").css({color: "red"});
            }
            if(data==3){
                var r=parseInt($("#dzl").text());
                var g=r-1;
                $("#dzl").text(g);
                $("#tb").css({color: ""});
            }

        },
        error:function(jqXHR){
            alert("发生错误："+ jqXHR.status);
        }

    });
}


//收藏的方法

function ghg() {

    $.ajax({
        type:"post",
        url:"/pst/addordelCollection",
        dataType:"json",
        data:{
            id:id
        },
        success:function(data){
            if(data==3){
                $("#sc").text("收藏");
            }
            if (data==4){
                $("#sc").text("取消收藏");
            }
        },
        error:function(jqXHR){
            alert("发生错误："+ jqXHR.status);
        }

    });

}


$(function () {


    var url=window.location.search; //获取url中"?"符后的字串  
    if(url.indexOf("?")!=-1){
        id = url.substr(url.indexOf("=")+1);

    }

  var tieuid="";//帖子uid

    //调用ajax
    jztzxx();//加载帖子详细信息



    //开始请求ajax,获取所有评论
    initdata();

    //调用点赞方法
    querydz();
    var tcommentUid2=""; //回复对方的uid
    var tcommentFid="";  //回复的评论父id


//取消按钮的隐藏
    $("#quxhuifu").click(function () {
        $("#quxhuifu").hide();//隐藏取消回复
        tcommentUid2=tieid;   //恢复  作者的uid
        tcommentFid=0;     //回复  评论的父id
    });







//发表评论
    $("#fb").click(function () {

        if($("#editor").val()==""){
            alert("请填写评论内容");
        }
        else {
            //如果回复id为空，也就是一级评论。即对贴子的评论
            if (huifuid == "") {
                tcommentUid2 = tieid;
                tcommentFid = 0;
            }
            //否者的话则是回复
            else {
                tcommentUid2 = huifuid;
                tcommentFid = plfuid;
            }


            $.ajax({
                type: "post",
                url: "/pst/addTcomment",
                dataType: "json",
                data: {
                    tcommentCid: id  //帖子id
                    , tcommentContent: $("#editor").val() //获取评论内容
                    , tcommentUid2: tcommentUid2   //回复谁的id
                    , tcommentFid: tcommentFid     //评论父id
                },
                success: function (data) {
                    if (data > 0) {
                        $("#quxhuifu").hide();//隐藏取消回复
                        tcommentUid2 = tieid;   //恢复  作者的uid
                        tcommentFid = 0;     //回复  评论的父id
                        huifuid = "";//给回复id复原
                        $("#editor").attr("text", "");
                        initdata();
                    } else {

                        $("#quxhuifu").hide();//隐藏取消回复
                        tcommentUid2 = tieid;   //恢复  作者的uid
                        tcommentFid = 0;     //回复  评论的父id
                        huifuid = "";//给回复id复原
                        alert("评论失败");
                    }
                },
                error: function (jqXHR) {
                    alert("发生错误：" + jqXHR.status);
                }
            });
            return false;
        }
    });




   //增加浏览量的方法
    $.ajax({
        type:"post",
        url:"/pst/addpstNum",
        dataType:"json",
        data:{
            id:id
        },
        success:function(data){
        },
        error:function(jqXHR){
            alert("发生错误："+ jqXHR.status);
        }

    });







  //查看是否已经收藏
    sc();






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
                if (obj.tcommentFid==0) {
                    var g=0;
                    content += "<div class=\"answer-item\">\n" +
                        "                    <div class=\"answer-head\">\n" +
                        "                            <div class=\"user-avatar \">\n" +
                        "                                        <a class=\"avatar\" href=\"/user/212008\" target=\"_blank\">\n" +
                        "                                            <img src=\""+obj.user.userImg+"\">\n" +
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

                        "                                    <span>(" + obj.tcommentTime + "&nbsp;&nbsp;&nbsp;&nbsp;#" + obj.tcommentLounum + "楼)</span>\n" +
                        "                                    &nbsp;&nbsp;&nbsp;&nbsp;\n" +
                        "                                    <a href=\"javascript:void(0);\"   onclick=\"aha('" + obj.tcommentID + "')\">查看回复</a>（"+obj.user.userLastTime+"）\n"
                       if (userid==obj.user.userID) {
                       content+= "                                    <a href=\"javascript:void(0);\" onclick=\"plkk('" + obj.user.userID + "','" + obj.user.userName + "','" + obj.tcommentID + "')\">&nbsp;&nbsp;&nbsp;&nbsp;回复:&nbsp;&nbsp;&nbsp;&nbsp;</a><a href='javascript:void(0);'  onclick=\"delpl('" + obj.tcommentID + "','" + obj.tcommentFid + "' )\"  >删除</a>\n"
                       }
                    if (userid!=obj.user.userID) {
                        content+= "                                    <a href=\"javascript:void(0);\" onclick=\"plkk('" + obj.user.userID + "','" + obj.user.userName + "','" + obj.tcommentID + "')\">&nbsp;&nbsp;&nbsp;&nbsp;回复:&nbsp;&nbsp;&nbsp;&nbsp;</a><a href='javascript:void(0);'  onclick=\"delpl('" + obj.tcommentID + "','" + obj.tcommentFid + "' )\"  ></a>\n"
                    }
                       content+= "                                </div>\n" +
                        "                            </div>\n" +
                        "                    </span>\n" +
                        "\n" +
                        "                    <div class=\"answer-content markdown-body\">\n" +
                        "                         " + obj.tcommentContent + "\n" +
                        "                    </div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "                    \n" +
                        "\n" +
                        "\n" +
                        "                        <div id=\"" + obj.tcommentID + "\" style=\"display:none\">\n"

                    $.each(data, function (j, obj2) {
                        if (obj.tcommentID==obj2.tcommentFid) {
                            content += "<div class=\"answer-head\">\n" +
                                "                                    <div class=\"user-avatar \">\n" +
                                "                                        <a class=\"avatar\" href=\"/user/212008\" target=\"_blank\">\n" +
                                "                                            <img src=\""+obj2.user.userImg+"\">\n" +
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
                                "                                            <span>(" + obj2.tcommentTime + "&nbsp;&nbsp;&nbsp;&nbsp;)</span>\n"
                               if (userid==obj2.user.userID) {
                                   content += "                                            <a href=\"javascript:void(0);\" onclick=\"plkk('" + obj2.user.userID + "','" + obj2.user.userName + "','" + obj2.tcommentFid + "')\">回复:&nbsp;&nbsp;&nbsp;&nbsp;</a><a href=\"javascript:void(0);\"  onclick=\"delpl('" + obj2.tcommentID + "','" + obj2.tcommentFid + "' )\" >删除</a>\n"
                               }
                            if (userid!=obj2.user.userID) {
                                content += "                                            <a href=\"javascript:void(0);\" onclick=\"plkk('" + obj2.user.userID + "','" + obj2.user.userName + "','" + obj2.tcommentFid + "')\">回复:&nbsp;&nbsp;&nbsp;&nbsp;</a><a href=\"javascript:void(0);\"  onclick=\"delpl('" + obj2.tcommentID + "','" + obj2.tcommentFid + "' )\" ></a>\n"
                            }
                               content+= "\n" +
                                "\n" +
                                "                                        </div>\n" +
                                "                                    </div>\n" +
                                "                            </span>\n" +
                                "\n" +
                                "                                    <div class=\"answer-content markdown-body\">\n" +
                                "                                       " + obj2.tcommentContent + "\n" +
                                "\n" +
                                "                                    </div>\n"

                        }
                    });
                    content += "                        </div>\n"
                    content += "                               \n" +
                        "                        <hr/>\n" +
                        "                        \n" +
                        "                    \n" +
                        "                </div>\n" +
                        "                </div>\n"


                }

            });

            $("#pp").html(content);

        },
        error: function (jqXHR) {
            alert("发生错误：" + jqXHR.status);
        }
    });
}




//查看点赞记录
function querydz() {
    //如果用户id不为空时
    if (userid!=null) {
        $.ajax({
            type: "post",
            url: "/pst/querygive",
            dataType: "json",
            data: {
                id: id
            },
            success: function (data) {
                //查找数据库，如果要是无点赞记录，则给他点赞
                if (data>0){
                    $("#tb").css({color: "red"});
                }
                //如果数据库有的话，则改变样式
                if(data==0)  {
                    $("#tb").css({color: ""});
                }
            },
            error: function (jqXHR) {
                alert("发生错误：" + jqXHR.status);
            }
        });
    }

}


//加载帖子信息
function jztzxx() {
    $.ajax({
        type: "post",
        url: "/pst/tzxx",
        dataType: "json",
        data: {
            id: id
        },
        success: function (data) {
            userid = data.l;//用户id

            tieid = data.c;//给全局变量帖子uid赋值
            var content = "";
            content += "<div class=\"question-headline\">\n" +
                "\n" +
                "        <span  class=\"question-title\">" + data.d + "</span>\n" +
                "        <span class=\"question-figure\">浏览量" + data.j + "</span><span class=\"question-figure\" id='dzl'>"+data.k+"</span><span class=\"question-figure\" ><a href='javascript:void(0);' onclick='dz()'><i class=\"layui-icon\" style=\"font-size: 30px;\" id='tb'>&#xe6c6;</i></a> </span>\n" +
                "    </div>\n" +
                "    <div class=\"question-author\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "        <div class=\"user-avatar \">\n" +
                "        <a class=\"avatar\" href=\"/user/347060\" target=\"_blank\">\n" +
                "        <img src=\""+data.mg+"\">\n" +
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
                "        " + data.f + "\n" +
                "        </a>\n" +
                "        <span class=\"user-level\">L4</span>\n" +
                "        </div>\n" +
                "\n" +
                "        <span>" + data.i + "</span>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "   <button type=\"button\" class=\"layui-btn  layui-btn-sm\" id='sc' onclick='ghg()'>收藏</button>\n" +
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
        error: function (jqXHR) {
            alert("发生错误：" + jqXHR.status);
        }
    });
}




//查询是否已经收藏了
function sc() {
    if(userid!=null) {//如果id不为空的话就查找
        $.ajax({
            type: "post",
            url: "/pst/queryCollection",
            dataType: "json",
            data: {
                id: id
            },
            success: function (data) {
                if (data > 0) {
                    $("#sc").text("取消收藏");
                }
                if (data == 0) {
                    $("#sc").text("收藏");
                }
            },
            error: function (jqXHR) {
                alert("发生错误：" + jqXHR.status);
            }

        });
    }
}





































