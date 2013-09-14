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
			} else if (data.get(position).isEmpty()) {
				convertView.setClickable(false);
				return convertView;
			} else {
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
		if (!isFull()) {
			for (int i = data.size() - 1; i >= 0; i--) {
				Solution so = data.get(i);
				if (so.isEmpty()) {
					// Chen dap an dung
					Log.d("SS", ""+so.getSolution());
					return this.insertSolution(so);
				}
			}
		} else {
			for (Solution so : data) {
				if (!so.isReveal()) {
					// Thay the bang dap an dung
					count--;
					return this.insertSolution(so);
				}
			}
		}
		return 0;
	}

	public char insertSolution(Solution so) {
		// Neu chua duoc goi y thi tra ve 1 ki tu dung
		so.setReveal(true);
		so.setEmpty(false);
		so.setAnswer(so.getSolution());
		count++;
		notifyDataSetChanged();
		return so.getSolution();
	}

	public void remove(int position) {
		data.get(position).setEmpty(true);
		count--;
		notifyDataSetChanged();
	}

	public void add(char c, int pos) {
		for (Solution so : data) {
			if (so.isEmpty()) {
				so.setAnswer(c);
				so.setTag(pos);
				so.setEmpty(false);
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
