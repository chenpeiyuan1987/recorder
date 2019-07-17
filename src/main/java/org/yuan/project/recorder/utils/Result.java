package org.yuan.project.recorder.utils;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class Result {
    /**
     * 默认错误编码
     */
    public static final int FAILURE = -1;

    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String info;
    /**
     * 数据
     */
    private Object data;

    private Result(Integer code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static Result success () {
        return success(null);
    }

    public static Result success (Object data) {
        return success("执行成功", data);
    }

    public static Result success (String info, Object data) {
        return new Result(0, info, data);
    }

    public static Result failure () {
        return failure("执行失败");
    }

    public static Result failure (String info) {
        return failure(-1, info);
    }

    public static Result failure (Integer code, String info) {
        return new Result(code, info, null);
    }

    //-------------------------------------------
    // 分页对象
    //-------------------------------------------
    @Data
    public static class Page<T> {
        /**
         * 当页数据
         */
        private List<T> list;

        /**
         * 总页数
         */
        private Long pages;

        /**
         * 当前页数
         */
        private Long curr;

        /**
         * 页记录数
         */
        private Long size;

        /**
         * 记录总数
         */
        private Long total;
    }
}
