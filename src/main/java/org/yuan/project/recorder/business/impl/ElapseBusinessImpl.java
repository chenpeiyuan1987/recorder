package org.yuan.project.recorder.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.yuan.project.recorder.business.ElapseBusiness;
import org.yuan.project.recorder.service.IElapseService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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

}
