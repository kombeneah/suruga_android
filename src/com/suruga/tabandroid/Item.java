package com.suruga.tabandroid;

import java.io.Serializable;

import android.widget.ImageButton;

public class Item implements Serializable {
	private static final long serialVersionUID = -5435670920302756945L;
	
	private String name = "";
	private double value = 0;
	//private ImageButton image = null;

	public Item(String name, double value) {
		this.setName(name);
		this.setValue(value);
		//this.setImage(imgB);
	}
	
//	public void setImage(ImageButton imgB){
//		this.image=imgB;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}