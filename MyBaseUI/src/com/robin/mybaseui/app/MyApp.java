package com.robin.mybaseui.app;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

//Application ��Ķ���  ��һ��ȫ�ֶ���  ��Ӧ�������й���Ķ���,���ݷ�������
//�������ں�Ӧ����ͬ
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
