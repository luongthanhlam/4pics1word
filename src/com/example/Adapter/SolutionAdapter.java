package com.example.Adapter;

import java.util.Arrays;
import com.example.pic.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SolutionAdapter extends BaseAdapter {
	Context context;
	final static String SPACE = " ";
	String[] data;
	Integer[] tag;
	String sAnswer;
	int count = 0;

	public SolutionAdapter(Context c, int length) {
		this.context = c;
		this.data = new String[length];
		this.tag = new Integer[length];
		Arrays.fill(this.data, SPACE);
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public String getItem(int position) {
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(
				R.layout.grid_solution, null);
		TextView tv = (TextView) convertView.findViewById(R.id.tvSolution);

		tv.setText(this.getItem(position));
		tv.setTag(this.tag[position]);
		return convertView;
	}

	public String getAnswer() {
		String str = "";
		for (String s : data) {
			str += s;
		}
		return str;
	}

	public boolean add(String w, int tagPosition) {
		for (int i = 0; i < data.length; i++) {
			if (count == data.length)
				return false;
			else if (data[i].equals(SPACE)) {
				data[i] = w;
				count++;
				tag[i] = tagPosition;

				notifyDataSetChanged();
				return true;
			}
		}
		return false;
	}

	public void remove(int position) {
		tag[position] = null;
		data[position] = SPACE;
		count--;
		notifyDataSetChanged();
	}

}
