package com.example.Adapter;

import java.util.Arrays;
import java.util.Random;

import com.example.App.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SuggestAdapter extends BaseAdapter {
	private static final int SUM_REMOVE = 3;
	private static final int MAX = 12;
	Random r = new Random();
	Context context;
	char[] listData, listRC;
	Boolean[] hidden;

	public SuggestAdapter(Context context, String s) {
		this.context = context;
		this.listData = this.convertSuggest(s);
		hidden = new Boolean[listData.length];
		Arrays.fill(hidden, Boolean.FALSE);
	}

	@Override
	public int getCount() {
		return listData.length;
	}

	@Override
	public String getItem(int position) {
		return listData[position] + "";
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

			if (hidden[position]) {
				tv.setVisibility(View.INVISIBLE);
			} else {
				tv.setText(this.getItem(position));
			}
			return convertView;
	}

	public void show(int position) {
		hidden[position] = false;
		notifyDataSetChanged();
	}

	public void hidden(int position) {
		hidden[position] = true;
		notifyDataSetChanged();
	}
	
	public void hidden(char c) {
		for(int i=0;i<listData.length;i++){
			if(listData[i]== c){
				hidden[i]= true;
				break;
			}
		}
		notifyDataSetChanged();
	}
	
	public void remove(){
		int n=0;
		while(n<SUM_REMOVE){
			for(char c: listRC)
			for(int i=0; i<listData.length; i++){
				if(r.nextBoolean() && listData[i]== c && n<SUM_REMOVE){
					hidden[i]= true;
					n++;
					break;
				}
			}
		}
		notifyDataSetChanged();
	}
	
	/**
	 * Convert String to random char array
	 * 
	 * @param String
	 * @return char[]
	 */

	private char[] convertSuggest(String convertString) {

		int length = MAX - convertString.length();
		listRC= new char[length];

		// Chen cac ki tu ngau nhien
		for (int i = 0; i < length; i++) {
			char randomChar = (char) (r.nextInt(26) + 'A');
			listRC[i] = randomChar;
			convertString += randomChar;
		}

		char[] arr = convertString.toCharArray();

		// Tron ngau nhien
		for (int k = 0; k < MAX * 2; k++) {
			int x = r.nextInt(arr.length - 1);
			int y = r.nextInt(arr.length - 1);

			char X = arr[x];

			arr[x] = arr[y];
			arr[y] = X;
		}

		return arr;
	}

	
}
