package com.mail.common;

public class MailConstants {
//	public static final String SCEDULEDEMAILINTERVAL = "0 0 6 1/1 * ? *"; //every day at 6:00
//	public static final String SCEDULEDEMAILINTERVAL = "0 0 6 ? * TUE,FRI *";//every tue, fri at 6:00
	public static final String SCEDULEDEMAILINTERVAL = "0 0/1 * 1/1 * ? *";//every one minute
//	public static final String SCEDULEDEMAILINTERVAL = "0/5 * * * * ?";//every one second
	
	public static final Integer SUBSCRIBED = 1;
	public static final String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	
	public static final Long BEFORHOLIDAY = 7L;
	public static final String TARGETDAY1 = "TUESDAY";
	public static final String TARGETDAY2 = "WEDNESDAY";

	public static final String TUESTEMPLATE = "TestMail";
	public static final String FRITEMPLATE = "TestMail";
}
