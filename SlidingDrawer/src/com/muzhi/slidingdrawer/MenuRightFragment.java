package com.muzhi.slidingdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuRightFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		return inflater.inflate(R.layout.menu_layout_right, container, false);
	}
}
