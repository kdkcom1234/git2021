package com.git.myworkspace.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PhotoComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition = "VARCHAR(1000)")
	private String content;
	
	
	// 상위테이블의 PK(Id)
//	// photo
//	id	title	photo_url
//	1	펭귄	penguin.jpg
//	2	코알라	koala.jpg
//
//	// photo_comment
//	id 	content		photo_id
//	1	펭귄이 댓글	1
//	2	펭귄이 댓글	1
//	3	코알라 댓글	2
	
	private long photoId;
	
	private String userId;
	private long createdTime;
}
