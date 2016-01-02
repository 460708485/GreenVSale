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

public class HttpUtil {
	//创建HttpClient对象
	public static HttpClient httpClient=new DefaultHttpClient();
	//服务器地址
	public static final String BASE_URL="http://localhost:8080/GVSaleService/";
	
	/**
	 * get请求
	 * @param url 发送url请求
	 * @return json数据
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static String getRequest(final String url) throws InterruptedException, ExecutionException{
		FutureTask<String> task=new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				//创建HttpGet对象
				HttpGet get=new HttpGet();
				//发送get请求
				HttpResponse httpResponse=httpClient.execute(get);
				//如果服务器成功地响应
				if(httpResponse.getStatusLine().getStatusCode()==200){
					//获取服务器响应的字符串
					String result=EntityUtils.toString(httpResponse.getEntity());
					
					return result;
				}
				return null;
			}
		});
		new Thread(task).start();
		return task.get();
	}
	
	/**
	 * post请求
	 * @param url
	 * @param params
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static String postRequest(final String url,final Map<String,String> params) throws InterruptedException, ExecutionException{
		FutureTask<String> task=new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				HttpPost post=new HttpPost();
				List<NameValuePair> parms=new ArrayList<NameValuePair>();
				for(String key:params.keySet()){
					//封装请求参数
					parms.add(new BasicNameValuePair(key, params.get(key)));
				}
				//设置请求参数
				post.setEntity(new UrlEncodedFormEntity(parms,"gbk"));
				//发送post请求
				HttpResponse response =httpClient.execute(post);
				if(response.getStatusLine().getStatusCode()==200){
					//获取服务器响应的字符串
					String result=EntityUtils.toString(response.getEntity());
					return result;
				}
				return null;
			}
			
			
		});
		
		new Thread(task).start();
		return task.get();
		
		
	}
	
}
