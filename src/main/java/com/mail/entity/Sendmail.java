package com.mail.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Sendmail implements Serializable{
    /**
	 * 
	 */
	private static long serialVersionUID = 1L;

	private Integer rec_id;
	private Integer status;
	private String mail_to;
	private String mail_category;
	private String mail_title;
	private String user_name;
	private String content;
	private Integer send_time;
	private Timestamp create_time;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Integer getRec_id() {
		return rec_id;
	}
	public void setRec_id(Integer rec_id) {
		this.rec_id = rec_id;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	public String getMail_category() {
		return mail_category;
	}
	public void setMail_category(String mail_category) {
		this.mail_category = mail_category;
	}
	public String getMail_title() {
		return mail_title;
	}
	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSend_time() {
		return send_time;
	}
	public void setSend_time(Integer send_time) {
		this.send_time = send_time;
	}


}