package com.suruga.tabandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author changey
 * The welcome page which prompts users to enter passcode, the current passcode is 963369
 */
public class WelcomeActivity extends Activity {
	
	private static enum WelcomeStatus {
		viewWelcome,
		viewOverview,
		viewTasks,
		enterPinCode
	}
	
	private WelcomeStatus activityStatus = WelcomeStatus.viewWelcome;
	
	private void UpdateView() {
		
		TextView headerText = (TextView) findViewById(R.id.headerTextView);
		TextView descriptionText = (TextView) findViewById(R.id.contentTextView);
		ImageView checkMark = (ImageView) findViewById(R.id.passCodeOK);
		Button nextButton = (Button) findViewById(R.id.nextButton);
		Button backButton = (Button) findViewById(R.id.backButton);
		EditText pinEditText = (EditText) findViewById(R.id.welcomeEditText);
		
		switch(WelcomeActivity.this.activityStatus) {
		
		case viewWelcome:
		{
			pinEditText.setVisibility(View.GONE);
			checkMark.setVisibility(View.GONE);
			backButton.setVisibility(View.GONE);
			
			headerText.setVisibility(View.VISIBLE);
			descriptionText.setVisibility(View.VISIBLE);
			nextButton.setVisibility(View.VISIBLE);
			
			headerText.setText(R.string.welcomeHeader);
			descriptionText.setText(R.string.welcomeMessage);
			
			break;
		}
		
		case viewOverview:
		{
			pinEditText.setVisibility(View.GONE);
			checkMark.setVisibility(View.GONE);
			
			headerText.setVisibility(View.VISIBLE);
			descriptionText.setVisibility(View.VISIBLE);
			nextButton.setVisibility(View.VISIBLE);
			backButton.setVisibility(View.VISIBLE);
			
			headerText.setText(R.string.overviewHeader);
			descriptionText.setText(R.string.overviewMessage);
			
			break;
		}
		
		case viewTasks:
		{
			pinEditText.setVisibility(View.GONE);
			checkMark.setVisibility(View.GONE);
			
			headerText.setVisibility(View.VISIBLE);
			descriptionText.setVisibility(View.VISIBLE);
			nextButton.setVisibility(View.VISIBLE);
			backButton.setVisibility(View.VISIBLE);
			
			headerText.setText(R.string.tasksHeader);
			descriptionText.setText(R.string.tasksMessage);
			
			break;
		}
		
		case enterPinCode:
		{
			pinEditText.setVisibility(View.VISIBLE);
			checkMark.setVisibility(View.VISIBLE);
			
			headerText.setVisibility(View.VISIBLE);
			descriptionText.setVisibility(View.VISIBLE);
			nextButton.setVisibility(View.GONE);
			backButton.setVisibility(View.GONE);
			
			headerText.setText(R.string.welcome);
			descriptionText.setText(R.string.codeEntryInstructions);
			

			// request focus to the survey code text box
			pinEditText.requestFocus();
			
			// automatically show the keypad for text input to the editBox.
			final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(pinEditText, InputMethodManager.SHOW_IMPLICIT);
			
			break;
		}
		
		default:
		{
			// do nothing here
			
			break;
		}
		}
	}
	
	public void onResume() {
		
		super.onResume();
		
		UpdateView();
	}
	
	public void onCreate(Bundle savedInstanceState) {

		final EditText welcomeEditText;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_layout);
		
		WelcomeActivity.this.activityStatus = WelcomeStatus.viewWelcome;
		
		final Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (WelcomeActivity.this.activityStatus) {
                
                case viewWelcome:
                {
                	WelcomeActivity.this.activityStatus = WelcomeActivity.WelcomeStatus.viewOverview;
                	UpdateView();
                	
                	break;
                }
                
                case viewOverview:
                {
                	WelcomeActivity.this.activityStatus = WelcomeActivity.WelcomeStatus.viewTasks;
                	UpdateView();
                	break;
                }
                
                case viewTasks:
                {
                	WelcomeActivity.this.activityStatus = WelcomeActivity.WelcomeStatus.enterPinCode;
                	UpdateView();
                	break;
                }
                
                case enterPinCode:
                {
                	// button not even visible in this state
                	break;
                }
                
                default:
                {
                	// do nothing
                	break;
                }
                
                }
            }
        });
        
        final Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (WelcomeActivity.this.activityStatus) {
                
                case viewWelcome:
                {
                	// not visible here
                	break;
                }
                
                case viewOverview:
                {
                	WelcomeActivity.this.activityStatus = WelcomeActivity.WelcomeStatus.viewWelcome;
                	UpdateView();
                	break;
                }
                
                case viewTasks:
                {
                	WelcomeActivity.this.activityStatus = WelcomeActivity.WelcomeStatus.viewOverview;
                	UpdateView();
                	break;
                }
                
                case enterPinCode:
                {
                	WelcomeActivity.this.activityStatus = WelcomeActivity.WelcomeStatus.viewTasks;
                	UpdateView();
                	break;
                }
                
                default:
                {
                	// do nothing
                	break;
                }
                
                }
            }
        });

		welcomeEditText = (EditText) findViewById(R.id.welcomeEditText);
		
		// listener for the DONE button on passcode entry
		welcomeEditText.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN
						&& keyCode == KeyEvent.KEYCODE_ENTER) {
					
					String passcode = getResources().getString(R.string.passcode);
					
					// correct passcode provided
					if (welcomeEditText.getText().toString().equals(passcode)) {

						Intent i = new Intent();
						i.setClass(WelcomeActivity.this, AndroidTabLayoutActivity.class);
						
						// dismiss the dialog
						//imm.hideSoftInputFromWindow(welcomeEditText.getWindowToken(), 0);
						
						startActivity(i);
						return true;
					}

					// incorrect passcode: will show an "X" next to entry box
					
				}
				
				return false;
			}
		});
		
		welcomeEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				
				ImageView checkImage = (ImageView) findViewById(R.id.passCodeOK);
				
				if (welcomeEditText.getText().toString().length() == 
						getResources().getString(R.string.passcode).length())
				{
					if (welcomeEditText.getText().toString()
							.equals(getResources().getString(R.string.passcode)))
					{
						checkImage.setImageResource(R.drawable.affordable);
					}

					else {
						checkImage.setImageResource(R.drawable.unaffordable);
					}				
				}
				else {
					checkImage.setImageResource(R.drawable.unaffordable);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// nothing here for now

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// nothing here for now

			}
		});
	}
}
