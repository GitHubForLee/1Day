package com.robin.mybaseui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.robin.mybaseui.app.MyApp;

public class WelcomeAct extends Activity{
	ArrayList<View> views=new ArrayList<View>();
	PagerAdapter adapter=new PagerAdapter() {
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));
			return views.get(position);
		}
		
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}
	};
	Button btn;
	ViewPager pager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		LayoutInflater inflater=LayoutInflater.from(this);
		views.add(inflater.inflate(R.layout.welcome_1, null));
		views.add(inflater.inflate(R.layout.welcome_2, null));
		views.add(inflater.inflate(R.layout.welcome_3, null));
		View view4=inflater.inflate(R.layout.welcome_4, null);
		views.add(view4);
		btn=(Button) view4.findViewById(R.id.btnStart);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doClick();
			}
		});
		pager=(ViewPager)findViewById(R.id.pager);
		pager.setAdapter(adapter);
	}
	protected void doClick() {
		// TODO Auto-generated method stub
		SharedPreferences sp=getSharedPreferences(MyApp.SYSTEM_SHARE_FILE, MODE_PRIVATE);
		Editor editor=sp.edit();
		editor.putBoolean(MyApp.SYSTEM_CONF_WELCOME, true);
		editor.commit();
		Intent intent=new Intent(MyApp.ACTION_MAINACT);
		startActivity(intent);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		finish();
		
	}

}
