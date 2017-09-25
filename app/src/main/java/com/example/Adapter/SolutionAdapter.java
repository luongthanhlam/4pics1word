package com.example.Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.App.R;
import com.example.Entity.Solution;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SolutionAdapter extends BaseAdapter {
	Context context;
	List<Solution> listSolutions= new ArrayList<Solution>();
	String keyword;	

	public SolutionAdapter(Context context, String data, List<Solution> listData) {
		this.context = context;
		this.keyword= data;
		
		if(listData.isEmpty()){
			this.convert(data);
		}else{
			this.listSolutions= listData;
		}
	}
	
	public List<Solution> getSolutions() {
		return listSolutions;
	}

	private void convert(String keyword) {
		for(char c: keyword.toCharArray()){
			
			Solution so = new Solution();
			so.setSolution(c);
			so.setRevealed(false);
			listSolutions.add(so);
		}
	}
	

	@Override
	public int getCount() {
		return listSolutions.size() + 1;
	}

	@Override
	public Solution getItem(int position) {
		return listSolutions.get(position);
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
			iv.setClickable(false);
			return iv;
		} else {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.grid_solution, null);
			TextView tv = (TextView) convertView.findViewById(R.id.tvSolution);

			Solution so= getItem(position);
			if(so.isRevealed()){
				tv.setText(""+ so.getSolution());
			}else if(so.getAnswer() == 0){
				tv.setText("");
			}else{
				tv.setText(""+ so.getAnswer());
			}
			return convertView;
		}
	}

	public boolean isMatch() {
		String str = new String();
		for (Solution so : listSolutions) {
			if(so.getAnswer() != 0)
				str += so.getAnswer();
		}

		if (str.equals(keyword))
			return true;
		else
			return false;
	}

	public char autoInsert() {
		for(Solution so: listSolutions){
			if(so.getAnswer()== 0){
				so.setRevealed(true);
				so.setAnswer(so.getSolution());				

				notifyDataSetChanged();
				return so.getSolution();
			}
		}
		return 0;
	}

	public Solution autoReplace() {
		for (Solution so : listSolutions){
			if (!so.isRevealed()) {
				so.setRevealed(true);
				so.setAnswer(so.getSolution());
				
				notifyDataSetChanged();
				return so;
			}
		}
		return null;
	}

	public void remove(int position) {
		char x= 0;
		getItem(position).setAnswer(x);
		notifyDataSetChanged();
	}
	
	public void add(char c, int pos) {
		for (Solution so : listSolutions) {
			if (so.getAnswer()== 0) {
				so.setAnswer(c);
				so.setTag(pos);
				
				notifyDataSetChanged();
				break;
			}
		}
	}

	public boolean isFull() {
		for(Solution so:listSolutions){
			if(so.getAnswer()== 0 && !so.isRevealed()){
				return false;
			}
		}
		
		return true;
	}

	public boolean isRevealed(int position){
		return getItem(position).isRevealed();
	}

}
