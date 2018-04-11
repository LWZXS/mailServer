package com.mail.delegate;

import java.util.List;

public interface SubscriberService {
	List<String> getEmails(Integer subscribed);
}
