package com.mail.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mail.dao.TemplateDao;
import com.mail.delegate.MailServiceDelegate;




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

}