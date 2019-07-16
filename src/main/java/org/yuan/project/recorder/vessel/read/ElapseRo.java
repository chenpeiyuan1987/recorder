package org.yuan.project.recorder.vessel.read;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class ElapseRo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 任务标识
     */
    private Long taskId;
    /**
     * 耗时状态
     */
    private Integer status;
}
