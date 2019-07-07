package org.yuan.project.recorder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @TableField("CREATER_ID")
    private Long createrId;

    @TableField("UPDATER_ID")
    private Long updaterId;

    @TableField("VALID")
    private Integer valid;
}
