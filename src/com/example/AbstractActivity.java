package com.example;


import java.util.ArrayList;
import java.util.List;

import com.example.Entity.Model;
import com.example.Public.JsonParse;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public abstract class AbstractActivity extends Activity {
	final static String KEY_LEVEL = "level", KEY_COIN = "coin",KEY_MODELS="models",
			KEY_FILE= "my_data", KEY_SUGGEST= "suggest",KEY_WORD="keyword",
			KEY_ISLOAD="isLoad", KEY_POOLID = "poolId", KEY_SOLUTION = "solution";
	SharedPreferences pre;
	Gson gson = new Gson();
	protected int level, coin, poolId=1;
	protected static List<Model> listModel= new ArrayList<Model>();
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		pre = getSharedPreferences(KEY_FILE, MODE_PRIVATE);
		context = this;
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Boolean isLoad= pre.getBoolean(KEY_ISLOAD, false);
		if (isLoad== false) {
			SharedPreferences.Editor editor = pre.edit();
			editor.putBoolean(KEY_ISLOAD, true);
			editor.commit();
			
			JsonParse jp = new JsonParse(this);
			listModel= jp.getData(poolId);
		}
	}
	
	void tt(String t){
		Log.d("TAG", t);
	}
}
