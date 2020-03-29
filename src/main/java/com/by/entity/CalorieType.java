package com.by.entity;

public class CalorieType {
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CalorieType [id=" + id + ", name=" + name + "]";
	}
	
	
}
