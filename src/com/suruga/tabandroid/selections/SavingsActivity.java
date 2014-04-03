package com.suruga.tabandroid.selections;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

import com.suruga.tabandroid.Globals;
import com.suruga.tabandroid.GuideActivity;
import com.suruga.tabandroid.R;
import com.suruga.tabandroid.SettingsActivity;

public class SavingsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.savings_layout);
		
		final TextView messageView = (TextView) findViewById(R.id.textView2);
		if (Globals.getInstance(getApplicationContext()).isForRent()) {
			messageView.setText(R.string.rentalUpfrontDesc);
		}
		else {
			messageView.setText(R.string.buyingUpfrontDesc);
		}

		final EditText editText1;

		editText1 = (EditText) findViewById(R.id.editText1);
		int savings = Globals.getInstance(getApplicationContext()).getSavings();
		if (savings != 0)
		{
			editText1.setText(String.valueOf(savings));
		}

		// automatically show the keypad for text input to the editBox.
		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(editText1, InputMethodManager.SHOW_IMPLICIT);

		editText1.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				Globals g = Globals.getInstance(getApplicationContext());

				if (event.getAction() == KeyEvent.ACTION_DOWN
						&& keyCode == KeyEvent.KEYCODE_ENTER) {
					
					int savings = 0;
					try {
						savings = Integer.parseInt(editText1.getText().toString());
						g.setSavings(savings);
						
						setResult(RESULT_OK);

						SavingsActivity.this.onBackPressed();
						
						return true;
						
					} catch (NumberFormatException nfe) {
						Log.e("SavingsActivity", "Integer.parseInt() error parsing: "+editText1.getText().toString(), nfe);
						
						return false;
					}
				}
				return false;
			}
		});

	}
}
