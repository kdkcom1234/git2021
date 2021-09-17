package com.git.controller.photo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.git.controller.lib.TextProcesser;

@RestController
public class PhotoController {
	
	private SortedMap<Long, Photo> photos = 
			Collections.synchronizedSortedMap(new TreeMap<Long, Photo>(Collections.reverseOrder())) ;
	private AtomicLong maxId = new AtomicLong();
	
	@GetMapping(value = "/photos")
	public List<Photo> getPhotos() {
		return new ArrayList<Photo>(photos.values());
	}
	
	@PostMapping(value = "/photos")
	public Photo addPhoto(@RequestBody Photo photo, HttpServletResponse res) throws InterruptedException {
		
		Thread.sleep(1000);	// �ӽ�
		
		// Ÿ��Ʋ�� ��
		if(TextProcesser.isEmpyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// ����URL�� ��
		if(TextProcesser.isEmpyText(photo.getPhotoUrl())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}			
		
		// id���� ����
		Long currentId = maxId.incrementAndGet();
		
		// ��ü ����
		Photo photoItem = Photo.builder()
									.id(currentId)
									.title(photo.getTitle())
									.description(TextProcesser.getPlainText(photo.getDescription()))
									.photoUrl(photo.getPhotoUrl())
									.fileType(photo.getFileType())
									.fileName(photo.getFileType())
									.createdTime(new Date().getTime())
								.build();
		
		photos.put(currentId, photoItem);
		
		// ���ҽ� ������
		res.setStatus(HttpServletResponse.SC_CREATED);
		
		
		// �߰��� ��ü�� ��ȯ
		return photoItem;
	}
	
	@DeleteMapping(value="/photos/{id}")
	public boolean removePhotos(@PathVariable long id, HttpServletResponse res) {
		
		// id�� �ش��ϴ� ��ü�� ������
		Photo photo = photos.get(Long.valueOf(id));
		if(photo == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		
		// ���� ����
		photos.remove(Long.valueOf(id));
		
		return true;
	}
	
	@PutMapping(value="/photos/{id}")	
	public Photo modifyPhotos(@PathVariable long id, @RequestBody Photo photo, HttpServletResponse res) {

		// id�� �ش��ϴ� ��ü�� ������
		Photo photoItem = photos.get(Long.valueOf(id));
		if(photoItem == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		// Ÿ��Ʋ�� ��
		if(TextProcesser.isEmpyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// ����URL�� ��
		if(TextProcesser.isEmpyText(photo.getPhotoUrl())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		photoItem.setTitle(photo.getTitle());
		photoItem.setDescription(TextProcesser.getPlainText(photo.getDescription()));
		photoItem.setPhotoUrl(photo.getPhotoUrl());
		photoItem.setFileType(photo.getFileType());
		photoItem.setFileName(photo.getFileType());		
		
		return photoItem;
	}
}
