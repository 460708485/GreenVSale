package com.wang.greenvsale.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/*
 * 网络连接工具类
 */
public class HttpUtil {
	
	/**
	 * 通过泛型将json转化为对象
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	 public static <T> List<T> getObject(final Class<T> cls,String url,RequestParams params) {
		final List<T> t=new ArrayList<T>();
		HttpUtils http=new HttpUtils();
//		RequestParams  params=new RequestParams();
//		params.addQueryStringParameter("id", "1");
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Log.i("error", "获取数据失败");
			}

			public void onSuccess(ResponseInfo<String> info) {
				Gson gson=new Gson();
				
				t.add(gson.fromJson(info.result, cls));
				
			}
		});
		return t;
	 }
	 
	 
	 
	 
	
}
