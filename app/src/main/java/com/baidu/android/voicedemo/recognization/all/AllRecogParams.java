package com.baidu.android.voicedemo.recognization.all;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.baidu.android.voicedemo.recognization.CommonRecogParams;
import com.baidu.android.voicedemo.recognization.PidBuilder;
import com.baidu.android.voicedemo.recognization.offline.OfflineRecogParams;
import com.baidu.speech.asr.SpeechConstant;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by fujiayi on 2017/6/24.
 */

public class AllRecogParams extends CommonRecogParams {


    private static final String TAG = "NluRecogParams";

    public AllRecogParams(Activity context) {
        super(context);
        Log.d(TAG, "AllRecogParams: yyk1");
        stringParams.addAll(Arrays.asList(
                SpeechConstant.NLU,
                "_language",
                "_model"));

        intParams.addAll(Arrays.asList(
                SpeechConstant.DECODER,
                SpeechConstant.PROP));

        boolParams.addAll(Arrays.asList(SpeechConstant.DISABLE_PUNCTUATION, "_nlu_online"));

        Log.d(TAG, "AllRecogParams: yyk7");

       //  copyOfflineResource(context);
    }

    public Map<String, Object> fetch(SharedPreferences sp) {

        Log.d(TAG, "fetch: son class yyk4");
        Map<String, Object> map = super.fetch(sp);


        PidBuilder builder = new PidBuilder();
        map = builder.addPidInfo(map);
        //boolean isOfflineEnabled = sp.getBoolean(SpeechConstant.DECODER, false);
        //map.put(SpeechConstant.DECODER, 0);


        /*if ("sp.getString(SpeechConstant.DECODER, "0")){
            // 离线需要额外设置离线资源文件

        }*/
        return map;

    }


}
