layui.define(['jquery'], function (exports) {
    let $ = layui.jquery;

    let kit = {
        args: function (names) {

        },
        req: function (opt) {
            let done = opt.done;
            let ajax = opt.ajax;
            let success = ajax.success;

            $.ajax(Object.assign({
                type: 'POST',
                success: function (res) {
                    if (res.code === 0) {
                        done && done(res);
                    }
                    else {
                        layer.msg(res.info);
                    }
                    success && success(res);
                },
            }, ajax));
        }
    };

    kit.format = {
    };

    kit.render = {
    };

    exports('kit', );
});