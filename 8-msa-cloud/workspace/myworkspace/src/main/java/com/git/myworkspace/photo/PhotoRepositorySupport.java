package com.git.myworkspace.photo;

import static com.git.myworkspace.photo.QPhoto.photo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PhotoRepositorySupport {

	private JPAQueryFactory query;
	
	// EntityManager
	// JPA의 SQL 쿼리를 만들어는 객체
	@Autowired
	public PhotoRepositorySupport(EntityManager em) {
		this.query = new JPAQueryFactory(em);
	}
	
	// 자동완성됨, IDE에서 에러 교정, 컴파일 시점에 에러 교정해움
	// SQL 쿼리 형태로 자연스러움
	// SQL 쿼리를 완전까먹지는 않는다.
	public List<Photo> searchByKeyword(String keyword) {
		return query.selectFrom(photo)
				.where(
				photo.title.containsIgnoreCase(keyword)
				.or(photo.description.containsIgnoreCase(keyword))
				.or(photo.fileName.containsIgnoreCase(keyword)))
			.orderBy(photo.id.desc())
			.fetch();
	}
}
