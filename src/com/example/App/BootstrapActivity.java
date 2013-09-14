package com.example.App;


import java.util.ArrayList;

import com.example.Entity.Model;
import com.example.Public.JsonParse;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class BootstrapActivity extends Activity {
	
	protected static ArrayList<Model> listModel;
	int level;

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_bootstrap);
		init();
	}

	private void init() {
		JsonParse jp = new JsonParse(this);
		listModel = jp.getData(1);
		findViewById(R.id.play).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
		});
	}

	@Override
	protected void onStart() {
		SharedPreferences pre= getSharedPreferences("my_data", MODE_PRIVATE);
		level= pre.getInt("level", 1);
		TextView tv= (TextView)findViewById(R.id.tvLevel0);
		tv.setText(""+level);
		super.onStart();
	}
}
