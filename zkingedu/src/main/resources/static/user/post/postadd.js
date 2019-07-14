layui.use(['table', 'layer', 'jquery', 'layedit', 'laydate'], function () {
    var table = layui.table
        , form = layui.form,
        layer = layui.layer
        , layedit = layui.layedit
        , laydate = layui.laydate;
    var $ = layui.jquery;


    //创建一个编辑器
    var editIndex = layedit.build('LAY_demo_editor');


    /*************************************************************************************************/
    //表单提交
    form.on('submit(demo1)', function (data) {
        //获取整个内容的值
        var postContent = $("#editor").val();

        $.ajax({
            type: "post",
            url: "/pst/addpost",
            dataType: "json",
            data: {
                postName: $("#postName").val(),
                zt: data.field.postState,
                postCategory: $("#ca").val(),
                postContent: postContent
            },
            success: function (data) {
                if (data > 0) {
                    parent.layer.msg('发布成功！', {icon: 1, time: 10000, shade: 0.2});
                    /*layer.close("iframe");*/
                    location.href = "/pst/aa";//跳转界面
                } else {
                    parent.layer.msg('发布失败！', {icon: 2, time: 3000, shade: 0.2});
                    layer.close("iframe");
                }
            },
            error: function (jqXHR) {
                alert("发生错误：" + jqXHR.status);
            }
        });
        return false;


    });
    /*****************************************************************************************************/
//

    //监听表格复选框选择
    table.on('checkbox(demo)', function (obj) {
        console.log(obj)
    });

//点击按钮的时候弹出一个页面
    $("#fb").click(function () {
        if ($("#editor").val() != "") {
            //开始调用数据库的类型
            $.ajax({
                type: "post",
                url: "/pst/getpcs",
                success: function (data) {
                    var content = '<select name="postCategory" lay-verify="required" id="ca">';
                    content += '<option value="">请选择</option>';
                    $.each(data, function (i, obj) {
                        content += '<option value="' + obj.pcid + '">' + obj.pcname + '</option>';
                    });
                    content += '</select>';
                    $("#xlk").html(content);
                    form.render('select');//渲染
                },
                error: function (jqXHR) {
                    alert("发生错误：" + jqXHR.status);
                }
            });


            layer.open({
                type: 1,
                title: "发布文章",
                area: ['500px', '530px'],
                content: $("#formTable"),//引用的弹出层的页面层的方式加载修改界面表单
                maxmin: true,
            });
        }
        else {
            parent.layer.msg('请填写发布内容！', {icon: 2, time: 3000, shade: 0.2});
        }

    });


    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});