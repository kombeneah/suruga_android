package com.suruga.tabandroid;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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
		ArrayList<Setting> settings = new ArrayList<Setting>();
		populateSettingsArray(settings);

		for (int i = 0; i < settings.size(); i++) {
			adapter.insert(settings.get(i), i);
		}

	}

	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();

		setupListViewAdapter();

		ArrayList<Setting> settings = new ArrayList<Setting>();

		// SharedPreferences prefs = this.getSharedPreferences(
		// "com.suruga.tabandroid", Context.MODE_PRIVATE);
		// String city = prefs.getString("city", "");
		populateSettingsArray(settings);

		for (int i = 0; i < settings.size(); i++) {
			adapter.insert(settings.get(i), i);
		}

		// adapter.insert(itemsToDisplay.get(i), i);

		// Toast.makeText(getApplicationContext(), city,
		// Toast.LENGTH_LONG).show();
	}

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {

		SettingListAdapter.ItemHolder settingHolder = (SettingListAdapter.ItemHolder) v
				.getTag();
		int position = settingHolder.setting.getId();

		Intent i = new Intent();

		if (position == 0) {

			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.CityActivity.class);

		} else if (position == 1) {
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.InterestActivity.class);
		} else if (position == 2){
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.MonthlyActivity.class);
		} else if (position == 3){
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.SavingsActivity.class);
		}

		startActivity(i);

	}

	private void setupListViewAdapter() {
		adapter = new SettingListAdapter(SettingsActivity.this,
				R.layout.setting_row, new ArrayList<Setting>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);

	}
	
	private void populateSettingsArray(ArrayList<Setting> settings) {
		Globals g = Globals.getInstance();
		String city = g.getCity();
		String interest=g.getInterest();
		String monthly=g.getMonthly();
		String savings=g.getSavings();

		settings.add(new Setting(0, "City", city));
		settings.add(new Setting(1, "Interested In", interest));
		settings.add(new Setting(2, "Monthly Budget", monthly));
		settings.add(new Setting(3, "Savings", savings));
	}

}