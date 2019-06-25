$(function () {

    //方法二则直接使分用layui-v2.0.2里面的页功能，没有用到core.js里面的方法
    layui.use('laypage', function(){
        var laypage = layui.laypage;
        var config = {page:1,pageSize:9};
        var url = "/findTool";
        $.getJSON(url,config,function(res){
            //alert(res);
            laypage.render({
                elem: 'page1',//必须放id
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
       //alert(res.data)
        var content = "";
        $.each(res.data, function (i, o) {
            //alert(o.data);
            content += '<div class="col-md-4">';
            content += '<a class="media" href="'+o.toolURL+'" target="_blank">';
            content += '<div class="media-left">';
            content += '<img class="media-object" src="'+o.toolImg+'">';
            content += '</div>';
            content += '<div class="media-body">';
            content += '<p>'+o.toolName+'</p>';
            content += '<h4>'+o.toolDescribe+'</h4>';
            content += '</div>';
            content += '</a>';
            content += '</div>';
        });
        $('#tbody').html(content);
    }

});