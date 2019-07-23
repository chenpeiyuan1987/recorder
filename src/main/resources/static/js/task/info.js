layui.use(['jquery', 'form', 'kit', 'laydate'], function ($, form, kit, laydate) {
    let handle = {
        prev: function (type) {
            if (type === undefined || type === 1) {
                $('[func]').hide();
            }
            if (type === undefined || type === 2) {
                $('[name]').css({color: ''});
            }
        },
        // 未启动
        0: function (d, type) {
            this. prev(type);

            // 设置按钮
            if (type === undefined || type === 1) {
                ['start'].forEach(function (name) {
                    $('[func="' + name + '"]').show();
                });
            }
        },
        //  已启动
        1: function (d, type) {
            this. prev(type);

            // 设置按钮
            if (type === undefined || type === 1) {
                ['pause'].forEach(function (name) {
                    $('[func="' + name + '"]').show();
                });
            }

            // 设置颜色
            if (type === undefined || type === 2) {
                ['statusName', 'startTime'].forEach(function (name) {
                    let $elem = $('[name="' + name + '"]').css({color: 'red'});
                    d[name] && $elem.val(d[name]);
                });
            }
        },
        // 已暂停
        2: function (d, type) {
            this. prev(type);

            // 设置按钮
            if (type === undefined || type === 1) {
                ['start', 'submit'].forEach(function (name) {
                    $('[func="' + name + '"]').show();
                });
            }

            // 设置颜色
            if (type === undefined || type === 2) {
                ['statusName', 'pauseTime'].forEach(function (name) {
                    $('[name="' + name + '"]').val(d[name]).css({color: 'red'});
                });
            }
        },
        // 已提交
        3: function (d, type) {
            this. prev(type);

            // 设置按钮
            if (type === undefined || type === 1) {
                ['start', 'finish'].forEach(function (name) {
                    $('[func="' + name + '"]').show();
                });
            }

            // 设置颜色
            if (type === undefined || type === 2) {
                ['statusName', 'submitTime'].forEach(function (name) {
                    $('[name="' + name + '"]').val(d[name]).css({color: 'red'});
                });
            }
        },
        // 已完成
        4: function (d, type) {
            this. prev(type);

            // 设置颜色
            if (type === undefined || type === 2) {
                ['statusName', 'finishTime'].forEach(function (name) {
                    $('[name="' + name + '"]').val(d[name]).css({color: 'red'});
                });
            }
        },
    };

    let $elapse = $('[name="actualElapse"]');
    let clock = new Clock({
        start: function () {
            let val = $elapse.val();
            $elapse.css({color: 'red'}).attr('data-val', val);;
            return 0;
        },
        cycle: function (s) {
            let b = kit.format.hm2s($elapse.attr('data-val'));
            let val = kit.format.s2hms(s + b) + ' / ' + kit.format.s2hms(s);
            $elapse.val(val);
        },
        finis: function (elapse) {
            $elapse.css({color: ''});
            $elapse.val(kit.format.m2hm(elapse));
        }
    });

    function getElapse (taskId, fn) {
        kit.req({
            ajax: {
                url: '/api/elapse/unfinished',
                data: {
                    id: taskId,
                    type: 1,
                },
            },
            done: function (res) {
                fn && fn(res);
            },
        });
    }

    function getTask (taskId, fn) {
        kit.req({
            ajax: {
                url: '/api/task/info',
                data: {
                    id: taskId,
                },
            },
            done: function (res) {
                fn && fn(res);
            }
        });
    }

    let task;
    let elapse;
    let taskId = kit.args('id');
    getTask(taskId, function (res) {
        task = res.data;

        // 设置任务信息
        let temp = Object.assign({}, task);
        temp.expectElapse = kit.format.m2hm(task.expectElapse);
        temp.actualElapse = kit.format.m2hm(task.actualElapse);
        form.val('info', temp);

        // 设置不可更改
        $('[name]').attr('disabled', '');

        // 注册操作事件
        $('[func]').click(function (event) {
            let $this = $(this);
            let name = $this.attr('func');
            let fn = func[name];
            fn && fn(taskId);
        });

        // 初始化计时器
        if (task.status === 1) {
            getElapse(taskId, function (res) {
                elapse = res.data;

                clock.start(function () {
                    $elapse.css({color: 'red'}).attr('data-val', $elapse.val());
                    let a = new Date().getTime() - new Date(elapse.createTime).getTime();
                    return parseInt(a / 1000);
                });
            });
        }

        handle[task.status](task);
    });

    // 计时器
    function Clock (opt) {
        this.data = {
            id: 0,
            time: 0,
            start: function () {return 0;},
            cycle: function (m) {},
            finis: function () {},
        };
        Object.assign(this.data, opt);
    }
    Object.assign(Clock.prototype, {
        /**
         * 启动
         */
        start: function (fn) {
            let d = this.data;
            if (d.id !== 0) {
                return;
            }
            fn = fn || d.start;
            d.time = fn && fn();
            d.id = setInterval(function () {
                d.cycle && d.cycle(++d.time);
            }, 1000);
        },
        /**
         * 关闭
         */
        finis: function (elapse) {
            let d = this.data;
            if (d.id === 0) {
                return;
            }
            clearInterval(d.id);
            d.id = 0;

            d.finis && d.finis(elapse);
        }
    });

    let func = {
        /**
         * 启动任务
         */
        start: function (id) {
            kit.req({
                ajax: {
                    url: '/api/task/start',
                    data: {
                        id: id,
                    },
                },
                done: function (res) {
                    let d = res.data;

                    handle[d.status](d);
                    clock.start();

//                    getElapse(taskId, function (res) {
//                        elapse = res.data;
//                    });
                }
            });
        },
        /**
         * 暂停任务
         */
        pause: function (id) {
            layer.prompt({
                value: '',
                title: '请确认结束时间',
                area: ['800px', '350px'],
                success: function ($elem) {
                    let $input = $elem.find('input');
                    laydate.render({
                        elem: $input.selector,
                        type: 'datetime',
                        value: new Date(),
                        min: elapse.createTime,
                    });
                }
            }, function(value, index, elem){
                kit.req({
                    ajax: {
                        url: '/api/task/pause',
                        data: {
                            id: id,
                            finishTime: value,
                        },
                    },
                    done: function (res) {
                        let d = res.data;

                        clock.finis(d.actualElapse);
                        handle[d.status](d);
                        layer.close(index);
                    }
                });
            });
        },
        /**
         * 提交任务
         */
        submit: function (id) {
            kit.req({
                ajax: {
                    url: '/api/task/submit',
                    data: {
                        id: id,
                    },
                },
                done: function (res) {
                    let d = res.data;
                    handle[d.status](d);
                }
            });
        },
        /**
         * 完成任务
         */
        finish: function (id) {
            kit.req({
                ajax: {
                    url: '/api/task/finish',
                    data: {
                        id: id,
                    },
                },
                done: function (res) {
                    let d = res.data;
                    handle[d.status](d);
                }
            });
        }
    };

});