<#-- 头部 -->
<#macro head admin=true>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/lib/node_modules/layui-src/dist/css/layui.css">
    <#if admin>
    <link rel="stylesheet" type="text/css" href="/css/admin.css">
    </#if>
    <link rel="stylesheet" type="text/css" href="/css/adjust.css">
</#macro>

<#-- 脚本 -->
<#macro script>
<script src="/lib/node_modules/layui-src/dist/layui.js"></script>
<#nested/>
</#macro>
