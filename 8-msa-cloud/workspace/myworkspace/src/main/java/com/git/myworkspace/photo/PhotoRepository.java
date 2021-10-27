package com.git.myworkspace.photo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	// SQL
//	SELECT * FROM photo 
//	WHERE lower(title)  LIKE concat('%',lower('penGuin'), '%') 
//			or lower(description)  LIKE concat('%',lower('penGuin'), '%') 
//			or lower(file_name)  LIKE concat('%',lower('penGuin'), '%');
	
	// 자동완성 안됨, 컴파일시점이아니라 실행시점(스프링시작) 에러 발생
	// 단어가 5개이상 연결된 메서드 명은 쉽게 안 보임.
	// JPA Query creation
	List<Photo> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrFileNameContainingIgnoreCase(String title, String description, String fileName);
    
	// 자동완성 안됨, 컴파일시점이아니라 실행시점(스프링시작) 에러 발생
	// JPQL: Java Persistence Query Language
//	@Query(value="SELECT p FROM photo p WHERE title like  ")
	
	
	// 자동완성 안됨, 컴파일시점이아니라 실행시점(API호출) 에러 발생
	// SQL native query
	@Query(value="SELECT * FROM photo p "
			+ "WHERE lower(p.title)  LIKE concat('%',lower(:keyword), '%') "
			+ "		or lower(p.description)  LIKE concat('%',lower(:keyword), '%') "
			+ "		or lower(p.file_name)  LIKE concat('%',lower(:keyword), '%') ", nativeQuery=true)	
	List<Photo> findByKeyword(@Param("keyword") String keyword);
	
	Optional<Photo> findByIdAndUserId(long Id, String userId);
	
	List<Photo> findByUserId(Sort sort, String userId);
	Page<Photo> findByUserId(Pageable page, String userId);
}
