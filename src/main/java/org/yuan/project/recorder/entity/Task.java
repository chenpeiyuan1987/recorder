package org.yuan.project.recorder.entity;

import org.yuan.project.recorder.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("TASK")
public class Task extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
