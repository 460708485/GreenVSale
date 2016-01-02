package com.wang.greenvsale.fragment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wang.greenvsale.MainActivity;
import com.wang.greenvsale.R;
import com.wang.greenvsale.util.DialogUtil;
import com.wang.greenvsale.util.HttpUtil;

public class Person_Fragment extends Fragment{
	private EditText et_userName;
	private EditText et_userPass;
	private Button login;
	private Button regist;
	private View view;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.person, container, false);
		init();
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(validate()){
					if(loginPro()){
						Intent intent=new Intent(getActivity(),MainActivity.class);
						startActivity(intent);
						getActivity().finish();
					}
					
				}
				
				
			}
		});
		return view;
	}
	
	public void init(){
		et_userName=(EditText)view.findViewById(R.id.login_name_edit);
		et_userPass=(EditText)view.findViewById(R.id.login_pwd_edit);
		login=(Button)view.findViewById(R.id.bt_login);
		regist=(Button)view.findViewById(R.id.bt_regist);
		
		
	}
	
	//常规检查，检查用户输入的是否合法
	public boolean validate(){
		String username=et_userName.getText().toString().trim();
		if("".equals(username)){
			DialogUtil.showDialog(getActivity(),"用户名不能为空", false);
			return false;
		}
		String password=et_userPass.getText().toString().trim();
		if("".equals(password)){
			DialogUtil.showDialog(getActivity(), "密码不能为空", false);
			return false;
		}
		
		return true;
		
	}
	
	//判断输入的是否匹配
	public boolean loginPro(){
		// 获取用户输入的用户名、密码
		String username = et_userName.getText().toString();
		String pwd = et_userPass.getText().toString();
		JSONObject jsonObj;
		try{
			jsonObj=query(username,pwd);
			if(jsonObj.getInt("status")==0){
				return true;
			}
			
		}catch(Exception e){
			DialogUtil.showDialog(getActivity()
					, "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}
		return false;
	}
	
	private JSONObject query(String username,String password) throws JSONException, InterruptedException, ExecutionException{
		Map<String,String> map=new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		String url=HttpUtil.BASE_URL+"user/login.do";
		
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
}
