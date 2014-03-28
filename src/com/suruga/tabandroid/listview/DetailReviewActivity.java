package com.suruga.tabandroid.listview;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

		Intent j = getIntent();

		int index = j.getIntExtra("index", 0);

		item = g.getItems(g.getCity()).get(index);

		int monthlyBudget = g.getMonthly();
		int savings = g.getSavings();
		
		int downPayment = g.isForRent() ? item.getRentingUpfront() : item.getBuyingUpfront();
		int monthlyCost = g.isForRent() ? item.getRentingMonthly() : item.getBuyingMonthly();
		
		yourMonthly.setText(String.valueOf(monthlyBudget) + " Yen");
		yourUpfront.setText(String.valueOf(savings) + " Yen");
		houseMonthly.setText(String.valueOf(monthlyCost) + " Yen");
		houseUpfront.setText(String.valueOf(downPayment) + " Yen");
		
		// can absolutely afford.
		if (downPayment <= savings && monthlyCost <= monthlyBudget) {
			affordLabel.setText("This home is within your budget.");
			solutions = 0;
		}
		
		// absolutely have enough savings for down payment
		// but monthly cost too high for budget.
		else if (downPayment <= savings && monthlyCost > monthlyBudget){
			affordLabel.setText("Need to review your monthly budget for making surplus in the monthly balance.");
			solutions = 1;
		}
		
        // not enough savings for down payment
        // but enough monthly budget for the monthly cost
		else if (downPayment > savings && monthlyCost <= monthlyBudget) {

            // trying to buy? 
            if (!g.isForRent()) {
            	
            	// mortgage allows to buy?
            	if (downPayment <= (savings + item.getMortgageLoan())) {
            		affordLabel.setText("Need to review your initial budget for making surplus in the initial balance.");
        			solutions = 2;
            	}
            	
            	// can't even be helped by mortgage
            	else {
            		// TODO: What happens here?
            	}
            }

            // otherwise renting
            else {
            	affordLabel.setText("Need to review your initial budget for making surplus in the initial balance.");
    			solutions = 3;
            }
        }
		
		//both negative
		else if (downPayment > savings && monthlyCost > monthlyBudget){
			affordLabel.setText("Need to review your budget.");
			solutions = 4;
		}
		
		// we shouldn't ever get into this block
		else {
			Log.e("Analysis", "Failed to classify the user data into one of the suggetsion buckets");
		}
		
		ArrayList<Detail> details = new ArrayList<Detail>();
		
        if (solutions==0) {
        	
        } else if (solutions==1) {
		    
		    details.add(new Detail(0, "Review your current loans", "img1", false));
		    details.add(new Detail(1, "Review other expenses", "img1", false));
		    details.add(new Detail(2, "Review your current insurance", "img1", false));
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
		
		int position = itemHolder.detail.getId();
		
		Intent i = new Intent();

		i.setClass(DetailReviewActivity.this,
				com.suruga.tabandroid.listview.ReviewActivity.class);
		
		i.putExtra("solutionNumber", solutions);
		
		setResult(RESULT_OK);

		startActivity(i);
	}

	private void setupListViewAdapter() {
		adapter = new DetailReviewListAdapter(DetailReviewActivity.this,
				R.layout.detail_review_row, new ArrayList<Detail>());
		
		ListView list = (ListView) findViewById(R.id.detaillist);

		list.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(com.suruga.tabandroid.R.menu.main, menu);
		return true;
	}
}
