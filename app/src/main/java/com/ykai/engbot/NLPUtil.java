package com.ykai.engbot;

import android.util.Log;

import com.baidu.aip.nlp.AipNlp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by ykai on 17/9/23.
 */
public class NLPUtil {


    private static String TAG = "NLP";
    public static final String APP_ID = "10179143";
    public static final String API_KEY = "yeOBROWjh3jfpDl3Z7LdY7bs";
    public static final String SECRET_KEY = "vG2XA9s2rYoERNBG4K9mwajqZHvi8I9e";


    public static double howSimilar(String sentence1, String sentence2) {


        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 0.0 - 1.0
//        JSONObject response = client.simnet("百度是个搜索公司", "百度是个科技公司", null);
//        Log.d(TAG, "run: " + response.toString());


        // 选择CNN算法
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("model", "CNN");
        JSONObject response1 = client.simnet("百度是个搜索公司", "百度是个科技公司", options);
        double hSimilar = 0.0;


        try {
            hSimilar = response1.getDouble("score");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "run: " + response1.toString());

        return hSimilar;

    }
}
