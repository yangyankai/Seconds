package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by ykai on 17/9/23.
 */
public class HistoryDetailActivity extends Activity {
    private String TAG = "HistoryDetailActivity";

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detail_activity);
        Intent intent = getIntent();

        String myKeyWord;
        if (null != intent) {
            myKeyWord = intent.getStringExtra("my_word");
            Log.d(TAG, "onCreate: keyword" + myKeyWord);

        }


    }
}
