package com.mail.dao;

import java.util.List;

import com.mail.entity.Subscriber;

public interface SubscriberDao {
	List<String> getEmails(Integer subscribed);
	
	Subscriber getSubscriberByEmail(String email);
	
	void unsubscribeByEmail(Subscriber subscriber);
}
