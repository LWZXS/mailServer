package com.mail.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mail.dao.SubscriberDao;

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
}
