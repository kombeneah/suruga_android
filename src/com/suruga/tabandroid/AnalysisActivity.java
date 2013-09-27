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
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class AnalysisActivity extends Activity {
	
	private ItemListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		setupListViewAdapter();
		
		setupAddPaymentButton();
		//ImageButton imgB= (ImageButton)findViewById(R.id.img1);
		
		adapter.insert(new Item("", 0), 0);
	}

	public void removeAtomPayOnClickHandler(View v) {
		Item itemToRemove = (Item)v.getTag();
		Intent i = new Intent();
		i.setClass(AnalysisActivity.this, HouseInfoActivity.class);

		// parameters
		//i.putExtra("position", String.valueOf(position + 1));
		
		/* selected item parameters
		 * 1.	House number
		 * 2.	Weather
		 * 3.	Wind speed
		 * 4.	Temperature
		 * 5.	Weather icon   
		 */
//		i.putExtra("number", weatherDataCollection.get(position).get(KEY_CITY));
//		i.putExtra("weather", weatherDataCollection.get(position).get(KEY_CONDN));
//		i.putExtra("windspeed", weatherDataCollection.get(position).get(KEY_SPEED));
//		i.putExtra("temperature", weatherDataCollection.get(position).get(KEY_TEMP_C));
//		i.putExtra("icon", weatherDataCollection.get(position).get(KEY_ICON));

		// start the sample activity
		startActivity(i);
		//adapter.remove(itemToRemove);
	}

	private void setupListViewAdapter() {
		adapter = new ItemListAdapter(AnalysisActivity.this, R.layout.atom_pay_list_item, new ArrayList<Item>());
		ListView atomPaysListView = (ListView)findViewById(R.id.EnterPays_atomPaysList);
		atomPaysListView.setAdapter(adapter);
	}
	
	private void setupAddPaymentButton() {
		findViewById(R.id.EnterPays_addAtomPayment).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.insert(new Item("", 0), 0);
			}
		});
	}
}