package com.mail.dao;

import java.util.List;

public interface SubscriberDao {
	List<String> getEmails(Integer subscribed);
}
