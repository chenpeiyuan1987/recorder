package org.yuan.project.recorder.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuan.project.recorder.business.TaskBusiness;
import org.yuan.project.recorder.utils.Result;
import org.yuan.project.recorder.vessel.read.TaskRo;

/**
 * 任务接口
 *
 * @author chenpeiyuan
 * @since 2019-07-15
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskBusiness taskBusiness;

    /**
     * 分页信息
     *
     * @param curr
     * @param size
     * @return
     */
    @PostMapping("/page")
    public Object page(int curr, int size) {

        Object result = taskBusiness.page(curr, size, null);

        return Result.success(result);
    }

    /**
     * 任务信息
     * @param id
     * @return
     */
    @PostMapping("/info")
    public Object info(long id) {

        Object info = taskBusiness.info(id);

        return Result.success(info);
    }

    /**
     * 启动任务
     * @param id
     * @return
     */
    @PostMapping("/start")
    public Object start(long id) {

        taskBusiness.start(id, getUserId());

        return Result.success();
    }

    /**
     * 暂停任务
     * @param id
     * @return
     */
    @PostMapping("/pause")
    public Object pause(long id) {

        taskBusiness.pause(id, getUserId());

        return Result.success();
    }

    /**
     * 提交任务
     * @param id
     * @return
     */
    @PostMapping("/submit")
    public Object submit(long id) {

        taskBusiness.submit(id, getUserId());

        return Result.success();
    }

    /**
     * 完成任务
     *
     * @param id
     * @return
     */
    @PostMapping("/finish")
    public Object finish(long id) {

        taskBusiness.finish(id, getUserId());

        return Result.success();
    }

    /**
     * 删除任务
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object delete(long id) {

        taskBusiness.delete(id, getUserId());

        return Result.success();
    }

    /**
     * 添加、修改任务
     *
     * @param ro
     * @return
     */
    @PostMapping("/save")
    public Object save(TaskRo ro) {

        taskBusiness.save(ro, getUserId());

        return Result.success();
    }

}
