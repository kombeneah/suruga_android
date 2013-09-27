package com.suruga.tabandroid;

import java.util.ArrayList;

import com.suruga.tabandroid.AnalysisActivity;
import com.suruga.tabandroid.ItemListAdapter;
import com.suruga.tabandroid.Item;
import com.suruga.tabandroid.R;
import com.suruga.tabandroid.listview.HouseInfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class AnalysisActivity extends Activity {

	private ItemListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();
		
		adapter.insert(new Item("House 1", "img1"), 0);
		adapter.insert(new Item("House 2", "img2"), 1);
		adapter.insert(new Item("House 3", "img21"), 2);
		adapter.insert(new Item("House 4", "img22"), 3);
		adapter.insert(new Item("House 5", "img3"), 4);
		adapter.insert(new Item("House 6", "img4"), 5);

	}
	
	public void cellOnClickHandler(View v) {
		//Item itemToRemove = (Item) v.getTag();
		Intent i = new Intent();
		i.setClass(AnalysisActivity.this, HouseInfoActivity.class);


		// start the detail page
		startActivity(i);
		
	}

	public void infoOnClickHandler(View v) {
		Item itemToRemove = (Item) v.getTag();
		Intent i = new Intent();
		i.setClass(AnalysisActivity.this, HouseInfoActivity.class);

		// parameters
		// i.putExtra("position", String.valueOf(position + 1));

		/*
		 * selected item parameters 1. House number 2. Weather 3. Wind speed 4.
		 * Temperature 5. Weather icon
		 */
		// i.putExtra("number",
		// weatherDataCollection.get(position).get(KEY_CITY));
		// i.putExtra("weather",
		// weatherDataCollection.get(position).get(KEY_CONDN));
		// i.putExtra("windspeed",
		// weatherDataCollection.get(position).get(KEY_SPEED));
		// i.putExtra("temperature",
		// weatherDataCollection.get(position).get(KEY_TEMP_C));
		// i.putExtra("icon",
		// weatherDataCollection.get(position).get(KEY_ICON));

		// start the detail page
		startActivity(i);
		
	}

	private void setupListViewAdapter() {
		adapter = new ItemListAdapter(AnalysisActivity.this,
				R.layout.list_item_row, new ArrayList<Item>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);

	}

}