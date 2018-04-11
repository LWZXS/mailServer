package com.mail.entity;

import org.thymeleaf.context.Context;

public class VoQuery {
	/**
	 * Context data = new Context(); data.setVariable("user_name", "Chris"); String
	 * toMail = "2355906871@qq.com"; String mailCategory = "CreateAccount"; String
	 * title = "Test"; String userName = "Chris Zhang";
	 */
	private Context data;
	private String toMail;
	private String mailCategory;
	private String title;
	private String userName;

	public Context getData() {
		return data;
	}

	public void setData(Context data) {
		this.data = data;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getMailCategory() {
		return mailCategory;
	}

	public void setMailCategory(String mailCategory) {
		this.mailCategory = mailCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
