package com.wang.greenvsale.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wang.greenvsale.R;
import com.wang.greenvsale.view.ExpandTabView;
import com.wang.greenvsale.view.ViewLeft;
import com.wang.greenvsale.view.ViewMiddle;
import com.wang.greenvsale.view.ViewRight;

public class Shop_Fragment extends Fragment {
	@ViewInject(R.id.expandtab_view)
	private ExpandTabView expandTabView;
	private ArrayList<View> mViewArray = new ArrayList<View>();
	private ViewLeft viewLeft;
	private ViewMiddle viewMiddle;
	private ViewRight viewRight;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.list, container,false);
		ViewUtils.inject(this,view);
		initView();
		initVaule();
		initListener();                                                                                                                                                                                                                    
		return view;
	}
	//初始化视图
	private void initView() {
		viewLeft=new ViewLeft(getActivity());
		viewMiddle=new ViewMiddle(getActivity());
		viewRight=new ViewRight(getActivity());
		
	}

	//初始化值
	private void initVaule() {
		mViewArray.add(viewLeft);
		mViewArray.add(viewMiddle);
		mViewArray.add(viewRight);
		ArrayList<String> mTextArray = new ArrayList<String>();
		
		mTextArray.add("距离");
		mTextArray.add("区域");
		mTextArray.add("距离");
		expandTabView.setValue(mTextArray, mViewArray);
		expandTabView.setTitle(viewLeft.getShowText(), 0);
		expandTabView.setTitle(viewMiddle.getShowText(), 1);
		expandTabView.setTitle(viewRight.getShowText(), 2);
	}
	

	private void initListener() {
		
		viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {

			@Override
			public void getValue(String distance, String showText) {
				onRefresh(viewLeft, showText);
			}
		});
		
		viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {
			
			@Override
			public void getValue(String showText) {
				
				onRefresh(viewMiddle,showText);
				
			}
		});
		
		viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {

			@Override
			public void getValue(String distance, String showText) {
				onRefresh(viewRight, showText);
			}
		});
		
	}
	
	private void onRefresh(View view, String showText) {
		
		expandTabView.onPressBack();
		int position = getPositon(view);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			expandTabView.setTitle(showText, position);
		}
		Toast.makeText(getActivity(), showText, Toast.LENGTH_SHORT).show();

	}
	
	private int getPositon(View tView) {
		for (int i = 0; i < mViewArray.size(); i++) {
			if (mViewArray.get(i) == tView) {
				return i;
			}
		}
		return -1;
	}


	
	
	
	
}
