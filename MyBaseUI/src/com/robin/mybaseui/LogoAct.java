package com.robin.mybaseui;

import com.robin.mybaseui.app.MyApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class LogoAct extends Activity {
	Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==1){
				//做是否启动宣传栏的判断  读sharedprefs
				SharedPreferences sp=getSharedPreferences(MyApp.SYSTEM_SHARE_FILE,Context.MODE_PRIVATE);
				boolean isWelcome=sp.getBoolean(MyApp.SYSTEM_CONF_WELCOME, false);
				Intent intent=new Intent();
				Log.e("robin debug",String.valueOf(isWelcome));
				if(!isWelcome){ //isWelcome=false  表示进入宣传界面
					intent.setAction(MyApp.ACTION_WELCOMEACT);
				}else{
					intent.setAction(MyApp.ACTION_MAINACT);
				}
				LogoAct.this.startActivity(intent);
				LogoAct.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
				LogoAct.this.finish();
				
				
				
				
				
			}
			
		}
	};
	
	
	Runnable runnable=new Runnable() {
		public void run() {
			//模拟初始化工作
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//子线程不能直接操作UI
			Message message=handler.obtainMessage();
			message.what=1;
			message.sendToTarget();//最终调用到handleMessage()
		
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		Thread thread=new Thread(runnable);
		thread.start();
		MyApp application=(MyApp) this.getApplicationContext();
		application.foo();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
