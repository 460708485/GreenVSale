package com.wang.greenvsale.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wang.greenvsale.R;

public class More_Fragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View view=inflater.inflate(R.layout.more, container,false);
		ViewUtils.inject(this,view);
		
		return view;
	}
	
	@ViewInject(R.id.show)//注解方式更加方便
	private TextView tv;
	@ViewInject(R.id.content_btn)
	private Button button;
	

	@OnClick(R.id.content_btn)
	public void showInfo(View view){
		HttpUtils http=new HttpUtils();
		RequestParams  params=new RequestParams();
		params.addQueryStringParameter("id", "1");
		http.send(HttpMethod.POST, "http://192.168.1.102:8080/GreenSale/getUserByID.do", params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

				tv.setText("not found");
			}

			@Override
			public void onSuccess(ResponseInfo<String> info) {
					
				tv.setText(info.result);
				
			}
			
		});
		
		
		
	}
	
	
}
