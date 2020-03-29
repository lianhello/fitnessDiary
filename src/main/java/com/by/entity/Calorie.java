package com.by.entity;

public class Calorie {
//	`id` int(10) UNSIGNED not null AUTO_INCREMENT,
//	`name` varchar(20),
//	`calorie` int(5) unsigned,
//	`gram` int(5) unsigned,
//	`type` varchar(10)
	
	private int id;
	private String name;
	private int gram;
	private int calorie;
	private String type;
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
	public int getGram() {
		return gram;
	}
	public void setGram(int gram) {
		this.gram = gram;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Calorie [id=" + id + ", name=" + name + ", gram=" + gram + ", calorie=" + calorie + ", type=" + type
				+ "]";
	}
	
	
}
