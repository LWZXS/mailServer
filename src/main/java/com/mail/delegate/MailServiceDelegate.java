package com.mail.delegate;

import com.mail.common.MailConstants;
import com.mail.common.StringUtil;
import com.mail.dao.SendMailDao;
import com.mail.entity.Sendmail;
import com.mail.entity.Template;
import com.mail.entity.VoQuery;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class MailServiceDelegate {
    @Autowired
    private SendMailDao sendMailDao;
    @Autowired
    private TemplateEngine templateEngine;
    private Logger logger = LogManager.getLogger(this.getClass().getName());

    public int sendBatchMail(String toMail, String mailCategory,
                             String title, String userName, String emailContent) throws Exception {
        int status = 0;
        //String emailContent = "";
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            //Context data = new Context();
            //data.setVariable("user_name", userName);
            //emailContent = templateEngine.process(mailCategory, data);
            htmlEmail.setHostName(MailConstants.HOST_NAME);
            htmlEmail.addTo(toMail, userName);
            htmlEmail.setAuthenticator(new DefaultAuthenticator(MailConstants.SENDER_ADR,
                    MailConstants.SENDER_PAS));
            htmlEmail.setFrom(MailConstants.SENDER_ADR, MailConstants.FROM_NAME);
            htmlEmail.setSubject(title);
            // htmlEmail.setSSLOnConnect(true);
            htmlEmail.setStartTLSEnabled(true);
            htmlEmail.setSmtpPort(MailConstants.SMTP_PORT);
            htmlEmail.setCharset("utf-8");
            // embed the image and get the content id

            htmlEmail.setHtmlMsg(emailContent);

            // send the email
            htmlEmail.send();

        } catch (EmailException e) {
            logger.fatal("Failed to send email. Got EmailException:" + e.getMessage(), e);
            status = 1;
        } catch (Exception e) {
            logger.fatal("Failed to send email. Got Exception:" + e.getMessage(), e);
            status = 1;
        } finally {
            Date now = new Date();
            Long time = now.getTime() / 1000;
            Sendmail sendmail = new Sendmail();
            sendmail.setContent(emailContent);
            sendmail.setMail_category(mailCategory);
            sendmail.setMail_title(title);
            sendmail.setMail_to(toMail);
            sendmail.setSend_time(Integer.valueOf(time.toString()));
            sendmail.setCreate_time(new Timestamp(now.getTime()));
            sendmail.setStatus(status);
            sendmail.setUser_name(userName == null ? "" : userName);
            sendMailDao.insert(sendmail);
        }
        return status;
    }

    public int sendMail(String toMail, String mailCategory,
                        String title, String userName, Context data) throws Exception {
        int status = 0;
        String emailContent = "";
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            //Context data = new Context();
            //data.setVariable("user_name", userName);
            data.setVariable("unsubscribe", MailConstants.UNSUBSCRIBE_URL + toMail);
            emailContent = templateEngine.process(mailCategory, data);
            htmlEmail.setHostName(MailConstants.HOST_NAME);
            htmlEmail.addTo(toMail, userName);
            htmlEmail.setAuthenticator(new DefaultAuthenticator(MailConstants.SENDER_ADR,
                    MailConstants.SENDER_PAS));
            htmlEmail.setFrom(MailConstants.SENDER_ADR, MailConstants.FROM_NAME);
            htmlEmail.setSubject(title);
            // htmlEmail.setSSLOnConnect(true);
            htmlEmail.setStartTLSEnabled(true);
            htmlEmail.setSmtpPort(MailConstants.SMTP_PORT);
            htmlEmail.setCharset("utf-8");
            // embed the image and get the content id

            htmlEmail.setHtmlMsg(emailContent);

            // send the email
            htmlEmail.send();

        } catch (EmailException e) {
            logger.fatal("Failed to send email");
            logger.fatal(e.toString());
            status = 1;
        } finally {
            Date now = new Date();
            Long time = now.getTime() / 1000;
            Sendmail sendmail = new Sendmail();
            sendmail.setContent(emailContent);
            sendmail.setMail_category(mailCategory);
            sendmail.setMail_title(title);
            sendmail.setMail_to(toMail);
            sendmail.setSend_time(Integer.valueOf(time.toString()));
            sendmail.setCreate_time(new Timestamp(now.getTime()));
            sendmail.setStatus(status);
            sendmail.setUser_name(userName == null ? "" : userName);
            sendMailDao.insert(sendmail);
        }
        return status;
    }

    public Map<String, Object> sendMailTest(String toMail, String mailCategory,
                                            String title, String userName, Context data) throws Exception {
        Map<String, Object> ret = new HashMap<String, Object>();
        int status = 0;
        String emailContent = "";
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            //Context data = new Context();
            //data.setVariable("user_name", userName);
            //ThymeleafTemplateResolver templateResolver = new ThymeleafTemplateResolver();
            //templateEngine.setTemplateResolver(templateResolver);

            String code = StringUtil.Encoder(toMail, "");
            data.setVariable("unsubscribe", MailConstants.UNSUBSCRIBE_URL + toMail + "?code=" + code);
            emailContent = templateEngine.process(mailCategory, data);
            htmlEmail.setHostName(MailConstants.HOST_NAME);
            htmlEmail.addTo(toMail, userName);
            htmlEmail.setAuthenticator(new DefaultAuthenticator(MailConstants.SENDER_ADR,
                    MailConstants.SENDER_PAS));
            htmlEmail.setFrom(MailConstants.SENDER_ADR, MailConstants.FROM_NAME);
            htmlEmail.setSubject(title);
            // htmlEmail.setSSLOnConnect(true);
            htmlEmail.setStartTLSEnabled(true);
            htmlEmail.setSmtpPort(MailConstants.SMTP_PORT);
            htmlEmail.setCharset("utf-8");
            // embed the image and get the content id

            htmlEmail.setHtmlMsg(emailContent);
            // send the email
            htmlEmail.send();
            ret.put("status", "success");

        } catch (EmailException e) {
            logger.fatal("Failed to send email. Got EmailException:" + e.getMessage(), e);
            status = 1;
            ret.put("Error:", e.getMessage());
        } catch (Exception e) {
            logger.fatal("Failed to send email. Got Exception:" + e.getMessage(), e);
            status = 1;
            ret.put("Error:", e.getMessage());
        } finally {
            Date now = new Date();
            Long time = now.getTime() / 1000;
            Sendmail sendmail = new Sendmail();
            sendmail.setContent(emailContent);
            sendmail.setMail_category(mailCategory);
            sendmail.setMail_title(title);
            sendmail.setMail_to(toMail);
            sendmail.setSend_time(Integer.valueOf(time.toString()));
            sendmail.setCreate_time(new Timestamp(now.getTime()));
            sendmail.setStatus(status);
            sendmail.setUser_name(userName == null ? "" : userName);
            sendMailDao.insert(sendmail);
        }
        return ret;
    }

    public Map<String, Object> sendEBookingMail(String toMail, String mailCategory,
                                                String title, String userName, Context data) throws Exception {
        Map<String, Object> ret = new HashMap<String, Object>();
        int status = 0;
        String emailContent = "";
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            //Context data = new Context();
            //data.setVariable("user_name", userName);
            //ThymeleafTemplateResolver templateResolver = new ThymeleafTemplateResolver();
            //templateEngine.setTemplateResolver(templateResolver);

            String code = StringUtil.Encoder(toMail, "");
            data.setVariable("unsubscribe", MailConstants.UNSUBSCRIBE_URL + toMail + "?code=" + code);
            emailContent = templateEngine.process(mailCategory, data);
            htmlEmail.setHostName(MailConstants.HOST_NAME);
            htmlEmail.addTo(toMail, userName);
            String[] emails = {"2355652773@qq.com", "2355652779@qq.com", "2355652781@qq.com", "2355652791@qq.com", "2355906871@qq.com", "2355958065@qq.com", "2853759750@qq.com", "2853759768@qq.com"};
            htmlEmail.addCc(emails);
            htmlEmail.setAuthenticator(new DefaultAuthenticator(MailConstants.SENDER_ADR,
                    MailConstants.SENDER_PAS));
            htmlEmail.setFrom(MailConstants.SENDER_ADR, MailConstants.FROM_NAME);
            htmlEmail.setSubject(title);
            // htmlEmail.setSSLOnConnect(true);
            htmlEmail.setStartTLSEnabled(true);
            htmlEmail.setSmtpPort(MailConstants.SMTP_PORT);
            htmlEmail.setCharset("utf-8");
            // embed the image and get the content id

            htmlEmail.setHtmlMsg(emailContent);
            // send the email
            htmlEmail.send();
            ret.put("status", "success");

        } catch (EmailException e) {
            logger.fatal("Failed to send email. Got EmailException:" + e.getMessage(), e);
            status = 1;
            ret.put("Error:", e.getMessage());
        } catch (Exception e) {
            logger.fatal("Failed to send email. Got Exception:" + e.getMessage(), e);
            status = 1;
            ret.put("Error:", e.getMessage());
        } finally {
            Date now = new Date();
            Long time = now.getTime() / 1000;
            Sendmail sendmail = new Sendmail();
            sendmail.setContent(emailContent);
            sendmail.setMail_category(mailCategory);
            sendmail.setMail_title(title);
            sendmail.setMail_to(toMail);
            sendmail.setSend_time(Integer.valueOf(time.toString()));
            sendmail.setCreate_time(new Timestamp(now.getTime()));
            sendmail.setStatus(status);
            sendmail.setUser_name(userName == null ? "" : userName);
            sendMailDao.insert(sendmail);
        }
        return ret;
    }

    public Map<String, Object> sendMail(Map<String, String> map, Context data) {
        Map<String, Object> ret = new HashMap<String, Object>();
        int status = 0;
        String emailContent = "";
        HtmlEmail htmlEmail = new HtmlEmail();
        try {
            emailContent = templateEngine.process(map.get("template"), data);
            htmlEmail.setHostName(map.get("host"));
            htmlEmail.addTo(map.get("recipientAddress"), map.get("recipientName"));
            htmlEmail.addBcc(MailConstants.MAIL_UU1_CC);
            htmlEmail.setAuthenticator(new DefaultAuthenticator(map.get("senderAddress"), map.get("senderPwd")));
            htmlEmail.setFrom(map.get("senderAddress"), map.get("senderName"));
            htmlEmail.setSubject(map.get("title"));
            // htmlEmail.setSSLOnConnect(true);
            htmlEmail.setStartTLSEnabled(true);
            htmlEmail.setSmtpPort(Integer.parseInt(map.get("port")));
            htmlEmail.setCharset("utf-8");

            htmlEmail.setHtmlMsg(emailContent);
            htmlEmail.send();
            ret.put("status", "success");

        } catch (EmailException e) {
            logger.fatal("Failed to send email. Got EmailException:" + e.getMessage(), e);
            status = 1;
            ret.put("Error:", e.getMessage());
        } catch (Exception e) {
            logger.fatal("Failed to send email. Got Exception:" + e.getMessage(), e);
            status = 1;
            ret.put("Error:", e.getMessage());
        } finally {
            Date now = new Date();
            long time = now.getTime() / 1000;
            Sendmail sendmail = new Sendmail();
            sendmail.setContent(emailContent);
            sendmail.setMail_category(map.get("template"));
            sendmail.setMail_title(map.get("title"));
            sendmail.setMail_to(map.get("recipientAddress"));
            sendmail.setSend_time(Integer.valueOf(Long.toString(time)));
            sendmail.setCreate_time(new Timestamp(now.getTime()));
            sendmail.setStatus(status);
            sendmail.setUser_name(map.get("recipientName") == null ? "" : map.get("recipientName"));
            sendMailDao.insert(sendmail);
        }
        return ret;
    }

    private MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void sendMail(VoQuery voQuery) throws Exception {
        logger.info("Ready to send emails...");
        List<String> emails = mailService.getEmails(MailConstants.SUBSCRIBED);
        for (String emailAddr : emails) {
            if (emailAddr != null && !emailAddr.trim().isEmpty() && Pattern.matches(MailConstants.RULE_EMAIL, emailAddr)) {
                String code = StringUtil.Encoder(emailAddr, "");
                voQuery.getData().setVariable("unsubscribe", MailConstants.UNSUBSCRIBE_URL + emailAddr + "?code=" + code);
                String emailContent = templateEngine.process(voQuery.getMailCategory(), voQuery.getData());
                sendBatchMail(emailAddr, voQuery.getMailCategory(), voQuery.getTitle(), "", emailContent);
            }
        }
        logger.info("Complete to send emails...");
    }

    public Template selectTemplateBySubject(String template_subject) {
        return mailService.selectTemplateBySubject(template_subject);
    }
}
