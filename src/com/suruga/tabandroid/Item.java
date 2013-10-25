package com.suruga.tabandroid;

import java.io.Serializable;

public class Item implements Serializable {
	private static final long serialVersionUID = -5435670920302756945L;
	
	private String name = "";
	private String image = "";
	private int id=0;
	private boolean selected=true;
	private int price=0;

	public Item(int id, String name, String img, boolean selected, int price) {
		this.setId(id);
		this.setName(name);
		this.setImage(img);
		this.setSelected(selected);
		this.setPrice(price);
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setPrice(int price){
		this.price=price;
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