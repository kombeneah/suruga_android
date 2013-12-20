package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.suruga.tabandroid.listview.DetailActivity;

/**
 * 
 * @author changey
 * The item activity will display houses based on selection criteria (city, buying or renting...)
 */
public class ItemActivity extends Activity {

	private ItemListAdapter adapter;
	private ArrayList<String> itemsSelected = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();

		Globals global = Globals.getInstance();

		// retrieve all items from the singleton class
		ArrayList<Item> items = global.getItems();		 

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getCity().equals("Tokyo")) {
				adapter.insert(items.get(i), i);
			}
		}

	}
	
	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();
        
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();

		Globals global = Globals.getInstance();

		// retrieve all items from the singleton class
		ArrayList<Item> items = global.getItems();
				 

		for (int i = 0; i < items.size(); i++) {
			//if (items.get(i).getCity().equals("Tokyo")) {
			if (items.get(i).getCity().equals(global.getCity())) {
				adapter.insert(items.get(i), i);
			}
		}
	}

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {

		ItemListAdapter.ItemHolder itemHolder = (ItemListAdapter.ItemHolder) v
				.getTag();
		int position = itemHolder.item.getId();
		boolean selected = itemHolder.item.getSelected();

		// SharedPreferences prefs = this.getSharedPreferences(
		// "com.suruga.tabandroid", Context.MODE_MULTI_PROCESS);
		// Editor editor = prefs.edit();

		if (selected == false) {
			itemsSelected.add(String.valueOf(position));
			Set<String> set = new HashSet<String>();
			set.addAll(itemsSelected);

			AndroidTabLayoutActivity.selectedHouses = set;

			itemHolder.arrow.setImageResource(R.drawable.check);
			itemHolder.info.setVisibility(View.INVISIBLE);

			itemHolder.item.setSelected(true);

		} else {
			itemsSelected.remove(String.valueOf(position));
			Set<String> set = new HashSet<String>();
			set.addAll(itemsSelected);

			AndroidTabLayoutActivity.selectedHouses = set;

			itemHolder.info.setVisibility(View.VISIBLE);
			itemHolder.info.setImageResource(R.drawable.information);
			itemHolder.arrow.setImageResource(R.drawable.arrow);

			itemHolder.item.setSelected(false);

		}

	}

	public void infoOnClickHandler(View v) {

		// itemHolder.

		Item itemSelected = (Item) v.getTag();

		Intent i = new Intent();

		i.setClass(ItemActivity.this, DetailActivity.class);

		// parameters
		
		i.putExtra("index", itemSelected.getId());
		i.putExtra("rating", itemSelected.getRating());
		// i.putExtra("position", String.valueOf(position + 1));

		// start the detail page
		startActivity(i);

	}

	private void setupListViewAdapter() {
		adapter = new ItemListAdapter(ItemActivity.this,
				R.layout.list_item_row, new ArrayList<Item>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);

	}
}