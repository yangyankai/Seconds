package com.ykai.engbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ykai on 17/11/12.
 */
public class LevelTwoActivity extends Activity {
    private Button btnBegin;
    private Activity _this;
    private ArrayList<String> topics = new ArrayList<>();
    String currentMeetingPath;


    private String beginTimeStamp;
    private String endTimeStamp;


    // yyk begin
    String top1 = null;
    String top2 = null;
    String top3 = null;
    String top4 = null;

    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;


    EditText et1_1;
    EditText et2_1;
    EditText et3_1;
    EditText et4_1;


    EditText et1_2;
    EditText et2_2;
    EditText et3_2;
    EditText et4_2;

    ProgressBar progressBar;


    String[] saperateResult;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
//                button1.setText(strTopic1 + ":" + top1Number);
//                button2.setText(strTopic2 + ":" + top2Number);
//                button3.setText(strTopic3 + ":" + top3Number);
//                button4.setText(strTopic4 + ":" + top4Number);
                if (null != msg.obj) {
//                    txtLog.setText("" + msg.obj.toString());
                }

            } else {
//                textTile.setText("NLP");
                progressBar.setVisibility(View.GONE);
//                txtLog.setText("处理完成");

                String resultTopic = "";
                for (String tic :
                        topics) {
                    resultTopic += (tic + " ");
                }
                String resultContent = "";
                for (String ctt :
                        saperateResult) {
                    resultContent += (ctt + "\n\n");
                }


                MyPrintLogUtil.printTopic(resultTopic, currentMeetingPath);
                MyPrintLogUtil.printResultContent(resultContent, currentMeetingPath);
                MyPrintLogUtil.moveMeetingContent(currentMeetingPath);


                Intent intent = new Intent(_this, ResultActivity.class);
                intent.putExtra("currentMeetingPath", currentMeetingPath);
                startActivity(intent);


                Toast.makeText(_this, "处理完成", Toast.LENGTH_LONG).show();
            }
        }
    };
    private int line;

    // yyk end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_two_activity);


        Intent intent = getIntent();
        top1 = intent.getStringExtra("tp1");
        top2 = intent.getStringExtra("tp2");
        top3 = intent.getStringExtra("tp3");
        top4 = intent.getStringExtra("tp4");
        beginTimeStamp = intent.getStringExtra("beginTimeStamp");
        endTimeStamp = intent.getStringExtra("endTimeStamp");

        currentMeetingPath = MyPrintLogUtil.APP_PATH + File.separator + (beginTimeStamp + "-" + endTimeStamp);
        MyPrintLogUtil.createFileDirectory(currentMeetingPath);


        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);

        et1_1 = (EditText) findViewById(R.id.edit_1_1);
        et2_1 = (EditText) findViewById(R.id.edit_2_1);
        et3_1 = (EditText) findViewById(R.id.edit_3_1);
        et4_1 = (EditText) findViewById(R.id.edit_4_1);

        et1_2 = (EditText) findViewById(R.id.edit_1_2);
        et2_2 = (EditText) findViewById(R.id.edit_2_2);
        et3_2 = (EditText) findViewById(R.id.edit_3_2);
        et4_2 = (EditText) findViewById(R.id.edit_4_2);

        progressBar = (ProgressBar) findViewById(R.id.progress);


        _this = this;
        btnBegin = (Button) findViewById(R.id.btn_begin);


        if (null != top1) {
            et1.setText(top1);
        }
        if (null != top2) {
            et2.setText(top2);
        }
        if (null != top3) {
            et3.setText(top3);
        }
        if (null != top4) {
            et4.setText(top4);
        }


        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//chinese_log_temp.txt";
                File file = new File(tempPath);
                if (!file.exists()) {
                    Toast.makeText(_this, "请返回上一界面进行录音", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                String top1 = et1.getText().toString().trim();
                String top2 = et2.getText().toString().trim();
                String top3 = et3.getText().toString().trim();
                String top4 = et4.getText().toString().trim();

                String t1_1 = et1_1.getText().toString().trim();
                String t2_1 = et2_1.getText().toString().trim();
                String t3_1 = et3_1.getText().toString().trim();
                String t4_1 = et4_1.getText().toString().trim();
                String t1_2 = et1_2.getText().toString().trim();
                String t2_2 = et2_2.getText().toString().trim();
                String t3_2 = et3_2.getText().toString().trim();
                String t4_2 = et4_2.getText().toString().trim();

                topics.clear();

                addTopic(top1, t1_1, t1_2);
                addTopic(top2, t2_1, t2_2);
                addTopic(top3, t3_1, t3_2);
                addTopic(top4, t4_1, t4_2);

                if (topics.size() <= 0) {
                    topics.add("此次会议没输入重点");
                }

                saperateResult = new String[topics.size()];

                for (int i = 0; i < topics.size(); i++) {
                    saperateResult[i] = " 重点 " + (i + 1) + " : " + topics.get(i);
                }

                beginNLP();


            }
        });
    }

    private void addTopic(String top1, String t1_1, String t1_2) {

        if (null != top1 && top1.length() > 0) {
            boolean isHasSubTopic = false;
            if (null != t1_1 && t1_1.length() > 0) {
                isHasSubTopic = true;
                topics.add(top1 + "-" + t1_1);
            }

            if (null != t1_2 && t1_2.length() > 0) {
                isHasSubTopic = true;
                topics.add(top1 + "-" + t1_2);
            }

            if (!isHasSubTopic) {
                topics.add(top1);
            }
        } else {

            if (null != t1_1 && t1_1.length() > 0) {
                topics.add(t1_1);
            }

            if (null != t1_2 && t1_2.length() > 0) {
                topics.add(t1_2);
            }
        }

    }

    private void beginNLP() {
        progressBar.setVisibility(View.VISIBLE);
//        begin.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                line = 0;

                String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//chinese_log_temp.txt";
                File tempFile = new File(tempPath);
                if (tempFile.exists() && tempFile.isFile()) {
                    BufferedReader reader = null;
                    StringBuilder stringBuilder = new StringBuilder();
                    try {
                        reader = new BufferedReader(new FileReader(tempFile));
                        String tempString = null;
//                        line = 1;
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
                } else {
                    progressBar.setVisibility(View.GONE);
//                    txtLog.setText("请返回上一界面录音");
//                    textTile.setText("NLP");
                }
                // return "文件为空,请先录音";
            }
        }).start();
    }


    private void myCompare(String str) {

        if (topics.size() <= 0) {
            return;
        }
        int numMax = 0;
        double similar = 0.0;


        for (int i = 0; i < topics.size(); i++) {
            double currentSimilar = NLPUtil.howSimilar(topics.get(i), str);
            if (similar < currentSimilar) {
                similar = currentSimilar;
                numMax = i;
            }
        }

        saperateResult[numMax] = saperateResult[numMax] + "\n" + str;
    }


}
