package com.robin.myasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Created by robin on 2016/10/13.
 */
public class  ListViewTaskAct extends AppCompatActivity{
    ListView listView;
    ArrayAdapter<String> adapter;
    Button button;
    RelativeLayout relativeLayout;
    ViewSwitcher switcher;
    ArrayList<String> list;
    String[] items={"rose","jack","karl","alice","john","golsing","rubin","biller",
            "rose","jack","karl","alice","john","golsing","rubin","biller",
            "rose","jack","karl","alice","john","golsing","rubin","biller"


    };
    int index;

    class DownloadItem extends AsyncTask<Object,Object,Object> {


        @Override
        protected Object doInBackground(Object... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i=index;
            for(;i<(index+10);i++){
                list.add("数据项"+i);
            }
            index=i;
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            switcher.showPrevious();
            adapter.notifyDataSetChanged();
            setTitle("数据源item有"+list.size()+"个");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        listView=(ListView)findViewById(R.id.mylistview);
        button=(Button)View.inflate(this, R.layout.button, null);
        relativeLayout=(RelativeLayout)View.inflate(this, R.layout.progress, null);
        //初始化脚部视图开关容器对象
        switcher=new ViewSwitcher(this);
        switcher.addView(button);
        switcher.addView(relativeLayout);
        //添加listview的footer
        listView.addFooterView(switcher);

        list=new ArrayList<>();
        for(String s:items){
            list.add(s);
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.showNext();//切换到下一个view
                new DownloadItem().execute();//启动异步任务
            }
        });
    }
}
