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

    /**
     * 记录标识
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 创建用户
     */
    @TableField("CREATER_ID")
    private Long createrId;

    /**
     * 更新用户
     */
    @TableField("UPDATER_ID")
    private Long updaterId;

    /**
     * 有效状态
     */
    @TableField("VALID")
    private Integer valid;

    /**
     * 创建时
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

    /**
     * 更新时
     * @param userId
     */
    public void initUpdate(long userId) {
        LocalDateTime time = LocalDateTime.now();
        this.updaterId = userId;
        this.updateTime = time;
    }

    /**
     * 更新时
     * @param userId
     */
    public void initDelete(long userId) {
        LocalDateTime time = LocalDateTime.now();
        this.updaterId = userId;
        this.updateTime = time;
        this.valid = VALID_0;
    }

}
