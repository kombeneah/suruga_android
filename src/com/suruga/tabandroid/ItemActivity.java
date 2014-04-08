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
	private static Set<Integer> ratedIndices;

	/**
	 * onCreate is the method called when the activity is created
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

		Globals g = Globals.getInstance(getApplicationContext());

		// retrieve all items from the singleton class
		ArrayList<Item> items = g.getItems(g.getCity());			 

		if (items != null) { //items may be null if no city is selected
			for (int i = 0; i < items.size(); i++) {
				adapter.insert(items.get(i), i);
			}
		}
		
		if (g.getGuideStatus() == GuideStatus.goToHouses)
		{
			this.CheckTaskCompletion();
		}
	}

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {
		
		Globals g = Globals.getInstance(getApplicationContext());

		ItemListAdapter.ItemHolder itemHolder = (ItemListAdapter.ItemHolder) v
				.getTag();
		int position = itemHolder.item.getId();
		boolean selected = itemHolder.item.getSelected();

		// SharedPreferences prefs = this.getSharedPreferences(
		// "com.suruga.tabandroid", Context.MODE_MULTI_PROCESS);
		// Editor editor = prefs.edit();

		if (selected == false) {
			ArrayList<String> temp = g.getItemsSelectedList();
			temp.add(String.valueOf(position));
			g.setItemsSelectedList(temp);
			
			Set<String> set = new HashSet<String>();
			set.addAll(g.getItemsSelectedList());

			AndroidTabLayoutActivity.selectedHouses = set;

			itemHolder.checkbox.setImageResource(R.drawable.checkedcheckbox);
			itemHolder.item.setSelected(true);
			
			if (g.getGuideStatus() == GuideStatus.goToHouses)
			{
				if (g.getItemsSelectedList().size() >= 3) {
					g.setSelectionComplete(true);

					this.CheckTaskCompletion();
				}
			}

		} else {			
			ArrayList<String> temp = g.getItemsSelectedList();
			temp.remove(String.valueOf(position));
			g.setItemsSelectedList(temp);
			
			Set<String> set = new HashSet<String>();
			set.addAll(g.getItemsSelectedList());

			AndroidTabLayoutActivity.selectedHouses = set;

			itemHolder.checkbox.setImageResource(R.drawable.emptycheckbox);

			itemHolder.item.setSelected(false);

			if (g.getGuideStatus() == GuideStatus.goToHouses)
			{
				if (g.getItemsSelectedList().size() < 3) {
					g.setSelectionComplete(false);
				}
			}
		}

	}

	public void infoOnClickHandler(View v) {

		Globals g = Globals.getInstance(getApplicationContext());

		Item itemSelected = (Item) v.getTag();

		Intent i = new Intent();

		i.setClass(ItemActivity.this, DetailActivity.class);

		// parameters
		
		i.putExtra("index", itemSelected.getId());
		i.putExtra("rating", itemSelected.getRating());
		// i.putExtra("position", String.valueOf(position + 1));
		
		g.setDetailViewedItems(g.getDetailViewedItems() + 1);

		if (g.getGuideStatus() == GuideStatus.goToHouses)
		{
			if (ratedIndices == null)
			{
				ratedIndices = new HashSet<Integer>();
			}
			
			if (ratedIndices.contains(itemSelected.getId()))
			{
				startActivity(i);
			}
			else
			{
				if (g.getDetailViewedItems() >= 2) {
					g.setDetailsViewed(true);

					this.CheckTaskCompletion();
				}
				
				// add this item to already rated items, to avoud finishing task by rating the same item twice
				ratedIndices.add(itemSelected.getId());

				// start the detail page
				startActivityForResult(i, Globals.SET_RATING_REQUEST);
			}
		}
		else if (g.getGuideStatus() == GuideStatus.goToSettings) {
			AlertDialog.Builder builder = new AlertDialog.Builder(ItemActivity.this);
			builder.setMessage(R.string.alertContentGeneric)
			.setTitle(R.string.alertPendingHeader);
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// navigate to the guide tab
					ItemActivity.this.onBackPressed();
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show();
		}
		else if (g.getGuideStatus() == GuideStatus.done || 
				g.getGuideStatus() == GuideStatus.goToAnalysis)
		{
			// start the detail page
			startActivity(i);
		}

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Globals g = Globals.getInstance(getApplicationContext());
		
		if (requestCode == Globals.SET_RATING_REQUEST) {
			if (resultCode == RESULT_OK) {
				if (g.getGuideStatus() == GuideStatus.goToHouses)
				{
					g.setRatedItems(g.getRatedItems() + 1);
					
					if (g.getRatedItems() >= 2) {
						g.setRatingComplete(true);

						this.CheckTaskCompletion();
					}
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
		
		Globals g = Globals.getInstance(getApplicationContext());
		if (g.getGuideStatus() == GuideStatus.goToHouses)
		{
			if (g.isSelectionComplete() && g.isDetailsViewed() && g.isRatingComplete())
			{
				g.setGuideStatus(GuideStatus.goToAnalysis);

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
}