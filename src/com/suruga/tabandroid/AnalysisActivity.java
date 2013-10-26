package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.suruga.tabandroid.AnalysisActivity;
import com.suruga.tabandroid.ItemListAdapter;
import com.suruga.tabandroid.Item;
import com.suruga.tabandroid.R;
import com.suruga.tabandroid.listview.HouseInfoActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class AnalysisActivity extends Activity {

	private AnalysisListAdapter adapter;

	Set<String> set = new HashSet<String>();
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<Integer> itemsId = new ArrayList<Integer>();
	ArrayList<Item> itemsToDisplay = new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		SharedPreferences prefs = this.getSharedPreferences(
				"com.suruga.tabandroid", Context.MODE_PRIVATE);

		setupListViewAdapter();

		items.add(new Item(0, "House 1", "img1", false, 0, 0));
		items.add(new Item(1, "House 2", "img2", false, 500000000, 0));
		items.add(new Item(2, "House 3", "img21", false,0, 0));
		items.add(new Item(3, "House 4", "img22", false,0, 0));
		items.add(new Item(4, "House 5", "img3", false,0, 0));
		items.add(new Item(5, "House 6", "img4", false,0, 0));

		// Toast.makeText(getApplicationContext(),
		// "this is my Toast message!!! =)", Toast.LENGTH_LONG).show();

	}

	public void cellOnClickHandler(View v) {

	}

	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();
		itemsId = new ArrayList<Integer>();
		setupListViewAdapter();

		itemsToDisplay = new ArrayList<Item>();

		Set<String> set = new HashSet<String>();

	//	SharedPreferences prefs = this.getSharedPreferences(
		//		"com.suruga.tabandroid", Context.MODE_MULTI_PROCESS);
		//set = prefs.getStringSet("key", set);
		
		
		set=AndroidTabLayoutActivity.selectedHouses;
		
		for (String i : set) {
			itemsId.add(Integer.valueOf(i));
			// itemsToDisplay.add(items.get(Integer.valueOf(i)));
		}
		// sort the id from the smallest to the largest
		Collections.sort(itemsId);

		for (int i = 0; i < itemsId.size(); i++) {
			itemsToDisplay.add(items.get(itemsId.get(i)));
		}

		for (int i = 0; i < itemsToDisplay.size(); i++) {
			adapter.insert(itemsToDisplay.get(i), i);
		}

		// Toast.makeText(getApplicationContext(), String.valueOf(set.size()),
		// Toast.LENGTH_LONG).show();
	}

	private void setupListViewAdapter() {
		adapter = new AnalysisListAdapter(AnalysisActivity.this,
				R.layout.analysis_row, new ArrayList<Item>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);

	}

}