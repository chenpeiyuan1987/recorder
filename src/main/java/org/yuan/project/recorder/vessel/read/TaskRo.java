package org.yuan.project.recorder.vessel.read;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * 后端发送给前端的对象
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TaskRo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务标识
     */
    private Integer id;
    /**
     * 任务标题
     */
    private String title;
    /**
     * 任务简介
     */
    private String intro;
    /**
     * 任务类型
     */
    private Integer type;
    /**
     * 任务级别
     */
    private Integer level;
    /**
     * 任务报告
     */
    private String report;
    /**
     * 预计耗时
     */
    private Integer expectElapse;
    /**
     * 预计开始时间
     */
    private String expectStartTime;
    /**
     * 预计结束时间
     */
    private String expectFinisTime;
}
