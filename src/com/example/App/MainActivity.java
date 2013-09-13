package com.example.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import com.example.Adapter.SolutionAdapter;
import com.example.Adapter.PicAdapter;
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
import android.app.Dialog;
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
	Dialog dialog;
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

		findViewById(R.id.ivBack).setOnClickListener(this);
		findViewById(R.id.ivRemove).setOnClickListener(this);
		findViewById(R.id.ivReveal).setOnClickListener(this);

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
			 
			this.onCheck();

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
			this.showRemoveDialog();
			break;
		case R.id.ivReveal:
			this.showRevealDialog();
			break;
		case R.id.btnContinue:
			dialog.dismiss();
			this.nextModel();
			break;
		case R.id.bReveal:
			dialog.dismiss();
			if (subCoin(COIN_REVEAL)) {
				char c = adtSolution.reveal();
				adtSuggest.hidden(c);
				this.onCheck();
			}
			break;
		case R.id.bRemove:
			dialog.dismiss();
			if (subCoin(COIN_REMOVE)) {
				adtSuggest.remove();
			}
			break;
		case R.id.bCancel:
			dialog.dismiss();
			break;
		}
	}

	private void nextModel() {
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

	private void onCheck() {
		// So khop cau tra loi voi dap an
		if (adtSolution.isFull() && adtSolution.isMatch()) {
			this.showContinueDialog();
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


	private void showContinueDialog() {
		dialog= new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_continue);
		dialog.findViewById(R.id.btnContinue).setOnClickListener(this);
		dialog.show();
	}

	
	private void showRevealDialog() {
		dialog= new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_reveal);
		dialog.findViewById(R.id.bReveal).setOnClickListener(this);
		dialog.findViewById(R.id.bCancel).setOnClickListener(this);
		dialog.show();
	}
	
	private void showRemoveDialog() {
		dialog= new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_remove);
		dialog.findViewById(R.id.bRemove).setOnClickListener(this);
		dialog.findViewById(R.id.bCancel).setOnClickListener(this);
		dialog.show();
	}

}
