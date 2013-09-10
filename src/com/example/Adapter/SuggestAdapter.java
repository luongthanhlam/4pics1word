package com.example.Adapter;


import java.util.Arrays;
import com.example.App.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SuggestAdapter extends BaseAdapter {
	Context context;
	char[] listData;
	Boolean[] hidden;

	public SuggestAdapter(Context context, char[] listData) {
		this.context = context;
		this.listData= listData;
		hidden= new Boolean[listData.length];
		Arrays.fill(hidden, Boolean.FALSE);
	}

	@Override
	public int getCount() {
		return listData.length;
	}

	@Override
	public String getItem(int position) {
		return listData[position]+"";
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(
				R.layout.grid_suggest, null);
		TextView tv = (TextView) convertView.findViewById(R.id.tvSuggest);
		
		if(hidden[position]){
			tv.setVisibility(View.INVISIBLE);
		}else{
			tv.setText(this.getItem(position));
		}
		return convertView;
	}
	
	public void show(int position){
		hidden[position]= false;
		notifyDataSetChanged();
	}
	
	public void hidden(int posion){
		hidden[posion]= true;
		notifyDataSetChanged();
	}
}
