package com.example.Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.example.App.R;
import com.example.Entity.Suggest;
import com.example.Public.RandomString;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SuggestAdapter extends BaseAdapter {
	final static int MAX = 12, SUM_REMOVE = 3;
	Random r = new Random();
	List<Suggest> listSuggest= new ArrayList<Suggest>();
	Context context;

	public SuggestAdapter(Context context, String data, List<Suggest> listData) {
		this.context = context;

		if (listData.isEmpty()) {
			this.convert(data);
		} else {
			listSuggest = listData;
		}
	}

	public List<Suggest> getSuggests() {
		return listSuggest;
	}

	private void convert(String data) {
		int length = MAX - data.length();
		
		//Khoi tao ki tu dung
		for(char c: data.toCharArray()){
			Suggest sg= new Suggest();
			sg.setSuggest(c);
			sg.setHidden(false);
			sg.setMatch(true);
			listSuggest.add(sg);
		}

		// Chen cac ki tu ngau nhien
		for (int i = 0; i < length; i++) {
			char randomChar = (char) (r.nextInt(26) + 'A');
			
			Suggest sg= new Suggest();
			sg.setSuggest(randomChar);
			sg.setHidden(false);
			sg.setMatch(false);
			listSuggest.add(sg);
			
			data += randomChar;
		}
		
		//Tron ngau nhien
		for(int i = 0; i < MAX * 2; i++){
			int x= r.nextInt(MAX-1);
			int y= r.nextInt(MAX-1);
			
			Suggest temp= listSuggest.get(x);
			
			listSuggest.set(x, listSuggest.get(y));
			listSuggest.set(y, temp);
		}
	}

	@Override
	public int getCount() {
		return listSuggest.size();
	}

	@Override
	public Suggest getItem(int position) {
		return listSuggest.get(position);
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

		Suggest sg= getItem(position);
		if (sg.isHidden()) {
			tv.setVisibility(View.INVISIBLE);
		} else {
			tv.setText(sg.getSuggest() + "");
		}
		return convertView;
	}

	public void show(int position) {
		getItem(position).setHidden(false);
		notifyDataSetChanged();
	}

	public void hidden(int position) {
		getItem(position).setHidden(true);
		notifyDataSetChanged();
	}

	public void hidden(char c) {
		for (Suggest su : listSuggest) {
			if (su.getSuggest() == c) {
				su.setHidden(true);
				break;
			}
		}
		notifyDataSetChanged();
	}

	public boolean removable() {
		int k = 0;
		for (Suggest sg : listSuggest) {
			if (sg.isMatch())
				k++;
		}
		if(k>3)
			return true;
		else
			return false;
	}

	public void remove() {
		int n = 0;
		while (n < SUM_REMOVE) {
			for (Suggest sg : listSuggest) {
				if (r.nextBoolean() && !sg.isHidden() && !sg.isMatch()
						&& n < SUM_REMOVE) {
					sg.setHidden(true);
					n++;
					break;
				}
			}
		}
		notifyDataSetChanged();
	}

}
