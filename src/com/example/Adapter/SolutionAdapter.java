package com.example.Adapter;

import java.util.ArrayList;

import com.example.App.R;
import com.example.Entity.Solution;
import com.example.Entity.Solution;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SolutionAdapter extends BaseAdapter {
	String solution;
	Context context;
	int count = 0;
	ArrayList<Solution> data;

	public SolutionAdapter(Context context, String solution) {
		data = new ArrayList<Solution>();
		this.context = context;
		this.solution = solution;
		for (char c : solution.toCharArray()) {
			Solution so = new Solution();
			so.setSolution(c);
			data.add(so);
		}
	}

	@Override
	public int getCount() {
		return data.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (getCount() - 1 == position) {
			ImageView iv = new ImageView(context);
			iv.setBackgroundResource(R.drawable.btn_facebook_new);
			return iv;
		} else {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.grid_solution, null);
			TextView tv = (TextView) convertView.findViewById(R.id.tvSolution);

			if (data.get(position).isReveal()) {
				tv.setText("" + data.get(position).getSolution());
			}else if(data.get(position).isRemove()){
				convertView.setClickable(false);
				return convertView;
			}else {
				tv.setText("" + data.get(position).getAnswer());
			}

			tv.setTag(data.get(position).getTag());

			return convertView;
		}
	}

	public boolean isMatch() {
		String str = new String();
		for (Solution so : data) {
			str += so.getAnswer();
		}

		if (str.equals(solution))
			return true;
		else
			return false;
	}

	public char reveal() {
		for (int i = data.size() - 1; i >= 0; i--) {
			Solution so= data.get(i);
			if (!so.isReveal()) {
				so.setReveal(true);
				so.setAnswer(so.getSolution());
				count++;
				notifyDataSetChanged();
				return so.getSolution();
			}
		}
		return 0;
	}

	public void remove(int position) {
		data.get(position).setRemove(true);
		count--;
		notifyDataSetChanged();
	}
	
	public boolean add(char c, int pos){
		if(!isFull()){
			for(Solution so: data){
				if(c!= 0 && so.isRemove()){
					so.setAnswer(c);
					so.setTag(pos);
					so.setRemove(false);
					count++;
					
					notifyDataSetChanged();
					return true;
				}
			}
		}
		return false;
	}

	public boolean isFull() {
		//Toast.makeText(context, count+"-"+data.size(), Toast.LENGTH_SHORT).show();
		if(data.size()== count){
			return true;
		}
		return false;
	}

}
