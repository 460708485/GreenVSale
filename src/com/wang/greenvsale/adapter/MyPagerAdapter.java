package com.wang.greenvsale.adapter;

import java.util.List;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPagerAdapter extends android.support.v4.view.PagerAdapter{
	private  List<ImageView> myImageViews;
	
	
	public MyPagerAdapter(List<ImageView> ImageViews) {
		
		this.myImageViews = ImageViews;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myImageViews.size();
	}
	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		((ViewPager)container).addView(myImageViews.get(position));
		return myImageViews.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		
		((ViewPager)container).removeView((View)object);
	}

}
