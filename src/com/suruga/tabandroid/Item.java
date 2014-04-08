package com.suruga.tabandroid;

import java.io.Serializable;

public class Item implements Serializable {
	private static final long serialVersionUID = -5435670920302756945L;
	
	private String name = "";
	private String[] imageArray;
	private int id=0;
	private boolean selected=true;
	private int rentingMonthly = 0;
	private int rentingUpfront = 0;
	private int buyingMonthly = 0;
	private int buyingUpfront = 0;
	private int mortgageLoan = 0;
	private String city="";
	private String address="";
	private boolean inComparison=true;
	private String layout="";
	private int rating=0;
	private int size=0;
	private String nearestStation = "";
	private int timeToStation=0;

	public Item(int id, String name, String[] imageArray, boolean selected, int rentingMonthly, int rentingUpfront,
			int buyingMonthly, int buyingUpfront, int mortgageLoan, String city, String address, boolean inComparison,
			String layout, int rating, int size, String nearestStation, int timeToStation) {
		this.setId(id);
		this.setName(name);
		this.setImageArray(imageArray);
		this.setSelected(selected);
		this.setRentingMonthly(rentingMonthly);
		this.setRentingUpfront(rentingUpfront);
		this.setBuyingMonthly(buyingMonthly);
		this.setBuyingUpfront(buyingUpfront);
		this.setMortgageLoan(mortgageLoan);
		this.setCity(city);
		this.setAddress(address);
		this.setInComparison(inComparison);
		this.setLayout(layout);
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

	public int getRentingMonthly() {
		return rentingMonthly;
	}

	public void setRentingMonthly(int rentingMonthly) {
		this.rentingMonthly = rentingMonthly;
	}

	public int getRentingUpfront() {
		return rentingUpfront;
	}

	public void setRentingUpfront(int rentingUpfront) {
		this.rentingUpfront = rentingUpfront;
	}

	public int getBuyingMonthly() {
		return buyingMonthly;
	}

	public void setBuyingMonthly(int buyingMonthly) {
		this.buyingMonthly = buyingMonthly;
	}

	public int getBuyingUpfront() {
		return buyingUpfront;
	}

	public void setBuyingUpfront(int buyingUpfront) {
		this.buyingUpfront = buyingUpfront;
	}

	public int getMortgageLoan() {
		return mortgageLoan;
	}

	public void setMortgageLoan(int mortgageLoan) {
		this.mortgageLoan = mortgageLoan;
	}
	
}