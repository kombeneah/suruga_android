package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.suruga.tabandroid.listview.HouseInfoActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private SettingListAdapter adapter;
	private ArrayList<String> itemsSelected = new ArrayList<String>();

	// private boolean selected=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();

		// add all the house items
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item(0, "City", "img1", false, 0));
		items.add(new Item(1, "Interested In", "img2", false, 500000000));
		items.add(new Item(2, "Monthly Budget", "img21", false, 0));
		items.add(new Item(3, "Savings", "img22", false, 0));

		for (int i = 0; i < items.size(); i++) {
			adapter.insert(items.get(i), i);
		}

	}
	
	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();

		SharedPreferences prefs = this.getSharedPreferences(
				"com.suruga.tabandroid", Context.MODE_PRIVATE);
		String city = prefs.getString("city", "");
		

//		 Toast.makeText(getApplicationContext(), city,
//		 Toast.LENGTH_LONG).show();
	}

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {

		// ItemListAdapter.ItemHolder itemHolder= (ItemListAdapter.ItemHolder)
		// v.getTag();

		Intent i = new Intent();
		i.setClass(SettingsActivity.this,
				com.suruga.tabandroid.selections.CityActivity.class);

		startActivity(i);

	}

	private void setupListViewAdapter() {
		adapter = new SettingListAdapter(SettingsActivity.this,
				R.layout.setting_row, new ArrayList<Item>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);

	}

}