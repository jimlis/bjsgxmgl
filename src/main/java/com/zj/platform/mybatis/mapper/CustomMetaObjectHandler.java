package com.zj.platform.mybatis.mapper;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 自定义公共字段自动填充类
 * @author zj
 * 注意：填充的公共字段必须在该字段添加注解
 * @TableField(fill=FieldFill.INSERT||FieldFill.UPDATE||INSERT_UPDATE) 填充的值才能被更新
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 新增
	 */
	@Override
	public  void insertFill(MetaObject metaObject) {


	}
	
	/**
	 * 更新
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		
	}
	

}
