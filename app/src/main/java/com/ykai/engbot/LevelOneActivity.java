package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ykai on 17/11/12.
 */
public class LevelOneActivity extends Activity{
    private Button btnBegin;
    private Activity _this;

    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_one_activity);
        _this = this;
        btnBegin = (Button) findViewById(R.id.btn_begin);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);


        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String top1= et1.getText().toString();
                String top2= et2.getText().toString();
                String top3= et3.getText().toString();
                String top4= et4.getText().toString();


                Intent intent = new Intent(_this,RecordActivity.class);
                intent.putExtra("tp1",top1);
                intent.putExtra("tp2",top2);
                intent.putExtra("tp3",top3);
                intent.putExtra("tp4",top4);
                startActivity(intent);
            }
        });
    }
}
