package com.example.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import com.example.Adapter.SolutionAdapter;
import com.example.Adapter.PictureAdapter;
import com.example.Adapter.SolutionAdapter2;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	final static int BONUS = 4, COIN_REMOVE = 80, COIN_REVEAL = 60;
	int level, coin, size = 0, poolId;
	GridView gv1, gv2, gv3;
	JsonParse jp;
	SolutionAdapter adtSolution;
	SuggestAdapter adtSuggest;
	ArrayList<Model> listModel;
	Random r = new Random();
	TextView tvLevel, tvCoin;
	SharedPreferences pre;
	Dialog dialog;
	Model model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.listModel = BootstrapActivity.listModel;
		model = this.listModel.get(r.nextInt(this.listModel.size()));
		size++;
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

		gv1.setAdapter(new PictureAdapter(this, model.getId()));
		gv1.setOnItemClickListener(this);

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
		case R.id.gv1:
			break;
		case R.id.gv2:
			try {
				String tag = ((TextView) v.findViewById(R.id.tvSolution))
						.getTag().toString();
				if (!tag.isEmpty()) {
					adtSuggest.show(Integer.parseInt(tag));
					adtSolution.remove(position);
				}
			} catch (Exception e) {
				break;
			}

			break;

		case R.id.gv3:
			String s = ((TextView) v.findViewById(R.id.tvSuggest)).getText()
					.toString();
			if (!adtSolution.isFull() && !s.isEmpty()) {
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
				if(adtSolution.isFull()){
					Solution so= adtSolution.replace();
					adtSuggest.show(so.getTag());
					adtSuggest.hidden(so.getSolution());
				}else{
					adtSuggest.hidden(adtSolution.insert());
				}
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
		if (adtSolution.isMatch()) {
			this.showContinueDialog();
		}
	}

	private boolean subCoin(int coin) {
		if (this.coin < coin) {
			return false;
		} else {
			this.coin -= coin;
			tvCoin.setText("" + this.coin);
			return true;
		}
	}

	private void showContinueDialog() {
		dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		ColorDrawable dialogColor = new ColorDrawable( Color.BLACK);
		dialogColor.setAlpha(180);
		dialog.getWindow().setBackgroundDrawable(dialogColor);
		
		dialog.setContentView(R.layout.dialog_continue);
		dialog.findViewById(R.id.btnContinue).setOnClickListener(this);
		GridView gv = (GridView) dialog.findViewById(R.id.gvContinue);

		SolutionAdapter2 adt = new SolutionAdapter2(this, model.getSolution());
		gv.setAdapter(adt);
		dialog.show();
	}

	private void showRevealDialog() {
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_reveal);
		dialog.findViewById(R.id.bReveal).setOnClickListener(this);
		dialog.findViewById(R.id.bCancel).setOnClickListener(this);
		dialog.show();
	}

	private void showRemoveDialog() {
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_remove);
		dialog.findViewById(R.id.bRemove).setOnClickListener(this);
		dialog.findViewById(R.id.bCancel).setOnClickListener(this);
		dialog.show();
	}

	@Override
	protected void onStart() {
		SharedPreferences pre = getSharedPreferences("my_data", MODE_PRIVATE);
		level = pre.getInt("level", 1);
		coin = pre.getInt("coin", 4000);
		poolId = pre.getInt("poolId", 1);
		init();
		super.onStart();
	}

	@Override
	protected void onStop() {
		SharedPreferences pre = getSharedPreferences("my_data", MODE_PRIVATE);
		SharedPreferences.Editor editor = pre.edit();

		editor.putInt("level", level);
		editor.putInt("coin", coin);
		editor.putInt("poolId", poolId);

		editor.commit();
		super.onStop();
	}
}
