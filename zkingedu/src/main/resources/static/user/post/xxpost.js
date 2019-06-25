$(function () {


    var  id="";
    var url=window.location.search; //获取url中"?"符后的字串  
    if(url.indexOf("?")!=-1){
        id = url.substr(url.indexOf("=")+1);

    }

    //调用ajax
    $.ajax({
        type:"post",
        url:"/pst/tzxx",
        dataType:"json",
        data:{
           id:id
        },
        success:function(data){
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
    $.ajax({
        type: "post",
        url: "/pst/getAllTs",//请求接口
        dataType: "json",
        data:{
            id:id
        },
        success: function (data) {
            alert(data);
            var content="<div class=\"answer-item\">";
            $.each(data, function (i, obj) {
                content+= "<div class=\"answer-head\">\n" +
                    "                            <div class=\"user-avatar \">\n" +
                    "                                        <a class=\"avatar\" href=\"/user/212008\" target=\"_blank\">\n" +
                    "                                            <img src=\"https://dn-simplecloud.shiyanlou.com/gravatarNTY0MzE5MjI0Njgz.png?v=1477283063583&amp;imageView2/1/w/200/h/200\">\n" +
                    "                                        </a>\n" +
                    "                        \n" +
                    "                                        <a class=\"member-icon\" href=\"/vip\" target=\"_blank\">\n" +
                    "                        \n" +
                    "                                <img src=\"\n" +
                    "                                            https://static.shiyanlou.com/img/vip-icon.png\n" +
                    "                                          \">\n" +
                    "                        \n" +
                    "                                        </a>\n" +
                    "                        \n" +
                    "                            </div>\n" +
                    "                     </div>\n" +
                    "              <div class=\"answer-detail\">\n" +
                    "                    <span class=\"comment-reply\">\n" +
                    "                            <div class=\"user-username \">\n" +
                    "                                <a class=\"username\" href=\"/user/212008\" target=\"_blank\">\n" +
                    "\n" +
                    "                                        米斯特_周\n" +
                    "\n" +
                    "\n" +
                    "                                </a>\n" +
                    "                                <span class=\"user-level\">L234</span>\n" +
                    "                            </div>\n" +
                    "                    </span>\n" +
                    "\n" +
                    "                    <div class=\"answer-content markdown-body\">\n" +
                    "                            <textarea style=\"display:none;\">开始实验后，如图所示：![图片描述](https://dn-simplecloud.shiyanlou.com/uid/212008/1483688692465.png-wm)\n" +
                    "\n" +
                    "                             </textarea>\n" +
                    "                    </div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                    <div>\n" +
                    "                        <span class=\"create-time\">21小时前</span>\n" +
                    "\n" +
                    "                        <hr/>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "  </div>"
            });
            /*$("#pp").html(content);*/
        },
        error: function (jqXHR) {
            alert("发生错误：" + jqXHR.status);
        }
    });






























});












































