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
	//����HttpClient����
	public static HttpClient httpClient=new DefaultHttpClient();
	//��������ַ
	public static final String BASE_URL="http://localhost:8080/GVSaleService/";
	
	/**
	 * get����
	 * @param url ����url����
	 * @return json����
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static String getRequest(final String url) throws InterruptedException, ExecutionException{
		FutureTask<String> task=new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				//����HttpGet����
				HttpGet get=new HttpGet();
				//����get����
				HttpResponse httpResponse=httpClient.execute(get);
				//����������ɹ�����Ӧ
				if(httpResponse.getStatusLine().getStatusCode()==200){
					//��ȡ��������Ӧ���ַ���
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
	 * post����
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
					//��װ�������
					parms.add(new BasicNameValuePair(key, params.get(key)));
				}
				//�����������
				post.setEntity(new UrlEncodedFormEntity(parms,"gbk"));
				//����post����
				HttpResponse response =httpClient.execute(post);
				if(response.getStatusLine().getStatusCode()==200){
					//��ȡ��������Ӧ���ַ���
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
