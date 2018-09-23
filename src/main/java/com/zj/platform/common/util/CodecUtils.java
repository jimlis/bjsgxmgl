package com.zj.platform.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * 加密工具类
 */
public class CodecUtils extends DigestUtils{

	public final static Base64 base64 = new Base64();
	
	public static String getPasswd(String passwd, String salt) {
		String pwd = sha256Hex(passwd + salt);
		return pwd;
	}
	
	public static String getSalt(){
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
	}
	
	public static String base64Encode(String str){
		return base64.encodeToString(str.getBytes());
	}
	
	public static String base64Decode(String str){
		return new String(base64.decode(str.getBytes()));
	}
	
	
}
