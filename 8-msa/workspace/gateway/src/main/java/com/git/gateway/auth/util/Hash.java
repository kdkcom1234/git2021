package com.git.gateway.auth.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Hash {

	public static String getSha512Hex(String str) {
		MessageDigest digest;
		String hex = str;
		try {
			digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			try {
				digest.update(str.getBytes("utf8"));
				hex = String.format("%0128x", new BigInteger(1, digest.digest()));
//				System.out.println(hex);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hex;

	}
	
	public static String getSessionId(String userId) {
		String secretKey= "secret1234!";
		long timeStamp = new Date().getTime();
		
		return getSha512Hex(secretKey + userId + timeStamp);
	}
}
