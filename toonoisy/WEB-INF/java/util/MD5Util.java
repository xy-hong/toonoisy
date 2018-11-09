package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String md5(String password){
		try {
			MessageDigest md=MessageDigest.getInstance("md5");
			byte[] byteArray=md.digest(password.getBytes());
			StringBuffer sb=new StringBuffer();
			for(byte b:byteArray){
				sb.append(numToHex(b));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static String numToHex(byte num){
		int targetNum=0;
		if(num<0){
			targetNum=256+num;
		}
		else{
			targetNum=num;
		}
		int first=targetNum/16;
		int second=targetNum%16;
		return str[first]+str[second];
	}
	public static String[] str={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

}
