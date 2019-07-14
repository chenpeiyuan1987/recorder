package org.yuan.project.recorder.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuan.project.recorder.utils.Result;

/**
 * 耗时接口
 *
 * @author chenpeiyuan
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/elapse")
public class ElapseController extends BaseController {

    /**
     * 开始计时
     *
     * @param taskId    任务标识
     * @return
     */
    public Object start(long taskId) {
        return Result.failure("接口未实现");
    }

    /**
     * 结束计时
     *
     * @param id
     * @return
     */
    public Object close(long id) {
        return Result.failure("接口未实现");
    }

}
