package com.example.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.spec.IvParameterSpec;

import com.example.Adapter.SolutionAdapter;
import com.example.Adapter.PicAdapter;
import com.example.Adapter.SolutionAdapter;
import com.example.Adapter.SuggestAdapter;
import com.example.Entity.Solution;
import com.example.Entity.Model;
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

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	final static int BONUS = 4, COIN_REMOVE = 80, COIN_REVEAL = 60;
	int level = 1, coin = 400, size = 0, poolId = 1;
	GridView gv1, gv2, gv3;
	JsonParse jp;
	SolutionAdapter adtSolution;
	SuggestAdapter adtSuggest;
	ArrayList<Model> listModel;
	Random r = new Random();
	TextView tvLevel, tvCoin;
	ImageView ivBack, ivReveal, ivRemove;
	Model model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		jp = new JsonParse(this);
		listModel = jp.getData(level);
		model = listModel.get(r.nextInt(listModel.size()));
		size++;
		init();
	}

	private void init() {
		setContentView(R.layout.activity_main);
		Log.d("SOLUTION", model.getSolution());
		Toast.makeText(getApplicationContext(), model.getSolution(),
				Toast.LENGTH_LONG).show();

		tvLevel = (TextView) findViewById(R.id.tvLevel);
		tvCoin = (TextView) findViewById(R.id.tvCoin);
		ivBack = (ImageView) findViewById(R.id.ivBack);
		ivRemove = (ImageView) findViewById(R.id.ivRemove);
		ivReveal = (ImageView) findViewById(R.id.ivReveal);

		gv1 = (GridView) findViewById(R.id.gv1);
		gv2 = (GridView) findViewById(R.id.gv2);
		gv3 = (GridView) findViewById(R.id.gv3);

		gv1.setAdapter(new PicAdapter(this, model.getId()));

		adtSolution = new SolutionAdapter(this, model.getSolution());
		gv2.setAdapter(adtSolution);

		adtSuggest = new SuggestAdapter(this, model.getSolution());
		gv3.setAdapter(adtSuggest);

		gv2.setOnItemClickListener(this);
		gv3.setOnItemClickListener(this);

		ivBack.setOnClickListener(this);
		ivRemove.setOnClickListener(this);
		ivReveal.setOnClickListener(this);

		// Update level va coin
		tvLevel.setText(level + "");
		tvCoin.setText(coin + "");

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
			String s = ((TextView) v.findViewById(R.id.tvSuggest)).getText()
					.toString();
			if (!adtSolution.isFull()) {
				adtSolution.add(s.charAt(0), position);
				adtSuggest.hidden(position);
			}
			if (adtSolution.isFull() && adtSolution.isMatch()) { // So khop cau
																	// tra loi
																	// voi dap
																	// an
				this.prepare();
			}

			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivBack:
			startActivity(new Intent(getApplicationContext(),
					BootstrapActivity.class));
			break;
		case R.id.ivRemove:
			if (subCoin(COIN_REMOVE)) {
				adtSuggest.remove();
			}
			break;
		case R.id.ivReveal:
			if (subCoin(COIN_REVEAL)) {
				char c = adtSolution.reveal();
				adtSuggest.hidden(c);
				if (adtSolution.isFull() && adtSolution.isMatch()) {
					this.prepare();
				}
			}
			break;
		}
	}

	private boolean subCoin(int coin) {
		if (this.coin < coin) {
			return false;
		} else {
			this.coin -= coin;
			tvCoin.setText(""+this.coin);
			return true;
		}
	}

	private void prepare() {
		model.setChecked(true);
		// Tang poolId neu duyet het pic
		if (listModel.size() == size) {
			poolId++;
			listModel = jp.getData(poolId);
		}

		// Thuong Coin va tang level
		coin += BONUS;
		level++;

		// Lay pic chua duoc check
		for (Model pi : listModel) {
			if (!pi.isChecked()) {
				this.model = pi;
				size++;
				break;
			}
		}
		// Reload lai toan bo layout
		this.init();
	}

}
