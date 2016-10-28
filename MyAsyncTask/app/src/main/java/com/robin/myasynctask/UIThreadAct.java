package com.robin.myasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by robin on 2016/10/13.
 */
public class UIThreadAct extends AppCompatActivity {
    Button btn1, btn2;
    TextView txt1, txt2;
    int cnt1, cnt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uithread_act);
        btn1 = (Button) findViewById(R.id.button1);
        txt1 = (TextView) findViewById(R.id.text1);
        btn2 = (Button) findViewById(R.id.button2);
        txt2 = (TextView) findViewById(R.id.text2);

    }

    public void doButton(View view) {
        switch (view.getId()) {
            case R.id.button1:
                cnt1++;
                txt1.setText("当前计数值:"+cnt1);


                break;
            case R.id.button2:

                new Thread() {
                    @Override
                    public void run() {
                        while (cnt2 < 20)
                            cnt2++;
                        try {
                            Thread.sleep(1000);//阻塞,休眠 让代码暂时停止执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        txt2.setText("当前计数值:" + cnt2);
                    }
                }.start();


                break;


        }

    }

}
