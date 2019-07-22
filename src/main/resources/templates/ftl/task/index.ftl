<!DOCTYPE html>
<#import "/frame.ftl" as f/>

<html lang="en">
<head>
    <@f.head/>
</head>

<body>
    <div class="layui-fluid">
        <div class="layui-card">
            <div class="layui-card-header">
                搜索条件
            </div>
            <div class="layui-card-body">
                <table id="table" lay-filter="table"></table>
            </div>
        </div>
    </div>
</body>

<@f.script/>
<script src="/js/task/index.js"></script>
<script id="toolbar" type="text/html">
    <a class="layui-btn layui-btn-sm" lay-event="insert">添加</a>
    <a class="layui-btn layui-btn-sm" lay-event="delete">删除</a>
    <a class="layui-btn layui-btn-sm" lay-event="clone">复制</a>
    <a class="layui-btn layui-btn-sm adjust" lay-event="LAYTABLE_COLS">筛选</a>
    <a class="layui-btn layui-btn-sm adjust" lay-event="LAYTABLE_EXPORT">导出</a>
    <a class="layui-btn layui-btn-sm" lay-event="LAYTABLE_PRINT">打印</a>
</script>
<script id="operate" type="text/html">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
</script>
</html>