package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.suruga.tabandroid.Globals.GuideStatus;


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
		
		Globals g = Globals.getInstance(getApplicationContext());
		items = g.getItems(g.getCity());

	}

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {
		AnalysisListAdapter.ItemHolder itemHolder = 
				(AnalysisListAdapter.ItemHolder) v.getTag();
		int position = itemHolder.item.getId();
		
		Globals g = Globals.getInstance(getApplicationContext());

		Intent i = new Intent();

		i.setClass(AnalysisActivity.this, com.suruga.tabandroid.listview.DetailReviewActivity.class);
	
		i.putExtra("index", position);
		
		if (g.getGuideStatus() == GuideStatus.goToAnalysis)
		{
			g.setBudgetDiagnosisSeen(true);
			this.CheckTaskCompletion();

			// start the detail page
			startActivityForResult(i, Globals.VIEW_RECOMMENDATION_REQUEST);
		}
		else if (g.getGuideStatus() == GuideStatus.done)
		{
			// start the detail page
			startActivity(i);
		}
		else if (g.getGuideStatus() == GuideStatus.goToSettings
				|| g.getGuideStatus() == GuideStatus.goToHouses){
			AlertDialog.Builder builder = new AlertDialog.Builder(AnalysisActivity.this);
			builder.setMessage(R.string.alertContentGeneric)
			.setTitle(R.string.alertPendingHeader);
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// navigate to the guide tab
					AnalysisActivity.this.onBackPressed();
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show();
			
			Log.i("favorites selected", String.valueOf(g.isSelectionComplete()));
			Log.i("rating complete", String.valueOf(g.isRatingComplete()));
			Log.i("viewing complete", String.valueOf(g.isDetailsViewed()));
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Globals g = Globals.getInstance(getApplicationContext());
		
		if (g.getGuideStatus() == GuideStatus.goToAnalysis)
		{
			if (requestCode == Globals.VIEW_RECOMMENDATION_REQUEST) {
				if (resultCode == RESULT_OK) {
					g.setRecommendationSeen(true);

					this.CheckTaskCompletion();
				}
			}
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();
		
		itemsId = new ArrayList<Integer>();
		setupListViewAdapter();
		Globals g = Globals.getInstance(getApplicationContext());
		items = g.getItems(g.getCity());
		
		TextView forBudgetHeader =(TextView) findViewById(R.id.preBudgetHeader);
		TextView afterBudgetHeader = (TextView) findViewById(R.id.postBudgetHeader);
		
		if (g.isForRent())
		{
			forBudgetHeader.setText(R.string.preRentalBudget);
			afterBudgetHeader.setText(R.string.postRentalBudget);
		}
		else
		{
			forBudgetHeader.setText(R.string.preBuyingBudget);
			afterBudgetHeader.setText(R.string.postBuyingBudget);
		}

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
	
	private void CheckTaskCompletion() {

		Globals g = Globals.getInstance(getApplicationContext());
		if (g.getGuideStatus() == GuideStatus.goToAnalysis)
		{
			if (g.isBudgetDiagnosisSeen() && g.isRecommendationSeen())
			{
				Globals.getInstance(getApplicationContext()).setGuideStatus(GuideStatus.done);

				AlertDialog.Builder builder = new AlertDialog.Builder(AnalysisActivity.this);
				builder.setMessage(R.string.alert3Content)
				.setTitle(R.string.alert3header);
				builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// navigate to the guide tab
						AnalysisActivity.this.onBackPressed();
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		}
	}

	private void setupListViewAdapter() {
		adapter = new AnalysisListAdapter(AnalysisActivity.this,
				R.layout.analysis_row, new ArrayList<Item>());
		ListView list = (ListView) findViewById(R.id.analysisItemList);

		list.setAdapter(adapter);

	}
	
	@Override
	public void onBackPressed() {
		AndroidTabLayoutActivity parentActivity;
        parentActivity = (AndroidTabLayoutActivity) this.getParent();
        parentActivity.switchTab(0);
	}

}