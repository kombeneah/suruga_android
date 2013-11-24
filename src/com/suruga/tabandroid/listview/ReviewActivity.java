package com.suruga.tabandroid.listview;

import com.suruga.tabandroid.R;
import com.suruga.tabandroid.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ReviewActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    setContentView(R.layout.review_layout);
	    
	    Intent i = new Intent();
	    
	    int solutionNumber=i.getIntExtra("solutionNumber", 0);

	}
}
