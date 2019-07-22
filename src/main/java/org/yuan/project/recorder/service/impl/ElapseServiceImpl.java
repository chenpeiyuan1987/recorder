package org.yuan.project.recorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.yuan.project.recorder.entity.BaseEntity;
import org.yuan.project.recorder.entity.Elapse;
import org.yuan.project.recorder.mapper.ElapseMapper;
import org.yuan.project.recorder.service.IElapseService;
import org.yuan.project.recorder.utils.Fault;

import java.time.Duration;
import java.time.LocalDateTime;

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
        Elapse origin = unfinishedByUserId(userId);
        if (origin != null) {
            throw new Fault("尚未关闭计时");
        }

        // 添加耗时
        Elapse insert = new Elapse();
        insert.setPlanId(0L);
        insert.setExpend(0);
        insert.setTaskId(taskId);
        insert.setStatus(Elapse.STATUS_0);
        insert.initCreate(userId);

        if (!save(insert)) {
            throw new Fault("启动计时失败");
        }
    }

    @Override
    public Elapse finis(long taskId, LocalDateTime finishTime, long userId) {
        // 校验参数
        Elapse origin = unfinishedByTaskId(taskId);
        if (origin == null) {
            throw new Fault("尚未开启计时");
        }
        int minutes = (int)Duration.between(origin.getCreateTime(), finishTime).toMinutes();
        if (minutes < 0) {
            throw new Fault("结束时间小于开始时间");
        }

        // 修改耗时
        Elapse update = new Elapse();
        update.setId(origin.getId());
        update.setExpend(minutes);
        update.setStatus(Elapse.STATUS_1);
        update.initUpdate(userId);

        if (!updateById(update)) {
            throw new Fault("关闭计时失败");
        }

        return update;
    }

    @Override
    public Elapse unfinishedByTaskId(long id) {
        LambdaQueryWrapper<Elapse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseEntity::getValid, BaseEntity.VALID_1);
        wrapper.eq(Elapse::getStatus, Elapse.STATUS_0);
        wrapper.eq(Elapse::getTaskId, id);
        return getOne(wrapper);
    }

    @Override
    public Elapse unfinishedByUserId (long id) {
        LambdaQueryWrapper<Elapse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseEntity::getValid, BaseEntity.VALID_1);
        wrapper.eq(Elapse::getStatus, Elapse.STATUS_0);
        wrapper.eq(Elapse::getCreaterId, id);
        return getOne(wrapper);
    }
}
