package com.wang.greenvsale.fragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.wang.greenvsale.R;
import com.wang.greenvsale.adapter.MyPagerAdapter;

public class Home_Fragment extends Fragment {
	private ViewPager viewPager;
	private List<ImageView> imageViews;
	private String[] titles;
	private int[] imageIds;
	private List<View> dots;
	private ScheduledExecutorService service;
	private TextView tv_title;
	
	View view;
	int currentItem = 0;
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			viewPager.setCurrentItem(currentItem);
		};
		
	};
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.home, container, false);

		initView();

		return view;

	};
	
	public void initView() {
		imageIds = new int[] { R.drawable.slide1, R.drawable.slide2,
				R.drawable.slide3, R.drawable.slide4, R.drawable.slide5 };
		titles = new String[imageIds.length];
		titles[0] = "�����߲���Ϣ";
		titles[1] = "��ɫʳƷ����ֵ������";
		titles[2] = "����ʳƷ����������";
		titles[3] = "�л�ʳƷ������";
		titles[4] = "ƽ̨��������";
		
		imageViews =new ArrayList<ImageView>();
		
		for (int i = 0; i < imageIds.length; i++) {
			ImageView imageView=new ImageView(getActivity());
			//imageView.setImageResource(imageIds[i]);
			//imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageIds[i]));
			imageView.setImageBitmap(readBitmap(getActivity(), imageIds[i]));
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
			
		}
		
		
		dots=new ArrayList<View>();
		dots.add((View)view.findViewById(R.id.v_dot0));
		dots.add((View)view.findViewById(R.id.v_dot1));
		dots.add((View)view.findViewById(R.id.v_dot2));
		dots.add((View)view.findViewById(R.id.v_dot3));
		dots.add((View)view.findViewById(R.id.v_dot4));
		
		tv_title=(TextView)view.findViewById(R.id.tv_title);
		tv_title.setText(titles[0]);
		
		viewPager=(ViewPager)view.findViewById(R.id.vp);
		viewPager.setAdapter(new MyPagerAdapter(imageViews));
		viewPager.setOnPageChangeListener(new MyPagerChangerListener());

	}
	
	@Override
	public void onStart() {
		//service�ĳ�ʼ��
		service=Executors.newSingleThreadScheduledExecutor();
		//��activity��ʾ������ÿ2�����һ��
		service.scheduleAtFixedRate(new MyTask(), 1, 5, TimeUnit.SECONDS);
		
		super.onStart();
	}

	@Override
	public void onStop() {

		service.shutdown();
		super.onStop();
	}
	
	/*
	 * ͨ���̲߳��ϵظ���currentItem�����͵�handle�д���
	 */
	class MyTask implements Runnable{

		@Override
		public void run() {
			synchronized (viewPager) {
				Log.i("currentitem:", currentItem+"");
				currentItem=(currentItem+1)%imageViews.size();
				handler.obtainMessage().sendToTarget();
			}
			
			
		}
		
		
	}
	
	/*
	 * viewpager�ļ����¼�
	 */
	private class MyPagerChangerListener implements OnPageChangeListener{
	
		private int oldPosition=0;
		@Override
		public void onPageSelected(int position) {
			tv_title.setText(titles[position]);
			currentItem=position;
			dots.get(position).setBackgroundResource((R.drawable.dot_focus));
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			
			oldPosition=position;
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	/**
	 * ��ֹ�ڼ���ͼƬ��Դ��ʱ�����
	 * outofmemoryError����
	 * ͨ��λͼ���أ���ͼƬ�������ã�����ѹ��
	 * @param view
	 * @param id
	 * @return ����õ�Bitmap
	 */
	public Bitmap readBitmap(Context context ,int id){
		//����λͼ
		BitmapFactory.Options opt=new BitmapFactory.Options();	
		//Ϊλͼ����100k����
		opt.inTempStorage = new byte[100 * 1024];
		//����λͼ��ɫ��ʾ�Ż���ʽ
		opt.inPreferredConfig=Bitmap.Config.RGB_565;
		//����λͼ���Ա��ջأ�����Bitmap���ڴ洢Pixel���ڴ�ռ���ϵͳ�ڴ治��ʱ���Ա�����
		opt.inPurgeable=true;
		//���ý���λͼ�ĳߴ���Ϣ
		opt.inInputShareable=true;
		//��ȡͼƬ��Դ
		InputStream is=context.getResources().openRawResource(id);
		return BitmapFactory.decodeStream(is);
	}

}
