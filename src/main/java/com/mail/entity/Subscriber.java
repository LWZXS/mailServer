package com.mail.entity;

import java.util.Date;

public class Subscriber {
	// id created_at updated_at email ip pos lang registered subscribed
	private Integer id;
	private Date created_at;
	private Date updated_at;

	private String email;
	private String ip;
	private String pos;

	private String lang;
	private Integer registered;
	private Integer subscribed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getRegistered() {
		return registered;
	}

	public void setRegistered(Integer registered) {
		this.registered = registered;
	}

	public Integer getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Integer subscribed) {
		this.subscribed = subscribed;
	}

}
