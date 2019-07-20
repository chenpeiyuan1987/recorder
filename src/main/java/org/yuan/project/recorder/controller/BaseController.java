package org.yuan.project.recorder.controller;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseController {

    /**
     * 获取用户标识
     * @return
     */
    protected Long getUserId() {
        return 1L;
    }

    /**
     * 获取常量对象
     * @param clazz
     * @param names
     * @return
     */
    protected Map<String, Object> cst(Class<?> clazz, String[] names) {
        Map<String, Object> result = new HashMap<>();
        for (String name : names) {
            Object map = new Object();
            try {
                String key = (name + "_map").toUpperCase();
                Field field = clazz.getField(key);
                if (field == null) {
                    continue;
                }
                map = field.get(null);
                if (map == null || !Map.class.isInstance(map)) {
                    continue;
                }
            }
            catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
            finally {
                result.put(name, map);
            }
        }
        return result;
    }
}
