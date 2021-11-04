package com.git.gateway.auth.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

public class WebFilterCors {
	
	private static final String ALLOWED_HEADERS = "APP_SESSION_ID, Content-Type";
	private static final String ALLOWED_METHODS = "*";
	private static final String ALLOWED_ORIGIN = "*";	
	
	public static void setCorsHeader(ServerHttpRequest req, ServerHttpResponse res) {
		HttpHeaders headers = res.getHeaders();
		
		headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);	
		headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS); 
		headers.add("Access-Control-Allow-Headers",ALLOWED_HEADERS);
	}
}
