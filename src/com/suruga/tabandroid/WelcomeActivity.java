package com.suruga.tabandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 
 * @author changey
 * The welcome page which prompts users to enter passcode, the current passcode is 963369
 */
public class WelcomeActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {

		final EditText welcomeEditText;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_layout);

		welcomeEditText = (EditText) findViewById(R.id.welcomeEditText);
		
		// request focus to the survey code text box
		welcomeEditText.requestFocus();
		
		// automatically show the keypad for text input to the editBox.
		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(welcomeEditText, InputMethodManager.SHOW_IMPLICIT);
		
		// put this where we need to remove the focus on the editText
		/*
		
		*/

		welcomeEditText.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN
						&& keyCode == KeyEvent.KEYCODE_ENTER) {
					
					Resources res = getResources();
					String passcode = res.getString(R.string.passcode);
					
					// correct passcode provided
					if (welcomeEditText.getText().toString().equals(passcode)) {

						Intent i = new Intent();
						i.setClass(WelcomeActivity.this,
								AndroidTabLayoutActivity.class);
						
						// dismiss the dialog
						imm.hideSoftInputFromWindow(welcomeEditText.getWindowToken(), 0);
						
						startActivity(i);
						return true;
					}

					// incorrect passcode, show alert dialog
					else {
						AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
						builder.setMessage(R.string.passcodeErrorMessage)
							.setTitle(R.string.passcodeErrorTitle);
						builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// nothing special to do here
							}
						});
						AlertDialog dialog = builder.create();
						dialog.show();
						return false;
					}
					
				}
				return false;
			}
		});


	}
}
