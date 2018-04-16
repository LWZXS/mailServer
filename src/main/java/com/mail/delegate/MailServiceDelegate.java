package com.mail.delegate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mail.common.MailConstants;
import com.mail.common.StringUtil;
import com.mail.dao.SendMailDao;
import com.mail.dao.TemplateDao;
import com.mail.entity.Sendmail;
import com.mail.entity.VoQuery;

@Service
public class MailServiceDelegate {
	@Autowired
	private TemplateDao templateDao;
	@Autowired
	private SendMailDao sendMailDao;
	@Autowired
	private TemplateEngine templateEngine;
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	public int sendBatchMail(String toMail, String mailCategory,
			String title, String userName, String emailContent) throws Exception {
		int status =0;
		//String emailContent = "";
		HtmlEmail htmlEmail = new HtmlEmail();
		try {
			//Context data = new Context();
			//data.setVariable("user_name", userName);
			//emailContent = templateEngine.process(mailCategory, data);
			htmlEmail.setHostName("smtp.gmail.com");
			htmlEmail.addTo(toMail, "");
			htmlEmail.setAuthenticator(new DefaultAuthenticator("usitripit1@gmail.com",
					"usitripbdusa"));
			htmlEmail.setFrom("DoNotReply@qq.com", "TestMail");
			htmlEmail.setSubject(title);
			// htmlEmail.setSSLOnConnect(true);
			htmlEmail.setStartTLSEnabled(true);
			htmlEmail.setSmtpPort(587);
			htmlEmail.setCharset("utf-8");
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
			sendmail.setUser_name(userName==null?"":userName);
			sendMailDao.insert(sendmail);
		}
		return status;
	}
	public int sendMail(String toMail, String mailCategory,
			String title, String userName, Context data) throws Exception {
		int status =0;
		String emailContent = "";
		HtmlEmail htmlEmail = new HtmlEmail();
		try {
			//Context data = new Context();
			//data.setVariable("user_name", userName);
			data.setVariable("unsubscribe", MailConstants.UNSUBSCRIBE_URL+toMail);
			emailContent = templateEngine.process(mailCategory, data);
			htmlEmail.setHostName("smtp.gmail.com");
			htmlEmail.addTo(toMail, "");
			htmlEmail.setAuthenticator(new DefaultAuthenticator("usitripit1@gmail.com",
					"usitripbdusa"));
			htmlEmail.setFrom("DoNotReply@qq.com", "TestMail");
			htmlEmail.setSubject(title);
			// htmlEmail.setSSLOnConnect(true);
			htmlEmail.setStartTLSEnabled(true);
			htmlEmail.setSmtpPort(587);
			htmlEmail.setCharset("utf-8");
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
			sendmail.setUser_name(userName==null?"":userName);
			sendMailDao.insert(sendmail);
		}
		return status;
	}
	
	public Map<String,Object> sendMailTest(String toMail, String mailCategory,
			String title, String userName, Context data) throws Exception {
		Map<String,Object> ret = new HashMap<String,Object>();
		int status =0;
		String emailContent = "";
		HtmlEmail htmlEmail = new HtmlEmail();
		try {
			//Context data = new Context();
			//data.setVariable("user_name", userName);
			//ThymeleafTemplateResolver templateResolver = new ThymeleafTemplateResolver();
			//templateEngine.setTemplateResolver(templateResolver);

			String code = StringUtil.Encoder(toMail, "");
			data.setVariable("unsubscribe", MailConstants.UNSUBSCRIBE_URL+toMail+"?code="+code);
			emailContent = templateEngine.process(mailCategory, data);
			htmlEmail.setHostName("smtp.gmail.com");
			htmlEmail.addTo(toMail, userName);
			htmlEmail.setAuthenticator(new DefaultAuthenticator("usitripit1@gmail.com",
					"usitripbdusa"));
			htmlEmail.setFrom("DoNotReply@qq.com", "MailSystemTest");
			htmlEmail.setSubject(title);
			// htmlEmail.setSSLOnConnect(true);
			htmlEmail.setStartTLSEnabled(true);
			htmlEmail.setSmtpPort(587);
			htmlEmail.setCharset("utf-8");
			// embed the image and get the content id

			htmlEmail.setHtmlMsg(emailContent);
			// send the email
			htmlEmail.send();
			ret.put("status", "success");

		} catch (EmailException e) {
			logger.fatal("Failed to send email. Got EmailException:"+e.getMessage(),e);
			status=1;
			ret.put("Error:", e.getMessage());
		} catch (Exception e) {
			logger.fatal("Failed to send email. Got Exception:"+e.getMessage(),e);
			status=1;
			ret.put("Error:", e.getMessage());
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
			sendmail.setUser_name(userName==null?"":userName);
			sendMailDao.insert(sendmail);
		}
		return ret;
	}
	
	private SubscriberService subscriberService;
	
	@Autowired
	public void setSubscriberService(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	public void sendMail(VoQuery voQuery) throws Exception {
		logger.info("Ready to send emails...");
		List<String> emails = subscriberService.getEmails(MailConstants.SUBSCRIBED);
		for (String emailAddr :emails) {
			if (Pattern.matches(MailConstants.RULE_EMAIL, emailAddr)) {
				String code = StringUtil.Encoder(emailAddr, "");
				voQuery.getData().setVariable("unsubscribe", MailConstants.UNSUBSCRIBE_URL+emailAddr+"?code="+code);
				String emailContent = templateEngine.process(voQuery.getMailCategory(), voQuery.getData());
				sendBatchMail(emailAddr, voQuery.getMailCategory(), voQuery.getTitle(), "", emailContent);
			}
		}
		logger.info("Complete to send emails...");
	}
}
