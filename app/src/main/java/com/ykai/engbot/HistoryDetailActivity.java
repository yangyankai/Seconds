package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ykai on 17/9/23.
 */
public class HistoryDetailActivity extends Activity {
    private String TAG = "HistoryDetailActivity";
    TextView textView;

    private ListView mListView;
    private MyAdapter mAdapter;
    TextView textViewBround;

    private List<String> mDatas = Arrays.asList("Hello", "Java", "Android");


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detail_activity);
        Intent intent = getIntent();
        String result = "";

        textView = (TextView) findViewById(R.id.detail);
        textViewBround = (TextView) findViewById(R.id.textbround);
        String myKeyWord;
        if (null != intent) {
            myKeyWord = intent.getStringExtra("my_word");
            Log.d(TAG, "onCreate: keyword" + myKeyWord);
            switch (myKeyWord) {
                case "111":
//                    textViewBround.setBackground(R.drawable.shape_cor);
                    textViewBround.setBackgroundResource(R.drawable.shape_top1);
//                    CCDB38
//                    textViewBround.setBackgroundColor(Color.parseColor("#CCDB38"));
                    result = ReadUtils.readTop1();
                    break;
                case "222":
                    textViewBround.setBackgroundResource(R.drawable.shape_top2);

//                    textViewBround.setBackgroundColor(Color.parseColor("#FD5621"));

                    result = ReadUtils.readTop2();

                    break;
                case "333":
                    textViewBround.setBackgroundResource(R.drawable.shape_top3);

//                    textViewBround.setBackgroundColor(Color.parseColor("#785446"));

                    result = ReadUtils.readTop3();

                    break;
                case "444":
                    textViewBround.setBackgroundResource(R.drawable.shape_top4);

//                    textViewBround.setBackgroundColor(Color.parseColor("#785446"));

                    result = ReadUtils.readTop4();

                    break;

            }
            textView.setText(result);

//            mListView = (ListView) findViewById(R.id.id_listview);
//            mAdapter = new MyAdapter(this, mDatas);
//            mListView.setAdapter(mAdapter);mAdapter


        }


    }
}
