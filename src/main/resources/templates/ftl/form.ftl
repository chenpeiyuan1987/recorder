<#-- 文本框 -->
<#macro text label name verify="required" attrs="">
<div class="layui-form-item">
    <label class="layui-form-label" style="font-size:.9em;">${label}</label>
    <div class="layui-input-block">
        <input type="text" name="${name}" lay-verify="${verify}" lay-verType="tips" autocomplete="off" class="layui-input" ${attrs}>
    </div>
</div>
</#macro>

<#-- 选择框 -->
<#macro select label name verify="required">
<div class="layui-form-item">
    <label class="layui-form-label" style="font-size:.9em;">${label}</label>
    <div class="layui-input-block">
        <select name="${name}" lay-verify="${verify}">
            <option value="">请选择</option>
        </select>
    </div>
</div>
</#macro>

<#-- 文本域 -->
<#macro textarea label name verify="required">
<div class="layui-form-item layui-form-text">
    <label class="layui-form-label">${label}</label>
    <div class="layui-input-block">
        <textarea name="${name}" lay-verify="${verify}" placeholder="请输入内容" class="layui-textarea"></textarea>
    </div>
</div>
</#macro>