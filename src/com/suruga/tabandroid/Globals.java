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
	
	private static final int NO_RESET = -1;
	
	//
	// variables for tracking progress
	//
	
	// Settings
	private boolean isCitySelected = false;
	private boolean isRentalSelected = false;
	private boolean isMonthlySet = false;
	private boolean isUpfrontSet = false;
	
	// Houses
	private int ratedItems = 0;
	private int detailViewedItems = 0;
	private boolean isSelectionComplete = false;
	private boolean isDetailsViewed = false;
	private boolean isRatingComplete = false;
	private ArrayList<String> itemsSelectedList = new ArrayList<String>();
	
	
	protected static final int SET_CITY_REQUEST = 0;
	protected static final int SET_RENTAL_REQUEST = 1;
	protected static final int SET_MONTHLY_REQUEST = 2;
	protected static final int SET_UPFRONT_REQUEST = 3;
	protected static final int SET_RATING_REQUEST = 4;
	
	protected static enum GuideStatus {
		goToSettings,
		goToHouses,
		goToAnalysis,
		done;
	}
	
	private GuideStatus guideStatus;
	
	private HashMap<String, ArrayList<Item>> items = new HashMap<String, ArrayList<Item>>();


	// Restrict the constructor from being instantiated
	private Globals(Context cxt, int resetSettingPosition) {
		this.context = cxt;
		Resources res = this.context.getResources();
		String[] cityNames = res.getStringArray(R.array.city_arrays);
		
		// we are resetting, do not randomize everything
		if (resetSettingPosition != NO_RESET) {
			switch(resetSettingPosition) {
			case 0:
			{
				this.setCitySelected(true);
				
				this.setForRent(instance.isForRent());
				this.setMonthly(instance.getMonthly());
				this.setSavings(instance.getSavings());
				
				this.setRentalSelected(true);
				this.setMonthlySet(true);
				this.setUpfrontSet(true);
			}
			case 1:
			{
				this.setRentalSelected(true);

				this.setCity(instance.getCity());
				this.setMonthly(instance.getMonthly());
				this.setSavings(instance.getSavings());
				
				this.setCitySelected(true);
				this.setMonthlySet(true);
				this.setUpfrontSet(true);
			}
			case 2:
			{
				this.setMonthlySet(true);

				this.setCity(instance.getCity());
				this.setForRent(instance.isForRent());
				this.setSavings(instance.getSavings());
				
				this.setCitySelected(true);
				this.setRentalSelected(true);
				this.setUpfrontSet(true);
			}
			case 3:
			{
				this.setUpfrontSet(true);

				this.setCity(instance.getCity());
				this.setForRent(instance.isForRent());
				this.setMonthly(instance.getMonthly());
				
				this.setCitySelected(true);
				this.setRentalSelected(true);
				this.setMonthlySet(true);
			}
			}
		}
		
		// otherwise, randomize default settings, but do not flag values as set
		else {
			this.selectedCity = cityNames[(int) Math.floor(Math.random()*(cityNames.length - 1))];
			this.forRent = Math.floor(Math.random() + 0.5) >= 1.0;
			this.monthly = 2000;
			this.savings = 25000;
			
			this.setCitySelected(false);
			this.setRentalSelected(false);
			this.setMonthlySet(false);
			this.setUpfrontSet(false);
		}

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
			instance = new Globals(context, NO_RESET);
		}
		return instance;
	}
	
	public static synchronized void reset(Context context, int position) {
		Globals newInstance = new Globals(context, position);
		instance = newInstance;
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

	public boolean isCitySelected() {
		return isCitySelected;
	}

	public void setCitySelected(boolean isCitySelected) {
		this.isCitySelected = isCitySelected;
	}

	public boolean isRentalSelected() {
		return isRentalSelected;
	}

	public void setRentalSelected(boolean isRentalSelected) {
		this.isRentalSelected = isRentalSelected;
	}

	public boolean isMonthlySet() {
		return isMonthlySet;
	}

	public void setMonthlySet(boolean isMonthlySet) {
		this.isMonthlySet = isMonthlySet;
	}

	public boolean isUpfrontSet() {
		return isUpfrontSet;
	}

	public void setUpfrontSet(boolean isUpfrontSet) {
		this.isUpfrontSet = isUpfrontSet;
	}

	public int getRatedItems() {
		return ratedItems;
	}

	public void setRatedItems(int ratedItems) {
		this.ratedItems = ratedItems;
	}

	public int getDetailViewedItems() {
		return detailViewedItems;
	}

	public void setDetailViewedItems(int detailViewedItems) {
		this.detailViewedItems = detailViewedItems;
	}

	public boolean isSelectionComplete() {
		return isSelectionComplete;
	}

	public void setSelectionComplete(boolean isSelectionComplete) {
		this.isSelectionComplete = isSelectionComplete;
	}

	public boolean isDetailsViewed() {
		return isDetailsViewed;
	}

	public void setDetailsViewed(boolean isDetailsViewed) {
		this.isDetailsViewed = isDetailsViewed;
	}

	public boolean isRatingComplete() {
		return isRatingComplete;
	}

	public void setRatingComplete(boolean isRatingComplete) {
		this.isRatingComplete = isRatingComplete;
	}

	public ArrayList<String> getItemsSelectedList() {
		return itemsSelectedList;
	}

	public void setItemsSelectedList(ArrayList<String> itemsSelectedList) {
		this.itemsSelectedList = itemsSelectedList;
	}
}