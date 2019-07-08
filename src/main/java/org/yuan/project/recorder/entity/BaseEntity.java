package org.yuan.project.recorder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 无效
     */
    public static final int VALID_0 = 0;
    /**
     * 有效
     */
    public static final int VALID_1 = 1;

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

    /**
     * 创建时初始化字段
     * @param userId
     */
    public void initCreate(long userId) {
        LocalDateTime time = LocalDateTime.now();
        this.createrId = userId;
        this.updaterId = userId;
        this.updateTime = time;
        this.createTime = time;
        this.valid = VALID_1;
    }
}
