package com.mail.delegate;

import java.util.List;

import com.mail.entity.Subscriber;

public interface SubscriberService {
	List<String> getEmails(Integer subscribed);
	
	void unsubscribeEmail(String email);
}
