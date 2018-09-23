package com.zj.platform.mybatis.configuration.objectwrapperfactory;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
/**
 * 目前：开启返回map结果集的下划线转驼峰 和oracl下非下划线字段小写转换
 * @author zj
 */
public class CustomWrapperFactory implements ObjectWrapperFactory{

	    @Override
	    public boolean hasWrapperFor(Object object) {
	    	//目前只对map类型处理
	    	if(object != null && object instanceof Map ) {
	    		return true;
	    	}
	        return false;
	    }

	    @Override
	    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
	    	if(object instanceof Map){
	    		return new CustomMapWrapper(metaObject, (Map) object);
	    	}
	        return null;
	    }
	    
}
