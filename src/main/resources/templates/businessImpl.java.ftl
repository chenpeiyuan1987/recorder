package ${cfg.package}.business.impl;

import lombok.extern.slf4j.Slf4j;
import ${cfg.package}.business.${entity}Business;
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * ${table.comment!}
 * 业务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
public class ${entity}BusinessImpl extends BaseBusinessImpl implements ${entity}Business {

    @Autowired
    private ${table.serviceName} service;

}
