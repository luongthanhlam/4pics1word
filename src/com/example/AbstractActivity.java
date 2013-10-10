package com.example;


import java.util.Random;

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
	Random r = new Random();
	protected int level, coin, poolId=1;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		pre = getSharedPreferences(KEY_FILE, MODE_PRIVATE);
		context = this;
	}
	
	void tt(String t){
		Log.d("TAG", t);
	}
}
