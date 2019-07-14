package org.yuan.project.recorder.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuan.project.recorder.utils.Result;

/**
 * 任务接口
 *
 * @author chenpeiyuan
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    /**
     * 分页信息
     *
     * @param page
     * @param size
     * @return
     */
    public Object page(int page, int size) {
        return Result.failure("接口未实现");
    }

    /**
     * 任务信息
     * @param id
     * @return
     */
    public Object info(long id) {
        return Result.failure("接口未实现");
    }

    /**
     * 启动任务
     * @param id
     * @return
     */
    public Object start(long id) {
        return Result.failure("接口未实现");
    }

    /**
     * 暂停任务
     * @param id
     * @return
     */
    public Object pause(long id) {
        return Result.failure("接口未实现");
    }

    /**
     * 提交任务
     * @param id
     * @return
     */
    public Object submit(long id) {
        return Result.failure("接口未实现");
    }

    /**
     * 完成任务
     * @param id
     * @return
     */
    public Object finish(long id) {
        return Result.failure("接口未实现");
    }

}
