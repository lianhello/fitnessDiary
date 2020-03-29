package com.by.entity;

import java.sql.Timestamp;

public class UserCalorie {

	/**
	 * `id` int(10) unsigned not null auto_increment,
	 *  `c_id` int(10) unsigned not null, 
	 * `u_id` int(10) unsigned not null, 
	 * `gram` int(10) unsigned,
	 * `create_time` timestamp not null default current_timestamp,
	 */
	
	private int id;
	private int c_id;
	private int u_id;
	private int gram;
	private Timestamp create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getGram() {
		return gram;
	}
	public void setGram(int gram) {
		this.gram = gram;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "UserCalorie [id=" + id + ", c_id=" + c_id + ", u_id=" + u_id + ", gram=" + gram + ", create_time="
				+ create_time + "]";
	}
	
	
}
