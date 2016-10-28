package com.robin.myasynctask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="robin debug" ;
    ListView listview;
    String[] functions={"ui线程","线程消息机制","使用handler定时","使用asynctask",
            "模拟listview分页加载"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=(ListView)findViewById(R.id.listView);
        listview.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,functions));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        doUIThread();
                        break;
                    case 1:
                        doHandleSendMsg();
                        break;
                    case 2:
                        doHandlerTime();
                        break;
                    case 3:
                        doAsynctask();
                        break;
                    case 4:
                        doListViewTask();
                        break;
                }
            }
        });
    }

    private void doListViewTask() {
        startActivity(new Intent(this,ListViewTaskAct.class));
    }

    private void doAsynctask() {
        startActivity(new Intent(this,AsynctTaskAct.class));
    }

    private void doHandlerTime() {
        startActivity(new Intent(this,HandlerTimeAct.class));
    }

    private void doHandleSendMsg() {
        Log.e(TAG,"activity thread:"+Thread.currentThread());
        startActivity(new Intent(this,HandlerAct.class));

    }

    private void doUIThread() {
        startActivity(new Intent(this,UIThreadAct.class));


            
    }
}
