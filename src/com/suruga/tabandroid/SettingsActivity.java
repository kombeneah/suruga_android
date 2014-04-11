package com.suruga.tabandroid;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.suruga.tabandroid.Globals.GuideStatus;

public class SettingsActivity extends Activity {

	private SettingListAdapter adapter;
	private ArrayList<String> itemsSelected = new ArrayList<String>();

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

		populateSettingsArray(settings);

		for (int i = 0; i < settings.size(); i++) {
			adapter.insert(settings.get(i), i);
		}
	}

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {

		SettingListAdapter.ItemHolder settingHolder = (SettingListAdapter.ItemHolder) v
				.getTag();
		int position = settingHolder.setting.getId();
		Globals g = Globals.getInstance(getApplicationContext());
		
		if (g.getGuideStatus() != GuideStatus.goToSettings)
		{
			if (AndroidTabLayoutActivity.selectedHouses != null) {
				AndroidTabLayoutActivity.selectedHouses.clear();
			}
			
			Globals.reset(getApplicationContext(), position);
		}

		Intent i = new Intent();

		if (position == 0) {
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.CityActivity.class);
			startActivityForResult(i, Globals.SET_CITY_REQUEST);
		} else if (position == 1) {
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.InterestActivity.class);
			startActivityForResult(i, Globals.SET_RENTAL_REQUEST);
		} else if (position == 2){
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.MonthlyActivity.class);
			startActivityForResult(i, Globals.SET_MONTHLY_REQUEST);
		} else if (position == 3){
			i.setClass(SettingsActivity.this,
					com.suruga.tabandroid.selections.SavingsActivity.class);
			startActivityForResult(i, Globals.SET_UPFRONT_REQUEST);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    
		Globals g = Globals.getInstance(getApplicationContext());
		if (g.getGuideStatus()
				== GuideStatus.goToSettings)
		{
			if (requestCode == Globals.SET_CITY_REQUEST) {
				if (resultCode == RESULT_OK) {
					g.setCitySelected(true);
				}
			}

			else if (requestCode == Globals.SET_RENTAL_REQUEST) {
				if (resultCode == RESULT_OK) {
					g.setRentalSelected(true);
				}
			}

			if (requestCode == Globals.SET_MONTHLY_REQUEST) {
				if (resultCode == RESULT_OK) {
					g.setMonthlySet(true);
				}
			}

			else if (requestCode == Globals.SET_UPFRONT_REQUEST) {
				if (resultCode == RESULT_OK) {
					g.setUpfrontSet(true);
				}
			}

			this.CheckTaskCompletion();
		}
	}
	
	private void CheckTaskCompletion() {
		
		Globals g = Globals.getInstance(getApplicationContext());

		if (g.getGuideStatus() == GuideStatus.goToSettings)
		{
			if (g.isMonthlySet() && g.isUpfrontSet())
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

		settings.add(new Setting(0, getResources().getString(R.string.city), city));
		
		String buyOrRent = forRent ? getResources().getString(R.string.renting) : getResources().getString(R.string.buying);
		settings.add(new Setting(1, getResources().getString(R.string.interestedIn), buyOrRent));
		
		if (g.isForRent())
		{
			settings.add(new Setting(2, getResources().getString(R.string.postRentalBudget), 
					String.valueOf(monthly) + " " + getResources().getString(R.string.yen)));
			settings.add(new Setting(3, getResources().getString(R.string.preRentalBudget), 
					String.valueOf(savings) + " " + getResources().getString(R.string.yen)));
		}
		else
		{
			settings.add(new Setting(2, getResources().getString(R.string.postBuyingBudget),
					String.valueOf(monthly) + " " + getResources().getString(R.string.yen)));
			settings.add(new Setting(3, getResources().getString(R.string.preBuyingBudget), 
					String.valueOf(savings) + " " + getResources().getString(R.string.yen)));
		}
	}
	
	@Override
	public void onBackPressed() {
		AndroidTabLayoutActivity parentActivity;
        parentActivity = (AndroidTabLayoutActivity) this.getParent();
        parentActivity.switchTab(0);
	}

	public ArrayList<String> getItemsSelected() {
		return itemsSelected;
	}

	public void setItemsSelected(ArrayList<String> itemsSelected) {
		this.itemsSelected = itemsSelected;
	}

}