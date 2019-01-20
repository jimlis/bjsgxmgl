package com.zj.platform.common.dingding;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zj.platform.common.util.CommonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jimli on 2018/10/29.
 */
public class SendMessage {
    //得到token
    public static String getToken(String getTokenPath) throws Exception{
        String tokenResult= CommonUtils.HttpURLConnectionGet(getTokenPath);
        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        Map<String ,String> tokenMap = new Gson().fromJson(tokenResult, type);
        return tokenMap.get("access_token");
    }
    /*
        contentTest:发送内容
        agentid：应用id
        touser：接收人，多个用|分开
     */
    public static String sendMessage(String appkey,String appsecret,String agentid,String contentText,String touser) throws Exception{
        String getTokenPath = "https://oapi.dingtalk.com/gettoken?appkey="+appkey+"&appsecret="+appsecret;
        String access_token = SendMessage.getToken(getTokenPath);

        JSONObject image=new JSONObject();
        JSONObject text=new JSONObject();
        text.put("content",contentText);
        image.put("text", text);
        image.put("msgtype","text");
        image.put("agentid",agentid);
        image.put("touser",touser);
        System.out.println(image.toString());
        JSONObject result=null;
        String url = "https://oapi.dingtalk.com/message/send?access_token="+access_token;
        try{
            result=CommonUtils.httpPost(url, image);
        }catch(Exception e) {
        //TODOAuto-generated catch block
            e.printStackTrace();
        }
        return result.toString();
    }


}
