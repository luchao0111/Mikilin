package com.simonalong.mikilin;

import com.simonalong.mikilin.annotation.Matcher;
import com.simonalong.mikilin.match.FieldMatchManager;
import com.simonalong.mikilin.match.MkContext;
import com.simonalong.mikilin.util.Maps;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 匹配器管理器
 *
 * @author zhouzhenyong
 * @since 2019/6/15 上午9:24
 */
final class MatchManager {

    /**
     * 存储对象和属性以及属性对应的匹配器的映射，key为类的全路径，二级key为类的属性名字，二级value为属性的判断核查器
     */
    private Map<String, Map<String, FieldMatchManager>> targetFieldMap;

    MatchManager() {
        targetFieldMap = new ConcurrentHashMap<>(16);
    }

    @SuppressWarnings("unchecked")
    MatchManager addWhite(String clsCanonicalName, Field field, Matcher validValue, MkContext context) {
        targetFieldMap.compute(clsCanonicalName, (k, v) -> {
            if (null == v) {
                return Maps.of(field.getName(), FieldMatchManager.buildFromValid(field, validValue, context)).build();
            } else {
                v.put(field.getName(), FieldMatchManager.buildFromValid(field, validValue, context));
                return v;
            }
        });
        return this;
    }

    Map<String, FieldMatchManager> getJudge(String targetClassName) {
        return targetFieldMap.get(targetClassName);
    }
}