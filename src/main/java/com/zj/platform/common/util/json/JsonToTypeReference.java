package com.zj.platform.common.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 反序列化json数据到对象
 * 为了提高json转化效率,将mapper对象单例静态化
 * 只有需要自己设置日期格式时才重新创建mapper对象
 * @author zj
 *
 */
public class JsonToTypeReference {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static ObjectMapper mapper =  new ObjectMapper();
	
	static {
		mapper.setDateFormat(sdf);
		//忽略字段不齐问题
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, false);
	}
	
	/**
	 * 创建静态变量
	 * @return
	 */
	public static ObjectMapper buildObjectMapper() {
		return mapper;
	}
	
	/**
	 * json数据转化成List<Map<String, Object>>对象
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> jsonToList(String json) {
		List<Map<String, Object>> list = null;
		try {
			buildObjectMapper().setDateFormat(sdf);
			json = json.replaceAll("&quot;", "\"");
			list = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * json数据转化成List<Map<String, Object>>对象
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> jsonToList(String json, String dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> list = null;
		try {
			if(StringUtils.isNotEmpty(dateFormat)){
				mapper.setDateFormat(new SimpleDateFormat(dateFormat));
			}
			json = json.replaceAll("&quot;", "\"");
			list = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * json数据转化成Map<String, Object>对象
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map = null;
		try {
			json = json.replaceAll("&quot;", "\"");
			map = buildObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return map;
	}
	
	/**
	 * json数据转化成Map<String, Object>对象
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json, String dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = null;
		try {
			if(StringUtils.isNotEmpty(dateFormat)){
				mapper.setDateFormat(new SimpleDateFormat(dateFormat));
			}
			json = json.replaceAll("&quot;", "\"");
			map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return map;
	}
	
	/**
	 * json数据转化成Entity对象
	 * @param json
	 * @param entity
	 * @return
	 */
	public static <T> T jsonToEntity(String json, Class<T> entity) {
		try {
			json = json.replaceAll("&quot;", "\"");
			return buildObjectMapper().readValue(json, entity);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * json数据转化成Entity对象
	 * @param json
	 * @param entity
	 * @return
	 */
	public static <T> T jsonToEntity(String json, Class<T> entity, String dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(StringUtils.isNotEmpty(dateFormat)){
				mapper.setDateFormat(new SimpleDateFormat(dateFormat));
			}
			json = json.replaceAll("&quot;", "\"");
			return mapper.readValue(json, entity);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * json数据转化成EntityList对象
	 * @param json
	 * @param entity
	 * @return
	 */
	public static <T> List<T> jsonToEntityList(String json, Class<T> entity) {
		TypeFactory t = TypeFactory.defaultInstance();
		try {
			json = json.replaceAll("&quot;", "\"");
			return buildObjectMapper().readValue(json, t.constructCollectionType(ArrayList.class, entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json数据转化成EntityList对象
	 * @param json
	 * @param entity
	 * @return
	 */
	public static <T> List<T> jsonToEntityList(String json, Class<T> entity, String dateFormat) {
		ObjectMapper mapper = new ObjectMapper();
		TypeFactory t = TypeFactory.defaultInstance();
		try {
			if(StringUtils.isNotEmpty(dateFormat)){
				mapper.setDateFormat(new SimpleDateFormat(dateFormat));
			}
			json = json.replaceAll("&quot;", "\"");
			return mapper.readValue(json, t.constructCollectionType(ArrayList.class, entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
