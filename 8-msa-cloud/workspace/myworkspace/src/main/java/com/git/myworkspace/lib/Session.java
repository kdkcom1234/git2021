package com.git.myworkspace.lib;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Session {
	
	public static Profile getSessionProfile(HttpServletRequest req) {		
		ObjectMapper mapper = new ObjectMapper();
		Profile profile = null;
		try {
			String sessionProfile = req.getHeader("session-profile");
			if(sessionProfile == null) {
				return profile;
			}
			profile = mapper.readValue(req.getHeader("session-profile"), Profile.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return profile;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Profile {
		private long id;
		private String userId;
		private String username;
		private String email;
		private String role;
		private String img;
		private String sessionId;			
	}
}
