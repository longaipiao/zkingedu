
//类别id
var cid="";
//内容
var nr="";
//分类长度
var cd;

//记录上次的样式
var jl="";


//调用分类的方法

function hh(aa){
    //给之前的那个设置为空
     $("#"+jl+"").attr("class","");

    $("#"+aa+"").attr("class","active");



    //给之前的赋值“”
    $("#Linddd").html("");
     cid=aa;
     jz();//开始调用加载
    jl=aa;
}




//调用搜索的方法
$("#tzss").click(function () {
    //给原来的赋值为空，才能刷新
    $("#Linddd").html("");
    //开始获取值
    nr=$("#sstz").val();
    //开始调用方法
    jz();
    nr="";
    $("#sstz").val("");


});



$(function () {
    //如果搜索不为空
    if($("#cgldez").val()==""){

        jz();
    }

    if($("#cgldez").val()!=""){
        //给原来的赋值为空，才能刷新
        $("#Linddd").html("");
        //开始获取值
        nr=$("#cgldez").val();
        //开始调用方法
        jz();
        //nr="";
    }


    //加载分类
    hq();

    //调用加载方法

        //开始




});

//
function jz() {
    layui.use('flow', function(){
        var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
        var flow = layui.flow;
    flow.load({
        elem: '#Linddd' //流加载容器
        , done: function (page, next) { //执行下一页的回调
            //模拟数据插入
            $.post("/pst/getTcomments", {cid: cid, nr: nr, page: page, pageSize: 10}, function (res) {
                var content = "";
                var lis = [];
                layui.each(res.data, function (index, item) {

                    var content = "<li class=\"question-item\" >\n" +
                        "        <div class=\"col-md-10\">\n" +
                        "        <div class=\"col-sm-2 question-item-author\">\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "        <div class=\"user-avatar \">\n" +
                        "        <a class=\"avatar\" href=\"../user/13/study.html\" target=\"_blank\">\n" +
                        "        <img src=\""+item.user_img+"\">\n" +
                        "        </a>\n" +
                        "\n" +
                        "        </div>\n" +
                        "\n" +
                        "        </div>\n" +
                        "        <div class=\"col-sm-10\">\n" +
                        "        <h4>\n" +
                        "\n" +

                        "\n" +
                        "        <a class=\"question-item-title\" href=\"/pst/userinfo?id=" + item.post_id + "\" target=\"_blank\"> " + item.post_name + "</a>\n" +
                        "\n" +
                        "    </h4>\n" +
                        "    <div class=\"question-item-summary\">\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "        <div class=\"user-username \">\n" +
                        "        <a class=\"avatar\" href=\"/user/user/13/study.html\" target=\"_blank\">\n" +
                        "\n" +
                        "        " + item.user_name + "\n" +
                        "\n" +
                        "\n" +
                        "        </a>\n" +

                        "        </div>\n" +
                        "\n" +
                        "\n" +
                        "        <span class=\"question-item-date\">" + item.post_time + "</span>\n" +
                        "\n" +

                        "\n" +
                        "\n" +
                        "\n" +
                        "        <div class=\"user-username \">\n" +
                        "        <a class=\"username\" href=\"/user/256544\" target=\"_blank\">\n" +
                        "\n" +

                        "\n" +
                        "\n" +
                        "        </a>\n" +

                        "        </div>\n" +
                        "        </span>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "        </div>\n" +
                        "        </div>\n" +
                        "        </div>\n" +
                        "        <div class=\"col-md-2 question-item-rank\">\n" +
                        "        <div class=\"question-item-answered\">\n" +
                        "        <div>" + item.jishu + "</div>\n" +
                        "        <div>评论</div>\n" +
                        "        </div>\n" +
                        "        <div class=\"question-item-views\">\n" +
                        "        <div>" + item.post_num + "</div>\n" +
                        "        <div>浏览量</div>\n" +
                        "        </div>\n" +
                        "        </div>\n" +
                        "        </li>"
                    lis.push(content);
                });

                //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                next(lis.join(''), page < res.count); //假设总页数为 10
            });
        }
    });
    });
}

function hq() {
     $.post("/pst/getpcs",function (res) {
         cd=res.length;
         var content="";
         $.each(res,function (i,obj) {
            content+="<li role=\"presentation\" id='"+obj.pcid+"'>\n" +
                "  <a href=\"javascript:;\"  aria-controls=\"all\" role=\"tab\" toggle=\"tab\" onclick='hh("+obj.pcid+",)'>"+obj.pcname+"</a>\n" +
                "  </li>"
         });
         $("#zl").append(content);
     });

}






























