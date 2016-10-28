package com.robin.myasynctask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by robin on 2016/10/13.
 */
public class HandlerAct extends AppCompatActivity {
    private static final String TAG = "robin debug";
    Button btn1, btn2,btn3;
    TextView txt1, txt2,txt3;
    int cnt1, cnt2;

    class Myhandler extends Handler {

        @Override //looper对象取出消息式才调用
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 123:

                    txt2.setText("当前计数值:" + cnt2);

                    break;
            }


        }
    }

    Myhandler myhandler = new Myhandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uithread_act2);
        btn1 = (Button) findViewById(R.id.button1);
        txt1 = (TextView) findViewById(R.id.text1);
        btn2 = (Button) findViewById(R.id.button2);
        txt2 = (TextView) findViewById(R.id.text2);
        btn3 = (Button) findViewById(R.id.button3);
        txt3 = (TextView) findViewById(R.id.text3);
    }

    public void doButton(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Log.e(TAG, "activity thread:" + Thread.currentThread());
                cnt1++;
                txt1.setText("当前计数值:" + cnt1);
                break;
            case R.id.button2:

                new Thread() {
                    @Override
                    public void run() {
                        Log.e(TAG,"sub thread:"+Thread.currentThread());
                        while (cnt2 < 20000) {
                            cnt2++;
                            try {
                                Thread.sleep(1000);//阻塞,休眠 让代码暂时停止执行
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //方法1
//                            Message msg = Message.obtain();//从消息池取不用的消息对象
//                            msg.what = 123;
//                            myhandler.sendMessage(msg);//子线程给主线程发消息

                            //方法2
                            myhandler.sendEmptyMessage(123);

                        }
                    }

                }.start();
                break;
            case R.id.button3:
                setTitle("启动定时炸弹....3秒爆!!!");
                txt3.setText("爆炸了.....");
                myhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG,"postdelaly thread:"+Thread.currentThread());
                        setTitle("爆炸了.....");
                        txt3.setText("爆炸了.....");
                    }
                },3000);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        cnt2=30000;//结束所有的子线程
        super.onDestroy();
    }
}
