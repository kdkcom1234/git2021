package com.git.gateway.auth.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

public class WebFilterCors {
	
	private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Content-Length, Authorization, credential, X-XSRF-TOKEN";
	private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, PATCH";
	private static final String ALLOWED_ORIGIN = "*";	
	
	public static void setCorsHeader(ServerHttpRequest req, ServerHttpResponse res) {
		HttpHeaders headers = res.getHeaders();
		
		headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);	
		headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS); 
		headers.add("Access-Control-Allow-Headers",ALLOWED_HEADERS);
	}
}
