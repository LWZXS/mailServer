package com.mail.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.mail.common.EmailUtils;
import com.mail.delegate.MailServiceDelegate;
import com.mail.entity.VoQuery;




@Service
@Path("/mail")
public class RestMailService {

	@Autowired
	private MailServiceDelegate mailServiceDelegate;
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@GET
	@Path("/sendmail")
	@Produces("application/json")
	public Response sendmail(@QueryParam("token") String token,@QueryParam("mail") String mail)throws Exception {

		Context data = new Context();
		data.setVariable("user_name", "Chris");
		String toMail=mail;
		String mailCategory="CreateAccountEmail";
		String title="Test";
		String userName="Chris Zhang";
		mailServiceDelegate.sendMail(toMail, mailCategory, title, userName, data);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "true");
		return Response.status(Status.OK).entity(result).build();

	}
	
	@GET
	@Path("/sendMailTest")
	@Produces("application/json")
	public Response sendMailTest(@QueryParam("token") String token,@QueryParam("mail") String mail)throws Exception {

		Context data = new Context();
		data.setVariable("user_name", "Chris");
		String toMail=mail;
		String mailCategory="CreateAccount";
		String title="Test";
		String userName="Chris Zhang";
		mailServiceDelegate.sendMailTest(toMail, mailCategory, title, userName, data);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "true");
		return Response.status(Status.OK).entity(result).build();

	}
	
	@GET
	@Path("/testEmail")
	@Produces("application/json")
	public Response testScheduledEmail() throws SchedulerException {
		System.out.println("testScheduledEmail!!!");
		VoQuery voQuery = new VoQuery();
		Context data = new Context();
		data.setVariable("user_name", "Jim");
		voQuery.setData(data);
		voQuery.setToMail("guojian0808@126.com");
		voQuery.setMailCategory("CreateAccount");
		voQuery.setTitle("Test Scheduled Email");
		voQuery.setUserName("Jim");
//		voQuery.setEmailList(subscriberService.getEmails(1));
		
		EmailUtils.buildEmailScheduler(voQuery);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "true");
		return Response.status(Status.OK).entity(result).build();
	}

}