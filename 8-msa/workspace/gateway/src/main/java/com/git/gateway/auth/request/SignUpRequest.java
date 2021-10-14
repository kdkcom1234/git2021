package com.git.gateway.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {

	private String userId;
	private String password;
	private String username;
	private String email;
	private String role;
	private String img;
}
