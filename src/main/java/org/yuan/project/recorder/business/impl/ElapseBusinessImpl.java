package org.yuan.project.recorder.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuan.project.recorder.business.ElapseBusiness;
import org.yuan.project.recorder.entity.Elapse;
import org.yuan.project.recorder.service.IElapseService;
import org.yuan.project.recorder.vessel.send.ElapseSo;

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
public class ElapseBusinessImpl extends BaseBusinessImpl implements ElapseBusiness {

    @Autowired
    private IElapseService service;

    @Override
    public ElapseSo unfinished(long id, int type) {
        Elapse elapse = null;
        if (type == 1) {
            elapse = service.unfinishedByTaskId(id);
        }
        if (type == 2) {
            elapse = service.unfinishedByUserId(id);
        }
        if (elapse != null) {
            return convert(elapse, ElapseSo.class);
        }
        return null;
    }
}
