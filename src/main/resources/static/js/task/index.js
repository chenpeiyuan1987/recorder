layui.use(['jquery', 'table', 'layer', 'kit'], function () {
    let $ = layui.jquery;
    let kit = layui.kit;
    let table = layui.table;
    let layer = layui.layer;

    let templet = {
        status: function (d) {
            return d.statusName;
        },
        type: function (d) {
            return d.typeName;
        },
        level: function (d) {
            return d.levelName;
        },
        actualElapse: function (d) {
            if (d.actualElapse === 0) {
                return 0;
            }
            return kit.format.m2hm(d.actualElapse);
        },
        expectElapse: function (d) {
            if (d.expectElapse === 0) {
                return 0;
            }
            return kit.format.m2hm(d.expectElapse);
        }
    };

    function save (opt) {
        layer.open(Object.assign({
            type: 2,
            title: '',
            content: '',
            area: ['600px', '630px'],
            btn: ['确定'],
            yes: function (index, $elem) {
                let iframe = $elem.find('iframe').get(0);
                iframe.contentWindow.postMessage({
                    action: 'submit'
                }, '*');
            }
        }, opt));
    }

    let event = {
        /**
         * 添加任务
         */
        insert: function (data) {
            save({
                title: '添加任务',
                content: '/web/task/save',
            });
        },
        /**
         * 编辑任务
         */
        update: function (data) {
            let id = data.data.id;
            save({
                title: '编辑任务',
                content: '/web/task/save?id=' + id,
            });
        },
        /**
         * 删除任务
         */
        delete: function (data, inst) {
            let rows = table.checkStatus(data.config.id).data;
            if (rows.length <= 0) {
                layer.msg('请先选择记录');
                return;
            }

            layer.confirm('确认删除吗？', function (index) {
                let ids = rows.map(function (row) {
                    return row.id;
                });
                kit.req({
                    ajax: {
                        url: '/api/task/delete',
                        data: {
                            ids: ids,
                        },
                        traditional: true,
                    },
                    done: function (res) {
                        // 关闭弹窗
                        layer.close(index);

                        // 更新表格
                        inst.reload();
                    }
                });
            });
        },
        clone: function (data) {
            layer.msg('该功能尚未完成');
        },
        /**
         * 任务详情
         */
        detail: function (data) {
            let id = data.data.id;
            layer.open({
                type: 2,
                title: '任务详情',
                content: '/web/task/info?id=' + id,
                area: ['600px', '900px'],
            });
        }
    };
    let inst = table.render({
        url: '/api/task/page',
        method: 'POST',
        elem: '#table',
        toolbar: '#toolbar',
        defaultToolbar: [],
        page: true,
        cols: [[
            {type: 'checkbox', width:40},
            {title: '任务名称', field:'title', event:'update'},
            {title: '任务状态', field:'status', templet: templet.status},
            {title: '任务类型', field:'type', templet: templet.type},
            {title: '任务级别', field:'level', templet: templet.level},
            {title: '实际耗时', field:'actualElapse', templet: templet.actualElapse},
            {title: '预计耗时', field:'expectElapse', templet: templet.expectElapse},
            {title: '预计开始时间', field:'expectStartTime'},
            {title: '预计结束时间', field:'expectFinisTime'},
            {title: '创建时间', field:'createTime'},
            {toolbar:'#operate', width:65},
        ]],
        parseData: function(res){ //res 即为原始返回的数据
            let data = res.data || {};
            let count = data.total || 0;
            let list = data.list || [];
            return {
              "code": res.code, //解析接口状态
              "msg": res.info, //解析提示文本
              "count": count, //解析数据长度
              "data": list //解析数据列表
            };
        },
        request: {
            pageName: 'curr', //页码的参数名称，默认：page
            limitName: 'size' //每页数据量的参数名，默认：limit
        },
    });

    let filter = inst.config.elem.attr('lay-filter');
    table.on('toolbar(' + filter + ')', function (obj) {
        // {event:'', config: {}}
        let fn = event[obj.event];
        fn && fn(obj, inst);
    });
    table.on('tool(' + filter + ')', function(obj){
        let fn = event[obj.event];
        fn && fn(obj, inst);
    });

    // 启动消息侦听
    window.addEventListener('message', function (event) {
        console.log('index', event);

        let data = event.data;
        if (data.action === 'save' && data.result === 'success') {
            // 关闭弹窗
            layer.closeAll();

            // 刷新表格
            inst.reload()
        }
    });
});