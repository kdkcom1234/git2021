package com.git.myworkspace.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// Spring Data JPA(Java Persistence API, 자바 영속화 API)
// 영속화: 휘발성 데이터 -> 비휘발성 장치
//           자바 객체(RAM) -> 테이블 레코드(파일내부의 특정값)

// ORM(Object Relational Mapping)
// : 객체를 테이블과 맵핑한 것 말함
//  1. 객체 지향으로 개발할 수 있게함(소프트웨어공학)
//  2. 특정 DB에 종속되지 않게함

// @Entity: 테이블과 클래스를 맵핑함
// 기본방법은 Photo(pascal-case) -> photo(snake-case)

@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@Column(columnDefinition = "VARCHAR(1000)")
	private String description;
	
	// 하위 레코드 개수
	@Formula("(SELECT COUNT(1) FROM photo_comment cmt WHERE cmt.photo_id = id)")
	private int commentCnt;	
	
	// BLOB: binary large object
	@Column(columnDefinition = "TEXT")
	private String photoUrl;
	private String fileType;
	private String fileName;
	private long createdTime;
	private String userId;
	

}
