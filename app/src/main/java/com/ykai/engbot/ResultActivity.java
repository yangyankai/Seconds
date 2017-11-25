package com.ykai.engbot;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ykai on 17/11/12.
 */
public class ResultActivity extends Activity{
    String currentMeetingPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        TextView textTopic = (TextView) findViewById(R.id.text_topic);
        TextView textDetail = (TextView) findViewById(R.id.text_detail);

        currentMeetingPath = getIntent().getStringExtra("currentMeetingPath");

        String strTopic = MyPrintLogUtil.readTopic(currentMeetingPath);
        String strDetail = MyPrintLogUtil.readResultContent(currentMeetingPath);

        if(null!=strTopic){
            textTopic.setText(strTopic);

        }



        if(null!=strDetail){
            textDetail.setText(strDetail);

        }
    }
}
