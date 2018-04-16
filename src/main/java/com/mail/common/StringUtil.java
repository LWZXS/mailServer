package com.mail.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class StringUtil {
	public  static String EncoderByMd5(String salt,String pwd) throws NoSuchAlgorithmException,UnsupportedEncodingException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update((salt+"||"+pwd).getBytes("UTF-8"));
		 StringBuffer buf=new StringBuffer(); 
		 for(byte b:md5.digest()){
			 buf.append(String.format("%02x", b&0xff));        
        }
		
		 String md5pwd = buf.toString();
		return md5pwd;
	}

	public  static String Encoder(String salt,String pwd) throws NoSuchAlgorithmException,UnsupportedEncodingException{
		String saltTemp = salt;
		for(int i =0;i<2;i++){
			saltTemp = StringUtil.EncoderByMd5(saltTemp, pwd);
		}
		return saltTemp;
	}
}
