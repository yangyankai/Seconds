package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ykai on 17/9/23.
 */
public class HistoryActivity extends Activity {
    private String TAG = "HistoryActivity";

    private ProgressBar progressBar;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Activity _this;
    TextView button1;
    TextView button2;
    int line;
    int top1Number = 0;
    int top2Number = 0;
    int top3Number = 0;
    int top4Number = 0;

    TextView button3;
    TextView button4;
    String strTopic1 = "我们团队的组成人员";
    String strTopic2 = "参加马拉松真好玩";
    String strTopic3 = "商业计划与推广计划";
    String strTopic4 = "我们产品的介绍";


    Button begin;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                button1.setText(strTopic1 + ":" + top1Number);
                button2.setText(strTopic2 + ":" + top2Number);

                button3.setText(strTopic3 + ":" + top3Number);

                button4.setText(strTopic4 + ":" + top4Number);

            } else {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(_this, "处理完成", Toast.LENGTH_LONG).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        _this = this;
        progressBar = (ProgressBar) findViewById(R.id.progress);
        button1 = (TextView) findViewById(R.id.btn1);
        button2 = (TextView) findViewById(R.id.btn2);
        button3 = (TextView) findViewById(R.id.btn3);
        button4 = (TextView) findViewById(R.id.btn4);
        begin = (Button) findViewById(R.id.begin);


        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                begin.setVisibility(View.GONE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//chinese_log_temp.txt";
                        File tempFile = new File(tempPath);
                        if (tempFile.exists() && tempFile.isFile()) {
                            BufferedReader reader = null;
                            StringBuilder stringBuilder = new StringBuilder();
                            try {
                                reader = new BufferedReader(new FileReader(tempFile));
                                String tempString = null;
                                line = 1;
                                while ((tempString = reader.readLine()) != null) {
                                    stringBuilder.append(tempString).append("\n");
                                    line++;
                                    myCompare(tempString);
                                }
                                Message msg = new Message();
                                msg.what = -100;
                                mHandler.sendMessage(msg);
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (reader != null) {
                                    try {
                                        reader.close();
                                    } catch (IOException e1) {
                                    }
                                }
                            }
                        }
                        // return "文件为空,请先录音";
                    }
                }).start();
            }
        });


        button1.setOnClickListener(new View.OnClickListener()

                                   {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(_this, HistoryDetailActivity.class);
                                           intent.putExtra("my_word", "111");
                                           startActivity(intent);

                                       }
                                   }

        );


        button2.setOnClickListener(new View.OnClickListener()

                                   {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(_this, HistoryDetailActivity.class);
                                           intent.putExtra("my_word", "222");
                                           startActivity(intent);

                                       }
                                   }

        );


        button3.setOnClickListener(new View.OnClickListener()

                                   {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(_this, HistoryDetailActivity.class);
                                           intent.putExtra("my_word", "333");
                                           startActivity(intent);

                                       }
                                   }

        );


        button4.setOnClickListener(new View.OnClickListener()

                                   {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(_this, HistoryDetailActivity.class);
                                           intent.putExtra("my_word", "444");
                                           startActivity(intent);

                                       }
                                   }

        );


    }

    private void myCompare(String str) {

        Message msg = new Message();
        int numMax = -1;
        double similar = 0.0;

        msg.what = 100;
        double currentSimilar = NLPUtil.howSimilar(strTopic1, str);
        Log.d(TAG, "myCompare: 1 " + currentSimilar);

        if (similar < currentSimilar) {
            numMax = 1;

            similar = currentSimilar;
        }
        currentSimilar = NLPUtil.howSimilar(strTopic2, str);
        Log.d(TAG, "myCompare: 2 " + currentSimilar);

        if (similar < currentSimilar) {
            numMax = 2;
            similar = currentSimilar;
        }
        currentSimilar = NLPUtil.howSimilar(strTopic3, str);
        Log.d(TAG, "myCompare: 3 " + currentSimilar);

        if (similar < currentSimilar) {
            numMax = 3;

            similar = currentSimilar;
        }
        currentSimilar = NLPUtil.howSimilar(strTopic4, str);
        Log.d(TAG, "myCompare: 4 " + currentSimilar);

        if (similar < currentSimilar) {
            numMax = 4;


            similar = currentSimilar;
        }
        switch (numMax) {
            case 1:
                top1Number++;
                MyPrintLogUtil.printTopic1("# " + similar + "\n" + str);

                break;

            case 2:
                MyPrintLogUtil.printTopic2("# " + similar + "\n" + str);


                top2Number++;
                break;
            case 3:
                MyPrintLogUtil.printTopic3("# " + similar + "\n" + str);

                top3Number++;
                break;
            case 4:
                MyPrintLogUtil.printTopic4("# " + similar + "\n" + str);

                top4Number++;
                break;
        }

//        msg.obj = str;

        mHandler.sendMessage(msg);
    }
}
