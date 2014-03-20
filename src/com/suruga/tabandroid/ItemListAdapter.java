package com.suruga.tabandroid;

import java.util.List;

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

public class ItemListAdapter extends ArrayAdapter<Item> {

	protected static final String LOG_TAG = ItemListAdapter.class.getSimpleName();
	
	private List<Item> items;
	private int layoutResourceId;
	private Context context;

	public ItemListAdapter(Context context, int layoutResourceId, List<Item> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}
	
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItemHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new ItemHolder();
		
		holder.item = items.get(position);	
		holder.checkbox = (ImageView) row.findViewById(R.id.select_image);
		holder.check_area = (ImageView) row.findViewById(R.id.select_view_area);

		holder.name = (TextView) row.findViewById(R.id.tvName);
		holder.list_image= (ImageView)row.findViewById(R.id.list_image);
		
		// ensure the checkbox reflects whether the item is selected
		boolean selected = holder.item.getSelected();
		if (selected) {
			holder.checkbox.setImageResource(R.drawable.checkedcheckbox);
		}
		else {
			holder.checkbox.setImageResource(R.drawable.emptycheckbox);
		}
		
		//set up the image of a cell
		String uri = "drawable/"+holder.item.getImageArray()[0];
	    int imageResource = row.getContext().getApplicationContext().getResources().getIdentifier(uri, null, row.getContext().getApplicationContext().getPackageName());
		Drawable image = row.getContext().getResources().getDrawable(imageResource);
	    holder.list_image.setImageDrawable(image);

		holder.checkbox.setTag(holder);
		holder.check_area.setTag(holder);
		row.setTag(holder.item);

		holder.station = (TextView) row.findViewById(R.id.nearestStation);
		holder.station.setText(holder.item.getNearestStation());
		
		holder.star_count = (TextView) row.findViewById(R.id.starCount);
		holder.star_count.setText(String.valueOf(holder.item.getRating()));
		
		holder.size = (TextView) row.findViewById(R.id.unitsize);
		holder.size.setText(holder.item.getLayout());

		setupItem(holder);
		return row;
	}
	
	

	private void setupItem(ItemHolder holder) {
		holder.name.setText(holder.item.getName());
		
	}

	public static class ItemHolder {
		Item item;
		TextView name;
		TextView station;
		AbsoluteLayout cell;
		ImageView list_image;
		ImageView checkbox;
		ImageView check_area;
		TextView star_count;
		TextView size;
	}

}
