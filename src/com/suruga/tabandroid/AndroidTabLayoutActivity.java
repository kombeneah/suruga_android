package com.suruga.tabandroid;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        TabSpec guidespec = tabHost.newTabSpec("Guide");
       // photospec.setIndicator("Main", getResources().getDrawable(R.drawable.icon_photos_tab));
        guidespec.setIndicator("Guide");
        Intent guideIntent = new Intent(this, GuideActivity.class);
        guidespec.setContent(guideIntent);
        
        TabSpec settingsspec = tabHost.newTabSpec("Settings");
        // setting Title and Icon for the Tab
        settingsspec.setIndicator("Settings");
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsspec.setContent(settingsIntent);
        
        TabSpec itemspec = tabHost.newTabSpec("Item");
        itemspec.setIndicator("Items");
        Intent itemIntent = new Intent(this, ItemActivity.class);
        itemspec.setContent(itemIntent);
        
        TabSpec analysisspec = tabHost.newTabSpec("Analysis");
        analysisspec.setIndicator("Analysis");
        Intent analysisIntent = new Intent(this, AnalysisActivity.class);
        analysisspec.setContent(analysisIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(guidespec); 
        tabHost.addTab(settingsspec);
        tabHost.addTab(itemspec);
        tabHost.addTab(analysisspec);
    }
}