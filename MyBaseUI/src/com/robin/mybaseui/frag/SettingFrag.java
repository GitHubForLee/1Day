package com.robin.mybaseui.frag;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robin.mybaseui.R;

/**  
 *  
 * @author robin  
 */
public class SettingFrag extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		return inflater.inflate(R.layout.setting_layout, container,false);
	}

}