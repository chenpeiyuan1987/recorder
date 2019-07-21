package org.yuan.project.recorder.business.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuan.project.recorder.business.TaskBusiness;
import org.yuan.project.recorder.entity.BaseEntity;
import org.yuan.project.recorder.entity.Task;
import org.yuan.project.recorder.service.IElapseService;
import org.yuan.project.recorder.service.ITaskService;
import org.yuan.project.recorder.utils.Fault;
import org.yuan.project.recorder.utils.Result;
import org.yuan.project.recorder.vessel.find.TaskFo;
import org.yuan.project.recorder.vessel.read.TaskRo;
import org.yuan.project.recorder.vessel.send.TaskSo;

import java.time.LocalDateTime;
import java.util.Arrays;

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

    @Autowired
    private IElapseService elapseService;

    @Override
    public Result.Page<TaskSo> page(int curr, int size, TaskFo fo) {
        Page<Task> page = new Page<>(curr, size);
        page.addOrder(OrderItem.desc("id"));

        service.page(page);

        return convert(page, TaskSo.class);
    }

    @Override
    public TaskSo info(long id) {
        Task task = service.getById(id);

        return convert(task, TaskSo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void start(long id, long userId) {
        // 校验参数
        Task origin = exist(id);

        if (!Arrays.asList(Task.STATUS_2, Task.STATUS_3).contains(origin.getStatus())) {
            throw new Fault("任务状态异常");
        }

        // 启动任务
        Task task = new Task();
        task.setId(id);
        task.setStatus(Task.STATUS_1);
        if (origin.getStartTime() == null) {
            task.setStartTime(LocalDateTime.now());
        }
        task.initUpdate(userId);
        if (!service.updateById(task)) {
            throw new Fault("启动任务失败");
        }

        // 启动计时
        elapseService.start(id, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(long id, long userId) {
        // 校验参数
        Task origin = exist(id);

        if (!origin.getStatus().equals(Task.STATUS_1)) {
            throw new Fault("任务状态异常");
        }

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
        elapseService.finis(id, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(long id, long userId) {
        // 校验参数
        Task origin = exist(id);

        if (!Arrays.asList(Task.STATUS_1, Task.STATUS_2).contains(origin.getStatus())) {
            throw new Fault("任务状态异常");
        }

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
        elapseService.finis(id, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finish(long id, long userId) {
        // 校验参数
        Task origin = exist(id);

        if (!origin.getStatus().equals(Task.STATUS_3)) {
            throw new Fault("任务状态异常");
        }

        // 完成任务
        Task task = new Task();
        task.setId(id);
        task.setStatus(Task.STATUS_4);
        task.setFinishTime(LocalDateTime.now());
        task.initUpdate(userId);
        if (!service.updateById(task)) {
            throw new Fault("完成任务失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TaskRo ro, long userId) {
        // 添加
        if (ro.getId() == null) {
            // 参数校验

            // 添加记录
            Task task = convert(ro, Task.class);
            task.setStatus(Task.STATUS_0);
            task.initCreate(userId);

            if (!service.save(task)) {
                throw new Fault("添加任务失败");
            }
        }

        // 修改
        else {
            // 参数校验

            // 修改记录
            Task task = convert(ro, Task.class);
            task.initUpdate(userId);

            if (!service.updateById(task)) {
                throw new Fault("修改任务失败");
            }
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


    private Task exist (long id) {
        Task task = service.getById(id);
        if (task == null || task.getValid() != BaseEntity.VALID_1) {
            throw new Fault("任务不存在或已删除");
        }
        return task;
    }
}
