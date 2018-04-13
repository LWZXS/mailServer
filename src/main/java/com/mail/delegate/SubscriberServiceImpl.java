package com.mail.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mail.dao.SubscriberDao;
import com.mail.entity.Subscriber;

@Service
public class SubscriberServiceImpl implements SubscriberService {
	private SubscriberDao subscriberDao;
	
	@Autowired
	public void setSubscriberDao(SubscriberDao subscriberDao) {
		this.subscriberDao = subscriberDao;
	}
	
	public List<String> getEmails(Integer subscribed) {
		return subscriberDao.getEmails(subscribed);
	}

	@Override
	public int unsubscribeEmail(String email) {
		Subscriber subscriber = subscriberDao.getSubscriberByEmail(email);
		if (subscriber == null) {
			return 0;
		}else {
			subscriber.setSubscribed(0);
			subscriberDao.unsubscribeByEmail(subscriber);
			return 1;
		}
	}
}
