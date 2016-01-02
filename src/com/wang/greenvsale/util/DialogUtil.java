package com.wang.greenvsale.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;

import com.wang.greenvsale.MainActivity;

public class DialogUtil {
	
	//定义一个消息提示框
	public static void showDialog(final Context context,String msg,boolean goHome){
		//创建一个AlertDialog.Builder对象	
		AlertDialog.Builder builder=new AlertDialog.Builder(context).setMessage(msg).setCancelable(false);
		if(goHome){
			builder.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent i=new Intent(context,MainActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(i);
					
				}
			});
		}
		else{
			builder.setPositiveButton("确定", null);
		}
		
		builder.create().show();
		
		
	}
	
	//定义一个显示组件的对话框
	public static void showDialog(Context context,View view){
		
		new AlertDialog.Builder(context)
			.setView(view)
			.setCancelable(false)
			.setPositiveButton("确定", null)
			.create()
			.show();
	}

}
