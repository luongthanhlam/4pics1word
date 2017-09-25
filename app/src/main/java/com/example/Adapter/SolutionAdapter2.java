package com.example.Adapter;

import com.example.App.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SolutionAdapter2 extends BaseAdapter {
	Context context;
	char[] data;

	public SolutionAdapter2(Context context, String solution) {
		this.context = context;
		this.data = solution.toCharArray();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(
				R.layout.grid_solution, null);
		TextView tv = (TextView) convertView.findViewById(R.id.tvSolution);
		tv.setText("" + data[position]);
		return convertView;
	}
}
