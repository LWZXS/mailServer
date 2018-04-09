package com.mail.delegate;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mail.common.DateUtil;
import com.mail.common.ThymeleafTemplateResolver;
import com.mail.dao.SendMailDao;
import com.mail.dao.TemplateDao;
import com.mail.entity.Sendmail;

@Service
public class MailServiceDelegate {
	@Autowired
	private TemplateDao templateDao;
	@Autowired
	private SendMailDao sendMailDao;
	@Autowired
	private TemplateEngine templateEngine;
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	public int sendMail(String toMail, String mailCategory,
			String title, String userName, Context data) throws Exception {
		int status =0;
		String emailContent = "";
		HtmlEmail htmlEmail = new HtmlEmail();
		try {
			//Context data = new Context();
			//data.setVariable("user_name", userName);
			emailContent = templateEngine.process(mailCategory, data);
			htmlEmail.setHostName("smtp.gmail.com");
			htmlEmail.addTo(toMail, userName);
			htmlEmail.setAuthenticator(new DefaultAuthenticator("usitripit1@gmail.com",
					"usitripbdusa"));
			htmlEmail.setFrom("DoNotReply@qq.com", "TestMail");
			htmlEmail.setSubject(title);
			// htmlEmail.setSSLOnConnect(true);
			htmlEmail.setStartTLSEnabled(true);
			htmlEmail.setSmtpPort(587);
			// embed the image and get the content id

			htmlEmail.setHtmlMsg(emailContent);

			// send the email
			htmlEmail.send();

		} catch (EmailException e) {
			logger.fatal("Failed to send email");
			logger.fatal(e.toString());
			status=1;
		} finally {
			Date now = new Date();
			Long time = now.getTime()/1000;
			Sendmail sendmail = new Sendmail();
			sendmail.setContent(emailContent);
			sendmail.setMail_category(mailCategory);
			sendmail.setMail_title(title);
			sendmail.setMail_to(toMail);
			sendmail.setSend_time(Integer.valueOf(time.toString()));
			sendmail.setCreate_time(new Timestamp(now.getTime()));
			sendmail.setStatus(status);
			sendmail.setUser_name(userName);
			sendMailDao.insert(sendmail);
		}
		return status;
	}
	
	public int sendMailTest(String toMail, String mailCategory,
			String title, String userName, Context data) throws Exception {
		int status =0;
		String emailContent = "";
		HtmlEmail htmlEmail = new HtmlEmail();
		try {
			//Context data = new Context();
			//data.setVariable("user_name", userName);
			ThymeleafTemplateResolver templateResolver = new ThymeleafTemplateResolver();
			templateEngine.setTemplateResolver(templateResolver);
			emailContent = templateEngine.process(mailCategory, data);
			htmlEmail.setHostName("smtp.gmail.com");
			htmlEmail.addTo(toMail, userName);
			htmlEmail.setAuthenticator(new DefaultAuthenticator("usitripit1@gmail.com",
					"usitripbdusa"));
			htmlEmail.setFrom("DoNotReply@qq.com", "TestMail");
			htmlEmail.setSubject(title);
			// htmlEmail.setSSLOnConnect(true);
			htmlEmail.setStartTLSEnabled(true);
			htmlEmail.setSmtpPort(587);
			// embed the image and get the content id

			htmlEmail.setHtmlMsg(emailContent);

			// send the email
			htmlEmail.send();

		} catch (EmailException e) {
			logger.fatal("Failed to send email. Got EmailException:"+e.getMessage(),e);
			status=1;
		} catch (Exception e) {
			logger.fatal("Failed to send email. Got Exception:"+e.getMessage(),e);
			status=1;
		} finally {
			Date now = new Date();
			Long time = now.getTime()/1000;
			Sendmail sendmail = new Sendmail();
			sendmail.setContent(emailContent);
			sendmail.setMail_category(mailCategory);
			sendmail.setMail_title(title);
			sendmail.setMail_to(toMail);
			sendmail.setSend_time(Integer.valueOf(time.toString()));
			sendmail.setCreate_time(new Timestamp(now.getTime()));
			sendmail.setStatus(status);
			sendmail.setUser_name(userName);
			sendMailDao.insert(sendmail);
		}
		return status;
	}
}
