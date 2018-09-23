package com.zj.platform.mybatis.interceptor;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;


@Intercepts({
		@Signature(type = ResultSetHandler.class, method = "handleResultSets", 
				args = {Statement.class}) })
public class CustomResultSetHandlerInterceptor implements Interceptor {
    /**
     * 日志
     */
    private static final Log logger = LogFactory.getLog(CustomResultSetHandlerInterceptor.class);
    
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
			ResultSetHandler resultSetHandler = (ResultSetHandler) PluginUtils.realTarget(invocation.getTarget());
	        MetaObject metaObject = SystemMetaObject.forObject(resultSetHandler);
	        Object object = invocation.proceed();
	       return  object;
	}

	@Override
	public Object plugin(Object target) {
		 if(target instanceof ResultSetHandler) {
	            return Plugin.wrap(target, this);
	      }
	        return target;
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
