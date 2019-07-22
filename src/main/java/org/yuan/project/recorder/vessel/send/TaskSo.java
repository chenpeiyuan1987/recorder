package org.yuan.project.recorder.vessel.send;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.yuan.project.recorder.entity.Task;

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
public class TaskSo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 任务标识
     */
    private Long id;
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
     * 任务状态
     */
    private Integer status;
    /**
     * 预计耗时
     */
    private Integer expectElapse;
    /**
     * 实际耗时
     */
    private Integer actualElapse;
    /**
     * 预计开始时间
     */
    private String expectStartTime;
    /**
     * 预计结束时间
     */
    private String expectFinisTime;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 暂停时间
     */
    private String pauseTime;
    /**
     * 提交时间
     */
    private String submitTime;
    /**
     * 完成时间
     */
    private String finishTime;
    /**
     * 创建时间
     */
    private String createTime;

    public String getTypeName () {
        return Task.TYPE_MAP.getOrDefault(getType(), "未知类型");
    }
    public String getLevelName () {
        return Task.LEVEL_MAP.getOrDefault(getLevel(), "未知级别");
    }
    public String getStatusName () {
        return Task.STATUS_MAP.getOrDefault(getStatus(), "未知状态");
    }
}
