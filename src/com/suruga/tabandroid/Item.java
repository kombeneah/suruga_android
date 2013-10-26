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

	public Item(int id, String name, String img, boolean selected, int monthly, int savings) {
		this.setId(id);
		this.setName(name);
		this.setImage(img);
		this.setSelected(selected);
		this.setMonthly(monthly);
		this.setSavings(savings);
	}
	
	public int getSavings(){
		return savings;
	}
	
	public void setSavings(int savings){
		this.monthly=savings;
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