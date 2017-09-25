package com.example;


import com.example.App.R;
import com.example.Public.Sound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BootstrapActivity extends AbstractActivity{
	protected static boolean TurnOff = false;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bootstrap);
		init();
	}

	private void init() {
		findViewById(R.id.play).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				sound.destroy();
				
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
		});
		
		findViewById(R.id.bSound).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sound.destroy();
			}
		});
	}

	@Override
	protected void onStart() {
		sound.play("bgm", this);
		level = pre.getInt(KEY_LEVEL, 1);
		
		TextView tv= (TextView)findViewById(R.id.tvLevel0);
		tv.setText(""+level);
		super.onStart();
	}
}
