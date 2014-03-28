package com.suruga.tabandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
    	
    	TextView title = (TextView) findViewById(R.id.guideTitleTextView);
		TextView info = (TextView) findViewById(R.id.guideInfoTextView);
    	TextView appCodeDesc = (TextView) findViewById(R.id.appCodeDesc);
    	TextView appCode = (TextView) findViewById(R.id.appCode);
    	
    	switch (Globals.getInstance(getApplicationContext()).getGuideStatus()) {
    	
    	case goToSettings:
    	{
    		title.setText(R.string.guideSettingsHeader);
    		info.setText(R.string.guideSettingsInfo);
    		appCodeDesc.setVisibility(View.GONE);
    		appCode.setVisibility(View.GONE);
    		
    		break;
    	}
    	
    	case goToHouses:
    	{
    		title.setText(R.string.guideHousesHeader);
    		info.setText(R.string.guideHousesInfo);
    		appCodeDesc.setVisibility(View.GONE);
    		appCode.setVisibility(View.GONE);
    		
    		break;
    	}
    	
    	case goToAnalysis:
    	{
    		title.setText(R.string.guideFinancialsHeader);
    		info.setText(R.string.guideFinancialsInfo);
    		appCodeDesc.setVisibility(View.GONE);
    		appCode.setVisibility(View.GONE);
    		
    		break;
    	}
    	
    	case done:
    	{
    		title.setText(R.string.guideDoneHeader);
    		info.setText(R.string.guideDoneInfo);
    		appCodeDesc.setVisibility(View.VISIBLE);
    		appCode.setVisibility(View.VISIBLE);
    		
    		break;
    	}
    	
    	default:
    		break;
    		
    	}
    
    }
}
