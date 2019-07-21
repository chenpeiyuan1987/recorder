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

<#macro textarea label name verify="required">
<div class="layui-form-item layui-form-text">
    <label class="layui-form-label">${label}</label>
    <div class="layui-input-block">
        <textarea name="${name}" lay-verify="${verify}" placeholder="请输入内容" class="layui-textarea"></textarea>
    </div>
</div>
</#macro>

<body>
    <div class="layui-form layui-form-pane" lay-filter="save" style="margin:20px 20px 0 20px;">
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

        <@textarea label="任务简介" name="intro" />
        <@textarea label="总结报告" name="report" />

        <div class="layui-form-item layui-hide">
            <button class="layui-btn" lay-submit lay-filter="save">确认</button>
        </div>
    </div>
</body>

<@f.script/>
<script>
    layui.use(['jquery', 'form', 'laydate', 'kit'], function ($, form, laydate, kit) {

        /**
         * 初始化
         */
        function init () {
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
                        setExpectFinisTime($expectStartTime.val(), $expectElapse.val());
                    }
                }
            });
            $expectElapse.change(function (event) {
                if ($expectStartTime.val()) {
                    setExpectFinisTime($expectStartTime.val(), $expectElapse.val());
                }
            });

            // 注册提交事件
            form.on('submit(save)', function (obj) {
                let field = Object.assign({}, obj.field);
                let elapse = field.expectElapse.split(':');
                field.expectElapse = elapse[0] * 60 + elapse[1];

                kit.req({
                    ajax: {
                        url: '/api/task/save',
                        data: field,
                    },
                    done: function (res) {
                        parent.postMessage({action: 'save', result: 'success'}, '*');
                    }
                });
            });

            // 启动消息侦听
            window.addEventListener('message', function (event) {
                let data = event.data;
                if (data.action === 'submit') {
                    $('[lay-submit]').click();
                }
            });
        }

        /**
         * 设置预计结束时间
         */
        function setExpectFinisTime (start, elapse) {
            let date = new Date(start);
            let hm = elapse.split(':');
            date.setHours(date.getHours() + parseInt(hm[0]));
            date.setMinutes(date.getMinutes() + parseInt(hm[1]));
            $('[name="expectFinisTime"]').val(format(date));
        }

        /**
         * 格式化日期 YYYY-MM-DD
         */
        function format (date) {
            let two = (s) => {
                return s > 10 ? s : '0' + s
            };

            let list = [
                date.getFullYear(),
                '-',
                two(date.getMonth() + 1),
                '-',
                two(date.getDate()),
                ' ',
                two(date.getHours()),
                ':',
                two(date.getMinutes()),
                ':',
                two(date.getSeconds())
            ];
            return list.join('');
        }

        // 编辑
        let id = kit.args('id');
        if (id) {
            // 获取任务信息
            kit.req({
                ajax: {
                    url: '/api/task/info',
                },
                done: function (res) {
                    let data = res.data;
                    form.val('save', data);
                    init();
                }
            });
        }
        else {
            // 删除总结报告
            $('[name="report"]').closest('.layui-form-item').remove();

            init();
        }
    });
</script>
</html>