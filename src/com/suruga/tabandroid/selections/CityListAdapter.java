package com.suruga.tabandroid.selections;

import java.util.List;

import com.suruga.tabandroid.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CityListAdapter extends ArrayAdapter<City> {

	protected static final String LOG_TAG = CityListAdapter.class.getSimpleName();
	
	private List<City> cities;
	private int layoutResourceId;
	private Context context;

	public CityListAdapter(Context context, int layoutResourceId, List<City> cities) {
		super(context, layoutResourceId, cities);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.cities = cities;
	}
	
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItemHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new ItemHolder();
		
		
		holder.city = cities.get(position);
		
//		holder.arrow=(ImageView)row.findViewById(R.id.arrow);
		
//		holder.info = (ImageButton)row.findViewById(R.id.imageButton);
//		holder.info.setTag(holder.city);
//		
//		holder.info.setImageResource(R.drawable.information);

		holder.name = (TextView)row.findViewById(R.id.tvName);
		

	    //row.findViewById(R.id.cellOnClick).setTag(position);

		row.setTag(holder);

		setupItem(holder);
		return row;
	}
	
	

	private void setupItem(ItemHolder holder) {
		holder.name.setText(holder.city.getName());
		
	}

	public static class ItemHolder {
		City city;
		TextView name;
		TextView value;
		AbsoluteLayout cell;
		ImageButton info;
		ImageView arrow;
		ImageView list_image;
	}

}
