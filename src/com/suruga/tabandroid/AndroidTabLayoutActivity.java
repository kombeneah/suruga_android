package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.Set;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class AndroidTabLayoutActivity extends TabActivity {
	
	public static Set<String> selectedHouses;
	
	public static ArrayList<Item> items=new ArrayList<Item>();
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        TabSpec guidespec = tabHost.newTabSpec(getResources().getString(R.string.guide));
       // photospec.setIndicator("Main", getResources().getDrawable(R.drawable.icon_photos_tab));
        guidespec.setIndicator(getResources().getString(R.string.guide));
        Intent guideIntent = new Intent(this, GuideActivity.class);
        guidespec.setContent(guideIntent);
        
        TabSpec settingsspec = tabHost.newTabSpec(getResources().getString(R.string.settings));
        // setting Title and Icon for the Tab
        settingsspec.setIndicator(getResources().getString(R.string.settings));
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsspec.setContent(settingsIntent);
        
        TabSpec itemspec = tabHost.newTabSpec(getResources().getString(R.string.houses));
        itemspec.setIndicator(getResources().getString(R.string.houses));
        Intent itemIntent = new Intent(this, ItemActivity.class);
        itemspec.setContent(itemIntent);
        
        TabSpec analysisspec = tabHost.newTabSpec(getResources().getString(R.string.analysis));
        analysisspec.setIndicator(getResources().getString(R.string.analysis));
        Intent analysisIntent = new Intent(this, AnalysisActivity.class);
        analysisspec.setContent(analysisIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(guidespec); 
        tabHost.addTab(settingsspec);
        tabHost.addTab(itemspec);
        tabHost.addTab(analysisspec);
        
        // ensure tab titles have black font color
        for (int tabIndex = 0 ; tabIndex < tabHost.getTabWidget().getTabCount() ; tabIndex ++) {
            View tab = tabHost.getTabWidget().getChildTabViewAt(tabIndex);
            TextView t = (TextView) tab.findViewById(android.R.id.title);
            t.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
    
    public void switchTab(int tab) {
    	getTabHost().setCurrentTab(tab);
    }
    
}