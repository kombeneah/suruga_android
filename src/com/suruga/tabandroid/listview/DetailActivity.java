package com.suruga.tabandroid.listview;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.suruga.tabandroid.Globals;
import com.suruga.tabandroid.Item;
import com.suruga.tabandroid.R;


public class DetailActivity extends Activity {
	
	private DetailListAdapter adapter;
	
	String position = "1";

	String iconfile = "";
	ImageButton imgWeatherIcon;
	
	int rating_int = 0;
	
	Item item=null;

	Globals g;
	
	
	@SuppressLint("NewApi")
	@Override
	public void onResume() {
		super.onResume();
		
		item = g.getItems(g.getCity()).get(getIntent().getIntExtra("index", -1));
		
		rating_int=item.getRating();

		int filledStarId = getApplicationContext().getResources().getIdentifier
				("filledstar", "drawable", getApplicationContext().getPackageName());
		int emptyStarId = getApplicationContext().getResources().getIdentifier
				("emptystar", "drawable", getApplicationContext().getPackageName());
        
        for (int i = 1; i <= 5; i++) {
        	String viewUri = "starRating"+String.valueOf(i);
			int viewId = getApplicationContext().getResources().getIdentifier
					(viewUri, "id", getApplicationContext().getPackageName());
			ImageView star = (ImageView) findViewById(viewId);
			
        	if (rating_int >= i) {
        		star.setImageResource(filledStarId);
        	}
        	else {
        		star.setImageResource(emptyStarId);
        	}
        }
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.suruga.tabandroid.R.layout.detailpage);
        g = Globals.getInstance(getApplicationContext());

        Intent j = getIntent();
        
        int index = j.getIntExtra("index", -1); // -1 is invalid index
        item = g.getItems(g.getCity()).get(index);
        
        rating_int = j.getIntExtra("rating", 0);
        
		for (int i = 0; i < 3; i++) {
			String viewUri = "detailImage"+String.valueOf(i);
			int viewId = getApplicationContext().getResources().getIdentifier
					(viewUri, "id", getApplicationContext().getPackageName());
			
			ImageView thumbnail = (ImageView) findViewById(viewId);
			String iconUri = "drawable/"+item.getImageArray()[i];
			int imageResource = getApplicationContext().getResources().getIdentifier
					(iconUri, null, getApplicationContext().getPackageName());
			thumbnail.setImageResource(imageResource);
		}
        
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
	        //this.rating_int=i;
            
	        
	        String uri = "drawable/"+ "d" + iconfile;
	       // int imageBtnResource = getResources().getIdentifier(uri, null, getPackageName());
			
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
    
    public void onClickStar(View v) {
    	
    	int filledStarId = getApplicationContext().getResources().getIdentifier
				("filledstar", "drawable", getApplicationContext().getPackageName());
		int emptyStarId = getApplicationContext().getResources().getIdentifier
				("emptystar", "drawable", getApplicationContext().getPackageName());
		
		int newRating = 0;
		switch (v.getId()) {
		case R.id.starRating1:
			newRating = 1;
			break;
		case R.id.starRating2:
			newRating = 2;
			break;
		case R.id.starRating3:
			newRating = 3;
			break;
		case R.id.starRating4:
			newRating = 4;
			break;
		case R.id.starRating5:
			newRating = 5;
			break;
		}
		
		item.setRating(newRating);
		
    	for (int i = 1; i <= 5; i++) {
    		
        	String viewUri = "starRating"+String.valueOf(i);
			int viewId = getApplicationContext().getResources().getIdentifier
					(viewUri, "id", getApplicationContext().getPackageName());
			ImageView star = (ImageView) findViewById(viewId);
			
        	if (newRating >= i) {
        		star.setImageResource(filledStarId);
        	}
        	else {
        		star.setImageResource(emptyStarId);
        	}
        }
    }
}
