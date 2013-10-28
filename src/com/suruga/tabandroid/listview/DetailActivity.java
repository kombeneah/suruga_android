package com.suruga.tabandroid.listview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;


public class DetailActivity extends Activity {
	
	String position = "1";
	String city = "";
	String weather = "";
	String temperature = "";
	String windSpeed = "";
	String iconfile = "";
	ImageButton imgWeatherIcon;
	
	TextView tvcity;
	TextView tvtemp;
	TextView tvwindspeed;
	TextView tvCondition;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.suruga.tabandroid.R.layout.detailpage);
        
		try {
			
			//handle for the UI elements 
			//imgWeatherIcon = (ImageButton) findViewById(com.suruga.tabandroid.R.id.imageButtonAlpha);
			//Text fields
			//tvcity = (TextView) findViewById(com.suruga.tabandroid.R.id.textViewCity);
			
			// Get position to display
	        Intent i = getIntent();
	        
	        this.position = i.getStringExtra("position");
	        this.city = i.getStringExtra("city");
	        this.weather=	i.getStringExtra("weather");
	        this.temperature =  i.getStringExtra("temperature");
	        this.windSpeed =  i.getStringExtra("windspeed");
	        this.iconfile = i.getStringExtra("icon");
	        
	        String uri = "drawable/"+ "d" + iconfile;
	        int imageBtnResource = getResources().getIdentifier(uri, null, getPackageName());
		    Drawable dimgbutton = getResources().getDrawable(imageBtnResource);
		
		    
		    //text elements
		    //tvcity.setText(city);

		    
		    //thumb_image.setImageDrawable(image);
		    //imgWeatherIcon.setImageDrawable(dimgbutton);
		
			
		}
		
		catch (Exception ex) {
			Log.e("Error", "Loading exception");
		}
		
    }
	 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.suruga.tabandroid.R.menu.main, menu);
        return true;
    }
}
