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
        
        // Tab for Photos
        TabSpec mainspec = tabHost.newTabSpec("Main");
       // photospec.setIndicator("Main", getResources().getDrawable(R.drawable.icon_photos_tab));
        mainspec.setIndicator("Main");
        Intent photosIntent = new Intent(this, PhotosActivity.class);
        mainspec.setContent(photosIntent);
        
        // Tab for Songs
        TabSpec settingsspec = tabHost.newTabSpec("Settings");
        // setting Title and Icon for the Tab
        //songspec.setIndicator("Settings", getResources().getDrawable(R.drawable.icon_songs_tab));
        settingsspec.setIndicator("Settings");
        Intent songsIntent = new Intent(this, SongsActivity.class);
        settingsspec.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec itemspec = tabHost.newTabSpec("Item");
     //   videospec.setIndicator("Videos", getResources().getDrawable(R.drawable.icon_videos_tab));
        itemspec.setIndicator("Items");
        Intent videosIntent = new Intent(this, VideosActivity.class);
        itemspec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(mainspec); // Adding photos tab
        tabHost.addTab(settingsspec); // Adding songs tab
        tabHost.addTab(itemspec); // Adding videos tab
    }
}