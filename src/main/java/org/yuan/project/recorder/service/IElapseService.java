package org.yuan.project.recorder.service;

import org.yuan.project.recorder.entity.Elapse;

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
    void finis (long taskId, long userId);

}
