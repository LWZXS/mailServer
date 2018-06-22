package com.mail.delegate;

import com.mail.entity.Template;

import java.util.List;

public interface MailService {
	List<String> getEmails(Integer subscribed);
	
	int unsubscribeEmail(String email);

    Template selectTemplateBySubject(String template_subject);
}
