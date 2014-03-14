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
 * @author changey, kombeneah
 * The class that stores global variables such as savings and monthly income
 */

public class Globals {
	private static Globals instance;

	// Global variable
	private int data;
	private Set<String> selectedHouses;
	private String selectedCity;
	private int monthly;
	private int savings;
	private Context context;
	private boolean forRent;
	
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
		this.context = cxt;
		Resources res = this.context.getResources();
		String[] cityNames = res.getStringArray(R.array.city_arrays);
		
		// randomly auto select a default city
		this.selectedCity = cityNames[(int) Math.floor(Math.random()*(cityNames.length - 1))];
		this.monthly = 2000;
		this.savings = 25000;
		
		// randomize default for buying/renting
		this.forRent = Math.floor(Math.random() + 0.5) >= 1.0;

		this.setGuideStatus(GuideStatus.goToSettings);
		
		TypedArray housesByCity = res.obtainTypedArray(R.array.citiesAndHousesData);

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
						 * [name, nearestStation, address, imageArray, size, layout, timeToStation,
						 * rentalUpfront, rentalMonthly, buyingUpfront, buyingMonthly, mortgageLoan]
						 * 
						 ***/

						String name = cityData.getString(0);
						String nearestStation = cityData.getString(1);
						String address = cityData.getString(2);
						int imageArrayID = cityData.getResourceId(3, 0);
						int size = cityData.getInt(4, 0);
						String layout = cityData.getString(5);
						int timeToNearestStation = cityData.getInt(6, 0);
						int rentalUpfront = cityData.getInt(7, 0);
						int rentalMonthly = cityData.getInt(8, 0);
						int buyingUpfront = cityData.getInt(9, 0);
						int buyingMonthly = cityData.getInt(10, 0);
						int mortgageLoan = cityData.getInt(11, 0);
						if (imageArrayID > 0) {
							String[] imageArray = res.getStringArray(imageArrayID);
							Item cityItem = new Item(
									j,						// index: 0->5
									name, 			
									imageArray, 
									false, 					// selected?
									rentalMonthly,
									rentalUpfront, 
									buyingMonthly,
									buyingUpfront,
									mortgageLoan,
									cityName, 
									address, 
									false, 					// in comparison?
									layout,
									0,
									size,
									nearestStation,
									timeToNearestStation
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

	public void setSavings(int savings) {
		this.savings = savings;
	}

	public int getSavings(){
		return this.savings;
	}
	
	public void setMonthly(int monthly) {
		this.monthly = monthly;
	}

	public int getMonthly() {
		return this.monthly;
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

	public boolean isForRent() {
		return forRent;
	}

	public void setForRent(boolean forRent) {
		this.forRent = forRent;
	}
}