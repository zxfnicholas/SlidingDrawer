package com.muzhi.slidingdrawer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.nineoldandroids.view.ViewHelper;

/**
 * http://weibo.com/zengxiaofeng
 * @author jazzy
 *
 */
public class MainActivity extends FragmentActivity
{

	private DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initEvents();

	}
	private void initView(){
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
	}
	
	public void OpenLeftMenu(View view){
		mDrawerLayout.openDrawer(Gravity.LEFT);
	}
	public void OpenRightMenu(View view){
		mDrawerLayout.openDrawer(Gravity.RIGHT);
	}
	


	private void initEvents(){
		mDrawerLayout.setDrawerListener(new DrawerListener(){
			@Override
			public void onDrawerStateChanged(int newState){
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset){
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals("LEFT")){

					float leftScale = 1 - 0.3f * scale;

					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
					ViewHelper.setTranslationX(mContent,mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					//以下代码是仿QQ效果
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} 
				else{
					ViewHelper.setTranslationX(mContent,-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					//以下代码是仿QQ效果
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			@Override
			public void onDrawerOpened(View drawerView){
				if (drawerView.getTag().equals("LEFT")){
					mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
				}
				else{
					mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.LEFT);
				}
			}

			@Override
			public void onDrawerClosed(View drawerView){
				mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
			}
		});
	}

	

}
