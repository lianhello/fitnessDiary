package com.by.entity;

import java.sql.Timestamp;

public class WxUser {

	/**
	 * `id` int(10) unsigned not null auto_increment,
		`openid` char(28) not null UNIQUE,
		`count` int(10) unsigned not null default 1,
		`create_time` timestamp not null default current_timestamp,
		`update_time` timestamp not null default current_timestamp on update current_timestamp,
		primary key(`id`)
	 */
	private int id;
	private String openid;
	private int count;
	private Timestamp create_time;
	private Timestamp update_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "WxUser [id=" + id + ", openid=" + openid + ", count=" + count + ", create_time=" + create_time
				+ ", update_time=" + update_time + "]";
	}
	
	
}
