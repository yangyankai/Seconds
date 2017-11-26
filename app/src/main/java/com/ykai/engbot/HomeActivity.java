package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ykai.engbot.adapter.RecordAdapter;
import com.ykai.engbot.entity.RecordEntity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ykai on 17/11/12.
 */
public class HomeActivity extends Activity {
    private LinearLayout llAdd;
    private Activity _this;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _this = this;

        setContentView(R.layout.home_activity);

        // init app root path
        MyPrintLogUtil.initAppPath();

        final ArrayList<RecordEntity> list = MyPrintLogUtil.GetFiles();
        listView = (ListView) findViewById(R.id.list);
        RecordAdapter adapter = new RecordAdapter(_this, list);
        listView.setAdapter(adapter);

        llAdd = (LinearLayout) findViewById(R.id.ll_add_meeting);

        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(_this, LevelOneActivity.class);

                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(_this, ResultActivity.class);
                RecordEntity entity = list.get(i);
                String currentMeetingPath = MyPrintLogUtil.APP_PATH + File.separator + entity.beginTimeStamp + "-" + entity.endTimeStamp;
                intent.putExtra("currentMeetingPath", currentMeetingPath);
                startActivity(intent);


            }
        });

//
//        findViewById(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });

    }
}
