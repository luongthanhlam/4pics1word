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
			Solution so= data.get(position);
			
			if (so.isReveal()) {
				tv.setText("" + so.getSolution());
			} else if (so.getAnswer()!= 0) {
				tv.setText("" + so.getAnswer());
				tv.setTag(so.getTag());
			}
			return convertView;
		}
	}

	public boolean isMatch() {
		String str = new String();
		for (Solution so : data) {
			if(so.getAnswer() != 0)
				str += so.getAnswer();
		}

		if (str.equals(solution))
			return true;
		else
			return false;
	}
	
	public char insert(){
		for(int i= data.size()-1; i>= 0; i--){
			Solution so= data.get(i);
			if(so.getAnswer()== 0){
				so.setReveal(true);
				so.setAnswer(so.getSolution());
				count++;
				
				notifyDataSetChanged();
				return so.getSolution();
			}
		}
		return 0;
	}
	
	public Solution replace(){
		for (Solution so : data){
			if (!so.isReveal()) {
				so.setReveal(true);
				so.setAnswer(so.getSolution());
				
				notifyDataSetChanged();
				return so;
			}
		}
		return null;
	}

	public void remove(int position) {
		char x= 0;
		data.get(position).setAnswer(x);
		count--;
		notifyDataSetChanged();
	}

	public void add(char c, int pos) {
		for (Solution so : data) {
			if (so.getAnswer()== 0) {
				so.setAnswer(c);
				so.setTag(pos);
				count++;

				notifyDataSetChanged();
				break;
			}
		}
	}

	public boolean isFull() {
		if (data.size() == count) {
			return true;
		}
		return false;
	}

}
