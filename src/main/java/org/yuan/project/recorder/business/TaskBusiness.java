package org.yuan.project.recorder.business;

import org.yuan.project.recorder.utils.Result;
import org.yuan.project.recorder.vessel.find.TaskFo;
import org.yuan.project.recorder.vessel.read.TaskRo;
import org.yuan.project.recorder.vessel.send.TaskSo;

/**
 * <p>
 * 
 * 业务接口类
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-16
 */
public interface TaskBusiness {

    /**
     * 获取任务分页
     *
     * @param curr
     * @param size
     * @param fo
     * @return
     */
    Result.Page<TaskSo> page (int curr, int size, TaskFo fo);

    /**
     * 获取任务信息
     *
     * @param id
     * @return
     */
    TaskSo info (long id);

    /**
     * 添加、修改任务
     *
     * @param ro
     * @param userId
     * @return
     */
    void save (TaskRo ro, long userId);

    /**
     * 启动任务
     *
     * @param id
     * @param userId
     */
    void start (long id, long userId);

    /**
     * 暂停任务
     *
     * @param id
     * @param userId
     */
    void pause (long id, long userId);

    /**
     * 提交任务
     *
     * @param id
     * @param userId
     */
    void submit (long id, long userId);

    /**
     * 完成任务
     *
     * @param id
     * @param userId
     */
    void finish (long id, long userId);

    /**
     * 删除任务
     *
     * @param id
     * @param userId
     */
    void delete (long id, long userId);
}
