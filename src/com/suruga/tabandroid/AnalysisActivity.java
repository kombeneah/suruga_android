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
import android.widget.Toast;

public class AnalysisActivity extends Activity {

	private ItemListAdapter adapter;
	private ArrayList<String> itemsSelected=new ArrayList<String>();
	private boolean selected=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		setupListViewAdapter();
		
		ArrayList<Item> items=new ArrayList<Item>();
		Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)", Toast.LENGTH_LONG).show();

	}
	
	private void setupListViewAdapter() {
		adapter = new ItemListAdapter(AnalysisActivity.this,
				R.layout.list_item_row, new ArrayList<Item>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);
		

	}

}