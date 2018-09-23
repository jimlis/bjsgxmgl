package com.zj.platform.mybatis.configuration.objectwrapperfactory;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
 * 返回Map结果集，下划线转驼峰
 * 新增oracl非包含下划线字段小写
 * @author zj
 */
public class CustomMapWrapper extends MapWrapper {

    public CustomMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
       if (useCamelCaseMapping
            && ((name.charAt(0) >= 'A' && name.charAt(0) <= 'Z')
            || name.contains("_"))) {
            return StringUtils.underlineToCamel(name);
        }
        return name;
    }
}
