package com.suruga.tabandroid;

import java.io.Serializable;

import android.content.res.TypedArray;

public class Item implements Serializable {
	private static final long serialVersionUID = -5435670920302756945L;
	
	private String name = "";
	private String[] imageArray;
	private int id=0;
	private boolean selected=true;
	private int monthly=0;
	private int savings=0;
	private String city="";
	private String address="";
	private String availableFor="";
	private boolean inComparison=true;
	private String layout="";
	private String notes="";
	private int rating=0;
	private int size=0;
	private String nearestStation = "";
	private int timeToStation=0;

	public Item(int id, String name, String[] imageArray, boolean selected, int monthly, int savings,
			String city, String address, String availableFor, boolean inComparison, String layout,
			String notes, int rating, int size, String nearestStation, int timeToStation) {
		this.setId(id);
		this.setName(name);
		this.setImageArray(imageArray);
		this.setSelected(selected);
		this.setMonthly(monthly);
		this.setSavings(savings);
		this.setCity(city);
		this.setAddress(address);
		this.setAvailableFor(availableFor);
		this.setInComparison(inComparison);
		this.setLayout(layout);
		this.setNotes(notes);
		this.setRating(rating);
		this.setSize(size);
		this.setNearestStation(nearestStation);
		this.setTimeToStation(timeToStation);
	}
	
	public int getTimeToStation(){
		return timeToStation;
	}
	
	public void setTimeToStation(int timeToStation){
		this.timeToStation=timeToStation;
	}
	
	public int getSize(){
		return size;
	}
	
	public void setSize(int size){
		this.size=size;
	}
	
	public int getRating(){
		return rating;
	}
	
	public void setRating(int rating){
		this.rating=rating;
	}
	
	public String getNotes(){
		return notes;
	}
	
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	public String getLayout(){
		return layout;
	}
	
	public void setLayout(String layout){
		this.layout=layout;
	}
	
	public boolean getInComparison(){
		return inComparison;
	}
	
	public void setInComparison(boolean inComparison){
		this.inComparison=inComparison;
	}
	
	public String getAvailableFor(){
		return availableFor;
	}
	
	public void setAvailableFor(String availableFor){
		this.availableFor=availableFor;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCity(String city){
		this.city=city;
	}
	
	public int getSavings(){
		return savings;
	}
	
	public void setSavings(int savings){
		this.savings=savings;
	}
	
	public int getMonthly(){
		return monthly;
	}
	
	public void setMonthly(int monthly){
		this.monthly=monthly;
	}
	
	public boolean getSelected(){
		return selected;
	}
	
	public void setSelected(boolean selected){
		this.selected=selected;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public String[] getImageArray(){
		return imageArray;
	}
	
	public void setImageArray(String[] imageArray){
		this.imageArray = imageArray;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}
	
}