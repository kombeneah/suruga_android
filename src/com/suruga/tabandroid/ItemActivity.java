package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.suruga.tabandroid.Globals.GuideStatus;
import com.suruga.tabandroid.listview.DetailActivity;

/**
 * 
 * @author changey, kombeneah
 * The item activity will display houses based on selection criteria (city, buying or renting...)
 */

public class ItemActivity extends Activity {

	private ItemListAdapter adapter;
	private ArrayList<String> itemsSelected = new ArrayList<String>();
	
	int ratedItems = 0;
	int detailViewedItems = 0;
	
	boolean isSelectionComplete = false;
	boolean isDetailsViewed = false;
	boolean isRatingComplete = false;
	
	static final int SET_RATING_REQUEST = 4;

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
			
			if (itemsSelected.size() >= 3) {
				this.isSelectionComplete = true;
				
				this.CheckTaskCompletion();
			}

		} else {
			itemsSelected.remove(String.valueOf(position));
			Set<String> set = new HashSet<String>();
			set.addAll(itemsSelected);

			AndroidTabLayoutActivity.selectedHouses = set;

			itemHolder.checkbox.setImageResource(R.drawable.emptycheckbox);

			itemHolder.item.setSelected(false);
			
			if (this.itemsSelected.size() < 3) {
				this.isSelectionComplete = false;
			}
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
		
		this.detailViewedItems++;
		if (this.detailViewedItems >= 2) {
			this.isDetailsViewed = true;
			
			this.CheckTaskCompletion();
		}

		// start the detail page
		startActivityForResult(i, SET_RATING_REQUEST);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == SET_RATING_REQUEST) {
			if (resultCode == RESULT_OK) {
				this.ratedItems++;
				if (this.ratedItems >= 2) {
					this.isRatingComplete = true;
					
					this.CheckTaskCompletion();
				}
			}
		}
	}

	private void setupListViewAdapter() {
		adapter = new ItemListAdapter(ItemActivity.this,
				R.layout.list_item_row, new ArrayList<Item>());
		ListView list = (ListView) findViewById(R.id.itemList);

		list.setAdapter(adapter);
	}
	
	@Override
	public void onBackPressed() {
		AndroidTabLayoutActivity parentActivity;
        parentActivity = (AndroidTabLayoutActivity) this.getParent();
        parentActivity.switchTab(0);
	}
	
	private void CheckTaskCompletion() {
		if (isSelectionComplete && isDetailsViewed && isRatingComplete)
	    {
	    	Globals.getInstance(getApplicationContext()).setGuideStatus(GuideStatus.goToAnalysis);
	    	
	    	AlertDialog.Builder builder = new AlertDialog.Builder(ItemActivity.this);
			builder.setMessage(R.string.alertContentGeneric)
				.setTitle(R.string.alert2header);
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// navigate to the guide tab
					ItemActivity.this.onBackPressed();
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show();
	    }
	}
}