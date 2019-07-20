<!--操作数据的script 方法-->

$(function () {

    $.post('/user/findinteginte',function (data) {
        var jf = $("#jfs").val();
        var sa = data.jfs;
        var a = jf+=sa;
        $("#jfs").html(a);
    });




    /**
     * 获取消息的记录数   messageCount
     * 阿飘
     */
    $.post("/messageCount", function (data) {
        var ss = $("#messa").val();
        //alert(data.mess);
        var sa = data.mess;
        var a = ss += sa;
        //alert(a);
        if (parseInt(a) == 0) {
            $("#sss").html("  ");
        } else {
            $("#sss").html(a);
        }
    });


    layui.use(['layer', 'laypage', 'flow'], function () {
        var layer = layui.layer;
        var laypage = layui.laypage;
        var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
        var flow = layui.flow;

        /*订单的展示*/

        $("#dingdan").click(function () {

            var config = {page: 1, limit: 2};
            var url = "/orderUid";
            $.getJSON(url, config, function (res) {
                //alert(res.count)
                laypage.render({
                    elem: 'page3',//必须放id
                    count: 100,
                    first: '首页',
                    last: '尾页',
                    count: res.count, //总条数
                    limit: config.limit, //每页条数
                    theme: '#FFB800', //自定义颜色
                    jump: function (obj, first) {
                        //alert(obj.curr)
                        if (!first) { //首次则不进入
                            config.page = obj.curr;
                            getUserListByPage(url, config);
                        }
                    }
                });
                parseUserList(res, config.page);
            });


            //点击页数从后台获取数据
            function getUserListByPage(url, config) {
                $.getJSON(url, config, function (res) {
                    parseUserList(res, config.page);
                });
            }

            //解析数据，currPage参数为预留参数，当删除一行刷新列表时，可以记住当前页而不至于显示到首页去
            function parseUserList(res, currPage) {
                //alert(res.count)
                var content = "";
                $.each(res.data, function (i, o) {
                    //alert(o.billType);
                    content += '<tr>';
                    content += '<td>' + o.order_id + '</td>';
                    content += '<td>' + o.course_name + '</td>';
                    content += '<td>' + o.order_integral + '</td>';
                    content += '<td>' + o.charge_time + '</td>';
                    //content += '<td>'+o.order_state+'</td>';
                    content += '</tr>';
                });
                $('#wddd').html(content);
                if (res.count == 0) {
                    $("#wddd").html("<span style='font-size: 20px;color: red; align-content: center'>暂无数据！</span>");
                }
            }

        });


        /*账单的展示*/

        $("#zhangdan").click(function () {

            var config = {page: 1, limit: 2};
            var url = "/findBill";
            $.getJSON(url, config, function (res) {
                //alert(res);
                laypage.render({
                    elem: 'page1',//必须放id
                    count: 100,
                    first: '首页',
                    last: '尾页',
                    count: res.count, //总条数
                    limit: config.limit, //每页条数
                    theme: '#FFB800', //自定义颜色
                    jump: function (obj, first) {
                        //alert(obj.curr)
                        if (!first) { //首次则不进入
                            config.page = obj.curr;
                            getUserListByPage(url, config);
                        }
                    }
                });
                parseUserList(res, config.page);
            });


            //点击页数从后台获取数据
            function getUserListByPage(url, config) {
                $.getJSON(url, config, function (res) {
                    parseUserList(res, config.page);
                });
            }

            //解析数据，currPage参数为预留参数，当删除一行刷新列表时，可以记住当前页而不至于显示到首页去
            function parseUserList(res, currPage) {
                //alert(res.data)
                var content = "";
                $.each(res.data, function (i, o) {
                    //alert(o.billType);
                    content += '<tr>';
                    content += '<td>' + o.billIntegral + '</td>';
                    content += '<td>' + o.billContent + '</td>';
                    content += '<td>' + o.billTime + '</td>';
                    if (o.billType == 1) {
                        content += '<td style="color: royalblue">收入积分</td>';
                    } else {
                        content += '<td style="color: red">支出积分</td>';
                    }
                    content += '</tr>';
                });
                $('#shuj').html(content);
                if (res.count == 0) {
                    $("#shuj").html("<span style='font-size: 20px;color: red; align-content: center'>暂无数据！</span>");
                }
            }
        });


        /*我的充值记录*/

        $("#chji").click(function () {
            var config = {page: 1, limit: 2};
            var url = "/findCharge";
            $.getJSON(url, config, function (res) {
                //alert(res);
                laypage.render({
                    elem: 'page2',//必须放id
                    count: 100,
                    first: '首页',
                    last: '尾页',
                    count: res.count, //总条数
                    limit: config.limit, //每页条数
                    theme: '#FFB800', //自定义颜色
                    jump: function (obj, first) {
                        //alert(obj.curr)
                        if (!first) { //首次则不进入
                            config.page = obj.curr;
                            getUserListByPage(url, config);
                        }
                    }
                });
                parseUserList(res, config.page);
            });


            //点击页数从后台获取数据
            function getUserListByPage(url, config) {
                $.getJSON(url, config, function (res) {
                    parseUserList(res, config.page);
                });
            }

            //解析数据，currPage参数为预留参数，当删除一行刷新列表时，可以记住当前页而不至于显示到首页去
            function parseUserList(res, currPage) {
                //alert(res.data)
                var content = "";
                $.each(res.data, function (i, o) {
                    //alert(o.billType);
                    content += '<tr>';
                    content += '<td>' + o.chargeIntegral + '</td>';
                    content += '<td>' + o.chargeMoney + '</td>';
                    content += '<td>' + o.chargeTime + '</td>';
                    content += "<td><a href='javascript:void(0);' onclick='schu(\"" + o.chargeID + "\")'>删除</a></td>";
                    content += '</tr>';
                });
                $('#cjjl').html(content);
                if (res.count == 0) {
                    $("#cjjl").html("<span style='font-size: 20px;color: red; align-content: center'>暂无数据！</span>");
                }
            }
        });

        /*
         * 我的资源模块
         */
        $("#haoyou").click(function () {
            var config = {page: 1, limit: 4};
            var url = "/findziyuanuid";
            $.getJSON(url, config, function (res) {
                //alert(res);
                laypage.render({
                    elem: 'page4',//必须放id
                    count: 100,
                    first: '首页',
                    last: '尾页',
                    count: res.count, //总条数
                    limit: config.limit, //每页条数
                    theme: '#FFB800', //自定义颜色
                    jump: function (obj, first) {
                        //alert(obj.curr)
                        if (!first) { //首次则不进入
                            config.page = obj.curr;
                            getUserListByPage(url, config);
                        }
                    }
                });
                parseUserList(res, config.page);
            });


            //点击页数从后台获取数据
            function getUserListByPage(url, config) {
                $.getJSON(url, config, function (res) {
                    parseUserList(res, config.page);
                });
            }

            //解析数据，currPage参数为预留参数，当删除一行刷新列表时，可以记住当前页而不至于显示到首页去
            function parseUserList(res, currPage) {
                //alert(res.data);
                var content = "";
                $.each(res.data, function (i, o) {
                    if (o.order_cid == 0) {
                        content += '<div class="col-md-3  course" ><a class="course-box" href="/user/showCourse/' + o.order_sid + '">';
                    } else {
                        content += '<div class="col-md-3  course" ><a class="course-box" href="/user/showVideo/' + o.order_sid + '/' + o.order_cid + '">';
                    }
                    content += '<div class="sign-box">';
                    content += '<i class="fa fa-star-o course-follow pull-right" data-follow-url="/courses/20/follow" data-unfollow-url="/courses/20/unfollow" style="display:none"></i>';
                    content += '</div>';
                    content += '<div class="course-img">';
                    content += '<img alt="' + o.course_name + '" src="' + o.course_img + '">';
                    content += '</div>';
                    content += '<div class="course-body">';
                    content += '<span class="course-title" data-toggle="tooltip" data-placement="bottom" title="' + o.course_name + '">' + o.course_name + '</span>';
                    content += '</div>';
                    content += '</a></div>';
                });
                $('#ziyuan').html(content);
                if (res.count == 0) {
                    $("#ziyuan").html("<span style='font-size: 20px;color: red; align-content: center'>暂无数据！</span>");
                }
            }
        });


        /**
         * 消息中心的模块
         */
        $("#xiaoxi").click(function () {
            flow.load({
                elem: '#message' //流加载容器
                , done: function (page, next) { //执行下一页的回调
                    //模拟数据插入
                    $.post("/findMessage", {page: page, limit: 2}, function (res) {
                        //alert(res);
                        var lis = [];
                        layui.each(res.data, function (i, o) {
                            var content = '<div class="answer-item" >';
                            content += '<div class="answer-head">';
                            content += '<div class="user-avatar ">';
                            content += '<a class="avatar" href="/user/userinfo/index" target="_blank">';
                            content += '<img src="' + o.user_img + '">';
                            content += '</a>';
                            content += '</div>';
                            content += '</div>';
                            content += '<div class="answer-detail">';
                            content += '<span class="comment-reply">';
                            content += '<div class="user-username ">';
                            content += '<a class="username" href="javascript:void(0);" style="margin-left: 20px;" target="_blank">' + o.message_name + '</a>';
                            content += '</div>';
                            content += '</span>';
                            content += '<div class="answer-content markdown-body">';
                            content += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='ydzt(\"" + o.message_id + "\")' style='color: #000000'>" + o.message_content + "</a>";
                            content += '</div>';
                            content += '<div>';
                            content += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="create-time">时间：<span>' + o.message_time + '</span></span>';
                            if (o.message_state == 1) {
                                content += '<span style="margin-left: 600px; color: red">未读消息</span>';
                            } else {
                                content += '<span style="margin-left: 600px; color: #9E792E">已读消息</span>';
                            }
                            content += '<hr/>';
                            content += '</div>';
                            content += '</div>';
                            lis.push(content);
                        });
                        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                        //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                        next(lis.join(''), page < res.count); //假设总页数为 10
                    });
                }
            });

        });


        /**
         *点击查看收藏的课程
         * yan
         */

        $("#shoucang").click(function () {
            var config = {page: 1, limit: 3};
            var url = "/hoarding/getCourseHoarding";//请求地址
            $.getJSON(url, config, function (res) {
                //alert(res);
                laypage.render({
                    elem: 'pagess',//必须放id
                    count: 100,
                    first: '首页',
                    last: '尾页',
                    count: res.count, //总条数
                    limit: config.limit, //每页条数
                    theme: '#FFB800', //自定义颜色
                    jump: function (obj, first) {
                        //alert(obj.curr)
                        if (!first) { //首次则不进入
                            config.page = obj.curr;
                            getUserListByPage(url, config);
                        }
                    }
                });
                parseUserList(res, config.page);
            });


            //点击页数从后台获取数据
            function getUserListByPage(url, config) {
                $.getJSON(url, config, function (res) {
                    parseUserList(res, config.page);
                });
            }

            //解析数据，currPage参数为预留参数，当删除一行刷新列表时，可以记住当前页而不至于显示到首页去
            function parseUserList(res, currPage) {
                //alert(res.data)
                var content = "";
                $.each(res.data, function (i, o) {
                    //alert(o.billType);
                    content += "<div class=\"col-md-4 col-sm-6  course\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<a class=\"course-box\" href=\"/user/showCourse/" + o.course_id + "\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<div class=\"sign-box\">\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-star-o course-follow pull-right\"\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t   data-follow-url=\"/courses/20/follow\"\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t   data-unfollow-url=\"/courses/20/unfollow\"  style=\"display:none\"  ></i>\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<div class=\"course-img\">\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<img alt=\"" + o.course_name + "\" src=\"" + o.course_img + "\">\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<div class=\"course-body\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"course-title\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"" + o.course_name + "\">" + o.course_name + "</span>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<div class=\"course-footer\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"course-per-num pull-left\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-users\"></i>\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t" + o.course_num + "\n" +
                        "\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</a>\n" +
                        "\t\t\t\t\t\t\t\t\t</div>";
                });
                $('#datas').html(content);
                if (res.count == 0) {
                    $("#datas").html("<span style='font-size: 20px;color: red; align-content: center'>暂无数据！</span>");
                }
            }
        });

    });


});


/*删除的操作*/
function schu(chargeID) {
    layui.use('layer', function () {
        var layer = layui.layer;

        layer.confirm('您确定要删除吗?', function (index) {
            $.post("/updateState", {chargeID: chargeID}, function (data) {
                if (data == 1) {
                    parent.layer.msg('删除成功！', {icon: 16, time: 2000, shade: 0.2});
                    // 停顿一秒后,进行刷新详情页面.
                    setTimeout(function () {
                        location.reload();
                    }, 1000);
                } else {
                    layer.msg("删除失败！", {icon: 5});
                }
            });
            layer.close(index);

        });
    });
}


/**
 * 修改未读状态为已读状态
 * 阿飘
 */
function ydzt(message_id) {
    var messageID = message_id;
    //alert(messageID);
    $.post('/updateMessageState', {messageid: messageID}, function (data) {
        //alert(data);
        window.location.href = '/pst/userinfo/' + data;
    });


}