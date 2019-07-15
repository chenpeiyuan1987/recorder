<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Hello, I'm recorder!</h1>
    <h2>Result: ${result}</h2>
</body>

<#include "/script.ftl"/>
<script>
    layui.use(['layer'], function (layer) {
        layer.msg('加载layui成功!');
    });
</script>
</html>