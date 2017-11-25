package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ykai on 17/11/12.
 */
public class HomeActivity extends Activity {
    private LinearLayout llAdd;
    private Activity _this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // init app root path
        MyPrintLogUtil.initAppPath();

        _this = this;
        llAdd = (LinearLayout) findViewById(R.id.ll_add_meeting);

        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(_this, LevelOneActivity.class);

                startActivity(intent);
            }
        });

        findViewById(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(_this,ResultActivity.class);
                startActivity(intent);
            }
        });

    }
}
