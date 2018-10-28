package com.zj.platform.common.util;

import com.dingtalk.oapi.lib.aes.DingTalkJsApiSingnature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.io.ByteStreams.toByteArray;

/**
 * Created by jimli on 2018/10/25.
 */
public class CommonUtils {
    //get请求
    public static String HttpURLConnectionGet(String httpUrl){
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            InputStream inStream = conn.getInputStream();
            byte[] data = toByteArray(inStream);
            String result = new String(data, "UTF-8");

            return result;
        }catch (Exception e){
            System.err.print(e);
        }
       return "";
    }
    //钉钉官方算法获得signature
    public static String sign(String ticket, String nonceStr, long timeStamp, String url) {
        try {
            return DingTalkJsApiSingnature.getJsApiSingnature(url, nonceStr, timeStamp, ticket);
        } catch (Exception e) {
            e.getMessage();
        }
        return "";
    }
}
