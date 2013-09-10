package com.example.Entity;

import java.util.ArrayList;

import com.example.pic.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PicAdapter extends BaseAdapter {
	int[] picId = new int[4];
	Context context;

	public PicAdapter(Context context, int picId) {
		this.context = context;
		this.setPicId(100);
	}

	private void setPicId(int id0) {
		for (int i = 0; i < 4; i++) {
			String id = "_" + id0 + "_" + (i + 1);
			this.picId[i] = context.getResources().getIdentifier(id, "raw",
					context.getPackageName());
		}
	}

	@Override
	public int getCount() {
		return picId.length;
	}

	@Override
	public Object getItem(int arg0) {
		return picId[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ImageView imageView = new ImageView(context);
		imageView.setImageResource(picId[position]);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(95, 95));
		imageView.setBackgroundResource(R.drawable.bg_photo);
		imageView.setPadding(10, 10, 10, 10);
		return imageView;
	}

}
