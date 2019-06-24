    $(function () {

        /**
         * 绑定学习路径数据
         */
        $.ajax({
            type:"GET",
            url:"/user/CourseEight",
            dataType:"json", //预期服务器返回数据的类型
            success:function(data){
                mydata(data);
            }
        })
    })

//绑定体系数据
function mydata(data){
    var objs=data.data;
    var str="";
    for (var i=0;i<objs.length;i++){
        str+="<div class=\"col-xs-12 col-sm-6 col-md-4\">" +
            "<a href=\"/user/resourseShow?courseId="+objs[i].systemID+"\">" +
            "<div class=\"path-item\">" +
            "<div class=\"col-xs-5 col-sm-4 path-img\">" +
            "<img src="+objs[i].systemImg+">" +
            "</div>" +
            "<div class=\"col-xs-7 col-sm-8\">" +
            "<div class=\"path-name\">"+objs[i].systemName+"</div>" +
            "<div class=\"path-course-num\">" +
            "</span>"+objs[i].sourcesNum+"</span>" +
            "门课程" +
            "</div>" +
            "</div>" +
            "<div class=\"desc-layer\">" +
            "<div class=\"center\">"+objs[i].systemDesc+"</div>" +
            "</div>" +
            "</div>" +
            "</a>" +
            "</div>";
    }
    //console.log(str);
    $("#course").html(str);
}
