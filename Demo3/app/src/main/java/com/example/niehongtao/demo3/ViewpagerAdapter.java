package com.example.niehongtao.demo3;

import java.util.HashMap;
import java.util.Map;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("UseSparseArrays")
public class ViewpagerAdapter extends PagerAdapter {

	private Map<Integer, View> views = new HashMap<Integer, View>();
	private LayoutInflater inflater;

	public ViewpagerAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public void destroyItem(View v, int position, Object obj) {
		((ViewPager) v).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(View v, int position) {
		View view = inflater.inflate(R.layout.viewpager_view, null);
		ImageView imageView = (ImageView) view
				.findViewById(R.id.imageView_show);

		switch (position) {
		case 0:
			imageView.setImageResource(R.mipmap.zhou_01);
			break;
		case 1:
			imageView.setImageResource(R.mipmap.zhou_02);
			break;
		case 2:
			imageView.setImageResource(R.mipmap.zhou_03);
			break;
		}
		((ViewPager) v).addView(view);
		views.put(position, view);
		return view;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
}