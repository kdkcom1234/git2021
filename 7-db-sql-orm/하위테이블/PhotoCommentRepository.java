package com.git.myworkspace.photo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoCommentRepository extends JpaRepository<PhotoComment, Long> {

	List<PhotoComment> findByPhotoId(long photoId);
}
