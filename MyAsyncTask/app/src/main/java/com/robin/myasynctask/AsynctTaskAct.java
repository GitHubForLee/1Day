package com.robin.myasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.robin.myasynctask.async.AnotherAsync;

/**
 * Created by robin on 2016/10/13.
 */
public class AsynctTaskAct extends AppCompatActivity{
    private static final String TAG ="robin debug" ;
    public ProgressBar progressBar;
    public TextView txtBar;

    class MyTask extends AsyncTask<String,Integer,String>{

        @Override //前台操作 UI线程上执行的代码
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "doPreexe:" + Thread.currentThread().getName());
        }

        @Override//后台操作  做耗时异步任务 代码是一个子线程中运行
        protected String doInBackground(String... params) {
            Log.e(TAG, "doInBackground:" + Thread.currentThread().getName());
            String url=params[0];
            Log.e(TAG,"doInBackground:url==>"+url);
            int value=0;
            for(int i=1;i<=10;i++){
                publishProgress(i*10);//参数值会传给onProgressUpdate()
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String result="这个力气活,我搞完了....";
            return result;
        }

        @Override //前台操作 UI线程上执行的代码
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e(TAG, "doPostExe:" + Thread.currentThread().getName());
            progressBar.setVisibility(View.GONE);
            txtBar.append(result+"\n");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            txtBar.append("当前进度值:"+values[0]+"\n");


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_act);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        txtBar=(TextView)findViewById(R.id.txtBar);
        //new MyTask().execute("http://www.sina.com");

        new AnotherAsync(this).execute(1,2,3,4);



    }
}
