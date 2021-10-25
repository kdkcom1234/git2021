package com.git.myworkspace.photo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoCommentRepository extends JpaRepository<PhotoComment, Long> {

	// select * from photo_comment where photo_id = :photoId
	List<PhotoComment> findByPhotoId(long photoId);
}
