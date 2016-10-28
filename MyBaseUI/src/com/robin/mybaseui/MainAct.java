package com.robin.mybaseui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.robin.mybaseui.app.MyApp;
import com.robin.mybaseui.frag.ContactsFrag;
import com.robin.mybaseui.frag.MsgFrag;
import com.robin.mybaseui.frag.NewsFrag;
import com.robin.mybaseui.frag.SettingFrag;

public class MainAct extends Activity{
	MsgFrag msgFragment;
	NewsFrag dtFragment;
	ContactsFrag contactsFragment;
	SettingFrag guanyuFragment;
	RadioGroup radioGroup1;
	
	private Button setting;
	FragmentManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		manager=this.getFragmentManager();
		
		setting=(Button)findViewById(R.id.button2);
		setting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MyApp.ACTION_SETTING));
			}
		});
		msgFragment = new MsgFrag();
		dtFragment = new NewsFrag();
		contactsFragment = new ContactsFrag();
		guanyuFragment = new SettingFrag();

		radioGroup1 = (RadioGroup) this.findViewById(R.id.radioGroup1);
		radioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.radio0:
					radio0();
					break;
				case R.id.radio1:
					radio1();
					break;
				case R.id.radio2:
					radio2();
					break;
				case R.id.radio3:
					radio3();
					break;
				}
			}
		});
		radio0();
	}
	protected void radio3() {
		// TODO Auto-generated method stub
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.line,guanyuFragment);
		transaction.commit();
	}
	protected void radio2() {
		// TODO Auto-generated method stub
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.line,dtFragment);
		transaction.commit();
	}
	protected void radio1() {
		// TODO Auto-generated method stub
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.line, contactsFragment);
		transaction.commit();
	}
	protected void radio0() {
		// TODO Auto-generated method stub
		FragmentTransaction transaction=manager.beginTransaction();
		transaction.replace(R.id.line, msgFragment);
		transaction.commit();
	}

}
