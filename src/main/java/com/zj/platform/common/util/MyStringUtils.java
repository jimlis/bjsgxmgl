package com.zj.platform.common.util;

import java.util.Objects;

public class MyStringUtils {
	/**
	 * 是否是控制字符串
	 * ""--->true
	 * "  "--->false
	 * null--->false
	 * "1111"--->false
	 * @param str 字符串
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		return Objects.nonNull(str)&&str.length()==0;
	}
}	
