package com.git.myworkspace.photo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.git.myworkspace.lib.TextProcesser;

@RestController
public class PhotoController {

	private PhotoRepository repo;

	// Autowired 어노테이션은 매개변수나 필드 타입에 맞는 객체를
	// Spring에서 생성하여 주입하여줌(의존성 주입, 의존객체주입, DI, Dependency Injection)
	// Repository 인터페이스 구조에 맞는 객체를 Spring에 생성하여 넣어줌
	@Autowired
	public PhotoController(PhotoRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/photos")
	public List<Photo> getPhotos(HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getHeader("session-profile"));
//		Session.Profile profile = Session.getSessionProfile(req);
//
//		if(profile == null) {
//			// 401: 인증 필요
//			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			return null;
//		}
//
		
		// repository.findAll();
		// SELECT * FROM photo;
		// 기본적으로 PK 순정렬(asc, ascending)되고 있는 상황
		// 1 2 3 .....
//		return repo.findAll();

		// id컬럼 역정렬(clusted index)
		// Sort.by("정렬컬럼").desceding() 역정렬
		// Sort.by("정렬컬럼").ascending() 순정렬
		return repo.findAll(Sort.by("id").descending());
		
		// **특정 사용자의 데이터만 조회
//		return repo.findByUserId(Sort.by("id").descending(), profile.getUserId());
	}

	// 예) 한페이지 2개, 1번째 페이지
	// 예) GET /photos/paging?page=0&size=2
	@GetMapping("/photos/paging")
	public Page<Photo> getPhotosPaging(@RequestParam int page, @RequestParam int size, HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getHeader("session-profile"));
//		Session.Profile profile = Session.getSessionProfile(req);
//
//		if(profile == null) {
//			// 401: 인증 필요
//			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			return null;
//		}
						
		
		// findAll(Pageable page)
		// findAll(PageRequest.of(page, size, Sort sort));

		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
//		return repo.findByUserId(PageRequest.of(page, size, Sort.by("id").descending()), profile.getUserId());
	}

	@GetMapping("/photos/search")
	public List<Photo> getPhotosSearchKeyword(@RequestParam String keyword){
		return repo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
	}
	
	@PostMapping(value = "/photos")
	public Photo addPhoto(@RequestBody Photo photo, HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getHeader("session-profile"));
//		Session.Profile profile = Session.getSessionProfile(req);
//
//		if(profile == null) {
//			// 401: 인증 필요
//			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			return null;
//		}
		
		// 타이틀이 빈값
		if (TextProcesser.isEmpyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 파일URL이 빈값
		if (TextProcesser.isEmpyText(photo.getPhotoUrl())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 객체 생성
		Photo photoItem = Photo.builder()
				.title(photo.getTitle())
				.description(TextProcesser.getPlainText(photo.getDescription()))
				.photoUrl(photo.getPhotoUrl())
				.fileType(photo.getFileType())
				.fileName(photo.getFileName())
				.createdTime(new Date().getTime())
//				.userId(profile.getUserId())
				.build();
				

		// repository.save(entity)
		// insert into photo(...) values(...)
		Photo photoSaved = repo.save(photoItem);

		// 리소스 생성됨
		res.setStatus(HttpServletResponse.SC_CREATED);

		// 추가된 객체를 반환
		return photoSaved;
	}

	@DeleteMapping(value = "/photos/{id}")
	public boolean removePhoto(@PathVariable long id, HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getHeader("session-profile"));
//		Session.Profile profile = Session.getSessionProfile(req);
//
//		if(profile == null) {
//			// 401: 인증 필요
//			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			return false;
//		}
		
		//		Thread.sleep(5000);

		// id에 해당하는 객체가 없으면
		// Optional null-safe, 자바 1.8 나온 방식
		// repository.findBy(id)
		// select * from photo where id = ?;
		Optional<Photo> photo = repo.findById(id);
//		Optional<Photo> photo = repo.findByIdAndUserId(id, profile.getUserId());
		if (photo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// 삭제 수행
		// repository.deletebyId(id)
		// delete from photo where id = ?
		repo.deleteById(id);

		return true;
	}

	@PutMapping(value = "/photos/{id}")
	public Photo modifyPhoto(@PathVariable long id, @RequestBody Photo photo, HttpServletRequest req, HttpServletResponse res) {
//		System.out.println(req.getHeader("session-profile"));
//		Session.Profile profile = Session.getSessionProfile(req);
//
//		if(profile == null) {
//			// 401: 인증 필요
//			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			return null;
//		}
		Optional<Photo> photoItem = repo.findById(id);
//		Optional<Photo> photoItem = repo.findByIdAndUserId(id, profile.getUserId());
		if (photoItem.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 타이틀이 빈값
		if (TextProcesser.isEmpyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 파일URL이 빈값
		if (TextProcesser.isEmpyText(photo.getPhotoUrl())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Photo photoToSave = photoItem.get();

		photoToSave.setTitle(photo.getTitle());
		photoToSave.setDescription(TextProcesser.getPlainText(photo.getDescription()));
		photoToSave.setPhotoUrl(photo.getPhotoUrl());
		photoToSave.setFileType(photo.getFileType());
		photoToSave.setFileName(photo.getFileName());

		// repository.save(entity)
		// id가 있으면 UPDATE, 없으면 INSERT
		// UPDATE
		// SET title=?, descript=?,......
		// WHERE id = ?
		return repo.save(photoToSave);
	}
}
