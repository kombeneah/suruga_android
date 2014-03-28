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

public class CityActivity extends Activity {

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
		String[] cityNames = getApplicationContext().getResources().getStringArray(R.array.city_arrays);
		for (int i = 0; i<cityNames.length; i++) {
			City city = new City(i, cityNames[i], "img1", false);
			cities.add(city);
			adapter.insert(city, i);
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

		itemHolder.arrow.setVisibility(View.VISIBLE);
		itemHolder.arrow.setImageResource(R.drawable.check_medium);
		
		Globals g = Globals.getInstance(getApplicationContext());
		g.setCity(itemHolder.city.getName());
		
		//editor.putString("city", itemHolder.city.getName());
		//editor.commit();

		if (oldItemHolder != null && oldItemHolder.equals(itemHolder) == false) {
			oldItemHolder.arrow.setVisibility(View.INVISIBLE);
		}

		oldItemHolder = itemHolder;
		
		setResult(RESULT_OK);
		finish();
	}

	private void setupListViewAdapter() {
		adapter = new CityListAdapter(CityActivity.this, R.layout.city_row,
				new ArrayList<City>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);

	}

}