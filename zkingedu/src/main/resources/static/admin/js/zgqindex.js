$(function () {

    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
        var $ = layui.jquery;




//获取所有的菜单动态生成菜单===============================================================
        $.ajax({

            type: "post",
            url: "/admin/getMenus",//请求接口
            dataType: "json",
            contentType: "application/json",//应用类型/json
            success: function (data) {
                /*alert(data.mname);*/
                //先添加所有的主材单
                $.each(data, function (i, obj) {
                    var  content='<li class="layui-nav-item">';
                    content += '<a href="javascript:;" class="site-demo-active"> <i class="layui-icon">'+obj.icons+'</i>' + obj.mname + '</a>';
                    //这里是添加所有的子菜单
                    content += loadchild(obj.menuList);
                    content += '</li>';
                    $(".nav").append(content);
                });
                element.init();
            },
            error: function (jqXHR) {
                alert("发生错误：" + jqXHR.status);
            }
        });

        //组装子菜单的方法
        function loadchild(obj) {
            //alert(obj[0].treeTitile);
            if (obj == null) {
                return;
            }

            var content = '';
            if (obj != null && obj.length > 0) {
                content += '<dl class="layui-nav-child">';
            } else {
                content += '<dl>';
            }

            if (obj != null && obj.length > 0) {
                $.each(obj, function (i, note) {
                    content += '<dd>';
                    content += '<a href="javascript:void(0)" data-url="' + note.href + '" data-title="' + note.mname + '" data-id="' + note.mid + '" name="item" class="site-demo-active" ><i class="layui-icon">'+note.icons+'</i>' + note.mname + '</a>';
                    if (note == null) {
                        return;
                    }
                    //content+=loadchild(note);
                    content += '</dd>';
                });
                content += '</dl>';
            }
            return content;
        };

        //=======================================================================


    });
});


