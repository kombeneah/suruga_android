package com.suruga.tabandroid;

import java.util.ArrayList;

import com.suruga.tabandroid.Globals.GuideStatus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class SettingsActivity extends Activity {

	private SettingListAdapter adapter;
	private ArrayList<String> itemsSelected = new ArrayList<String>();
	
	boolean isCitySelected = false;
	boolean isRentalSelected = false;
	boolean isMonthlySet = false;
	boolean isUpfrontSet = false;
	
	static final int SET_CITY_REQUEST = 0;
	static final int SET_RENTAL_REQUEST = 1;
	static final int SET_MONTHLY_REQUEST = 2;
	static final int SET_UPFRONT_REQUEST = 3;
	

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
			startActivityForResult(i, SET_CITY_REQUEST);
		} else if (position == 1) {
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.InterestActivity.class);
			startActivityForResult(i, SET_RENTAL_REQUEST);
		} else if (position == 2){
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.MonthlyActivity.class);
			startActivityForResult(i, SET_MONTHLY_REQUEST);
		} else if (position == 3){
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.SavingsActivity.class);
			startActivityForResult(i, SET_UPFRONT_REQUEST);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    
	    if (requestCode == SET_CITY_REQUEST) {
	        if (resultCode == RESULT_OK) {
	        	isCitySelected = true;
	        }
	    }
	    
	    else if (requestCode == SET_RENTAL_REQUEST) {
	        if (resultCode == RESULT_OK) {
	        	isRentalSelected = true;
	        }
	    }
	    
	    else if (requestCode == SET_MONTHLY_REQUEST) {
	        if (resultCode == RESULT_OK) {
	        	isMonthlySet = true;
	        }
	    }
	    
	    else if (requestCode == SET_UPFRONT_REQUEST) {
	        if (resultCode == RESULT_OK) {
	        	isUpfrontSet = true;
	        }
	    }
	    
	    // If everything is set, update the global settings so that
	    // the guide tab is updated. Then show pop up.
	    
	    if (isCitySelected && isRentalSelected && 
	    		isMonthlySet && isUpfrontSet)
	    {
	    	Globals.getInstance(getApplicationContext()).setGuideStatus(GuideStatus.goToHouses);
	    	
	    	AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
			builder.setMessage(R.string.alertContentGeneric)
				.setTitle(R.string.alert1header);
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// navigate to the guide tab
					SettingsActivity.this.onBackPressed();
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show();
	    }
	}

	private void setupListViewAdapter() {
		adapter = new SettingListAdapter(SettingsActivity.this,
				R.layout.setting_row, new ArrayList<Setting>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);
	}
	
	private void populateSettingsArray(ArrayList<Setting> settings) {
		Globals g = Globals.getInstance(getApplicationContext());
		String city = g.getCity();
		boolean forRent = g.isForRent();
		int monthly = g.getMonthly();
		int savings = g.getSavings();

		settings.add(new Setting(0, "City", city));
		
		String buyOrRent = forRent ? "Renting" : "Buying";
		settings.add(new Setting(1, "Interested In", buyOrRent));
		
		settings.add(new Setting(2, "Monthly Budget", String.valueOf(monthly)));
		settings.add(new Setting(3, "Savings", String.valueOf(savings)));
	}
	
	@Override
	public void onBackPressed() {
		AndroidTabLayoutActivity parentActivity;
        parentActivity = (AndroidTabLayoutActivity) this.getParent();
        parentActivity.switchTab(0);
	}

}