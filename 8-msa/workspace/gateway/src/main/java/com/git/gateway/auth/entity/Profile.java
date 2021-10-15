package com.git.gateway.auth.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

	@Id
	private long id;
	private String userId;
	private String username;
	private String email;
	private String role;
	private String img;
	private String sessionId;	
}
