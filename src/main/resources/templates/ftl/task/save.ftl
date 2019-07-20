<!DOCTYPE html>
<#import "/frame.ftl" as f/>

<html lang="en">
<head>
    <@f.head admin=false/>
</head>

<#macro text label name verify="required" attrs="">
<div class="layui-form-item">
    <label class="layui-form-label" style="font-size:.9em;">${label}</label>
    <div class="layui-input-block">
        <input type="text" name="${name}" lay-verify="${verify}" autocomplete="off" class="layui-input" ${attrs}>
    </div>
</div>
</#macro>

<#macro select label name verify="required">
<div class="layui-form-item">
    <label class="layui-form-label" style="font-size:.9em;">${label}</label>
    <div class="layui-input-block">
        <select name="${name}" lay-verify="${verify}">
            <option value="">请选择</option>
        </select>
    </div>
</div>
</#macro>

<body>
    <div class="layui-form layui-form-pane" style="margin:20px 20px 0 20px;">
        <@text label="任务标题" name="title" />

        <div class="layui-row layui-col-space5">
            <div class="layui-col-xs6">
                <@select label="任务类型" name="type" />
            </div>
            <div class="layui-col-xs6">
                <@select label="任务级别" name="level" />
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
                <@text label="预计结束时间" name="expectFinisTime" attrs="readonly"/>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">任务简介</label>
            <div class="layui-input-block">
                <textarea name="intro" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <button class="layui-btn" lay-submit lay-filter="save">确认</button>
        </div>
    </div>
</body>

<@f.script/>
<script>
    layui.use(['jquery', 'form', 'laydate', 'kit'], function ($, form, laydate, kit) {

        // 设置选择项
        let names = ['type', 'level'];
        $.ajax({
            url: '/api/task/cst',
            type: 'post',
            data: {
                names: names
            },
            traditional: true,
            success: function (res) {
                if (res.code !== 0) {
                    return;
                }
                // 设置选项
                let data = res.data;
                names.forEach(function (name) {
                    let $select = $('select[name="' + name + '"]');
                    $.each(data[name], function (value, title) {
                        let $option = $('<option>');
                        $option.text(title).val(value);
                        $select.append($option);
                    });
                });
                // 更新渲染
                form.render('select');
            }
        });

        // 设置时间控件
        let $expectElapse = $('[name="expectElapse"]');
        let $expectStartTime = $('[name="expectStartTime"]');
        laydate.render({
            elem: $expectStartTime.selector,
            type: 'datetime',
            value: new Date(),
            min: new Date().getTime(),
            done: function(value){
                if ($expectElapse.val()) {
                    setExpectStartTime($expectStartTime.val(), $expectElapse.val());
                }
            }
        });
        $expectElapse.change(function (event) {
            if ($expectStartTime.val()) {
                setExpectStartTime($expectStartTime.val(), $expectElapse.val());
            }
        });

        function setExpectStartTime (start, elapse) {
            let date = new Date(start);
            let hm = elapse.split(':');
            date.setHours(date.getHours() + parseInt(hm[0]));
            date.setMinutes(date.getMinutes() + parseInt(hm[1]));
            $('[name="expectFinisTime"]').val(format(date));
        }

        function format (date) {
            let two = (s) => {
                return s > 10 ? s : '0' + s
            };

            let list = [];
            list.push(date.getFullYear());
            list.push('-');
            list.push(two(date.getMonth() + 1));
            list.push('-');
            list.push(two(date.getDate()));
            list.push(' ');
            list.push(two(date.getHours()));
            list.push(':');
            list.push(two(date.getMinutes()));
            list.push(':');
            list.push(two(date.getSeconds()));
            return list.join('');
        }

        let args = kit.args(['id']);
        if (args.id) {
            kit.req({
                ajax: {
                    url: '',
                    success: function (res) {
                    }
                },
                done: function (res) {
                    let data = res.data;
                }
            });
        }
    });
</script>
</html>