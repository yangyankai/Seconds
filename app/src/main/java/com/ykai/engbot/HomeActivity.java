package com.ykai.engbot;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.StrictMode;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.android.voicedemo.activity.setting.AllSetting;
import com.baidu.android.voicedemo.control.MyRecognizer;
import com.baidu.android.voicedemo.recognization.CommonRecogParams;
import com.baidu.android.voicedemo.recognization.IStatus;
import com.baidu.android.voicedemo.recognization.MessageStatusRecogListener;
import com.baidu.android.voicedemo.recognization.StatusRecogListener;
import com.baidu.android.voicedemo.recognization.all.AllRecogParams;
import com.baidu.android.voicedemo.recognization.offline.OfflineRecogParams;
import com.baidu.android.voicedemo.recognization.online.InFileStream;
import com.baidu.android.voicedemo.util.Logger;
import com.ykai.englishdialog.myapplication.ChatUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 取保手机有网
 * Created by ykai on 17/9/16.
 */
public class HomeActivity extends Activity implements IStatus {
    // HomeActivity begin
    private Activity _this;
    TextView editText;
    private boolean isRecording = false;
    Timer timer = new Timer();
    TextView textView;
    Button btnVoice;
    Button btnTTS;
    Button btnChat;
    Button setting;
    private int recLen = 0;

    private String TAG = "yyk";

    PowerManager pm;
    PowerManager.WakeLock wakeLock;
    // HomeActivity end


    // Voice begin
    ImageView imageView;

    protected Handler handler;
    protected String DESC_TEXT;
    protected int layout = R.layout.common;
    protected Class settingActivityClass = null;
    String strParam;
    String fileName_timeStamp;
    TextView counter;


    /**
     * 识别控制器，使用MyRecognizer控制识别的流程
     */
    protected MyRecognizer myRecognizer;
    /*
     * Api的参数类，仅仅用于生成调用START的json字符串，本身与SDK的调用无关
     */
    protected CommonRecogParams apiParams;
    /*
     * 本Activity中是否需要调用离线语法功能。根据此参数，判断是否需要调用SDK的ASR_KWS_LOAD_ENGINE事件
     */
    protected boolean enableOffline = false;
    /**
     * 控制UI按钮的状态
     */
    private int status;

    public HomeActivity() {
        super();
        settingActivityClass = AllSetting.class;
    }
    // Voice end


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        fileName_timeStamp = "" + SystemClock.uptimeMillis();
        timer.schedule(task, 1000, 1000);       // timeTask
        imageView = (ImageView) findViewById(R.id.recorder_bigger);

        // HomeActivity begin
        _this = this;


//        Log.d(TAG, "read_chinese "+       ReadUtils.readChinese());
//        Log.d(TAG, "read_english "+       ReadUtils.readEnglish());


        pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "PostLocationService");
        if (null != wakeLock) {
            wakeLock.acquire();
        }

        editText = (TextView) findViewById(R.id.edit);
        textView = (TextView) findViewById(R.id.text);
        btnVoice = (Button) findViewById(R.id.voice_2_txt_btn);
        btnChat = (Button) findViewById(R.id.chat_btn);
        btnTTS = (Button) findViewById(R.id.txt_2_voice_btn);
        setting = (Button) findViewById(R.id.voice_settings);
        counter = (TextView) findViewById(R.id.counter);


        btnChat.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                           translate();


                                       }
                                   }

        );
        // HomeActivity end


        // Voice begin
        setStrictMode();
        InFileStream.setContext(this);
        initView();
        handler = new Handler() {

            /*
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMsg(msg);
            }

        };
        Logger.setHandler(handler);
        initPermission();
        initRecog();

        // Voice end
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRecording) {
                recLen++;

            }
            int seconds = recLen % 60;
            int minute = recLen / 60;


            String strSecods;
            if (seconds < 10) {
                strSecods = "0" + seconds;
            } else {
                strSecods = "" + seconds;

            }

            String strMinutes;
            if (minute < 10) {
                strMinutes = "0" + minute;
            } else {
                strMinutes = "" + minute;

            }
            counter.setText(strMinutes + ":" + strSecods);
            handler.postDelayed(this, 1000);
        }
    };

    private void translate() {
        isRecording = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                strParam = editText.getText().toString();
                ChatUtil.iCallBack = new ChatUtil.ICallBack() {
                    @Override
                    public void onSmartChatBack(String s) {


                        Log.d(TAG, "onSmartChatBack: " + s);
                        Message message = new Message();
                        message.obj = s;
                        message.what = 100111;
                        mHandler.sendMessage(message);

                    }
                };
                ChatUtil.getEnglistReturn(strParam);

            }
        }).start();

    }

    // HomeActivity begin
    @Override
    protected void onDestroy() {

        if (null != wakeLock) {
            wakeLock.release();
            wakeLock = null;
        }

        myRecognizer.release(); // voice

        super.onDestroy();
    }

    // HomeActivity end


    private Handler mHandler = new Handler() {

        /*
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {

                case 100111:
                    if (null != msg.obj) {
//                        String mRes=
                        String res = msg.obj.toString().replace(",", ". ");
                        editText.setText("" + res);
                        imageView.setVisibility(View.VISIBLE);

//                        PrintUtils.myPrintLog(fileName_timeStamp, strParam, msg.obj.toString());
//                        MyPrintLogUtil.printLog("" + strParam + "\n");
                        MyPrintLogUtil.printChineseLog(strParam);
                        MyPrintLogUtil.printEnglishLog(res);

//                        mHandler.removeCallbacks(runnable);
                        //textView.setText("" + msg.obj.toString());
                    } else {
//                        textView.setText("null");
                        editText.setText("未输入...");

                    }

                    break;

                default:
                    break;
            }
        }

    };


// Voice begin


    protected void handleMsg(Message msg) {
        if (textView != null && msg.obj != null) {
            String res = msg.obj.toString();
            res.replace(",", ". ");
            textView.setText(res + "\n");
            if (res.length() % 2 == 0) {
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setVisibility(View.VISIBLE);

            }
//            textView.append(msg.obj.toString() + "\n");
        }
        switch (msg.what) { // 处理MessageStatusRecogListener中的状态回调
            case STATUS_FINISHED:
                if (msg.arg2 == 1) {
                    editText.setText(msg.obj.toString());
                    translate();
//                    toPrint("recognize finished !");
                }
                //故意不写break
            case STATUS_NONE:
            case STATUS_READY:
            case STATUS_SPEAKING:
            case STATUS_RECOGNITION:
                status = msg.what;
                updateBtnTextByStatus();
                break;

        }
    }

    protected void initView() {
        textView.setText(DESC_TEXT + "\n");
        if (setting != null && settingActivityClass != null) {
            setting.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, settingActivityClass);
                    startActivity(intent);
                }
            });
        }

        btnVoice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (btnVoice.getText().toString().contains("开始")) {


                }
                beginTalk();
//                handler.postDelayed(runnable, 1000);


            }
        });


    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
//                    if (isRecording) {
                    if (btnVoice.getText().toString().contains("停止")) {
                        recLen++;

                    }
                    int seconds = recLen % 60;
                    String strSecods;
                    if (seconds < 10) {
                        strSecods = "0" + seconds;
                    } else {
                        strSecods = "" + seconds;

                    }
                    int minute = recLen / 60;

                    String strMinutes;
                    if (minute < 10) {
                        strMinutes = "0" + minute;
                    } else {
                        strMinutes = "" + minute;

                    }
                    counter.setText(strMinutes + ":" + strSecods);
                }
            });
        }
    };


    private void beginTalk() {

        switch (status) {
            case STATUS_NONE: // 初始状态
                start();
                status = STATUS_WAITING_READY;
                updateBtnTextByStatus();
                textView.setText("");
                editText.setText("");
                break;
            case STATUS_WAITING_READY: // 调用本类的start方法后，即输入START事件后，等待引擎准备完毕。
            case STATUS_READY: // 引擎准备完毕。
            case STATUS_SPEAKING:
            case STATUS_FINISHED:// 长语音情况
            case STATUS_RECOGNITION:
                stop();
                status = STATUS_STOPPED; // 引擎识别中
                updateBtnTextByStatus();
                break;
            case STATUS_STOPPED: // 引擎识别中
                cancel();
                status = STATUS_NONE; // 识别结束，回到初始状态
                updateBtnTextByStatus();
                break;
        }

    }


    /**
     * 在onCreate中调用。初始化识别控制类MyRecognizer
     */
    protected void initRecog() {
        StatusRecogListener listener = new MessageStatusRecogListener(handler);
        myRecognizer = new MyRecognizer(this, listener);
        apiParams = getApiParams();
        status = STATUS_NONE;
        if (enableOffline) {
            myRecognizer.loadOfflineEngine(OfflineRecogParams.fetchOfflineParams());
        }
    }

    protected CommonRecogParams getApiParams() {

        Log.d(TAG, "getApiParams: yyk5");
        return new AllRecogParams(this);
    }

    /**
     * 开始录音，点击“开始”按钮后调用。
     */
    protected void start() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        Map<String, Object> params = apiParams.fetch(sp);
        myRecognizer.start(params);
    }


    /**
     * 开始录音后，手动停止录音。SDK会识别在此过程中的录音。点击“停止”按钮后调用。
     */
    private void stop() {
        myRecognizer.stop();
    }

    /**
     * 开始录音后，取消这次录音。SDK会取消本次识别，回到原始状态。点击“取消”按钮后调用。
     */
    private void cancel() {
        myRecognizer.cancel();
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }

    private void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

    }


    private void updateBtnTextByStatus() {
        switch (status) {
            case STATUS_NONE:
                btnVoice.setText("开始录音");
                btnVoice.setEnabled(true);
                setting.setEnabled(true);
                break;
            case STATUS_WAITING_READY:
            case STATUS_READY:
            case STATUS_SPEAKING:
            case STATUS_RECOGNITION:
                btnVoice.setText("停止录音");
                btnVoice.setEnabled(true);
                setting.setEnabled(false);
                isRecording = false;
                break;

            case STATUS_STOPPED:

                //timer.schedule(task, 100000000, 1000);       // timeTask

                btnVoice.setText("正在取消...");
                btnVoice.setEnabled(true);
                setting.setEnabled(false);
                break;
        }
    }

    // Voice end

}
