package com.mail.delegate;

import java.util.List;

import com.mail.dao.TemplateDao;
import com.mail.entity.Template;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mail.dao.SubscriberDao;
import com.mail.entity.Subscriber;

@Service
public class MailServiceImpl implements MailService {
	private SubscriberDao subscriberDao;
	
	@Autowired
	public void setSubscriberDao(SubscriberDao subscriberDao) {
		this.subscriberDao = subscriberDao;
	}

	private TemplateDao templateDao;

	@Autowired
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
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

	@Override
	public Template  selectTemplateBySubject(String template_subject) {
		return templateDao.selectTemplateBySubject(template_subject);
	}
}
