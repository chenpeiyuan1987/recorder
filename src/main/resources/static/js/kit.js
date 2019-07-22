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
        /**
         * minutes -> hours:minutes
         */
        m2hm: function (m) {
            let two = (n) => n > 9 ? n : '0' + n;

            let h = parseInt(m / 60);
            m = m % 60;
            return two(h) + ':' + two(m);
        },

        /**
         * hours:minutes -> minutes
         */
        hm2m: function (hm) {
            hm = hm.split(':');
            let m = parseInt(hm[0]) * 60 + parseInt(hm[1]);
            return m;
        },

        /**
         * seconds -> hours:minutes:seconds
         */
        s2hms: function (s) {
            let two = (n) => n > 9 ? n : '0' + n;

            let m = parseInt(s / 60);
            let h = parseInt(m / 60);
            m = m % 60;
            s = s % 60;
            return two(h) + ':' + two(m) + ':' + two(s);
        },

        /**
         * hours:minutes -> seconds
         */
        hm2s: function (hm) {
            hm = hm.split(':');
            let s = parseInt(hm[0]) * 60 * 60 + parseInt(hm[1]) * 60;
            return s;
        },

        /**
         * date -> YYYY-MM-DD HH:mm:ss
         */
        d2dt: function (date) {
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
    };

    kit.render = {
    };

    exports('kit', kit);
});