layui.define(['jquery'], function (exports) {
    let $ = layui.jquery;

    let kit = {
        /**
         * 获取请求参数
         */
        args: function (name) {
            let search = location.search.substring(1);
            if (name) {
                let regex = new RegExp(name + '=([^&]*)');
                let match = search.match(regex);
                if (match) {
                    return match[1];
                }
                return;
            }

            search = search.split('&');
            let res = {};
            search.forEach(function (item) {
                let pair = item.split('=');
                if (pair.length == 1) {
                    pair[1] = '';
                }
                res[pair[0]] = pair[1];
            });
            return res;
        },

        /**
         * ajax请求
         */
        req: function (opt) {
            let done = opt.done;
            let ajax = opt.ajax;
            let success = ajax.success;
            delete ajax.success;

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

    exports('kit', kit);
});