package com.git.controller.photo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
	private long id;
	private String title;
	private String description;
	private String photoUrl;
	private String fileType;
	private String fileName;
	private long createdTime;	
}
