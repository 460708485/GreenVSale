package com.wang.greenvsale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
/**
 * 欢迎界面,延迟3秒后进入主界面
 * @author wang
 *
 */
public class WelcomeActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
				
			}
		}, 3000);
		
	
	}
	
}
