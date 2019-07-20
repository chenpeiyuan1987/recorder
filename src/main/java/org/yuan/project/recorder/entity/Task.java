package org.yuan.project.recorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 任务信息
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("TASK")
public class Task extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 未启动
     */
    public static final int STATUS_0 = 0;
    /**
     * 已启动
     */
    public static final int STATUS_1 = 1;
    /**
     * 已暂停
     */
    public static final int STATUS_2 = 2;
    /**
     * 已提交
     */
    public static final int STATUS_3 = 3;
    /**
     * 已完成
     */
    public static final int STATUS_4 = 4;
    public static final Map<Integer, String> STATUS_MAP;

    /**
     * 一般
     */
    public static final int LEVEL_10 = 10;
    /**
     * 重要
     */
    public static final int LEVEL_20 = 20;
    /**
     * 紧急
     */
    public static final int LEVEL_30 = 30;
    public static final Map<Integer, String> LEVEL_MAP;

    /**
     * 新增功能
     */
    public static final int TYPE_1 = 1;
    /**
     * 完善功能
     */
    public static final int TYPE_2 = 2;
    /**
     * 学习研究
     */
    public static final int TYPE_3 = 3;
    /**
     * 修复故障
     */
    public static final int TYPE_4 = 4;
    public static final Map<Integer, String> TYPE_MAP;

    static {
        STATUS_MAP = new HashMap<>();
        STATUS_MAP.put(STATUS_0, "未启动");
        STATUS_MAP.put(STATUS_1, "已启动");
        STATUS_MAP.put(STATUS_2, "已暂停");
        STATUS_MAP.put(STATUS_3, "已提交");
        STATUS_MAP.put(STATUS_4, "已完成");

        LEVEL_MAP = new HashMap<>();
        LEVEL_MAP.put(LEVEL_10, "一般");
        LEVEL_MAP.put(LEVEL_20, "重要");
        LEVEL_MAP.put(LEVEL_30, "紧急");

        TYPE_MAP = new HashMap<>();
        TYPE_MAP.put(TYPE_1, "新增功能");
        TYPE_MAP.put(TYPE_2, "完善功能");
        TYPE_MAP.put(TYPE_3, "学习研究");
        TYPE_MAP.put(TYPE_4, "修复故障");
    }

    /**
     * 计划标识
     */
    @TableField("PLAN_ID")
    private Long planId;

    /**
     * 任务标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 任务简介
     */
    @TableField("INTRO")
    private String intro;

    /**
     * 任务类型
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 任务级别
     */
    @TableField("LEVEL")
    private Integer level;

    /**
     * 任务报告
     */
    @TableField("REPORT")
    private String report;

    /**
     * 任务状态
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 预计耗时
     */
    @TableField("EXPECT_ELAPSE")
    private Integer expectElapse;

    /**
     * 实际耗时
     */
    @TableField("ACTUAL_ELAPSE")
    private Integer actualElapse;

    /**
     * 预计开始时间
     */
    @TableField("EXPECT_START_TIME")
    private LocalDateTime expectStartTime;

    /**
     * 预计暂停时间
     */
    @TableField("EXPECT_PAUSE_TIME")
    private LocalDateTime expectPauseTime;

    /**
     * 预计结束时间
     */
    @TableField("EXPECT_FINIS_TIME")
    private LocalDateTime expectFinisTime;

    /**
     * 开始时间
     */
    @TableField("START_TIME")
    private LocalDateTime startTime;

    /**
     * 暂停时间
     */
    @TableField("PAUSE_TIME")
    private LocalDateTime pauseTime;

    /**
     * 提交时间
     */
    @TableField("SUBMIT_TIME")
    private LocalDateTime submitTime;

    /**
     * 完成时间
     */
    @TableField("FINISH_TIME")
    private LocalDateTime finishTime;

}
