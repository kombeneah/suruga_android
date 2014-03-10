package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/**
 * 
 * @author changey
 * The class that stores global variables such as savings and monthly income
 */

public class Globals {
	private static Globals instance;

	// Global variable
	private int data;
	private Set<String> selectedHouses;
	private String selectedCity;
	private String interest;
	private String monthly;
	private String savings;
	private Context context;
	
	protected static enum GuideStatus {
		goToSettings,
		goToHouses,
		goToAnalysis,
		done;
	}
	
	private GuideStatus guideStatus;
	
	private HashMap<String, ArrayList<Item>> items = new HashMap<String, ArrayList<Item>>();
	

	// Restrict the constructor from being instantiated
	private Globals(Context cxt) {

		this.selectedCity = "";
		this.interest = "";
		this.monthly = "";
		this.savings = "";

		this.setGuideStatus(GuideStatus.goToSettings);
		this.context = cxt;

		Resources res = this.context.getResources();
		TypedArray housesByCity = res.obtainTypedArray(R.array.citiesAndHousesData);
		String[] cityNames = res.getStringArray(R.array.city_arrays);

		for (int i = 0; i < cityNames.length; i++) {
			String cityName = cityNames[i];
			ArrayList<Item> cityItems = new ArrayList<Item>();

			// get the id of the current city's array of data
			int id = housesByCity.getResourceId(i, 0);
			if (id > 0) {
				TypedArray cityDatas = res.obtainTypedArray(id);
				for (int j = 0; j < cityDatas.length(); j++) {
					int subId = cityDatas.getResourceId(j, 0);
					if (subId > 0) {
						TypedArray cityData = res.obtainTypedArray(subId);

						/***
						 * TypedArray data schema is:
						 * 
						 * [name, nearestStation, address, imageArray]
						 * 
						 ***/

						String name = cityData.getString(0);
						String nearestStation = cityData.getString(1);
						String address = cityData.getString(2);
						int imageArrayID = cityData.getResourceId(3, 0);
						if (imageArrayID > 0) {
							String[] imageArray = res.getStringArray(imageArrayID);
							Item cityItem = new Item(
									j,				// index: 0->5
									name, 			
									imageArray, 
									false, 			// selected?
									5, 				// monthly cost
									5, 				// savings
									cityName, 
									address, 
									"", 			// available for renting or buying?
									false, 			// in comparison?
									"", 			// layout
									"", 			// notes
									0, 				// rating
									0, 				// size
									nearestStation,
									0				// time to station
									);
							cityItems.add(cityItem);
						}
						cityData.recycle();
					}
				}

				cityDatas.recycle();
			}
			this.items.put(cityName, cityItems);
		}
		housesByCity.recycle();
	}

	public ArrayList<Item> getItems(String city) {
		return this.items.get(city);
	}

	public void setSavings(String savings) {
		this.savings = savings;
	}

	public String getSavings(){
		return this.savings;
	}
	
	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public String getMonthly() {
		return this.monthly;
	}
	
	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getInterest() {
		return this.interest;
	}
	
	public void setCity(String city) {
		this.selectedCity = city;
	}

	public String getCity() {
		return this.selectedCity;
	}

	public void setSelectedHouses(Set<String> input) {
		this.selectedHouses = input;
	}

	public Set<String> getSelectedHouses() {
		return this.selectedHouses;
	}

	public void setData(int d) {
		this.data = d;
	}
    
	public int getData() {
		return this.data;
	}

	public static synchronized Globals getInstance(Context context) {
		if (instance == null) {
			instance = new Globals(context);
		}
		return instance;
	}

	public GuideStatus getGuideStatus() {
		return guideStatus;
	}

	public void setGuideStatus(GuideStatus guideStatus) {
		this.guideStatus = guideStatus;
	}
}