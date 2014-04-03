package com.suruga.tabandroid.listview;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.suruga.tabandroid.Globals;
import com.suruga.tabandroid.Item;
import com.suruga.tabandroid.R;

public class DetailReviewActivity extends Activity {

	private DetailReviewListAdapter adapter;

	String position = "1";

	String iconfile = "";
	ImageButton imgWeatherIcon;

	int rating_int = 0;
	Item item = null;
	Globals g;

	int solutions = 0;

	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();

		final TextView affordLabel;

		affordLabel = (TextView) findViewById(R.id.affordLabel);
		
		final TextView houseMonthly = (TextView) findViewById(R.id.TextViewHouseMonthly);
		final TextView houseUpfront = (TextView) findViewById(R.id.TextViewHouseUpfront);
		final TextView yourMonthly = (TextView) findViewById(R.id.TextViewYourMonthly);
		final TextView yourUpfront = (TextView) findViewById(R.id.TextViewYourUpfront);
		
		final TextView monthlyTitle = (TextView) findViewById(R.id.TextViewMonthlyTitle);
		final TextView savingsTitle = (TextView) findViewById(R.id.TextViewUpfrontTitle);
		
		final TextView houseCostsTitle = (TextView) findViewById(R.id.TextViewHouseCosts);
		final TextView yourBudgetTitle = (TextView) findViewById(R.id.TextViewYourBudget);
		
		final TextView recommendationsTitle = (TextView) findViewById(R.id.recsLabel);
		
		recommendationsTitle.setText(R.string.recommendations);
		
		houseCostsTitle.setText(R.string.houseCosts);
		yourBudgetTitle.setText(R.string.yourBudget);
		
		if (g.isForRent())
		{
			monthlyTitle.setText(R.string.postRentalBudget);
			savingsTitle.setText(R.string.preRentalBudget);
		}
		else
		{
			monthlyTitle.setText(R.string.postBuyingBudget);
			savingsTitle.setText(R.string.preBuyingBudget);
		}

		Intent j = getIntent();

		int index = j.getIntExtra("index", 0);

		item = g.getItems(g.getCity()).get(index);

		int monthlyBudget = g.getMonthly();
		int savings = g.getSavings();
		
		int downPayment = g.isForRent() ? item.getRentingUpfront() : item.getBuyingUpfront();
		int monthlyCost = g.isForRent() ? item.getRentingMonthly() : item.getBuyingMonthly();
		
		Resources res = getResources();
		
		yourMonthly.setText(String.valueOf(monthlyBudget) + " " + res.getString(R.string.yen));
		yourUpfront.setText(String.valueOf(savings) + " " + res.getString(R.string.yen));
		houseMonthly.setText(String.valueOf(monthlyCost) + " " + res.getString(R.string.yen));
		houseUpfront.setText(String.valueOf(downPayment) + " " + res.getString(R.string.yen));
		
		boolean isUpfrontAffordable = 
				(g.isForRent() && downPayment <= savings) ||
				(!g.isForRent() && downPayment <= savings + item.getMortgageLoan());
		
		boolean isMonthlyAffordable = monthlyCost <= monthlyBudget;
		
		// can absolutely afford.
		if (isUpfrontAffordable && isMonthlyAffordable)
		{
			affordLabel.setText(R.string.diagnosis1);
			solutions = 1;
		}
		
		// not enough monthly budget
		else if (isUpfrontAffordable && !isMonthlyAffordable)
		{
			affordLabel.setText(R.string.diagnosis2);
			solutions = 2;
		}
		
		// not enough upfront (savings)
		else if (!isUpfrontAffordable && isMonthlyAffordable)
		{
			affordLabel.setText(R.string.diagnosis3);
			solutions = 3;
		}
		
		// not enough everything
		else if (!isUpfrontAffordable && !isMonthlyAffordable)
		{
			affordLabel.setText(R.string.diagnosis4);
			solutions = 4;
		}
		
		// we shouldn't ever get into this block
		else {
			Log.e("Analysis", "Failed to classify the user data into one of the suggestion buckets");
		}
		
		ArrayList<Detail> details = new ArrayList<Detail>();

		if (solutions == 1) {
			details.add(new Detail(0, getResources().getString(R.string.solution1), "img1", item.getSelected()));
		}
		
		else if (solutions == 2) {

			details.add(new Detail(0, getResources().getString(R.string.solution21), "img1", item.getSelected()));
			details.add(new Detail(1, getResources().getString(R.string.solution22), "img1", item.getSelected()));
			details.add(new Detail(2, getResources().getString(R.string.solution23), "img1", item.getSelected()));
		}

		else if (solutions == 3) {

			details.add(new Detail(0, getResources().getString(R.string.solution31), "img1", item.getSelected()));
			details.add(new Detail(1, getResources().getString(R.string.solution32), "img1", item.getSelected()));
			details.add(new Detail(2, getResources().getString(R.string.solution33), "img1", item.getSelected()));
		}

		else if (solutions == 4) {
			details.add(new Detail(0, getResources().getString(R.string.solution4), "img1", item.getSelected()));
		}

        setupListViewAdapter();
        
        for (int i = 0; i < details.size(); i++) {
			adapter.insert(details.get(i), i);
		}

		rating_int = item.getRating();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.suruga.tabandroid.R.layout.detailreviewpage);
		
		g = Globals.getInstance(getApplicationContext());

		Intent j = getIntent();

		// this.position = i.getStringExtra("position");
		rating_int = j.getIntExtra("rating", 0);

		ArrayList<Detail> details = new ArrayList<Detail>();

		details.add(new Detail(0, "Review your financial plan", "img1", false));
		setupListViewAdapter();

		// Toast.makeText(getApplicationContext(), details.get(0).getName(),
		// Toast.LENGTH_LONG).show();

		for (int i = 0; i < details.size(); i++) {
			adapter.insert(details.get(i), i);
		}

		try {
			Intent i = getIntent();

			this.position = i.getStringExtra("position");
			// this.rating_int=i;

			String uri = "drawable/" + "d" + iconfile;
			int imageBtnResource = getResources().getIdentifier(uri, null,
					getPackageName());

		}

		catch (Exception ex) {
			Log.e("Error", "Loading exception");
		}

	}

	@SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {
		DetailReviewListAdapter.ItemHolder itemHolder = 
				(DetailReviewListAdapter.ItemHolder) v.getTag();
		
		int position = itemHolder.detail.getId() + 1;
		
		Intent i = new Intent();

		i.setClass(DetailReviewActivity.this,
				com.suruga.tabandroid.listview.ReviewActivity.class);
		
		i.putExtra("solutionNumber", solutions);
		i.putExtra("itemIndex", position);
		
		setResult(RESULT_OK);

		startActivity(i);
	}

	private void setupListViewAdapter() {
		adapter = new DetailReviewListAdapter(DetailReviewActivity.this,
				R.layout.detail_review_row, new ArrayList<Detail>());
		
		ListView list = (ListView) findViewById(R.id.detaillist);

		list.setAdapter(adapter);

	}
}
