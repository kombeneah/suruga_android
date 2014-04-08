package com.suruga.tabandroid.listview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.suruga.tabandroid.R;

public class ReviewActivity extends Activity {
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    setContentView(R.layout.review_layout);
	    
	    int solutionNumber = getIntent().getIntExtra("solutionNumber", -1);
	    int position = getIntent().getIntExtra("itemIndex", -1);
	    
	    TextView tipView = (TextView) findViewById(R.id.tipDetail);
	    TextView productView = (TextView) findViewById(R.id.recommDetail);
	    
	    String resourceTip = "tip" + String.valueOf(solutionNumber) + String.valueOf(position);
	    int tipId = getResources().getIdentifier(resourceTip, "string", getApplicationContext().getPackageName());
	    
	    String resourceProduct = "product" + String.valueOf(solutionNumber) + String.valueOf(position);
	    int productId = getResources().getIdentifier(resourceProduct, "string", getApplicationContext().getPackageName());
	    
	    tipView.setText(tipId);
	    productView.setText(productId);
	}
}
