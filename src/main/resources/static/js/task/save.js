layui.use(['jquery', 'form', 'laydate', 'kit'], function ($, form, laydate, kit) {
    // 编辑
    let id = kit.args('id');
    if (id) {
        // 获取任务信息
        kit.req({
            ajax: {
                url: '/api/task/info',
                data: {
                    id: id,
                },
            },
            done: function (res) {
                init(id, function () {
                    let data = res.data;
                    data.expectElapse = kit.format.m2hm(data.expectElapse);
                    form.val('save', data);
                });
            }
        });
    }
    else {
        // 删除总结报告
        $('[name="report"]').closest('.layui-form-item').remove();
        init();
    }

    /**
     * 初始化
     */
    function init (id, fn) {
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

                //
                fn && fn();
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
            field.id = id;
            field.expectElapse = kit.format.hm2m(field.expectElapse);

            // 提交后端
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


});