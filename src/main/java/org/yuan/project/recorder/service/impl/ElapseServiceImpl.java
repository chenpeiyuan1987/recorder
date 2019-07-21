package org.yuan.project.recorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.yuan.project.recorder.entity.BaseEntity;
import org.yuan.project.recorder.entity.Elapse;
import org.yuan.project.recorder.mapper.ElapseMapper;
import org.yuan.project.recorder.service.IElapseService;
import org.yuan.project.recorder.utils.Fault;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-15
 */
@Service
public class ElapseServiceImpl extends BaseServiceImpl<ElapseMapper, Elapse> implements IElapseService {

    @Override
    public void start(long taskId, long userId) {
        Elapse elapse = unfinishedByUserId(userId);
        if (elapse != null) {
            throw new Fault("尚未关闭计时");
        }

        // 添加耗时
        Elapse insert = new Elapse();
        insert.setPlanId(0L);
        insert.setTaskId(taskId);
        insert.setStatus(Elapse.STATUS_0);
        insert.initCreate(userId);

        if (!save(insert)) {
            throw new Fault("启动计时失败");
        }
    }

    @Override
    public void finis(long taskId, long userId) {
        // 校验参数
        Elapse elapse = unfinishedByTaskId(taskId);
        if (elapse == null) {
            throw new Fault("尚未开启计时");
        }

        // 修改耗时
        Elapse update = new Elapse();
        update.setId(elapse.getId());
        update.setStatus(Elapse.STATUS_1);
        update.initUpdate(userId);

        if (!updateById(update)) {
            throw new Fault("关闭计时失败");
        }
    }

    /**
     * 获取未关闭计时
     * @param id
     * @return
     */
    private Elapse unfinishedByTaskId(long id) {
        LambdaQueryWrapper<Elapse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseEntity::getValid, BaseEntity.VALID_1);
        wrapper.eq(Elapse::getStatus, Elapse.STATUS_0);
        wrapper.eq(Elapse::getTaskId, id);
        return getOne(wrapper);
    }

    private Elapse unfinishedByUserId (long id) {
        LambdaQueryWrapper<Elapse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseEntity::getValid, BaseEntity.VALID_1);
        wrapper.eq(Elapse::getStatus, Elapse.STATUS_0);
        wrapper.eq(Elapse::getCreaterId, id);
        return getOne(wrapper);
    }
}
