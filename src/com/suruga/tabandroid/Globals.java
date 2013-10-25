package com.suruga.tabandroid;

import java.util.Set;

public class Globals {
	private static Globals instance;

	// Global variable
	private int data;
	private Set<String> selectedHouses;
	private String city;
	private String interest;
	private int monthly;
	private int savings;

	// Restrict the constructor from being instantiated
	private Globals() {
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