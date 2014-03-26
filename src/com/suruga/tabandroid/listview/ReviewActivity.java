package com.suruga.tabandroid.listview;

import com.suruga.tabandroid.R;
import com.suruga.tabandroid.R.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class ReviewActivity extends Activity {
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    setContentView(R.layout.review_layout);
	    
	    Intent i = new Intent();
	    
	    int solutionNumber = i.getIntExtra("solutionNumber", 0);
	    
	    /*
	    ImageView surugaText = (ImageView) findViewById(R.id.surugaTextImage);
	    ImageView surugaHollow = (ImageView) findViewById(R.id.surugaHollowImage);
	    
	    Display display = getWindowManager().getDefaultDisplay();
	    
	    final Point outSize = new Point();
	    try {
	        display.getSize(outSize);
	    } catch (java.lang.NoSuchMethodError ignore) { // Older device
	        outSize.x = display.getWidth();
	        outSize.y = display.getHeight();
	    }
	    
	    int width = (int) Math.floor(outSize.x * 0.9);
	    
	    int hollowSize = (int) Math.floor(width/7.0);
	    int textLength = 6*hollowSize;
	    
	    surugaHollow.setMinimumWidth(hollowSize);
	    surugaHollow.setMinimumHeight(hollowSize);
	    surugaText.setMinimumWidth(textLength);
	    surugaText.setMinimumHeight(hollowSize);
	    */
	}
}
