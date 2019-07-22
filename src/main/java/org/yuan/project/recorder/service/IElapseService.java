package org.yuan.project.recorder.service;

import org.yuan.project.recorder.entity.Elapse;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-15
 */
public interface IElapseService extends BaseService<Elapse> {

    /**
     * 启动计时
     */
    void start (long taskId, long userId);

    /**
     * 关闭计时
     */
    Elapse finis (long taskId, LocalDateTime finishTime, long userId);

    /**
     * 获取未结束计时记录
     * @param id 任务标识
     * @return
     */
    Elapse unfinishedByTaskId(long id);

    /**
     * 获取未结束计时记录
     * @param id 用户标识
     * @return
     */
    Elapse unfinishedByUserId(long id);
}
