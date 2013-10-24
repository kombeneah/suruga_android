package com.suruga.tabandroid;

import java.io.Serializable;

public class Setting implements Serializable {
	private static final long serialVersionUID = -5435670920302756945L;
	
	private String name = "";
	private String value = "";

	public Setting(int id, String name, String value) {
		this.setName(name);
		this.setValue(value);
	}
	
	public String getValue(){
		return value;
	}
	
	public void setValue(String value){
		this.value=value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}