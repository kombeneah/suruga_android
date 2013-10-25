package com.suruga.tabandroid.selections;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.suruga.tabandroid.Globals;
import com.suruga.tabandroid.ItemListAdapter;
import com.suruga.tabandroid.R;

public class InterestActivity extends Activity {

	private CityListAdapter adapter;
	private ArrayList<String> itemsSelected = new ArrayList<String>();

	// private boolean selected=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();

		// add all the house items
		ArrayList<City> cities = new ArrayList<City>();
		cities.add(new City(0, "Buying", "img1", false));
		cities.add(new City(0, "Renting", "img1", false));

		for (int i = 0; i < cities.size(); i++) {
			adapter.insert(cities.get(i), i);
		}

	}

	CityListAdapter.ItemHolder oldItemHolder = null;

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {

		CityListAdapter.ItemHolder itemHolder = (CityListAdapter.ItemHolder) v
				.getTag();

		SharedPreferences prefs = this.getSharedPreferences(
				"com.suruga.tabandroid", Context.MODE_PRIVATE);
		Editor editor = prefs.edit();

		// ItemListAdapter.ItemHolder itemHolder= (ItemListAdapter.ItemHolder)
		// v.getTag();

		// Intent i = new Intent();
		// i.setClass(CityActivity.this,
		// com.suruga.tabandroid.selections.City.class);
		//
		// startActivity(i);

		itemHolder.arrow.setVisibility(View.VISIBLE);
		itemHolder.arrow.setImageResource(R.drawable.check);
		
		Globals g = Globals.getInstance();
		g.setCity(itemHolder.city.getName());
		
		//editor.putString("city", itemHolder.city.getName());
		//editor.commit();

		if (oldItemHolder != null && oldItemHolder.equals(itemHolder) == false) {
			oldItemHolder.arrow.setVisibility(View.INVISIBLE);
		}

		oldItemHolder = itemHolder;

	}

	// public void infoOnClickHandler(View v) {
	//
	//
	// Item itemToRemove = (Item) v.getTag();
	//
	// Intent i = new Intent();
	// i.setClass(SettingsActivity.this, HouseInfoActivity.class);
	//
	// // parameters
	// // i.putExtra("position", String.valueOf(position + 1));
	//
	// /*
	// * selected item parameters 1. House number 2. Weather 3. Wind speed 4.
	// * Temperature 5. Weather icon
	// */
	// // i.putExtra("number",
	// // weatherDataCollection.get(position).get(KEY_CITY));
	// // i.putExtra("weather",
	// // weatherDataCollection.get(position).get(KEY_CONDN));
	//
	// // start the detail page
	// startActivity(i);
	//
	// }

	private void setupListViewAdapter() {
		adapter = new CityListAdapter(InterestActivity.this, R.layout.city_row,
				new ArrayList<City>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);

	}

}