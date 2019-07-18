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
</script>
</html>