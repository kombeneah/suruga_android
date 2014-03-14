package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

	/**
	 * oncreate is the method called when the activity is created
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();

		Globals global = Globals.getInstance(getApplicationContext());

		// retrieve all items for the selected city from the singleton class
		ArrayList<Item> items = global.getItems(global.getCity()); 
		
		if (items != null) { //items may be null if no city is selected
			for (int i = 0; i < items.size(); i++) {
				adapter.insert(items.get(i), i);
			}
		}
		// TODO: suggest filling out the settings info first
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();
        
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();

		Globals global = Globals.getInstance(getApplicationContext());

		// retrieve all items from the singleton class
		ArrayList<Item> items = global.getItems(global.getCity());			 

		if (items != null) { //items may be null if no city is selected
			for (int i = 0; i < items.size(); i++) {
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

			itemHolder.checkbox.setImageResource(R.drawable.checkedcheckbox);
			itemHolder.item.setSelected(true);

		} else {
			itemsSelected.remove(String.valueOf(position));
			Set<String> set = new HashSet<String>();
			set.addAll(itemsSelected);

			AndroidTabLayoutActivity.selectedHouses = set;

			itemHolder.checkbox.setImageResource(R.drawable.emptycheckbox);

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