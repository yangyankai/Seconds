package com.ykai.engbot;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ykai on 17/11/12.
 */
public class ResultActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        TextView textTopic = (TextView) findViewById(R.id.text_topic);
        TextView textDetail = (TextView) findViewById(R.id.text_detail);

        String strTopic = MyPrintLogUtil.readTopic1();
        String strDetail = MyPrintLogUtil.readTopic2();

        if(null!=strTopic){
            textTopic.setText(strTopic);

        }



        if(null!=strDetail){
            textDetail.setText(strDetail);

        }
    }
}
