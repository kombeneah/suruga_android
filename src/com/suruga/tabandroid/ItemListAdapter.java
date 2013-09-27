package com.suruga.tabandroid;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
		holder.info = (ImageButton)row.findViewById(R.id.imageButton);
		holder.info.setTag(holder.item);
		
		holder.info.setImageResource(R.drawable.information);

		holder.name = (TextView)row.findViewById(R.id.tvName);
		holder.list_image= (ImageView)row.findViewById(R.id.list_image);
		
		//set up the image of a cell
		String uri = "drawable/"+holder.item.getImage();
	    int imageResource = row.getContext().getApplicationContext().getResources().getIdentifier(uri, null, row.getContext().getApplicationContext().getPackageName());
		Drawable image = row.getContext().getResources().getDrawable(imageResource);
	    holder.list_image.setImageDrawable(image);
		//setNameTextChangeListener(holder);
		//setValueTextListeners(holder);

		row.setTag(holder);

		setupItem(holder);
		return row;
	}
	
	

	private void setupItem(ItemHolder holder) {
		holder.name.setText(holder.item.getName());
		
	}

	public static class ItemHolder {
		Item item;
		TextView name;
		TextView value;
		ImageButton info;
		ImageView list_image;
	}
	
	private void setNameTextChangeListener(final ItemHolder holder) {
		holder.name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				holder.item.setName(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { }
		});
	}

}
