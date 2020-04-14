package com.by.entity;

public class UserInfo {
	/**
	 * `uid` int(10) unsigned NOT NULL, `height` int(3) unsigned DEFAULT NULL,
	 * `weight` int(3) unsigned DEFAULT NULL, `sex` int(1) unsigned DEFAULT NULL,
	 */
	private int uid;
	private int height;
	private int weight;
	private int sex;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", height=" + height + ", weight=" + weight + ", sex=" + sex + "]";
	}

}
