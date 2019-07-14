package org.yuan.project.recorder.service.impl;

import org.yuan.project.recorder.entity.Task;
import org.yuan.project.recorder.mapper.TaskMapper;
import org.yuan.project.recorder.service.ITaskService;
import org.yuan.project.recorder.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-15
 */
@Service
public class TaskServiceImpl extends BaseServiceImpl<TaskMapper, Task> implements ITaskService {

}
