<!DOCTYPE html>
<#import "/frame.ftl" as f/>

<html lang="en">
<head>
    <@f.head admin=false/>
</head>

<#macro text label name verify="required">
<div class="layui-form-item">
    <label class="layui-form-label" style="font-size:.9em;">${label}</label>
    <div class="layui-input-block">
        <input type="text" name="${name}" lay-verify="${verify}" autocomplete="off" class="layui-input">
    </div>
</div>
</#macro>

<body>
    <div class="layui-form layui-form-pane" style="margin:20px 20px 0 20px;">
        <@text label="任务标题" name="title" />

        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@text label="任务类型" name="type" />
            </div>
            <div class="layui-col-xs6">
                <@text label="任务级别" name="level" />
            </div>
        </div>

        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@text label="预计开始时间" name="expectStartTime" />
            </div>
            <div class="layui-col-xs6">
                <@text label="预计耗时" name="expectElapse" />
            </div>
        </div>
        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@text label="预计结束时间" name="expectStartTime" />
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">任务简介</label>
            <div class="layui-input-block">
                <textarea name="intro" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>

        <#--
        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@text label="预计结束时间" name="expectFinisTime" />
            </div>
            <div class="layui-col-xs6">
                <@text label="实际耗时" name="actualElapse" />
            </div>
        </div>
        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@text label="启动时间" name="startTime" />
            </div>
            <div class="layui-col-xs6">
                <@text label="暂停时间" name="pauseTime" />
            </div>
        </div>
        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@text label="提交时间" name="submitTime" />
            </div>
            <div class="layui-col-xs6">
                <@text label="完成时间" name="finisTime" />
            </div>
        </div>
        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@text label="创建时间" name="createTime" />
            </div>
            <div class="layui-col-xs6">
                <@text label="任务状态" name="status" />
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">任务报告</label>
            <div class="layui-input-block">
                <textarea name="report" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        -->
    </div>
</body>

<@f.script/>
</html>