package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ykai on 17/9/23.
 */
public class HistoryActivity extends Activity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Activity _this;
    Button button1;
    Button button2;

    Button button3;
    Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        _this = this;
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_this, HistoryDetailActivity.class);
                intent.putExtra("my_word", button1.getText().toString());
                startActivity(intent);

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_this, HistoryDetailActivity.class);
                intent.putExtra("my_word", button2.getText().toString());
                startActivity(intent);

            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_this, HistoryDetailActivity.class);
                intent.putExtra("my_word", button3.getText().toString());
                startActivity(intent);

            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_this, HistoryDetailActivity.class);
                intent.putExtra("my_word", button4.getText().toString());
                startActivity(intent);

            }
        });


    }
}
