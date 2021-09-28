package com.git.myworkspace.photo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

	// Autowired ������̼��� �Ű������� �ʵ� Ÿ�Կ� �´� ��ü��
	// Spring���� �����Ͽ� �����Ͽ���(������ ����, ������ü����, DI, Dependency Injection)
	// Repository �������̽� ������ �´� ��ü�� Spring�� �����Ͽ� �־���
	@Autowired
	public PhotoController(PhotoRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/photos")
	public List<Photo> getPhotos() throws InterruptedException {
		// repository.findAll();
		// SELECT * FROM photo;
		// �⺻������ PK ������(asc, ascending)�ǰ� �ִ� ��Ȳ
		// 1 2 3 .....
//		return repo.findAll();

		// id�÷� ������(clusted index)
		// Sort.by("�����÷�").desceding() ������
		// Sort.by("�����÷�").ascending() ������
		return repo.findAll(Sort.by("id").descending());
	}

	// ��) �������� 2��, 1��° ������
	// ��) GET /photos/paging?page=0&size=2
	@GetMapping("/photos/paging")
	public Page<Photo> getPhotosPaging(@RequestParam int page, @RequestParam int size) {
		// findAll(Pageable page)
		// findAll(PageRequest.of(page, size, Sort sort));
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	@PostMapping(value = "/photos")
	public Photo addPhoto(@RequestBody Photo photo, HttpServletResponse res) throws InterruptedException {
		// Ÿ��Ʋ�� ��
		if (TextProcesser.isEmpyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// ����URL�� ��
		if (TextProcesser.isEmpyText(photo.getPhotoUrl())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// ��ü ����
		Photo photoItem = Photo.builder().title(photo.getTitle())
				.description(TextProcesser.getPlainText(photo.getDescription())).photoUrl(photo.getPhotoUrl())
				.fileType(photo.getFileType()).fileName(photo.getFileType()).createdTime(new Date().getTime()).build();

		// repository.save(entity)
		// insert into photo(...) values(...)
		Photo photoSaved = repo.save(photoItem);

		// ���ҽ� ������
		res.setStatus(HttpServletResponse.SC_CREATED);

		// �߰��� ��ü�� ��ȯ
		return photoSaved;
	}

	@DeleteMapping(value = "/photos/{id}")
	public boolean removePhoto(@PathVariable long id, HttpServletResponse res) throws InterruptedException {
//		Thread.sleep(5000);

		// id�� �ش��ϴ� ��ü�� ������
		// Optional null-safe, �ڹ� 1.8 ���� ���
		// repository.findBy(id)
		// select * from photo where id = ?;
		Optional<Photo> photo = repo.findById(id);
		if (photo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// ���� ����
		// repository.deletebyId(id)
		// delete from photo where id = ?
		repo.deleteById(id);

		return true;
	}

	@PutMapping(value = "/photos/{id}")
	public Photo modifyPhoto(@PathVariable long id, @RequestBody Photo photo, HttpServletResponse res)
			throws InterruptedException {

		// id�� �ش��ϴ� ��ü�� ������
		Optional<Photo> photoItem = repo.findById(id);
		if (photoItem.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// Ÿ��Ʋ�� ��
		if (TextProcesser.isEmpyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// ����URL�� ��
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
		// id�� ������ UPDATE, ������ INSERT
		// UPDATE
		// SET title=?, descript=?,......
		// WHERE id = ?
		Photo photoSaved = repo.save(photoToSave);

		return photoSaved;
	}
}
