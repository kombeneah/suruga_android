package com.suruga.tabandroid.selections;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
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

		// automatically show the keypad for text input to the editBox.
		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(editText1, InputMethodManager.SHOW_IMPLICIT);

		editText1.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				Globals g = Globals.getInstance(getApplicationContext());

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
