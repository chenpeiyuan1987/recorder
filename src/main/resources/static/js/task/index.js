layui.use(['jquery', 'table'], function () {
    let table = layui.table;

    let templet = {
        status: function (d) {
            return d.statusName;
        },
        type: function (d) {
            return d.typeName;
        },
        priority: function (d) {
            return d.priorityName;
        }
    };
    let event = {
        insert: function () {
            layer.open({
                type: 2,
                title: '添加任务',
                content: ['/web/task/save'],
                area: ['600px', '800px'],
            });
        },
        update: function (data) {
            layer.open({
                type: 2,
                title: '编辑任务',
                content: ['/web/task/save'],
                area: ['600px', '800px'],
            });
        },
        delete：function (data) {
        }
    };
    let inst = table.render({
        url: '/api/task/page',
        method: 'POST',
        elem: '#table',
        toolbar: '#toolbar',
        page: true,
        cols: [[
            {type: 'checkbox'},
            {title: '任务名称', field:'title'},
            {title: '任务状态', field:'status', templet: templet.status},
            {title: '任务类型', field:'type', templet: templet.type},
            {title: '任务级别', field:'priority', templet: templet.priority},
            {title: '实际耗时', field:'actualElapse'},
            {title: '预计耗时', field:'expectElapse'},
            {title: '预计开始时间', field:'expectStartTime'},
            {title: '预计结束时间', field:'expectFinisTime'},
            {title: '创建时间', field:'createTime'},
        ]],
    });

    let filter = inst.config.elem.attr('lay-filter');
    table.on('toolbar(' + filter + ')', function (obj) {
        layer.open({
            type: 2,
            content: ['/web/task/save'],
            area: ['600px', '800px'],
            yes: function (index) {
            }
        });
    });

    window.addEventListener('message', function (event) {
        let data = event.data;
        if (data.action === 'save' && data.result === 'success') {
            inst.reload()
        }
    });
});