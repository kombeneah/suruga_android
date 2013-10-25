package com.suruga.tabandroid.selections;

import com.suruga.tabandroid.AndroidTabLayoutActivity;
import com.suruga.tabandroid.R;
import com.suruga.tabandroid.WelcomeActivity;
import com.suruga.tabandroid.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class MonthlyActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {

		final EditText editText1;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.monthly_layout);

		editText1 = (EditText) findViewById(R.id.editText1);



	}
}
