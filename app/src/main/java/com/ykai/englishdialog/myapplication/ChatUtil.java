package com.ykai.englishdialog.myapplication;

import android.util.Log;

/**
 * Input Chinese or English Text;
 * Output English Text;
 * <p/>
 * Created by ykai on 17/9/16.
 */
public class ChatUtil {
    static ChatMessage chatMessage;
    private static String TAG = "ChatUtil";
    private static String strResult;
    public static ICallBack iCallBack;

    // 需要开子线程异步调用
    public static String getEnglistReturn(String strInput) {
        chatMessage = HttpUtils.sendMessage(strInput);


        BaiduTranslateHelper baiduTranslateHelper = new BaiduTranslateHelper();
        try {
            baiduTranslateHelper.translate(new String(chatMessage.getMessage().getBytes(), "utf-8"), "zh", "en", new HttpCallBack() {
                @Override
                public void onSuccess(String result) {
//                    chatMessage.setMessage(chatMessage.getMessage() + "\n" + result);
                    Log.d(TAG, "onSuccess: " + result);
                    strResult = result;
                    iCallBack.onSmartChatBack(result);
                }

                @Override
                public void onFailure(String exception) {
                    strResult = exception;
                    Log.e(TAG, "onFailure: " + exception);
                    iCallBack.onSmartChatBack(exception);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //-----  stop translate
        return strResult;
    }

    public interface ICallBack {
        public void onSmartChatBack(String s);
    }

}
