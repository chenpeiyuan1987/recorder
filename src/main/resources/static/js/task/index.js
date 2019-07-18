layui.define(['jquery', 'table'], function (exports) {
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
    table.render({
        elem: '#table',
        toolbar: '#toolbar',
        page: true,
        data: [
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
            {title: '任务1', createTime: '2019-07-18'},
        ],
        cols: [[
            {type: 'checkbox'},
            {title: '任务名称', field:'title'},
            {title: '任务状态', field:'status', templet: templet.status},
            {title: '任务类型', field:'type', templet: templet.type},
            {title: '优先级', field:'priority', templet: templet.priority},
            {title: '实际耗时', field:'actualElapse'},
            {title: '预计耗时', field:'expectElapse'},
            {title: '预计开始时间', field:'expectStartTime'},
            {title: '预计结束时间', field:'expectFinisTime'},
            {title: '创建时间', field:'createTime'},
        ]],
    });

    exports('task/index', {});
});