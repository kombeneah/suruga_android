package com.suruga.tabandroid;

import java.io.Serializable;

public class Item implements Serializable {
	private static final long serialVersionUID = -5435670920302756945L;
	
	private String name = "";
	private String image = "";
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
	private int timeToStation=0;

	public Item(int id, String name, String img, boolean selected, int monthly, int savings,
			String city, String address, String availableFor, boolean inComparison, String layout,
			String notes, int rating, int size, int timeToStation) {
		this.setId(id);
		this.setName(name);
		this.setImage(img);
		this.setSelected(selected);
		this.setMonthly(monthly);
		this.setSavings(savings);
	}
	
	public String getCity(){
		return city;
	}
	
	public void setSavings(String city){
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
	
	public String getImage(){
		return image;
	}
	
	public void setImage(String img){
		this.image=img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}