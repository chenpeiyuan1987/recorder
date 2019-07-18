package org.yuan.project.recorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 *  耗时信息
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ELAPSE")
public class Elapse extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 启动
     */
    public static final int STATUS_0 = 0;
    /**
     * 关闭
     */
    public static final int STATUS_1 = 1;

    /**
     * 计划标识
     */
    @TableField("PLAN_ID")
    private Long planId;

    /**
     * 任务标识
     */
    @TableField("TASK_ID")
    private Long taskId;

    /**
     * 耗时状态
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 结束时间
     */
    @TableField("FINISH_TIME")
    private LocalDateTime finishTime;

}
