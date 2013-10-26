package com.suruga.tabandroid.selections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TabHost.TabSpec;

import com.suruga.tabandroid.Globals;
import com.suruga.tabandroid.GuideActivity;
import com.suruga.tabandroid.R;
import com.suruga.tabandroid.SettingsActivity;

public class SavingsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {

		final EditText editText1;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.savings_layout);

		editText1 = (EditText) findViewById(R.id.editText1);

		editText1.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				Globals g = Globals.getInstance();

				if (event.getAction() == KeyEvent.ACTION_DOWN
						&& keyCode == KeyEvent.KEYCODE_BACK) {

					g.setSavings(editText1.getText().toString());

					onBackPressed();

					return true;
				}
				return false;
			}
		});

	}
}
