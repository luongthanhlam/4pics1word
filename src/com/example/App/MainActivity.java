package com.example.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.spec.IvParameterSpec;

import com.example.Adapter.PicAdapter;
import com.example.Adapter.SolutionAdapter;
import com.example.Adapter.SuggestAdapter;
import com.example.Entity.Picture;
import com.example.Public.JsonParse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {
	static int MAX = 12, BONUS = 4, size= 0, poolId= 1;
	int level = 1, coin = 0;
	GridView gv1, gv2, gv3;
	JsonParse jp;
	SolutionAdapter adtSolution; 
	SuggestAdapter adtSuggest;
	ArrayList<Picture> listPic;
	Random r = new Random();
	TextView tvLevel, tvCoin;
	ImageView ivBack;
	Picture pic; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		jp = new JsonParse(this);
		listPic = jp.getData(level);
		pic = listPic.get(r.nextInt(listPic.size()));
		size++;
		init();
	}

	private void init() {
		setContentView(R.layout.activity_main);
		Log.d("SOLUTION", pic.getSolution());

		tvLevel = (TextView) findViewById(R.id.tvLevel);
		tvCoin = (TextView) findViewById(R.id.tvCoin);
		ivBack= (ImageView) findViewById(R.id.ivBack);
		gv1 = (GridView) findViewById(R.id.gv1);
		gv2 = (GridView) findViewById(R.id.gv2);
		gv3 = (GridView) findViewById(R.id.gv3);

		gv1.setAdapter(new PicAdapter(this, pic.getId()));

		String[] Solution = pic.getSolution().split("");
		ArrayList<String> aSolution = new ArrayList<String>(
				Arrays.asList(Solution));
		aSolution.remove(0);

		adtSolution = new SolutionAdapter(this, aSolution.size());
		gv2.setAdapter(adtSolution);

		adtSuggest = new SuggestAdapter(this, this.convertSuggest(pic
				.getSolution()));
		gv3.setAdapter(adtSuggest);

		gv2.setOnItemClickListener(this);
		gv3.setOnItemClickListener(this);
		ivBack.setOnClickListener(this);
		
		

		// Update level va coin
		tvLevel.setText(level + "");
		tvCoin.setText(coin + "");

	}

	/**
	 * Convert String to random char array
	 * 
	 * @param String
	 * @return char[]
	 */

	private char[] convertSuggest(String convertString) {

		int length = convertString.length();
		Random r = new Random();

		// Chen cac ki tu ngau nhien
		for (int i = 0; i < MAX - length; i++) {
			char randomChar = (char) (r.nextInt(26) + 'A');
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

	
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		switch (parent.getId()) {
		case R.id.gv2:
			try {
				String tag = ((TextView) v.findViewById(R.id.tvSolution))
						.getTag().toString();
				adtSuggest.show(Integer.parseInt(tag));
				adtSolution.remove(position);
			} catch (Exception e) {
				break;
			}
			break;

		case R.id.gv3:
			String text = ((TextView) v.findViewById(R.id.tvSuggest)).getText()
					.toString();
			if (adtSolution.add(text, position)) {
				adtSuggest.hidden(position);
			} else
				this.onCheck();

			break;
		}

	}

	private void onCheck() {
		// Kiem tra cau tra loi voi dap an
		if (adtSolution.getAnswer().equals(pic.getSolution())) {

			pic.setChecked(true);

			// Tang poolId neu duyet het pic
			if (listPic.size() == size) {
				poolId++;
				listPic = jp.getData(poolId);
			}

			// Thuong Coin va tang level
			coin += BONUS;
			level++;

			// Lay pic chua duoc check
			for(Picture pi : listPic){
				if(!pi.isChecked()){
					this.pic= pi;
					size++;
				}
			}
			this.init();

		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ivBack:
			startActivity(new Intent(getApplicationContext(), BootstrapActivity.class));
			break;
		}
	}
}
