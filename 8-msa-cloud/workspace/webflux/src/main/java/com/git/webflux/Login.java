package com.git.webflux;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {

	@Id
	private long id;
	private String userId;
	private String passwd;
	private String sessionId;
}
