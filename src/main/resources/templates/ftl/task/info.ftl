<!DOCTYPE html>
<#import "/frame.ftl" as f/>
<#import "/form.ftl" as m/>

<html lang="en">
<head>
    <@f.head admin=false/>
</head>

<body>
    <div class="layui-card">
        <div class="layui-card-header">
            <div class="layui-btn-group">
                <a class="layui-btn layui-btn-sm" func="start">启动</a>
                <a class="layui-btn layui-btn-sm" func="pause">暂停</a>
                <a class="layui-btn layui-btn-sm" func="submit">提交</a>
                <a class="layui-btn layui-btn-sm" func="finish">完成</a>
            </div>
        </div>
        <div class="layui-card-body">
            <div class="layui-form layui-form-pane" lay-filter="info">
                <@m.text label="任务标题" name="title" />

                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs6">
                        <@m.text label="任务状态" name="statusName" />
                    </div>
                    <div class="layui-col-xs6">
                        <@m.text label="任务级别" name="levelName" />
                    </div>
                </div>

                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs6">
                        <@m.text label="预计耗时" name="expectElapse" />
                    </div>
                    <div class="layui-col-xs6">
                        <@m.text label="实际耗时" name="actualElapse" />
                    </div>
                </div>

                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs6">
                        <@m.text label="任务类型" name="typeName" />
                    </div>
                    <div class="layui-col-xs6">
                        <@m.text label="创建时间" name="createTime" />
                    </div>
                </div>

                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs6">
                        <@m.text label="启动时间" name="startTime" />
                    </div>
                    <div class="layui-col-xs6">
                        <@m.text label="暂停时间" name="pauseTime" />
                    </div>
                </div>

                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs6">
                        <@m.text label="提交时间" name="submitTime" />
                    </div>
                    <div class="layui-col-xs6">
                        <@m.text label="完成时间" name="finishTime" />
                    </div>
                </div>

                <div class="layui-row layui-col-space5">
                    <div class="layui-col-xs6">
                        <@m.text label="预计开始时间" name="expectStartTime" />
                    </div>
                    <div class="layui-col-xs6">
                        <@m.text label="预计结束时间" name="expectFinisTime" />
                    </div>
                </div>

                <@m.textarea label="任务简介" name="intro" />
                <@m.textarea label="总结报告" name="report" />
            </div>
        </div>
    </div>
</body>

<@f.script/>
<script src="/js/task/info.js"></script>
</html>