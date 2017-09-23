package com.ykai.englishdialog.myapplication;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ykai.engbot.R;


/**
 * reference http://blog.csdn.net/u011546655/article/details/45896225#  // 智能对话
 * http://blog.csdn.net/highboys/article/details/52161744  //百度翻译
 */


public class ChatMainActivity extends Activity {
    private List<ChatMessage> list;
    private ListView chat_listview;
    private EditText chat_input;
    private Button chat_send;
    private ChatMessageAdapter chatAdapter;
    private ChatMessage chatMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat_main);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        chat_listview = (ListView) findViewById(R.id.chat_listview);
        chat_input = (EditText) findViewById(R.id.chat_input_message);
        chat_send = (Button) findViewById(R.id.chat_send);
    }

    private void initListener() {
        chat_send.setOnClickListener(onClickListener);
    }

    private void initData() {
        list = new ArrayList<ChatMessage>();
        list.add(new ChatMessage("let's begin talking!", ChatMessage.Type.INCOUNT, new Date()));
        chatAdapter = new ChatMessageAdapter(list);
        chat_listview.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
    }

    private void chat() {
        final String send_message = chat_input.getText().toString().trim();
        if (TextUtils.isEmpty(send_message)) {
            Toast.makeText(ChatMainActivity.this, "对不起，您还未发送任何消息",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ChatMessage sendChatMessage = new ChatMessage();
        sendChatMessage.setMessage(send_message);
        sendChatMessage.setData(new Date());
        sendChatMessage.setType(ChatMessage.Type.OUTCOUNT);
        list.add(sendChatMessage);
        chatAdapter.notifyDataSetChanged();
        chat_input.setText("");

        new Thread() {
            public void run() {
                ChatMessage chat = HttpUtils.sendMessage(send_message);


                Message message = new Message();
                message.what = 0x1;
                message.obj = chat;
                handler.sendMessage(message);
            }

        }.start();

    }

    private String TAG = "ChatMainActivity";
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x1) {
                if (msg.obj != null) {
                    chatMessage = (ChatMessage) msg.obj;
                }


                //----- begin translate


                BaiduTranslateHelper baiduTranslateHelper = new BaiduTranslateHelper();
                try {
                    baiduTranslateHelper.translate(new String(chatMessage.getMessage().getBytes(), "utf-8"), "zh", "en", new HttpCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            chatMessage.setMessage(chatMessage.getMessage() + "\n" + result);
//                    System.out.printf("res: "+result);
                        }

                        @Override
                        public void onFailure(String exception) {

                            Log.e(TAG, "onFailure: " + exception);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //-----  stop translate

                list.add(chatMessage);


                chatAdapter.notifyDataSetChanged();
            }
        }


    };

    OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.chat_send:
                    chat();
                    break;
            }
        }
    };
}
