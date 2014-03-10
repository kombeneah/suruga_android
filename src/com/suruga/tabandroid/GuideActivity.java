package com.suruga.tabandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 
 * @author changey
 * The guide activity that explains the app function to users
 */

public class GuideActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_layout);
    }
    
    public void onResume() {
    	super.onResume();
    	switch (Globals.getInstance(getApplicationContext()).getGuideStatus()) {
    	
    	case goToSettings:
    	{
    		TextView title = (TextView) findViewById(R.id.guideTitleTextView);
    		TextView info = (TextView) findViewById(R.id.guideInfoTextView);
    		title.setText(R.string.guideSettingsHeader);
    		info.setText(R.string.guideSettingsInfo);
    		
    		break;
    	}
    	
    	case goToHouses:
    	{
    		TextView title = (TextView) findViewById(R.id.guideTitleTextView);
    		TextView info = (TextView) findViewById(R.id.guideInfoTextView);
    		title.setText(R.string.guideHousesHeader);
    		info.setText(R.string.guideHousesInfo);
    		
    		break;
    	}
    	
    	case goToAnalysis:
    	{
    		TextView title = (TextView) findViewById(R.id.guideTitleTextView);
    		TextView info = (TextView) findViewById(R.id.guideInfoTextView);
    		title.setText(R.string.guideFinancialsHeader);
    		info.setText(R.string.guideFinancialsInfo);
    		
    		break;
    	}
    	
    	case done:
    	{
    		TextView title = (TextView) findViewById(R.id.guideTitleTextView);
    		TextView info = (TextView) findViewById(R.id.guideInfoTextView);
    		title.setText(R.string.guideDoneHeader);
    		info.setText(R.string.guideDoneInfo);
    		
    		break;
    	}
    	
    	default:
    		break;
    		
    	}
    
    }
}
