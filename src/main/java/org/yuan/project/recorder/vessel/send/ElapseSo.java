package org.yuan.project.recorder.vessel.send;

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
public class ElapseSo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消耗时间
     */
    private Integer expend;

    /**
     * 结束时间
     */
    private String finishTime;

    /**
     * 开始时间
     */
    private String createTime;
}
