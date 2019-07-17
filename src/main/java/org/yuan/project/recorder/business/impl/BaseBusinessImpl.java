package org.yuan.project.recorder.business.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuan.project.recorder.utils.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseBusinessImpl {

    @Autowired
    private Mapper mapper;

    /**
     * 对象转换
     * @param src   原始对象
     * @param cls   目标类型
     * @param <D>   目标类型
     * @return
     */
    protected <D> D convert(Object src, Class<D> cls) {
        if (src == null) {
            return null;
        }
        if (cls == null) {
            throw new IllegalArgumentException("cls can not be null");
        }

        return mapper.map(src, cls);
    }

    /**
     * 分页转换
     * @param page  分页对象
     * @param cls   目标类型
     * @param <D>   目标类型
     * @return
     */
    protected <D> Result.Page<D> convert(Page<?> page, Class<D> cls) {
        if (page == null) {
            throw new IllegalArgumentException("page can not be null");
        }
        if (cls == null) {
            throw new IllegalArgumentException("cls can not be null");
        }

        Result.Page<D> result = new Result.Page<>();
        result.setCurr(page.getCurrent());
        result.setSize(page.getSize());
        result.setPages(page.getPages());
        result.setTotal(page.getTotal());
        result.setList(convert(page.getRecords(), cls));

        return result;
    }

    /**
     * 列表转换
     * @param list  原始列表
     * @param cls   目标类型
     * @param <D>   目标类型
     * @return
     */
    protected <D> List<D> convert(List<?> list, Class<D> cls) {
        if (list == null) {
            throw new IllegalArgumentException("list can not be null");
        }
        if (cls == null) {
            throw new IllegalArgumentException("cls can not be null");
        }
        if (list.size() <= 0) {
            return Collections.emptyList();
        }

        List<D> result = new ArrayList<>();
        for (Object item : list) {
            D dst = convert(item, cls);
            result.add(dst);
        }

        return result;
    }
}
