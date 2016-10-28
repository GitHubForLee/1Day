package com.robin.myasynctask.async;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.robin.myasynctask.AsynctTaskAct;

/**
 * Created by robin on 2016/10/13.
 */
public class AnotherAsync extends AsyncTask<Integer,Integer,String> {

    private static final String TAG ="robin debug" ;
    private AsynctTaskAct act;

    public AnotherAsync(AsynctTaskAct act){
        this.act=act;
    }

    @Override //前台操作 UI线程上执行的代码
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(TAG, "doPreexe:" + Thread.currentThread().getName());
    }

    @Override//后台操作  做耗时异步任务 代码是一个子线程中运行
    protected String doInBackground(Integer... params) {
        Log.e(TAG, "doInBackground:" + Thread.currentThread().getName());
        int cnt=0;
        for(int i=0;i<params.length;i++){
            cnt+=params[i];
        }

        int value=0;
        for(int i=1;i<=cnt;i++){
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
        act.progressBar.setVisibility(View.GONE);
        act.txtBar.append(result+"\n");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        act.progressBar.setProgress(values[0]);
        act.txtBar.append("当前进度值:"+values[0]+"\n");


    }
}
