package com.suruga.tabandroid.listview;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.suruga.tabandroid.R;

public class DetailListAdapter extends ArrayAdapter<Detail> {

	protected static final String LOG_TAG = DetailListAdapter.class.getSimpleName();
	
	private List<Detail> details;
	private int layoutResourceId;
	private Context context;

	public DetailListAdapter(Context context, int layoutResourceId, List<Detail> details) {
		super(context, layoutResourceId, details);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.details = details;
	}
	
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItemHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new ItemHolder();
		
		
		holder.detail = details.get(position);
		
		holder.arrow=(ImageView)row.findViewById(R.id.arrow);
		
//		holder.info = (ImageButton)row.findViewById(R.id.imageButton);
//		holder.info.setTag(holder.detail);
//		
//		holder.info.setImageResource(R.drawable.information);

		holder.name = (TextView)row.findViewById(R.id.tvName);
		

	    //row.findViewById(R.id.cellOnClick).setTag(position);

		row.setTag(holder);

		setupItem(holder);
		return row;
	}
	
	

	private void setupItem(ItemHolder holder) {
		holder.name.setText(holder.detail.getName());
		
	}

	public static class ItemHolder {
		Detail detail;
		TextView name;
		TextView value;
		AbsoluteLayout cell;
		ImageButton info;
		ImageView arrow;
		ImageView list_image;
	}

}
