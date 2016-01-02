package com.wang.greenvsale;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.ViewManager;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

import com.wang.greenvsale.fragment.Home_Fragment;
import com.wang.greenvsale.fragment.More_Fragment;
import com.wang.greenvsale.fragment.Person_Fragment;
import com.wang.greenvsale.fragment.Search_Fragment;
import com.wang.greenvsale.fragment.Shop_Fragment;

public class MainActivity extends FragmentActivity {
	
	private FragmentTabHost mTabHost;
	private RadioGroup rgGroup;
	private final Class[] fragments={Home_Fragment.class,
			Shop_Fragment.class,Search_Fragment.class,									     
			Person_Fragment.class,More_Fragment.class};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		initView();
		
	}
	
	
	
	/*
	 * 初始化视图
	 */
	public void initView(){
		
		mTabHost=(FragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		int count=fragments.length;
		for (int i = 0; i < count; i++) {
			TabSpec tabSpec=mTabHost.newTabSpec(i+"").setIndicator(i+"");
			
			mTabHost.addTab(tabSpec, fragments[i], null);
			
		}
		//RadioGroup的监听事件
		rgGroup=(RadioGroup)findViewById(R.id.tab_rg_menu);
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				switch (checkedId) {
				case R.id.radio_button0:
					mTabHost.setCurrentTab(0);
					break;
				case R.id.radio_button1:
					mTabHost.setCurrentTab(1);
					break;
				case R.id.radio_button2:
					mTabHost.setCurrentTab(2);
					break;
				case R.id.radio_button3:
					mTabHost.setCurrentTab(3);
					break;
				case R.id.radio_button4:
					mTabHost.setCurrentTab(4);
					break;

				default:
					break;
				}
				
				
			}
		});
		//默认进入界面的Tabhost按钮
		mTabHost.setCurrentTab(0);
		
		
		
		
	}



}
