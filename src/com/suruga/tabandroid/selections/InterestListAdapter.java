package com.suruga.tabandroid.selections;

import java.util.List;

import com.suruga.tabandroid.Globals;
import com.suruga.tabandroid.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class InterestListAdapter extends ArrayAdapter<City> {

	protected static final String LOG_TAG = InterestListAdapter.class.getSimpleName();
	
	private List<City> cities;
	private int layoutResourceId;
	private Context context;

	public InterestListAdapter(Context context, int layoutResourceId, List<City> cities) {
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
		
		holder.arrow=(ImageView)row.findViewById(R.id.arrow);

		holder.name = (TextView)row.findViewById(R.id.tvName);
		
		String interest = Globals.getInstance(row.getContext().getApplicationContext()).isForRent() ? "Renting" : "Buying";
		
		Log.i("Global interest: ", interest);
		Log.i("App interest: ", holder.city.getName());
		if (holder.city.getName().equals(interest))
		{
			holder.arrow.setImageResource(R.drawable.check_medium);
			holder.arrow.setVisibility(View.VISIBLE);
		}

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
