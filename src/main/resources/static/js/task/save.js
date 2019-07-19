layui.use(['form'], function () {
    let form = layui.form;
    let id = 0;

    if (id) {
        new Promise(function);
    }
    else {
        initSelect();
    }

    // 初始化选项
    function initSelect() {
    }

    // 初始化内容
    function initRecord () {
        $.ajax({
            url: '/api/task/info',
            type: 'post',
            data: {
                id:
            },
            success: function () {
            }
        });
    }
})