package com.suruga.tabandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private Spinner spinner1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);

		addListenerOnSpinnerItemSelection();
	}

	public void addListenerOnSpinnerItemSelection() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
	}
	
	

}