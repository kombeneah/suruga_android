package com.suruga.tabandroid;

import com.suruga.tabandroid.listview.DetailActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class WelcomeActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {

		final EditText editText1;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_layout);

		editText1 = (EditText) findViewById(R.id.editText1);

		editText1.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN
						&& keyCode == KeyEvent.KEYCODE_ENTER) {
					if (editText1.getText().toString().equals("1234")) {

						Intent i = new Intent();
						i.setClass(WelcomeActivity.this,
								AndroidTabLayoutActivity.class);

						startActivity(i);
					}

					return true;
				}
				return false;
			}
		});

		// Intent i = new Intent();
		// i.setClass(ItemActivity.this, HouseInfoActivity.class);
		//
		// startActivity(i);

	}
}
