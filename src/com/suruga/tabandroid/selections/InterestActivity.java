package com.suruga.tabandroid.selections;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.suruga.tabandroid.Globals;
import com.suruga.tabandroid.R;

public class InterestActivity extends Activity {

	private CityListAdapter adapter;
	static ArrayList<String> itemsSelected = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();

		// add the buying/renting interest items (abstracted as *City* types)
		ArrayList<City> cities = new ArrayList<City>();
		cities.add(new City(0, "Buying", "img1", false));
		cities.add(new City(1, "Renting", "img1", false));

		for (int i = 0; i < cities.size(); i++) {
			adapter.insert(cities.get(i), i);
		}
	}

	CityListAdapter.ItemHolder oldItemHolder = null;

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {

		CityListAdapter.ItemHolder itemHolder = (CityListAdapter.ItemHolder) v
				.getTag();

		itemHolder.arrow.setVisibility(View.VISIBLE);
		itemHolder.arrow.setImageResource(R.drawable.check_medium);
		
		Globals g = Globals.getInstance(getApplicationContext());
		boolean forRent = itemHolder.city.getName().equals(getResources().getString(R.string.renting));
		g.setForRent(forRent);

		if (oldItemHolder != null && oldItemHolder.equals(itemHolder) == false) {
			oldItemHolder.arrow.setVisibility(View.INVISIBLE);
		}

		oldItemHolder = itemHolder;
		
		setResult(RESULT_OK);
		finish();
	}

	private void setupListViewAdapter() {
		adapter = new CityListAdapter(InterestActivity.this, R.layout.city_row,
				new ArrayList<City>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);
	}

}