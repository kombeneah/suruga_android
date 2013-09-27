package com.suruga.tabandroid;

import java.util.ArrayList;

import com.suruga.tabandroid.AnalysisActivity;
import com.suruga.tabandroid.ItemListAdapter;
import com.suruga.tabandroid.Item;
import com.suruga.tabandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class AnalysisActivity extends Activity {
	
	private ItemListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		setupListViewAdapter();
		
		setupAddPaymentButton();
		
		adapter.insert(new Item("", 0), 0);
	}

	public void removeAtomPayOnClickHandler(View v) {
		Item itemToRemove = (Item)v.getTag();
		adapter.remove(itemToRemove);
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