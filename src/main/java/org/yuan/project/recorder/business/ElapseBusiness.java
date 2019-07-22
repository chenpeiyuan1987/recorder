package org.yuan.project.recorder.business;

import org.yuan.project.recorder.vessel.send.ElapseSo;

/**
 * <p>
 * 
 * 业务接口类
 * </p>
 *
 * @author chenpeiyuan
 * @since 2019-07-16
 */
public interface ElapseBusiness {

    /**
     * 获取未结束的计时信息
     * @param id
     * @param type
     * @return
     */
    ElapseSo unfinished(long id, int type);

}
