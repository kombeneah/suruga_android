package com.suruga.tabandroid.listview;

import java.util.ArrayList;

import com.suruga.tabandroid.R;
import com.suruga.tabandroid.selections.City;
import com.suruga.tabandroid.selections.CityActivity;
import com.suruga.tabandroid.selections.CityListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends Activity {
	
	private DetailListAdapter adapter;
	
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
        
        ArrayList<Detail> details = new ArrayList<Detail>();
		details.add(new Detail(0, "Layout", "img1", false));
		details.add(new Detail(0, "Size", "img1", false));
		details.add(new Detail(0, "Nearest Station", "img1", false));
		details.add(new Detail(0, "Time to Station", "img1", false));
		details.add(new Detail(0, "Address", "img1", false));
		details.add(new Detail(0, "Monthly Cost", "img1", false));
		details.add(new Detail(0, "Downpayment", "img1", false));
		
		setupListViewAdapter();
		
		// Toast.makeText(getApplicationContext(), details.get(0).getName(), Toast.LENGTH_LONG).show();


		for (int i = 0; i < details.size(); i++) {
			adapter.insert(details.get(i), i);
		}

        
		try {
			
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
         
		
			
		}
		
		catch (Exception ex) {
			Log.e("Error", "Loading exception");
		}
		
    }
    
    private void setupListViewAdapter() {
		adapter = new DetailListAdapter(DetailActivity.this, R.layout.detail_row,
				new ArrayList<Detail>());
		ListView list = (ListView) findViewById(R.id.list);

		list.setAdapter(adapter);

	}
	 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.suruga.tabandroid.R.menu.main, menu);
        return true;
    }
}
