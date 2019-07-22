package org.yuan.project.recorder.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuan.project.recorder.business.ElapseBusiness;
import org.yuan.project.recorder.utils.Result;
import org.yuan.project.recorder.vessel.send.ElapseSo;

/**
 * 耗时接口
 *
 * @author chenpeiyuan
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/api/elapse")
public class ElapseController extends BaseController {

    @Autowired
    private ElapseBusiness elapseBusiness;

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

    /**
     * 获取未结束的计时
     * @param id
     * @param type  1按任务查询，2按用户查询
     * @return
     */
    @PostMapping("/unfinished")
    public Object unfinished (long id, int type) {
        ElapseSo so = elapseBusiness.unfinished(id, type);

        return Result.success(so);
    }
}
