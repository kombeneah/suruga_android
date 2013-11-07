package com.suruga.tabandroid.listview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.suruga.tabandroid.AndroidTabLayoutActivity;
import com.suruga.tabandroid.ItemListAdapter;
import com.suruga.tabandroid.R;
import com.suruga.tabandroid.selections.City;
import com.suruga.tabandroid.selections.CityActivity;
import com.suruga.tabandroid.selections.CityListAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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
	
	int rating_int=0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.suruga.tabandroid.R.layout.detailpage);
        
        final Button minus = (Button) findViewById(R.id.minus);
        final Button plus = (Button) findViewById(R.id.plus);
        final TextView rating = (TextView) findViewById(R.id.rating);
        
        
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(rating_int>=1 && rating_int<=5){
            		rating_int--;
            		if(rating_int==0){
            			rating.setText(" ? ");
            		}
            		else{
            		    rating.setText(" "+String.valueOf(rating_int));
            		}
            	}

            }
        });
        
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(rating_int>=0 && rating_int<=4){
            		rating_int++;
            		rating.setText(" "+String.valueOf(rating_int));
            	}
            	

            }
        });
        
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
    
    @SuppressLint("NewApi")
	public void cellOnClickHandler(View v) {

	}
    
    private void setupListViewAdapter() {
		adapter = new DetailListAdapter(DetailActivity.this, R.layout.detail_row,
				new ArrayList<Detail>());
		ListView list = (ListView) findViewById(R.id.detaillist);

		list.setAdapter(adapter);

	}
	 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.suruga.tabandroid.R.menu.main, menu);
        return true;
    }
}
