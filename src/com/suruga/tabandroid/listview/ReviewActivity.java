package com.suruga.tabandroid.listview;

import com.suruga.tabandroid.R;
import com.suruga.tabandroid.R.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

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
	    Log.i("tipURI", resourceTip);
	    Log.i("tipId", String.valueOf(tipId));
	    
	    String resourceProduct = "product" + String.valueOf(solutionNumber) + String.valueOf(position);
	    int productId = getResources().getIdentifier(resourceProduct, "string", getApplicationContext().getPackageName());
	    Log.i("productURI", resourceProduct);
	    Log.i("productId", String.valueOf(productId));
	    
	    tipView.setText(tipId);
	    productView.setText(productId);
	    
	    
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
