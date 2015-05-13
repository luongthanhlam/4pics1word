package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.example.Adapter.SolutionAdapter;
import com.example.Adapter.PictureAdapter;
import com.example.Adapter.SolutionAdapter2;
import com.example.Adapter.SuggestAdapter;
import com.example.App.R;
import com.example.Entity.Solution;
import com.example.Entity.Model;
import com.example.Entity.Suggest;
import com.example.Public.JsonParse;
import com.example.Public.Sound;
import com.google.gson.Gson;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class MainActivity extends AbstractActivity implements OnClickListener,
		OnItemClickListener {

	final static int BONUS = 4, COIN_REMOVE = 80, COIN_REVEAL = 60;
	final static boolean DEV = false;
	Sound sound = new Sound();
	GridView gv1, gv2, gv3;
	SolutionAdapter adtSolution;
	SuggestAdapter adtSuggest;
	PictureAdapter adtPicture;
	TextView tvLevel, tvCoin;
	ImageView ivzoom;
	RelativeLayout rzoom;
	Dialog dialog;
	Model model;
	List<Solution> listSolution = new ArrayList<Solution>();
	List<Suggest> listSuggest = new ArrayList<Suggest>();
	List<Model> listModel = new ArrayList<Model>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();
	}

	protected void init() {
		level = pre.getInt(KEY_LEVEL, 1);
		coin = pre.getInt(KEY_COIN, 4000);
		poolId = pre.getInt(KEY_POOLID, 1);

		String sModdel = pre.getString(KEY_MODELS, null);
		String keyword = pre.getString(KEY_WORD, null);
		String so = pre.getString(KEY_SOLUTION, null);
		String sg = pre.getString(KEY_SUGGEST, null);

		listModel.addAll(Arrays.asList(gson.fromJson(sModdel, Model[].class)));
		if (listModel.size() - 1 > 0)
			model = listModel.get(r.nextInt(listModel.size() - 1));

		if (keyword != null) {
			listSolution.addAll(Arrays.asList(gson.fromJson(so,
					Solution[].class)));
			listSuggest
					.addAll(Arrays.asList(gson.fromJson(sg, Suggest[].class)));
			for (Model m : listModel) {
				if (m.getSolution().equals(keyword)) {
					this.model = m;
				}
			}
		}

		run();
	}

	private void run() {
		setContentView(R.layout.activity_main);
		Log.d("SOLUTION", model.getSolution() + "-" + poolId);
		if (DEV) {
			Toast.makeText(getApplicationContext(), model.getSolution(),
					Toast.LENGTH_SHORT).show();
		}

		tvLevel = (TextView) findViewById(R.id.tvLevel);
		tvCoin = (TextView) findViewById(R.id.tvCoin);
		rzoom = (RelativeLayout) findViewById(R.id.rZoom);
		ivzoom = (ImageView) findViewById(R.id.ivZoom);

		gv1 = (GridView) findViewById(R.id.gv1);
		gv2 = (GridView) findViewById(R.id.gv2);
		gv3 = (GridView) findViewById(R.id.gv3);

		gv1.setOnItemClickListener(this);
		gv2.setOnItemClickListener(this);
		gv3.setOnItemClickListener(this);
		ivzoom.setOnClickListener(this);
		rzoom.setOnClickListener(this);

		findViewById(R.id.ivBack).setOnClickListener(this);
		findViewById(R.id.ivRemove).setOnClickListener(this);
		findViewById(R.id.ivReveal).setOnClickListener(this);

		adtPicture = new PictureAdapter(this, model.getId());
		adtSolution = new SolutionAdapter(this, model.getSolution(),
				listSolution);
		adtSuggest = new SuggestAdapter(this, model.getSolution(), listSuggest);

		if (adtPicture != null)
			gv1.setAdapter(adtPicture);
		if (adtSolution != null)
			gv2.setAdapter(adtSolution);
		if (adtSuggest != null)
			gv3.setAdapter(adtSuggest);

		// Update level va coin
		tvLevel.setText(level + "");
		tvCoin.setText(coin + "");

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

		switch (parent.getId()) {
		case R.id.gv1:
			ivzoom.setImageResource(adtPicture.getPicId(position));
			rzoom.setVisibility(View.VISIBLE);
			gv1.setVisibility(View.INVISIBLE);
			break;
		case R.id.gv2:
			String text = ((TextView) v.findViewById(R.id.tvSolution))
					.getText().toString();
			if (!text.isEmpty() && !adtSolution.isRevealed(position)) {
				sound.play("no", context);
				int tag = adtSolution.getItem(position).getTag();
				adtSuggest.show(tag);
				adtSolution.remove(position);
			}

			break;

		case R.id.gv3:
			String s = ((TextView) v.findViewById(R.id.tvSuggest)).getText()
					.toString();
			if (!adtSolution.isFull() && !s.isEmpty()) {
				sound.play("click", context);
				adtSolution.add(s.charAt(0), position);
				adtSuggest.hidden(position);
			}
			this.onCheck();
			break;
		}
	}

	@Override
	public void onClick(View v) {
		sound.play("click", context);
		switch (v.getId()) {
		case R.id.ivZoom:
			gv1.setVisibility(View.VISIBLE);
			rzoom.setVisibility(View.INVISIBLE);
			break;
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
				if (adtSolution.isFull()) {
					Solution so = adtSolution.autoReplace();
					adtSuggest.show(so.getTag());
					adtSuggest.hidden(so.getSolution());
				} else {
					adtSuggest.hidden(adtSolution.autoInsert());
				}
				this.onCheck();
			}
			break;
		case R.id.bRemove:
			dialog.dismiss();
			if (subCoin(COIN_REMOVE) && adtSuggest.removable()) {
				adtSuggest.remove();
			}
			break;
		case R.id.bCancel:
			dialog.dismiss();
			break;
		}
	}

	private void nextModel() {
		// Thuong Coin va tang level
		coin += BONUS;
		level++;

		// Tang poolId neu xoa het model
		if (listModel.isEmpty()) {
			poolId++;
			listModel = jp.getData(poolId);
		} else if (listModel.contains(model)) {
			listModel.remove(model);
			listSolution.clear();
			listSuggest.clear();
		}

		// Load model moi
		if (listModel.size() - 1 > 0)
			model = listModel.get(r.nextInt(listModel.size() - 1));

		// Reload lai toan bo layout
		run();
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
		sound.play("applause", context);
		dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		ColorDrawable dialogColor = new ColorDrawable(Color.BLACK);
		dialogColor.setAlpha(180);
		dialog.getWindow().setBackgroundDrawable(dialogColor);

		dialog.setContentView(R.layout.dialog_continue);
		dialog.findViewById(R.id.btnContinue).setOnClickListener(this);
		GridView gv = (GridView) dialog.findViewById(R.id.gvContinue);

		SolutionAdapter2 adt = new SolutionAdapter2(this, model.getSolution());
		// gv.setAdapter(adt);
		gv.setVisibility(View.INVISIBLE);

		TextView tv = (TextView) dialog.findViewById(R.id.tvResult);
		String[] kw = model.getSolution().split("");
		String keyword = "";
		for (String c : kw) {
			keyword += c + " ";
		}
		tv.setText(keyword);
		dialog.show();
	}

	private void showRevealDialog() {
		dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		ColorDrawable dialogColor = new ColorDrawable(Color.BLACK);
		dialogColor.setAlpha(45);
		dialog.getWindow().setBackgroundDrawable(dialogColor);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_reveal);
		dialog.findViewById(R.id.bReveal).setOnClickListener(this);
		dialog.findViewById(R.id.bCancel).setOnClickListener(this);
		dialog.show();
	}

	private void showRemoveDialog() {
		dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		ColorDrawable dialogColor = new ColorDrawable(Color.BLACK);
		dialogColor.setAlpha(1);
		dialog.getWindow().setBackgroundDrawable(dialogColor);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_remove);
		dialog.findViewById(R.id.bRemove).setOnClickListener(this);
		dialog.findViewById(R.id.bCancel).setOnClickListener(this);
		dialog.show();
	}

	@Override
	protected void onStop() {
		saveAll();
		super.onStop();
	}

	@Override
	protected void onPause() {
		saveAll();
		super.onPause();
	}

	void saveAll() {
		String so = gson.toJson(adtSolution.getSolutions());
		String sg = gson.toJson(adtSuggest.getSuggests());
		String models = gson.toJson(listModel.toArray(new Model[listModel
				.size()]));

		if (!(so.isEmpty() && sg.isEmpty() && models.isEmpty())) {
			pre = getSharedPreferences(KEY_FILE, MODE_PRIVATE);
			SharedPreferences.Editor editor = pre.edit();
			editor.putInt(KEY_LEVEL, level);
			editor.putInt(KEY_COIN, coin);
			editor.putInt(KEY_POOLID, poolId);

			editor.putString(KEY_SOLUTION, so);
			editor.putString(KEY_SUGGEST, sg);
			editor.putString(KEY_WORD, model.getSolution());
			editor.putString(KEY_MODELS, models);
			editor.commit();
		}
	}

}
