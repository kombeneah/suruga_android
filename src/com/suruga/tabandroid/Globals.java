package com.suruga.tabandroid;

import java.util.Set;

public class Globals {
	private static Globals instance;

	// Global variable
	private int data;
	private Set<String> selectedHouses;

	// Restrict the constructor from being instantiated
	private Globals() {
	}

	public synchronized void setSelectedHouses(Set<String> input) {
		this.selectedHouses = input;
	}

	public synchronized Set<String> getSelectedHouses() {
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