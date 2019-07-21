<script src="/lib/node_modules/layui-src/dist/layui.js"></script>
<script>
    layui.config({
        version: true, //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
        debug: true //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
    }).extend({
        kit: '{/}/js/kit.js',
    });
</script>