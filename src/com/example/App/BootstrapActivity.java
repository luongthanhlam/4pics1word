package com.example.App;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class BootstrapActivity extends Activity {

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_bootstrap);
		init();
	}

	private void init() {
		findViewById(R.id.play).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
		});
		
		/*final Dialog dialog= new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		dialog.setContentView(R.layout.dialog_custom);
		
		//findViewById(R.id.btnContinue).setOnClickListener(this);
		//TextView dialogButton= (TextView) findViewById(R.id.btnContinue);
		findViewById(R.id.btnContinue).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
		
		dialog.show();*/
	}
}
