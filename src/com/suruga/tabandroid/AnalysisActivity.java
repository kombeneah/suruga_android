package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.suruga.tabandroid.listview.DetailActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 
 * @author changey
 * The page to analyze whether selected items meet monthly and savings budget
 */
public class AnalysisActivity extends Activity {

	private AnalysisListAdapter adapter;

	Set<String> set = new HashSet<String>();
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<Integer> itemsId = new ArrayList<Integer>();
	ArrayList<Item> itemsToDisplay = new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.analysis_page);

		setupListViewAdapter();
		
		Globals g=Globals.getInstance(getApplicationContext());
		items=g.getItems(g.getCity());

	}

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {
		AnalysisListAdapter.ItemHolder itemHolder = (AnalysisListAdapter.ItemHolder) v
				.getTag();
		int position = itemHolder.item.getId();

		Intent i = new Intent();

		i.setClass(AnalysisActivity.this, com.suruga.tabandroid.listview.DetailReviewActivity.class);
	
		i.putExtra("index", position);

		// start the detail page
		startActivity(i);
	}

	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();
		
		TextView monthlyHeader = (TextView) findViewById(R.id.budgetAfterHeader);
		TextView upfrontHeader = (TextView) findViewById(R.id.budgetBeforeHeader);
		
		itemsId = new ArrayList<Integer>();
		setupListViewAdapter();
		Globals g = Globals.getInstance(getApplicationContext());
		items = g.getItems(g.getCity());

		itemsToDisplay = new ArrayList<Item>();

		Set<String> set = new HashSet<String>();

	//	SharedPreferences prefs = this.getSharedPreferences(
		//		"com.suruga.tabandroid", Context.MODE_MULTI_PROCESS);
		//set = prefs.getStringSet("key", set);
		
		set = AndroidTabLayoutActivity.selectedHouses;
		
		if (set == null) {
			// no selected houses, exit
			return;
		}
		
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

	}

	private void setupListViewAdapter() {
		adapter = new AnalysisListAdapter(AnalysisActivity.this,
				R.layout.analysis_row, new ArrayList<Item>());
		ListView list = (ListView) findViewById(R.id.analysisItemList);

		list.setAdapter(adapter);

	}

}