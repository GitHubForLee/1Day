package com.robin.mybaseui.app;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

//Application 类的对象  是一个全局对象  可应用中所有共享的对象,数据放在这里
//生命周期和应用相同
public class MyApp extends Application{

	public static final String SYSTEM_SHARE_FILE = "myconfig";
	public static final String SYSTEM_CONF_WELCOME = "config_welcome";
	public static final String ACTION_WELCOMEACT = "com.robin.mybaseui.WelcomeAct";
	public static final String ACTION_MAINACT = "com.robin.mybaseui.MainAct";
	public static final String ACTION_SETTING = "com.robin.mybaseui.SettingAct";
	private static final String TAG = "robin debug";
	
	public void foo(){
		Log.e(TAG,"call Myapp  foo()");
	}

}
