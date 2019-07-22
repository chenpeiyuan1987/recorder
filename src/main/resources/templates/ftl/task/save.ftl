<!DOCTYPE html>
<#import "/frame.ftl" as f/>
<#import "/form.ftl" as m/>

<html lang="en">
<head>
    <@f.head admin=false/>
</head>

<body>
    <div class="layui-form layui-form-pane" lay-filter="save" style="margin:20px 20px 0 20px;">
        <@m.text label="任务标题" name="title" />

        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@m.select label="任务类型" name="type" />
            </div>
            <div class="layui-col-xs6">
                <@m.select label="任务级别" name="level" />
            </div>
        </div>

        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@m.text label="预计开始时间" name="expectStartTime" />
            </div>
            <div class="layui-col-xs6">
                <@m.text label="预计耗时" name="expectElapse" />
            </div>
        </div>

        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@m.text label="预计结束时间" name="expectFinisTime" attrs="disabled"/>
            </div>
        </div>

        <@m.textarea label="任务简介" name="intro" />
        <@m.textarea label="总结报告" name="report" />

        <div class="layui-form-item layui-hide">
            <button class="layui-btn" lay-submit lay-filter="save">确认</button>
        </div>
    </div>
</body>

<@f.script/>
<script src="/js/task/save.js"></script>
</html>