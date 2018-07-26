package com.mail.common;

public class MailConstants {
//	public static final String SCEDULEDEMAILINTERVAL = "0 0 6 1/1 * ? *"; //every day at 6:00
	//public static final String SCEDULEDEMAILINTERVAL = "0 0 6 ? * TUE,FRI *";//every tue, fri at 6:00
	public static final String SCEDULEDEMAILINTERVAL = "0 45 17 * * ? *";//every day at 17:00
//	public static final String SCEDULEDEMAILINTERVAL = "0 0/1 * 1/1 * ? *";//every one minute
//	public static final String SCEDULEDEMAILINTERVAL = "0/5 * * * * ?";//every one second
	
	public static final Integer SUBSCRIBED = 1;
	public static final String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	
	public static final Long BEFORHOLIDAY = 7L;
	public static final String TARGETDAY1 = "TUESDAY";
	public static final String TARGETDAY2 = "FRIDAY";

	public static final String TUESTEMPLATE = "TUESDAYMail";
	public static final String FRITEMPLATE = "FRIDAYMail";
	public static final String UNSUBSCRIBE_URL = "http://hotel.usitrip.com/subscribe/cancel/";
	

	public static final String HOST_NAME = "smtp.exmail.qq.com";
	public static final String SENDER_ADR = "hotel-order@usitrip.com";
	public static final String SENDER_PAS = "117UsiHorde";
	public static final Integer SMTP_PORT = 587;
	public static final String FROM_NAME = "走四方旅游网";

	public static final String URL_EB = "http://ebooking.117book.com";
	public static final String NEWORDERTEMPLATE_EB_EN = "ebookingNewOrder";
	public static final String SOLDOUTTEMPLATE_EB = "ebookingSoldOut";

	//USITOUR
	public static final String NEWORDERTEMPLATE_USITOUR_EN = "usitourNewOrderEN";
	public static final String CANCELLEDORDERTEMPLATE_USITOUR_EN = "usitourCancelledOrderEN";
	public static final String NEWORDERTEMPLATE_TITLEC_USITOUR_EN = "Usitour Hotel Confirmation - (Booking#***)";
	public static final String NEWORDERTEMPLATE_TITLEO_USITOUR_EN = "Usitour Hotel is in processing - (Booking#***)";

	public static final String CANCELLEDORDERTEMPLATE_TITLEO_USITOUR_EN = "Usitour Hotel Cancellation - (Booking#***)";

	public static final String MAIL_HOST_USITOUR = "smtp.exmail.qq.com";
	public static final Integer MAIL_PORT_USITOUR = 25;
	public static final String MAIL_USERNAME_USITOUR = "hotel-order@usitrip.com";
	public static final String MAIL_PASSWORD_USITOUR = "117UsiHorde";
	public static final String MAIL_ENCRYPTION_USITOUR = "tls";

	public static final String MAIL_UU1_CC = "2355958065@qq.com";
	//USITRIP
	public static final String NEWORDERTEMPLATE_USITRIP_EN = "usitripNewOrderEN";
	public static final String NEWORDERTEMPLATE_USITRIP_CN = "usitripNewOrderCN";
	public static final String CANCELLEDORDERTEMPLATE_USITRIP_EN = "usitripCancelledOrderEN";
	public static final String CANCELLEDORDERTEMPLATE_USITRIP_CN = "usitripCancelledOrderCN";
	public static final String NEWORDERTEMPLATE_TITLEC_USITRIP_EN = "Usitrip Hotel Confirmation - (Booking#***)";
	public static final String NEWORDERTEMPLATE_TITLEO_USITRIP_EN = "Usitrip Hotel is in processing - (Booking#***)";
	public static final String NEWORDERTEMPLATE_TITLEC_USITRIP_CN = "Usitrip走四方酒店预订确认 - (订单号***)";
	public static final String NEWORDERTEMPLATE_TITLEO_USITRIP_CN = "Usitrip走四方酒店预订正在处理 - (订单号***)";

	public static final String CANCELLEDORDERTEMPLATE_TITLEO_USITRIP_EN = "Usitrip Hotel Cancellation - (Booking#***)";
	public static final String CANCELLEDORDERTEMPLATE_TITLEO_USITRIP_CN = "Usitrip走四方酒店取消确认 - (订单号***)";

	public static final String MAIL_HOST_USITRIP = "smtp.exmail.qq.com";
	public static final Integer MAIL_PORT_USITRIP = 25;
	public static final String MAIL_USERNAME_USITRIP = "hotel-order@usitrip.com";
	public static final String MAIL_PASSWORD_USITRIP = "117UsiHorde";
	public static final String MAIL_ENCRYPTION_USITRIP = "tls";

	//117BOOK/
	public static final String NEWORDERTEMPLATE_117BOOK_EN = "117NewOrderEN";
	public static final String NEWORDERTEMPLATE_117BOOK_CN = "117NewOrderCN";
	public static final String CANCELLEDORDERTEMPLATE_117BOOK_EN = "117CancelledOrderEN";
	public static final String CANCELLEDORDERTEMPLATE_117BOOK_CN = "117CancelledOrderCN";
	public static final String NEWORDERTEMPLATE_TITLEC_117BOOK_EN = "117book Hotel Confirmation - (Booking#***)";
	public static final String NEWORDERTEMPLATE_TITLEO_117BOOK_EN = "117book Hotel is in processing - (Booking#***)";
	public static final String NEWORDERTEMPLATE_TITLEC_117BOOK_CN = "117book要趣订酒店预订确认 - (订单号***)";
	public static final String NEWORDERTEMPLATE_TITLEO_117BOOK_CN = "117book要趣订酒店预订正在处理 - (订单号***)";

	public static final String CANCELLEDORDERTEMPLATE_TITLEO_117BOOK_EN = "117book Hotel Cancellation - (Booking#***)";
	public static final String CANCELLEDORDERTEMPLATE_TITLEO_117BOOK_CN = "117book要趣订酒店取消确认 - (订单号***)";

	public static final String MAIL_HOST_117BOOK = "smtp.exmail.qq.com";
	public static final Integer MAIL_PORT_117BOOK = 25;
	public static final String MAIL_USERNAME_117BOOK = "noreply@117book.com";
	public static final String MAIL_PASSWORD_117BOOK = "Usi117#book";
	public static final String MAIL_ENCRYPTION_117BOOK = "tls";

}
