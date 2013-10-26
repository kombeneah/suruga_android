package com.suruga.tabandroid;

import java.util.List;

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

public class AnalysisListAdapter extends ArrayAdapter<Item> {

	protected static final String LOG_TAG = AnalysisListAdapter.class
			.getSimpleName();

	private List<Item> items;
	private int layoutResourceId;
	private Context context;

	public AnalysisListAdapter(Context context, int layoutResourceId,
			List<Item> items) {
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

		holder.arrow = (ImageView) row.findViewById(R.id.arrow);

		holder.monthly = (ImageView) row.findViewById(R.id.monthly);

		holder.savings = (ImageView) row.findViewById(R.id.savings);

		holder.name = (TextView) row.findViewById(R.id.tvName);
		holder.list_image = (ImageView) row.findViewById(R.id.list_image);

		Globals g = Globals.getInstance();

		String monthly = g.getMonthly();
		String savings = g.getSavings();
		
		int monthlyInt =0;
		int savingsInt =0;
        
		if (monthly != null && savings != null) {
		  monthlyInt = Integer.valueOf(monthly);
		  savingsInt = Integer.valueOf(savings);
		}


		String uriGreen = "drawable/green";
		int imageResourceGreen = row
				.getContext()
				.getApplicationContext()
				.getResources()
				.getIdentifier(
						uriGreen,
						null,
						row.getContext().getApplicationContext()
								.getPackageName());
		Drawable green = row.getContext().getResources()
				.getDrawable(imageResourceGreen);

		String uriRed = "drawable/red";
		int imageResourceRed = row
				.getContext()
				.getApplicationContext()
				.getResources()
				.getIdentifier(
						uriRed,
						null,
						row.getContext().getApplicationContext()
								.getPackageName());
		Drawable red = row.getContext().getResources()
				.getDrawable(imageResourceRed);
        
		if (monthly != null && savings != null) {
			holder.name.setText(String.valueOf(holder.item.getMonthly()));
			if (holder.item.getMonthly() >= monthlyInt) {
				holder.monthly.setImageDrawable(green);
			} else {
				holder.monthly.setImageDrawable(red);
			}

			if (holder.item.getSavings() >= savingsInt) {
				holder.monthly.setImageDrawable(green);
			} else {
				holder.monthly.setImageDrawable(red);
			}
		}

		// set up the image of a cell
		String uri = "drawable/" + holder.item.getImage();
		int imageResource = row
				.getContext()
				.getApplicationContext()
				.getResources()
				.getIdentifier(
						uri,
						null,
						row.getContext().getApplicationContext()
								.getPackageName());
		Drawable image = row.getContext().getResources()
				.getDrawable(imageResource);
		holder.list_image.setImageDrawable(image);
		// row.findViewById(R.id.cellOnClick).setTag(position);

		row.setTag(holder);

		//setupItem(holder);
		return row;
	}

	private void setupItem(ItemHolder holder) {
		holder.name.setText(holder.item.getName());

	}

	public static class ItemHolder {
		Item item;
		TextView name;
		TextView value;
		AbsoluteLayout cell;
		ImageButton info;
		ImageView arrow;
		ImageView list_image;
		ImageView monthly;
		ImageView savings;
	}

}
