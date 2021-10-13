package com.git.myworkspace.photo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// photo 테이블에 접근하는 객체

// PhotoRepository -▷ JpaRepository -▷ PagingAndSortingRepository -▷ CrudRepository
// JpaRepository에는 데이터 처리를 위한 기본적인 메서드들이 선언되어있음
// JpaRepository<Photo, Long>
// JpaRepository<엔티티타입, id타입>
// 엔티티(SE, 데이터객체) == 테이블(DB, 데이터객체)

// photo 테이블에 접근할 수 있는 기본적인 메서드들을 사용할 수 있음

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

	// where 컬럼 like '%키워드%';
	// 해당 컬럼에 키워드가 포함되는 레코드를 조회

	// SQL
	// select * from photo where description like '%:description%';

	// JPA
	List<Photo> findByDescriptionContaining(String description);
}
