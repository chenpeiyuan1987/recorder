package org.yuan.project.recorder.entity;

import org.yuan.project.recorder.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("ELAPSE")
public class Elapse extends BaseEntity {

    private static final long serialVersionUID = 1L;

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


}
