package com.mail.rest;

import com.mail.common.EmailUtils;
import com.mail.common.MailConstants;
import com.mail.common.StringUtil;
import com.mail.delegate.MailService;
import com.mail.delegate.MailServiceDelegate;
import com.mail.entity.VoQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
@Service
@Path("/mail")
public class RestMailService {

	@Autowired
	private MailServiceDelegate mailServiceDelegate;
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private MailService mailService;
	@Autowired
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	@GET
	@Path("/sendmail")
	@Produces("application/json")
	public Response sendmail(@QueryParam("token") String token,@QueryParam("mail") String mail,@QueryParam("title") String title,@QueryParam("mailCategory") String mailCategory)throws Exception {

		Context data = new Context();
		data.setVariable("user_name", "Chris");
		String toMail=mail;
		//String mailCategory="CreateAccountEmail";
		//String title="Test";
		String userName="Chris Zhang";
		mailServiceDelegate.sendMail(toMail, mailCategory, title, userName, data);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "true");
		return Response.status(Status.OK).entity(result).build();

	}
	
	@GET
	@Path("/sendMailTest")
	@Produces("application/json")
	public Response sendMailTest(@QueryParam("token") String token,@QueryParam("mail") String mail,@QueryParam("title") String title,@QueryParam("mailCategory") String mailCategory)throws Exception {

		Context data = new Context();
		data.setVariable("user_name", "Chris");
		String toMail=mail;
		//String mailCategory="CreateAccount";
		//String title="Test";
		String userName="Chris Zhang";
		Map<String, Object> result = mailServiceDelegate.sendMailTest(toMail, mailCategory, title, userName, data);
		
		return Response.status(Status.OK).entity(result).build();

	}
	
	@GET
	@Path("/testEmail")
	@Produces("application/json")
	public Response testScheduledEmail() throws SchedulerException {
		VoQuery voQuery = new VoQuery();
		EmailUtils.buildEmailScheduler(voQuery);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "true");
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/unsubscribeEmail/{emailAddr}")
	@Produces("application/json")
	public Response unsubscribeEmail(@PathParam("emailAddr") String emailAddr,@QueryParam("code") String code) {
		String msg;
		logger.info("emailAddr:"+emailAddr);
		logger.info("code:"+code);
		try {
			String code2 = StringUtil.Encoder(emailAddr, "");
			if(!code2.equals(code)){
				msg = "fail";
				logger.fatal("Don't have permit to unsubscribe this email..."+emailAddr);
			}else{
				if (mailService.unsubscribeEmail(emailAddr) == 1) {
					//msg = "You have successfully unsubscribed!";
					msg = "success";
				}else {
					//msg = "Unsubscription failed. Please contact the administrator!";
					msg = "fail";
					logger.warn("Email address does not exist...");
				}
			}
		} catch (Exception e) {
			msg = "fail";
			logger.fatal("unsub failed...",e);
		}

		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", msg);
		return Response.status(Status.OK).entity(result).build();
	}

	@POST
	@Path("/sendNewOrderEmail")
	@Produces("application/json")
	public Response sendNewOrderEmail(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> dataMap = (Map<String, Object>) paramMap.get("data");
		String toMail = (String) dataMap.get("toMail");
		String hotelName = (String) dataMap.get("hotelName");
		String orderId = String.valueOf(dataMap.get("orderId"));

		Context data = new Context();
		data.setVariable("hotelName", hotelName);
		data.setVariable("orderId", orderId);
		data.setVariable("ebUrl", MailConstants.EBURL);

		mailServiceDelegate.sendEBookingMail(toMail, MailConstants.NEWORDERTEMPLATE, mailServiceDelegate.selectTemplateBySubject(MailConstants.NEWORDERTEMPLATE).getTemplate_title(), "usitrip", data);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "true");
		return Response.status(Status.OK).entity(result).build();
	}

	@POST
	@Path("/sendSoldOutEmail")
	@Produces("application/json")
	public Response sendSoldOutEmail(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> dataMap = (Map<String, Object>) paramMap.get("data");
		String toMail = (String) dataMap.get("toMail");
		String hotelName = (String) dataMap.get("hotelName");

		Context data = new Context();
		data.setVariable("hotelName", hotelName);
		data.setVariable("ebUrl", MailConstants.EBURL);

		mailServiceDelegate.sendEBookingMail(toMail, MailConstants.SOLDOUTTEMPLATE, mailServiceDelegate.selectTemplateBySubject(MailConstants.SOLDOUTTEMPLATE).getTemplate_title(), "usitrip", data);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "true");
		return Response.status(Status.OK).entity(result).build();
	}

	@GET
	@Path("/test")
	@Produces("application/json")
	public void test() {
		System.out.println("RestMailService.test......");
	}
}