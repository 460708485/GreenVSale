package com.wang.greenvsale.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;

import com.wang.greenvsale.MainActivity;

public class DialogUtil {
	
	//����һ����Ϣ��ʾ��
	public static void showDialog(final Context context,String msg,boolean goHome){
		//����һ��AlertDialog.Builder����	
		AlertDialog.Builder builder=new AlertDialog.Builder(context).setMessage(msg).setCancelable(false);
		if(goHome){
			builder.setPositiveButton("ȷ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent i=new Intent(context,MainActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(i);
					
				}
			});
		}
		else{
			builder.setPositiveButton("ȷ��", null);
		}
		
		builder.create().show();
		
		
	}
	
	//����һ����ʾ����ĶԻ���
	public static void showDialog(Context context,View view){
		
		new AlertDialog.Builder(context)
			.setView(view)
			.setCancelable(false)
			.setPositiveButton("ȷ��", null)
			.create()
			.show();
	}

}
