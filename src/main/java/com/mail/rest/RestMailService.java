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
import org.junit.Test;
import org.thymeleaf.context.Context;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public Response sendmail(@QueryParam("token") String token, @QueryParam("mail") String mail, @QueryParam("title") String title, @QueryParam("mailCategory") String mailCategory) throws Exception {

        Context data = new Context();
        data.setVariable("user_name", "Chris");
        String toMail = mail;
        //String mailCategory="CreateAccountEmail";
        //String title="Test";
        String userName = "Chris Zhang";
        mailServiceDelegate.sendMail(toMail, mailCategory, title, userName, data);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "true");
        return Response.status(Status.OK).entity(result).build();

    }

    @GET
    @Path("/sendMailTest")
    @Produces("application/json")
    public Response sendMailTest(@QueryParam("token") String token, @QueryParam("mail") String mail, @QueryParam("title") String title, @QueryParam("mailCategory") String mailCategory) throws Exception {

        Context data = new Context();
        data.setVariable("user_name", "Chris");
        String toMail = mail;
        //String mailCategory="CreateAccount";
        //String title="Test";
        String userName = "Chris Zhang";
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
    public Response unsubscribeEmail(@PathParam("emailAddr") String emailAddr, @QueryParam("code") String code) {
        String msg;
        logger.info("emailAddr:" + emailAddr);
        logger.info("code:" + code);
        try {
            String code2 = StringUtil.Encoder(emailAddr, "");
            if (!code2.equals(code)) {
                msg = "fail";
                logger.fatal("Don't have permit to unsubscribe this email..." + emailAddr);
            } else {
                if (mailService.unsubscribeEmail(emailAddr) == 1) {
                    //msg = "You have successfully unsubscribed!";
                    msg = "success";
                } else {
                    //msg = "Unsubscription failed. Please contact the administrator!";
                    msg = "fail";
                    logger.warn("Email address does not exist...");
                }
            }
        } catch (Exception e) {
            msg = "fail";
            logger.fatal("unsub failed...", e);
        }


        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", msg);
        return Response.status(Status.OK).entity(result).build();
    }

    @POST
    @Path("/sendNewOrderEmail")
    @Produces("application/json")
    public Response sendNewOrderEmail(Map<String, Object> paramMap) {
        return sendEmail4Order(paramMap, "NEWORDERTEMPLATE");
    }

    @POST
    @Path("/sendSoldOutEmail")
    @Produces("application/json")
    public Response sendSoldOutEmail(Map<String, Object> paramMap) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, Object> dataMap = (Map<String, Object>) paramMap.get("data");
            String toMail = (String) dataMap.get("toMail");
            String hotelName = (String) dataMap.get("hotelName");
            Context data = new Context();
            data.setVariable("hotelName", hotelName);
            data.setVariable("ebUrl", MailConstants.URL_EB);
            mailServiceDelegate.sendEBookingMail(toMail, MailConstants.SOLDOUTTEMPLATE_EB_EN, mailServiceDelegate.selectTemplateBySubject(MailConstants.SOLDOUTTEMPLATE_EB_EN).getTemplate_title(), "usitrip", data);
            result.put("status", "true");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "error(" + e.getMessage() + ")");
        }
        return Response.status(Status.OK).entity(result).build();
    }

    @POST
    @Path("/sendCancelledEmail")
    @Produces("application/json")
    public Response sendCancelledEmail(Map<String, Object> paramMap) {
        return sendEmail4Order(paramMap, "CANCELLEDORDERTEMPLATE");
    }

    private Response sendEmail4Order(Map<String, Object> paramMap, String orderTemplatePrefix) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (paramMap.containsKey("data") && paramMap.get("data") != null) {
            String orderTemplate = null;
            String toMail = null;
            Context data = new Context();

            Map<String, Object> dataMap = (Map<String, Object>) paramMap.get("data");
            Set<String> keySet = dataMap.keySet();
            for (String s : keySet) {
                data.setVariable(s, String.valueOf(dataMap.get(s)));
            }
            try {
                String language = "EN";
                if (dataMap.containsKey("host") && dataMap.get("host") != null) {
                    String host = String.valueOf(dataMap.get("host")).toUpperCase();
                    language = String.valueOf(dataMap.get("language")).toUpperCase();
                    Integer partner = Integer.parseInt(dataMap.get("partner").toString());
                    String end = partner == 11087 ? host : "117BOOK";

                    Map<String, String> map = new HashMap<String, String>();
                    orderTemplate = String.valueOf(MailConstants.class.getDeclaredField(orderTemplatePrefix + "_" + host + "_" + language).get(null));
                    map.put("template", orderTemplate);

                    map.put("host", String.valueOf(MailConstants.class.getDeclaredField("MAIL_HOST_" + end).get(null)));
                    map.put("port", String.valueOf(MailConstants.class.getDeclaredField("MAIL_PORT_" + end).get(null)));
                    map.put("senderName", host.toLowerCase().substring(0, 1).toUpperCase() + host.toLowerCase().substring(1) + " - Hotel");
                    map.put("senderAddress", String.valueOf(MailConstants.class.getDeclaredField("MAIL_USERNAME_" + end).get(null)));
                    map.put("senderPwd", String.valueOf(MailConstants.class.getDeclaredField("MAIL_PASSWORD_" + end).get(null)));
                    map.put("encryption", String.valueOf(MailConstants.class.getDeclaredField("MAIL_ENCRYPTION_" + end).get(null)));

                    String recipientAddr = String.valueOf(dataMap.get("bookerEmail"));
                    String recipientName = String.valueOf(dataMap.get("bookerName"));
                    map.put("recipientAddress", recipientAddr);
                    map.put("recipientName", recipientName);

                    String title = "";
                    String status = String.valueOf(dataMap.get("status"));
                    if (status.contains("CONFIRMED") || status.contains("确认")) {
                        title = String.valueOf(MailConstants.class.getDeclaredField(orderTemplatePrefix + "_TITLEC_" + host + "_" + language).get(null));
                    } else {
                        title = String.valueOf(MailConstants.class.getDeclaredField(orderTemplatePrefix + "_TITLEO_" + host + "_" + language).get(null));
                    }
                    map.put("title", title.replaceAll("[*]{3}", String.valueOf(dataMap.get("orderId"))));

                    result = mailServiceDelegate.sendMail(map, data);
                } else {
                    toMail = String.valueOf(dataMap.get("toMail"));
                    data.setVariable("ebUrl", MailConstants.URL_EB);
                    orderTemplate = String.valueOf(MailConstants.class.getDeclaredField(orderTemplatePrefix + "_EB_" + language).get(null));
                    result = mailServiceDelegate.sendEBookingMail(toMail, orderTemplate, mailServiceDelegate.selectTemplateBySubject(orderTemplate).getTemplate_title(), "usitrip", data);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                result.put("status", "error(" + e.getMessage() + ")");
            }
        } else {
            result.put("status", "false(parameter format is incorrect)");
        }
        return Response.status(Status.OK).entity(result).build();
    }

    @GET
    @Path("/test")
    @Produces("application/json")
    public void test() throws Exception {
        Context data = new Context();
        data.setVariable("status", "status test status test");
        data.setVariable("orderId", 123456);
        data.setVariable("voucher", "http://ebooking.117book.com");
        mailServiceDelegate.sendMailTest("2355652781@qq.com", "usitripNewOrderEN", "test", "usitrip", data);
    }
}