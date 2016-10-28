package com.robin.myasynctask;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by robin on 2016/10/13.
 */
public class HandlerTimeAct extends AppCompatActivity{
    private static final int MSGTIME =0 ;
    private static final int MSGPD =1 ;
    Button btn;
    TextView txt1,txt2;
    ProgressDialog pd;
    int value;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSGTIME:
                    txt2.setText(getDate());
                    handler.sendEmptyMessageDelayed(MSGTIME,1000);//延时1秒再发消息

                    break;
                case MSGPD:
                    int v=(Integer)msg.obj;
                    if(v<=100){
                        pd.setProgress(v);
                        txt1.append(v+"\n");
                        if(v==100){
                            pd.dismiss();
                        }
                    }



                    break;

            }

        }
    };

    private String getDate() {
        Time time=new Time();
        time.setToNow();
        return time.format("%Y-%m-%d %H:%M:%S");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face);

        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
            }
        });

        txt1=(TextView)findViewById(R.id.textView1);
        txt2=(TextView)findViewById(R.id.textView2);//显示当前的时间

    }

    private void showProgressDialog() {
        pd=new ProgressDialog(this);
        pd.setMessage("正在下载,请稍后...");
        pd.setTitle("启动后台任务");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.show(); //弹出进度框
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    value=value+10;
                    Message msg=Message.obtain();
                    msg.what=MSGPD;
                    msg.obj=new Integer(value);
                    handler.sendMessage(msg);
                    if(value==100){
                        break;
                    }


                }
            }
        }.start();



    }

    @Override
    protected void onStart() {
        super.onStart();
        if(handler!=null){
            handler.sendEmptyMessage(MSGTIME);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(handler!=null){
            handler.removeMessages(MSGTIME);//移除消息,不调用消息处理方法
        }


    }
}
