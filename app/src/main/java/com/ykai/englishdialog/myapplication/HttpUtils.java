package com.ykai.englishdialog.myapplication;


import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;


public class HttpUtils {

    private static String TAG = "HttpUtils";


    /**
     * 发送消息到服务器
     *
     * @param message ：发送的消息
     * @return：消息对象
     */
    public static ChatMessage sendMessage(String message) {
        final ChatMessage chatMessage = new ChatMessage();
        String gsonResult = doGet(message);
        String strAiResult = "";
        Gson gson = new Gson();
        Result result = null;
        if (gsonResult != null) {
            try {
                result = gson.fromJson(gsonResult, Result.class);
                strAiResult = result.getText();
//                chatMessage.setMessage(result.getText());
            } catch (Exception e) {
                strAiResult = "error";
//                chatMessage.setMessage("服务器繁忙，请稍候再试...");
            }
        }

        chatMessage.setMessage(strAiResult);

        chatMessage.setData(new Date());
        chatMessage.setType(ChatMessage.Type.INCOUNT);
        return chatMessage;
    }

    public static String doGet(String message) {
        String result = "";
        String url = setParmat(message);
        Log.d(TAG, "doGet: "+ url);
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls
                    .openConnection();
            connection.setReadTimeout(5 * 1000);
            connection.setConnectTimeout(5 * 1000);
            connection.setRequestMethod("GET");

            is = connection.getInputStream();
            baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                baos.write(buff, 0, len);
            }
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static String setParmat(String message) {
        String url = "";
        try {
            url = Config.URL_KEY + "?" + "key=" + Config.APP_KEY + "&info="
                    + URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
