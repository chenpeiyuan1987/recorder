package org.yuan.project.recorder.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuan.project.recorder.business.TaskBusiness;
import org.yuan.project.recorder.entity.Task;
import org.yuan.project.recorder.service.ITaskService;
import org.yuan.project.recorder.utils.Fault;
import org.yuan.project.recorder.utils.Result;
import org.yuan.project.recorder.vessel.find.TaskFo;
import org.yuan.project.recorder.vessel.read.TaskRo;
import org.yuan.project.recorder.vessel.send.TaskSo;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * 业务实现类
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-16
 */
@Slf4j
@Service
public class TaskBusinessImpl extends BaseBusinessImpl implements TaskBusiness {

    @Autowired
    private ITaskService service;

    @Override
    public Result.Page<TaskSo> page(int curr, int size, TaskFo fo) {
        return null;
    }

    @Override
    public TaskSo info(long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void start(long id, long userId) {
        // 校验参数
        Task old = service.getById(id);

        // 启动任务
        Task task = new Task();
        task.setId(id);
        task.setStatus(Task.STATUS_1);
        if (old.getStartTime() == null) {
            task.setStartTime(LocalDateTime.now());
        }
        task.initUpdate(userId);
        if (!service.updateById(task)) {
            throw new Fault("启动任务失败");
        }

        // 启动计时
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(long id, long userId) {
        // 校验参数

        // 暂停任务
        Task task = new Task();
        task.setId(id);
        task.setStatus(Task.STATUS_2);
        task.setPauseTime(LocalDateTime.now());
        task.initUpdate(userId);
        if (!service.updateById(task)) {
            throw new Fault("暂停任务失败");
        }

        // 结束计时
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(long id, long userId) {
        // 校验参数

        // 提交任务
        Task task = new Task();
        task.setId(id);
        task.setStatus(Task.STATUS_3);
        task.setSubmitTime(LocalDateTime.now());
        task.initUpdate(userId);
        if (!service.updateById(task)) {
            throw new Fault("提交任务失败");
        }

        // 结束计时
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finish(long id, long userId) {
        // 校验参数

        // 完成任务
        Task task = new Task();
        task.setId(id);
        task.setStatus(Task.STATUS_4);
        task.setFinishTime(LocalDateTime.now());
        task.initUpdate(userId);
        if (!service.updateById(task)) {
            throw new Fault("完成任务失败");
        }

        // 结束计时
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TaskRo ro, long userId) {
        // 添加
        if (ro.getId() == null) {

        }

        // 修改
        else {

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id, long userId) {
        // 校验参数

        // 完成任务
        Task task = new Task();
        task.setId(id);
        task.initDelete(userId);
        if (!service.updateById(task)) {
            throw new Fault("删除任务失败");
        }

        // 结束计时
    }
}
