<%--
  Created by IntelliJ IDEA.
  User: 86185
  Date: 2019/6/28
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>第一个 Highcharts 图表</title>
</head>
<body>
<!-- 图表容器 DOM -->
<div id="container" style="width: 600px;height:400px;"></div>
<!-- 引入 highcharts.js -->
<script src="http://cdn.highcharts.com.cn/highcharts/highcharts.js"></script>
<script>
    // 图表配置
    var options = {
        chart: {
            type: 'bar'                          //指定图表的类型，默认是折线图（line）
        },
        title: {
            text: '我的第一个图表'                 // 标题
        },
        xAxis: {
            categories: ['苹果', '香蕉', '橙子']   // x 轴分类
        },
        yAxis: {
            title: {
                text: '吃水果个数'                // y 轴标题
            }
        },
        series: [{                              // 数据列
            name: '小明',                        // 数据列名
            data: [1, 0, 4]                     // 数据
        }, {
            name: '小红',
            data: [5, 7, 3]
        }]
    };
    // 图表初始化函数
    var chart = Highcharts.chart('container', options);
</script>
</body>
</html>