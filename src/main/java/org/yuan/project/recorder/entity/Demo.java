package org.yuan.project.recorder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("DEMO")
public class Demo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("INFO")
    private String info;


}
