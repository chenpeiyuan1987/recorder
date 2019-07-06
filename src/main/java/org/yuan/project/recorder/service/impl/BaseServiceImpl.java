package org.yuan.project.recorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.yuan.project.recorder.entity.BaseEntity;
import org.yuan.project.recorder.mapper.BaseMapper;
import org.yuan.project.recorder.service.BaseService;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {
}
