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
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.suruga.tabandroid.Globals;
import com.suruga.tabandroid.GuideActivity;
import com.suruga.tabandroid.R;
import com.suruga.tabandroid.SettingsActivity;

public class MonthlyActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.monthly_layout);
		
		final TextView messageView = (TextView) findViewById(R.id.textView2);
		if (Globals.getInstance(getApplicationContext()).isForRent()) {
			messageView.setText(R.string.rentalMonthlyDesc);
		}
		else {
			messageView.setText(R.string.buyingMonthlyDesc);
		}

		final EditText editText1;

		editText1 = (EditText) findViewById(R.id.editText1);
		editText1.setText(String.valueOf
				(Globals.getInstance(getApplicationContext()).getMonthly()));

		// automatically show the keypad for text input to the editBox.
		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(editText1, InputMethodManager.SHOW_IMPLICIT);

		editText1.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				if (event.getAction() == KeyEvent.ACTION_DOWN
						&& keyCode == KeyEvent.KEYCODE_BACK) {

					Globals g = Globals.getInstance(getApplicationContext());
					
					int monthly = 0;
					try {
						monthly = Integer.parseInt(editText1.getText().toString());
						g.setMonthly(monthly);
						
						setResult(RESULT_OK);
					} catch (NumberFormatException nfe) {
						Log.e("MonthlyActivity", "Integer.parseInt() error parsing: "+editText1.getText().toString(), nfe);
					}
					done();

					return true;
				}
				return false;
			}
		});
	}
	
	private void done() {
		finish();
	}
}
