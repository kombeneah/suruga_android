package com.suruga.tabandroid;

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

public class SettingListAdapter extends ArrayAdapter<Setting> {

	protected static final String LOG_TAG = SettingListAdapter.class.getSimpleName();
	
	private List<Setting> settings;
	private int layoutResourceId;
	private Context context;

	public SettingListAdapter(Context context, int layoutResourceId, List<Setting> settings) {
		super(context, layoutResourceId, settings);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.settings = settings;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItemHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new ItemHolder();
		
		holder.setting = settings.get(position);
		
		holder.arrow=(ImageView)row.findViewById(R.id.arrow);
		holder.value=(TextView)row.findViewById(R.id.settingRowTextView);
		
		//holder.info = (ImageButton)row.findViewById(R.id.imageButton);
		//holder.info.setTag(holder.item);
		
		//holder.info.setImageResource(R.drawable.information);

		holder.name = (TextView)row.findViewById(R.id.tvName);
		

	    //row.findViewById(R.id.cellOnClick).setTag(position);

		row.setTag(holder);

		setupItem(holder);
		return row;
	}
	
	

	private void setupItem(ItemHolder holder) {
		holder.name.setText(holder.setting.getName());
		holder.value.setText(holder.setting.getValue());
		
	}
	
	public void updateInterest(String value) {
		// TODO: udate this to toggle buy/rent state
	}

	public static class ItemHolder {
		Setting setting;
		TextView name;
		TextView value;
		AbsoluteLayout cell;
		ImageButton info;
		ImageView arrow;
		ImageView list_image;
	}

}
