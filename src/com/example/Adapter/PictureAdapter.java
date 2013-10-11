package com.example.Adapter;

import com.example.App.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class PictureAdapter extends BaseAdapter {
	int[] picId = new int[4];
	Context context;

	public PictureAdapter(Context context, int picId) {
		this.context = context;
		this.setPicId(picId);
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
	
	public int getPicId(int position){
		return picId[position];
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {

		convertView = LayoutInflater.from(context).inflate(R.layout.grid_image,
				null);
		final ImageView iv = (ImageView) convertView.findViewById(R.id.ivPhoto);
		
		iv.setImageResource(picId[position]);
		iv.setPadding(10, 10, 10, 10);
		iv.setScaleType(ScaleType.FIT_XY);

		return convertView;
	}

}
