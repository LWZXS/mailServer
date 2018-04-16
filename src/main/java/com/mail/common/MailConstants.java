package com.mail.common;

public class MailConstants {
//	public static final String SCEDULEDEMAILINTERVAL = "0 0 6 1/1 * ? *"; //every day at 6:00
	//public static final String SCEDULEDEMAILINTERVAL = "0 0 6 ? * TUE,FRI *";//every tue, fri at 6:00
	public static final String SCEDULEDEMAILINTERVAL = "0 0 17 1/1 * ? *";//every day at 17:00
//	public static final String SCEDULEDEMAILINTERVAL = "0 0/1 * 1/1 * ? *";//every one minute
//	public static final String SCEDULEDEMAILINTERVAL = "0/5 * * * * ?";//every one second
	
	public static final Integer SUBSCRIBED = 1;
	public static final String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	
	public static final Long BEFORHOLIDAY = 7L;
	public static final String TARGETDAY1 = "TUESDAY";
	public static final String TARGETDAY2 = "FRIDAY";

	public static final String TUESTEMPLATE = "TUESDAYMail";
	public static final String FRITEMPLATE = "FRIDAYMail";
	public static final String UNSUBSCRIBE_URL = "http://hotelstest.usitrip.com/subscribe/cancel/";
	

	public static final String HOST_NAME = "smtp.exmail.qq.com";
	public static final String SENDER_ADR = "hotel-order@usitrip.com";
	public static final String SENDER_PAS = "117UsiHorde";
	public static final Integer SMTP_PORT = 587;
	public static final String FROM_NAME = "UsitripMailSystem";
}
