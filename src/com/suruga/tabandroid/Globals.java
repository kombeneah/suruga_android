package com.suruga.tabandroid;

import java.util.ArrayList;
import java.util.Set;

public class Globals {
	private static Globals instance;

	// Global variable
	private int data;
	private Set<String> selectedHouses;
	private String city;
	private String interest;
	private String monthly;
	private String savings;
	
	private ArrayList<Item> items=new ArrayList<Item>();
	

	// Restrict the constructor from being instantiated
	private Globals() {
		this.items.add(new Item(0, "House 1", "img1", false, 0, 0,"Tokyo", "", "", true, "", "", 0, 0, 0));
		this.items.add(new Item(1, "House 2", "img2", false, 500000000, 0,"Tokyo", "", "", true, "", "", 0, 0, 0));
		this.items.add(new Item(2, "House 3", "img21", false,0, 0,"", "", "", true, "", "", 0, 0, 0));
		this.items.add(new Item(3, "House 4", "img22", false,0, 0,"", "", "", true, "", "", 0, 0, 0));
		this.items.add(new Item(4, "House 5", "img3", false,0, 0,"", "", "", true, "", "", 0, 0, 0));
		this.items.add(new Item(5, "House 6", "img4", false,0, 0,"", "", "", true, "", "", 0, 0, 0));
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
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
		this.city = city;
	}

	public String getCity() {
		return this.city;
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

	public static synchronized Globals getInstance() {
		if (instance == null) {
			instance = new Globals();
		}
		return instance;
	}
}