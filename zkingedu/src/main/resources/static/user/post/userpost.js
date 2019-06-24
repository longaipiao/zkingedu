$(function () {

    //方法二则直接使分用layui-v2.0.2里面的页功能，没有用到core.js里面的方法
    layui.use('laypage', function(){
        var laypage = layui.laypage;
        var config = {page:1,pageSize:5};
        var url = "/pst/getAllposts";
        $.getJSON(url,config,function(res){
            //alert(res);
            laypage.render({
                elem: 'fy',//必须放id
                count: 100,
                first: '首页',
                last: '尾页',
                count: res.count, //总条数
                limit:config.pageSize, //每页条数
                theme: '#FFB800', //自定义颜色
                jump: function(obj, first){
                    //alert(obj.curr)
                    if(!first){ //首次则不进入
                        config.page = obj.curr;
                        getUserListByPage(url,config);
                    }
                }
            });
            parseUserList(res,config.page);
        });
    });

    //点击页数从后台获取数据
    function getUserListByPage(url,config){
        $.getJSON(url,config,function(res){
            parseUserList(res,config.page);
        });
    }
    //解析数据，currPage参数为预留参数，当删除一行刷新列表时，可以记住当前页而不至于显示到首页去
    function parseUserList(res,currPage){
        var content = "";
        $.each(res.data, function (i, o) {
            //alert(o.data);
            content+='<li class="question-item">';
            content+='<div class="col-md-10">';
            content+='<div class="col-sm-2 question-item-author">';
            content+='<div class="user-avatar ">';
            content+='<a class="avatar" href="../user/13/study.html" target="_blank">';

            content+='<img src="https://dn-simplecloud.shiyanlou.com/gravataradmin.png?imageView2/1/w/100/h/100">';
            content+='</a>';
            content+='</div>';
            content+='</div>';
            content+='<div class="col-sm-10">';
            content+='<h4>';
            content+='<img src="/user/img/topQuestion.png">';
            content+='<a  class="question-item-title" href="/pst/userinfo?id='+o.postID+'" target="_blank">"'+o.postName+'"</a>';
            content+='</h4>';
            content+='<div class="question-item-summary">';
            content+='<div class="user-username ">';
            content+='<a class="avatar" href="../user/13/study.html" target="_blank">'+o.postTime+'</a>';
            content+='</div>';
            content+=' </div>';
            content+=' </div>';
            content+=' </div>';

        content+='<div class="col-md-2 question-item-rank">';
            content+='</div>';
            content+='</li>';


        });
        $('#tbody').html(content);
    }












});

