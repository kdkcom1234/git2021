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

import com.git.myworkspace.lib.Session;
import com.git.myworkspace.lib.TextProcesser;

@RestController
public class PhotoController {

	private PhotoRepository repo;
	private PhotoCommentRepository cmtRepo;	

	private PhotoRepositorySupport support;
	
	// Autowired 어노테이션은 매개변수나 필드 타입에 맞는 객체를
	// Spring에서 생성하여 주입하여줌(의존성 주입, 의존객체주입, DI, Dependency Injection)
	// Repository 인터페이스 구조에 맞는 객체를 Spring에 생성하여 넣어줌
	@Autowired
	public PhotoController(PhotoRepository repo, PhotoCommentRepository cmtRepo, PhotoRepositorySupport support) {
		this.repo = repo;
		this.cmtRepo = cmtRepo;
		this.support = support;
	}
	
	@GetMapping(value = "/photos/{id}")
	public Photo getPhoto(@PathVariable long id) {
		return repo.findById(id).orElse(null);
	}
	
	@GetMapping(value = "/photos")
	public List<Photo> getPhotos(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getHeader("session-profile"));
		Session.Profile profile = Session.getSessionProfile(req);
		System.out.println(profile);

		if(profile == null) {
			// 401: 인증 필요
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		
		if(!profile.getRole().equals("USER")) {
			// 403: 권한 불충분
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}		
		
		// **특정 사용자의 데이터만 조회
//		return repo.findAll(Sort.by("id").descending());		
		return repo.findByUserId(Sort.by("id").descending(), profile.getUserId());
	}

	@GetMapping("/photos/search/{keyword}")
	public List<Photo> getPhotosSearchByKeyword(@PathVariable String keyword){
		return support.searchByKeyword(keyword);
//		return repo.findKeyword(keyword);
//		return repo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrFileNameContainingIgnoreCase(keyword, keyword, keyword);
	}
	
	// 예) 한페이지 2개, 1번째 페이지
	// 예) GET /photos/paging?page=0&size=2
	@GetMapping("/photos/paging")
	public Page<Photo> getPhotosPaging(@RequestParam int page, @RequestParam int size, HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getHeader("session-profile"));
		Session.Profile profile = Session.getSessionProfile(req);
		System.out.println(profile);

		if(profile == null) {
			// 401: 인증 필요
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		
		if(!profile.getRole().equals("USER")) {
			// 403: 권한 불충분
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}	

		// **특정 사용자의 데이터만 조회
//		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
		return repo.findByUserId(PageRequest.of(page, size, Sort.by("id").descending()), profile.getUserId());
	}

	@PostMapping(value = "/photos")
	public Photo addPhoto(@RequestBody Photo photo, HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getHeader("session-profile"));
		Session.Profile profile = Session.getSessionProfile(req);
		System.out.println(profile);

		if(profile == null) {
			// 401: 인증 필요
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		
		if(!profile.getRole().equals("USER")) {
			// 403: 권한 불충분
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
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

		// 객체 생성
		Photo photoItem = Photo.builder()
				.title(photo.getTitle())
				.description(TextProcesser.getPlainText(photo.getDescription()))
				.photoUrl(photo.getPhotoUrl())
				.fileType(photo.getFileType())
				.fileName(photo.getFileName())
				.createdTime(new Date().getTime())
				.userId(profile.getUserId())	// 사용자Id 저장
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
		System.out.println(req.getHeader("session-profile"));
		Session.Profile profile = Session.getSessionProfile(req);
		System.out.println(profile);

		if(profile == null) {
			// 401: 인증 필요
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		
		if(!profile.getRole().equals("USER")) {
			// 403: 권한 불충분
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}		
				
		Optional<Photo> photo = repo.findById(id);
//		Optional<Photo> photo = repo.findByIdAndUserId(id, profile.getUserId());

		// 데이터가 없음(id가 잘못됨)
		if (photo.isEmpty()) {
			// 404: 리소스 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		// 데이터는 있으나 해당 사용자의 데이터가 이님
		if(!photo.get().getUserId().equals(profile.getUserId())) {
			// 403: 권한 불충분
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}

		// 삭제 수행
		// delete from photo where id = ?
		repo.deleteById(id);

		return true;
	}

	@PutMapping(value = "/photos/{id}")
	public Photo modifyPhoto(@PathVariable long id, @RequestBody Photo photo, HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getHeader("session-profile"));
		Session.Profile profile = Session.getSessionProfile(req);
		System.out.println(profile);

		if(profile == null) {
			// 401: 인증 필요
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		
		if(!profile.getRole().equals("USER")) {
			// 403: 권한 불충분
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}	
		
		Optional<Photo> photoItem = repo.findById(id);
//		Optional<Photo> photo = repo.findByIdAndUserId(id, profile.getUserId());

		// 데이터가 없음(id가 잘못됨)
		if (photoItem.isEmpty()) {
			// 404: 리소스 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		// 데이터는 있으나 해당 사용자의 데이터가 이님
		if(!photoItem.get().getUserId().equals(profile.getUserId())) {
			// 403: 권한 불충분
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
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

	// 포토 하위에 댓글 추가
	// POST /photos/{photoId}/comments
	// 예) POST /photos/1/comments {"content":"댓글 내용입니다"}
	// id가 1인 photo에 하위 레코드 comment를 추가
	@PostMapping(value="/photos/{photoId}/comments")
	public PhotoComment addPhotoComment(@PathVariable long photoId, @RequestBody PhotoComment comment, 
			HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getHeader("session-profile"));
		Session.Profile profile = Session.getSessionProfile(req);
		System.out.println(profile);

		if(profile == null) {
			// 401: 인증 필요
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		
		if(!profile.getRole().equals("USER")) {
			// 403: 권한 불충분
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}		
		
		// 값 검증 ...
		// content가 빈값인지
		// photoId 해당 데이터가 있는지 확인(FK(외래키) 제약조건으로 처리 가능)
		
		// 상위 테이블의 PK(id)를 넣어줌
		comment.setPhotoId(photoId);
		comment.setCreatedTime(new Date().getTime());
		comment.setUserId(profile.getUserId());	// 사용자 Id 저장
		
		// 저장하고 저장한 객체 리턴
		return cmtRepo.save(comment);
	}
	
	// 포토 하위에 댓글 목록 조회
	// GET /photos/{photoId}/comments
	@GetMapping(value="/photos/{photoId}/comments")
	public List<PhotoComment> getCommments(@PathVariable long photoId) {
		// select * from photo_comment where photo_id = :photoId
		return cmtRepo.findByPhotoId(photoId);
	}
	
}
